package cn.iald.platform.common.mybatis;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

/**
 * @description: MyBatisHelper注入redisTemplate
 * @author: wangyc
 * @create: 2020-11-06 21:06
 **/
@Component
public class MyBatisHelper {

	@Value("${spring.application.name:}")
	private String commonCacheKey;

	@Autowired
	@Qualifier("redisTemplate")
	public void setRedisTemplate(RedisTemplate<String, Object> redisTemplate) {
		MybatisRedisCache.setRedisTemplate(redisTemplate);
		MybatisRedisCache.setCommonCacheKey(commonCacheKey);
	}

}
