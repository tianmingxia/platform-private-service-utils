package cn.iald.platform.mybatis;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

/**
 * MyBatisHelper注入redisTemplate
 *
 * @author wangyc
 * @date 2021/06/03 17:31:04
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
