package cn.iald.platform.utils;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.json.JSONUtil;
import cn.iald.platform.base.vo.ResponseVO;
import cn.iald.platform.base.vo.ResultVO;
import cn.iald.platform.constant.ResponseConstant;
import cn.iald.platform.enums.SysCodeEnum;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 返回请求工具类
 *
 * @author wangyc
 */
public class ResponseUtil {

    /**
     * 请求成功，正常返回数据
     *
     * @param data 要返回的数据
     * @return
     */
    public static ResponseVO success(Object data) {
        if (data == null) {
            data = "";
        }
        return new ResponseVO(SysCodeEnum.SUCCESS.getCode(), ResponseConstant.SUCCESS, data, "", "");
    }

    /**
     * 请求成功，APP正常返回数据
     *
     * @param data 要返回的数据，对应key[obj]
     * @return
     */
    public static ResponseVO successApp(Object data) {
        if (data == null) {
            data = "";
        }
        Map<String, Object> resMap = new HashMap<>(1);
        resMap.put(ResponseConstant.APP_VAL_KEY, data);
        return new ResponseVO(SysCodeEnum.SUCCESS.getCode(), ResponseConstant.SUCCESS, resMap, "", "");
    }

    /**
     * 请求成功，返回list
     *
     * @param list 要返回的list
     * @return list大小及list数据
     */
    public static ResponseVO successList(List list) {
        if (CollUtil.isNotEmpty(list)) {
            return new ResponseVO(SysCodeEnum.SUCCESS.getCode(), ResponseConstant.SUCCESS, new ResultVO(list.size(), list), "", "");
        }
        return new ResponseVO(SysCodeEnum.SUCCESS.getCode(), ResponseConstant.SUCCESS, new ResultVO(0, list), "", "");
    }

    /**
     * 请求成功，返回list
     *
     * @param count 自定义大小
     * @param list  要返回的list大小
     * @return
     */
    public static ResponseVO successList(Long count, Object list) {
        if (count == null || count == 0 || list == null) {
            return new ResponseVO(SysCodeEnum.SUCCESS.getCode(), ResponseConstant.SUCCESS, new ResultVO(0, list), "", "");
        }
        return new ResponseVO(SysCodeEnum.SUCCESS.getCode(), ResponseConstant.SUCCESS, new ResultVO(count.intValue(), list), "", "");
    }

    /**
     * 异常返回数据
     *
     * @param errorMsg 错误提示语
     * @return
     */
    public static ResponseVO error(String errorMsg) {
        if (StrUtil.isBlank(errorMsg)) {
            errorMsg = "";
        }
        return new ResponseVO(SysCodeEnum.ERROR.getCode(), ResponseConstant.ERROR, "", errorMsg, "");
    }

    /**
     * 异常返回数据
     *
     * @param errorMsg     错误提示语
     * @param exceptionMsg 异常信息
     * @return
     */
    public static ResponseVO error(String errorMsg, String exceptionMsg) {
        if (StrUtil.isBlank(errorMsg)) {
            errorMsg = "";
        }
        return new ResponseVO(SysCodeEnum.ERROR.getCode(), ResponseConstant.ERROR, "", errorMsg, exceptionMsg);
    }

    /**
     * 异常返回数据
     *
     * @param code         对应异常码
     * @param errorMsg     错误提示语
     * @param exceptionMsg 异常信息
     * @return
     */
    public static ResponseVO error(String code, String errorMsg, String exceptionMsg) {
        if (StrUtil.isBlank(errorMsg)) {
            errorMsg = "";
        }
        return new ResponseVO(code, ResponseConstant.ERROR, "", errorMsg, exceptionMsg);
    }

    /**
     * 异常返回数据
     *
     * @param code         对应异常码
     * @param errorMsg     错误提示语
     * @param exceptionMsg 异常信息
     * @param data         要返回的数据
     * @return
     */
    public static ResponseVO error(String code, String errorMsg, String exceptionMsg, Object data) {
        if (StrUtil.isBlank(errorMsg)) {
            errorMsg = "";
        }
        return new ResponseVO(code, ResponseConstant.ERROR, data, errorMsg, exceptionMsg);
    }

    /**
     * 异常返回数据列表
     *
     * @param code         对应异常码
     * @param errorMsg     错误提示语
     * @param exceptionMsg 异常信息
     * @param list         要返回的数据
     * @return
     */
    public static ResponseVO errorList(String code, String errorMsg, String exceptionMsg, List list) {
        ResultVO resultVo = new ResultVO(0, list);
        if (CollUtil.isNotEmpty(list)) {
            resultVo.setCount(list.size());
        }
        if (StrUtil.isBlank(errorMsg)) {
            errorMsg = "";
        }
        return new ResponseVO(code, ResponseConstant.ERROR, resultVo, errorMsg, exceptionMsg);
    }

    /**
     * 异常返回数据列表
     *
     * @param code         对应异常码
     * @param errorMsg     错误提示语
     * @param exceptionMsg 异常信息
     * @param count        要返回的数据总数
     * @param list         要返回的数据
     * @return
     */
    public static ResponseVO errorList(String code, String errorMsg, String exceptionMsg, Long count, List list) {
        ResultVO resultVo = new ResultVO(0, list);
        if (count != null && CollUtil.isNotEmpty(list)) {
            resultVo.setCount(count.intValue());
        }
        return new ResponseVO(code, ResponseConstant.ERROR, resultVo, errorMsg, exceptionMsg);
    }


    /**
     * 请求响应成功，返回值转实体类
     *
     * @param responseVo 返回值
     * @param beanClass  要转换的实体类
     * @param <T>        返回类型
     * @return
     */
    public static <T> T toBean(ResponseVO responseVo, Class<T> beanClass) {
        if (SysCodeEnum.SUCCESS.getCode().equals(responseVo.getCode())) {
            Object data = responseVo.getData();
            if (ObjUtils.isJson(data)) {
                return JSONUtil.toBean(JSONUtil.toJsonStr(data), beanClass);
            }
        }
        return null;
    }

    /**
     * 请求响应成功，返回值转列表
     *
     * @param resultVo  分页后列表对象
     * @param beanClass 要转换的列表对应实体类
     * @param <T>       返回类型
     * @return
     */
    public static <T> List<T> toList(ResultVO resultVo, Class<T> beanClass) {
        Object data = resultVo.getList();
        if (ObjUtils.isJsonArray(data)) {
            return JSONUtil.toList(JSONUtil.parseArray(data), beanClass);
        }
        return null;
    }
}
