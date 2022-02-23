package cn.iald.platform.config;

import cn.hutool.core.util.StrUtil;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.annotation.Configuration;
import org.springframework.util.ReflectionUtils;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * 通过spring后置器处理FeignClientFactoryBean的name
 * 根据配置文件进行name的动态替换
 *
 * @author wangyc
 * @date 2021/09/17 14:28:46
 **/
@Slf4j
@Configuration
public class FeignClientServiceNameConfig implements BeanPostProcessor, ApplicationContextAware {

    private ApplicationContext applicationContext;

    private final AtomicInteger atomicInteger = new AtomicInteger();

    private static final String FIELD_NAME = "name";

    /**
     * token白名单
     */
    @Value("${feign-name-list:}")
    private String nameListStr;

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }

    @SneakyThrows
    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        if (StrUtil.isEmpty(nameListStr)) {
            return null;
        }
        String[] nameListArr = nameListStr.split(",");
        int len = nameListArr.length;
        Map<String, String> nameMap = new HashMap<>(len);
        for (int i = 0; i < len; i++) {
            String[] nameArr = nameListArr[i].split("&");
            if (nameArr.length == 2) {
                nameMap.put(nameArr[0], nameArr[1]);
            }
        }
        if (atomicInteger.getAndIncrement() == 0) {
            String beanNameOfFeignClientFactoryBean = "org.springframework.cloud.openfeign.FeignClientFactoryBean";
            Class beanNameClz = Class.forName(beanNameOfFeignClientFactoryBean);
            applicationContext.getBeansOfType(beanNameClz).forEach((feignBeanName, beanOfFeignClientFactoryBean) -> {
                try {
                    Field field = ReflectionUtils.findField(beanNameClz, FIELD_NAME);
                    if (Objects.nonNull(field)) {
                        ReflectionUtils.makeAccessible(field);
                        Object value = field.get(beanOfFeignClientFactoryBean);
                        if (Objects.nonNull(value)) {
                            log.info("feignClient:{}", value);
                            if (nameMap.containsKey(value)) {
                                value = nameMap.get(value);
                                log.info("feignClient-replace:{}", value);
                            }
                            ReflectionUtils.setField(field, beanOfFeignClientFactoryBean, value);
                        }
                    }
                } catch (Exception e) {
                    log.error(e.getMessage(), e);
                }
            });
        }
        return null;
    }
}
