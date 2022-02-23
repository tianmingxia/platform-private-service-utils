package cn.iald.platform.component;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.http.ContentType;
import cn.hutool.http.HttpRequest;
import cn.hutool.http.HttpResponse;
import cn.hutool.http.HttpStatus;
import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import cn.iald.platform.constant.XxlJobConstant;
import cn.iald.platform.enums.SysCodeEnum;
import cn.iald.platform.exception.BusinessException;
import cn.iald.platform.pojo.dto.XxlJobInfoDTO;
import cn.iald.platform.pojo.dto.XxlJobTriggerDTO;
import cn.iald.platform.pojo.vo.XxlJobInfoVO;
import cn.iald.platform.util.MessageUtil;
import com.xxl.job.core.biz.model.ReturnT;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.concurrent.TimeUnit;

/**
 * XxxJobComponent
 *
 * @author wangyc
 * @date 2021/07/21 14:07:58
 **/
@Slf4j
@Component
public class XxlJobComponent {

    @Resource
    private HttpServletRequest request;

    @Value("${xxl.job.admin.addresses:}")
    private String adminAddresses;

    @Value("${xxl.job.admin.userName:}")
    private String userName;

    @Value("${xxl.job.admin.password:}")
    private String password;

    @Value("${xxl.job.timeout:}")
    private String timeout;

    @Value("${xxl.job.cookieHour:}")
    private String cookieHour;

    @Resource
    private StringRedisTemplate stringRedisTemplate;

    /**
     * cookie缓存
     */
    private static final String XXL_JOB_LOGIN_COOKIE = "xxl_job_login_cookie";

    /**
     * 登录获取cookie
     *
     * @return
     */
    public String login() {
        String cookieVal;
        if (stringRedisTemplate.hasKey(XXL_JOB_LOGIN_COOKIE)) {
            cookieVal = stringRedisTemplate.opsForValue().get(XXL_JOB_LOGIN_COOKIE);
            if (StrUtil.isNotBlank(cookieVal)) {
                return cookieVal;
            }
        }
        String url = StrUtil.format(XxlJobConstant.JOB_ADMIN_LOGIN, adminAddresses, userName, password);
        HttpResponse httpResponse = HttpRequest.post(url).timeout(Integer.valueOf(timeout))
                .contentType(String.valueOf(ContentType.FORM_URLENCODED))
                .execute();
        if (httpResponse != null && httpResponse.getStatus() == HttpStatus.HTTP_OK) {
            String body = httpResponse.body();
            if (StrUtil.isBlank(body)) {
                throw new BusinessException(MessageUtil.getMessage("job.login.body.none", request));
            }
            ReturnT<String> res = JSONUtil.toBean(body, ReturnT.class);
            if (XxlJobConstant.SUCCESS_CODE != res.getCode()) {
                throw new BusinessException(SysCodeEnum.ERROR.getCode()
                        , StrUtil.format(MessageUtil.getMessage("job.login.code.error", request), res.getCode())
                        , body);
            }
            String cookieValue = httpResponse.getCookieValue(XxlJobConstant.XXL_JOB_LOGIN_IDENTITY);
            if (StrUtil.isNotBlank(cookieValue)) {
                cookieVal = StrUtil.format("{}={}", XxlJobConstant.XXL_JOB_LOGIN_IDENTITY, cookieValue);
                stringRedisTemplate.opsForValue().set(XXL_JOB_LOGIN_COOKIE, cookieVal, Integer.valueOf(cookieHour), TimeUnit.HOURS);
                return cookieVal;
            }
        }
        log.error("登录接口返回值：{}", httpResponse);
        throw new BusinessException(MessageUtil.getMessage("job.login.error", request));
    }

    /**
     * 添加调度任务
     *
     * @param xxlJobInfoDTO
     * @return 调度任务id
     */
    public Integer add(XxlJobInfoDTO xxlJobInfoDTO) {
        HttpResponse httpResponse = HttpRequest.post(StrUtil.format(XxlJobConstant.JOB_INFO_ADD, adminAddresses))
                .form(BeanUtil.beanToMap(xxlJobInfoDTO)).timeout(Integer.valueOf(timeout)).cookie(this.login())
                .contentType(String.valueOf(ContentType.FORM_URLENCODED))
                .execute();
        if (httpResponse != null && httpResponse.getStatus() == HttpStatus.HTTP_OK) {
            String body = httpResponse.body();
            if (StrUtil.isBlank(body)) {
                throw new BusinessException(MessageUtil.getMessage("job.add.body.none", request));
            }
            ReturnT<String> res = JSONUtil.toBean(body, ReturnT.class);
            if (XxlJobConstant.SUCCESS_CODE != res.getCode()) {
                throw new BusinessException(SysCodeEnum.ERROR.getCode()
                        , StrUtil.format(MessageUtil.getMessage("job.add.code.error", request), res.getCode())
                        , body);
            }
            String content = res.getContent();
            if (StrUtil.isBlank(content)) {
                throw new BusinessException(MessageUtil.getMessage("job.add.id.none", request));
            }
            return Integer.valueOf(content);
        }
        log.error("新增调度任务接口返回值：{}", httpResponse);
        throw new BusinessException(MessageUtil.getMessage("job.add.error", request));
    }

    /**
     * 根据id获取调度任务
     *
     * @param id
     * @return
     */
    public XxlJobInfoVO getJobInfo(Integer id) {
        HttpResponse httpResponse = HttpRequest.post(StrUtil.format(XxlJobConstant.JOB_INFO_GET, adminAddresses, id))
                .timeout(Integer.valueOf(timeout)).cookie(this.login())
                .contentType(String.valueOf(ContentType.FORM_URLENCODED))
                .execute();
        if (httpResponse != null && httpResponse.getStatus() == HttpStatus.HTTP_OK) {
            String body = httpResponse.body();
            if (StrUtil.isBlank(body)) {
                throw new BusinessException(MessageUtil.getMessage("job.get.body.none", request));
            }
            ReturnT<JSONObject> res = JSONUtil.toBean(body, ReturnT.class);
            if (XxlJobConstant.SUCCESS_CODE != res.getCode()) {
                throw new BusinessException(SysCodeEnum.ERROR.getCode()
                        , StrUtil.format(MessageUtil.getMessage("job.get.code.error", request), res.getCode())
                        , body);
            }
            JSONObject jsonObject = res.getContent();
            if (JSONUtil.isNull(jsonObject)) {
                throw new BusinessException(MessageUtil.getMessage("job.get.res.none", request));
            }
            return JSONUtil.toBean(jsonObject, XxlJobInfoVO.class);
        }
        log.error("获取调度任务接口返回值：{}", httpResponse);
        throw new BusinessException(MessageUtil.getMessage("job.get.error", request));
    }

    /**
     * 更新调度任务
     *
     * @param xxlJobInfoDTO
     */
    public void update(XxlJobInfoDTO xxlJobInfoDTO) {
        HttpResponse httpResponse = HttpRequest.post(StrUtil.format(XxlJobConstant.JOB_INFO_UPDATE, adminAddresses))
                .form(BeanUtil.beanToMap(xxlJobInfoDTO)).timeout(Integer.valueOf(timeout)).cookie(this.login())
                .contentType(String.valueOf(ContentType.FORM_URLENCODED))
                .execute();
        if (httpResponse != null && httpResponse.getStatus() == HttpStatus.HTTP_OK) {
            String body = httpResponse.body();
            if (StrUtil.isBlank(body)) {
                throw new BusinessException(MessageUtil.getMessage("job.update.body.none", request));
            }
            ReturnT<String> res = JSONUtil.toBean(body, ReturnT.class);
            if (XxlJobConstant.SUCCESS_CODE == res.getCode()) {
                return;
            }
            throw new BusinessException(SysCodeEnum.ERROR.getCode()
                    , StrUtil.format(MessageUtil.getMessage("job.update.code.error", request), res.getCode())
                    , body);
        }
        log.error("更新调度任务接口返回值：{}", httpResponse);
        throw new BusinessException(MessageUtil.getMessage("job.update.error", request));
    }

    /**
     * 启动任务
     *
     * @param id
     */
    public void start(Integer id) {
        HttpResponse httpResponse = HttpRequest.post(StrUtil.format(XxlJobConstant.JOB_INFO_START, adminAddresses, id))
                .timeout(Integer.valueOf(timeout)).cookie(this.login())
                .contentType(String.valueOf(ContentType.FORM_URLENCODED))
                .execute();
        if (httpResponse != null && httpResponse.getStatus() == HttpStatus.HTTP_OK) {
            String body = httpResponse.body();
            if (StrUtil.isBlank(body)) {
                throw new BusinessException(MessageUtil.getMessage("job.start.body.none", request));
            }
            ReturnT<String> res = JSONUtil.toBean(body, ReturnT.class);
            if (XxlJobConstant.SUCCESS_CODE == res.getCode()) {
                return;
            }
            throw new BusinessException(SysCodeEnum.ERROR.getCode()
                    , StrUtil.format(MessageUtil.getMessage("job.start.code.error", request), res.getCode())
                    , body);
        }
        log.error("启动调度任务接口返回值：{}", httpResponse);
        throw new BusinessException(MessageUtil.getMessage("job.start.error", request));
    }

    /**
     * 停止任务
     *
     * @param id
     */
    public void stop(Integer id) {
        HttpResponse httpResponse = HttpRequest.post(StrUtil.format(XxlJobConstant.JOB_INFO_STOP, adminAddresses, id))
                .timeout(Integer.valueOf(timeout)).cookie(this.login())
                .contentType(String.valueOf(ContentType.FORM_URLENCODED))
                .execute();
        if (httpResponse != null && httpResponse.getStatus() == HttpStatus.HTTP_OK) {
            String body = httpResponse.body();
            if (StrUtil.isBlank(body)) {
                throw new BusinessException(MessageUtil.getMessage("job.stop.body.none", request));
            }
            ReturnT<String> res = JSONUtil.toBean(body, ReturnT.class);
            if (XxlJobConstant.SUCCESS_CODE == res.getCode()) {
                return;
            }
            throw new BusinessException(SysCodeEnum.ERROR.getCode()
                    , StrUtil.format(MessageUtil.getMessage("job.stop.code.error", request), res.getCode())
                    , body);
        }
        log.error("停止调度任务接口返回值：{}", httpResponse);
        throw new BusinessException(MessageUtil.getMessage("job.stop.error", request));
    }

    /**
     * 删除任务
     *
     * @param id
     */
    public void delete(Integer id) {
        HttpResponse httpResponse = HttpRequest.post(StrUtil.format(XxlJobConstant.JOB_INFO_DELETE, adminAddresses, id))
                .timeout(Integer.valueOf(timeout)).cookie(this.login())
                .contentType(String.valueOf(ContentType.FORM_URLENCODED))
                .execute();
        if (httpResponse != null && httpResponse.getStatus() == HttpStatus.HTTP_OK) {
            String body = httpResponse.body();
            if (StrUtil.isBlank(body)) {
                throw new BusinessException(MessageUtil.getMessage("job.delete.body.none", request));
            }
            ReturnT<String> res = JSONUtil.toBean(body, ReturnT.class);
            if (XxlJobConstant.SUCCESS_CODE == res.getCode()) {
                return;
            }
            throw new BusinessException(SysCodeEnum.ERROR.getCode()
                    , StrUtil.format(MessageUtil.getMessage("job.delete.code.error", request), res.getCode())
                    , body);
        }
        log.error("删除调度任务接口返回值：{}", httpResponse);
        throw new BusinessException(MessageUtil.getMessage("job.delete.error", request));
    }

    /**
     * 执行一次任务
     *
     * @param xxlJobTriggerDTO
     */
    public void trigger(XxlJobTriggerDTO xxlJobTriggerDTO) {
        HttpResponse httpResponse = HttpRequest.post(StrUtil.format(XxlJobConstant.JOB_INFO_TRIGGER, adminAddresses))
                .form(BeanUtil.beanToMap(xxlJobTriggerDTO)).timeout(Integer.valueOf(timeout)).cookie(this.login())
                .contentType(String.valueOf(ContentType.FORM_URLENCODED))
                .execute();
        if (httpResponse != null && httpResponse.getStatus() == HttpStatus.HTTP_OK) {
            String body = httpResponse.body();
            if (StrUtil.isBlank(body)) {
                throw new BusinessException(MessageUtil.getMessage("job.trigger.body.none", request));
            }
            ReturnT<String> res = JSONUtil.toBean(body, ReturnT.class);
            if (XxlJobConstant.SUCCESS_CODE == res.getCode()) {
                return;
            }
            throw new BusinessException(SysCodeEnum.ERROR.getCode()
                    , StrUtil.format(MessageUtil.getMessage("job.trigger.code.error", request), res.getCode())
                    , body);
        }
        log.error("执行一次调度任务接口返回值：{}", httpResponse);
        throw new BusinessException(MessageUtil.getMessage("job.trigger.error", request));
    }
}
