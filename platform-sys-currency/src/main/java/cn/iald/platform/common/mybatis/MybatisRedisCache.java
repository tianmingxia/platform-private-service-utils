package cn.iald.platform.common.mybatis;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.ibatis.cache.Cache;
import org.springframework.data.redis.core.RedisCallback;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.util.CollectionUtils;

import java.util.Set;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * @description: MyBatis二级缓存配置
 * @author: wangyc
 * @create: 2020-11-06 20:35
 **/
@Slf4j
public class MybatisRedisCache implements Cache {

	/**
	 * 读写锁
	 */
	private final ReadWriteLock readWriteLock = new ReentrantReadWriteLock();

	/**
	 * 默认redis有效期 ：单位分钟
	 */
	private static final int DEFAULT_REDIS_EXPIRE = 240;

	/**
	 * 注入redis
	 */
	private static RedisTemplate<String, Object> redisTemplate;

	/**
	 * cache id
	 */
	private String id;

	/**
	 * key前缀
	 */
	private static String commonCacheKey;

	/**
	 * 按照一定规则标识key
	 */
	private String getKey(Object key) {
		StringBuilder keyMd5Str = new StringBuilder();
		keyMd5Str.append(commonCacheKey);
		keyMd5Str.append(this.id).append(":");
		keyMd5Str.append(DigestUtils.md5Hex(String.valueOf(key)));
		return keyMd5Str.toString();
	}

	/**
	 * redis key规则前缀
	 */
	private String getKeys() {
		return commonCacheKey + this.id + ":*";
	}

	/**
	 * 构造函数
	 *
	 * @param id
	 */
	public MybatisRedisCache(final String id) {
		if (null == id) {
			throw new IllegalArgumentException("MybatisRedisCache-必须传入 Id");
		}
		log.warn("MybatisRedisCache:id= " + id);
		this.id = id;
	}

	@Override
	public String getId() {
		return this.id;
	}

	@Override
	public int getSize() {
		Long size = redisTemplate.execute((RedisCallback<Long>) connection -> connection.dbSize());
		log.warn("MybatisRedisCache-总缓存数:: " + size.intValue());
		return size.intValue();
	}

	@Override
	public void putObject(Object key, Object value) {
		if (null != value) {
			String redisKey = getKey(key);
			log.warn("MybatisRedisCache-[putObject]key: " + redisKey);
			// 向Redis中添加数据
			redisTemplate.opsForValue().set(redisKey, value, DEFAULT_REDIS_EXPIRE, TimeUnit.MINUTES);
		}
	}

	@Override
	public Object getObject(Object key) {
		if (null != key) {
			String redisKey = getKey(key);
			log.warn("MybatisRedisCache-[getObject]key: " + redisKey);
			return redisTemplate.opsForValue().get(redisKey);
		}
		return null;
	}

	@Override
	public Object removeObject(Object key) {
		if (null != key) {
			String redisKey = getKey(key);
			log.warn("MybatisRedisCache-[removeObject]key: " + redisKey);
			redisTemplate.delete(redisKey);
			log.warn("LRU算法从缓存中移除-----" + this.id);
		}
		return null;
	}

	@Override
	public void clear() {
		log.warn("MybatisRedisCache-[clear]...");
		try {
			Set<String> keys = redisTemplate.keys(getKeys());
			log.warn("出现CUD操作，清空对应Mapper缓存======>" + keys.size());
			for (String key : keys) {
				log.warn("key : " + key);
			}
			if (!CollectionUtils.isEmpty(keys)) {
				redisTemplate.delete(keys);
			}
		} catch (Exception e) {
			log.warn("MybatisRedisCache-[clear] failed!", e);
		}
	}

	@Override
	public ReadWriteLock getReadWriteLock() {
		return this.readWriteLock;
	}

	/**
	 * 注入redis
	 *
	 * @param redisTemplate
	 */
	public static void setRedisTemplate(RedisTemplate<String, Object> redisTemplate) {
		MybatisRedisCache.redisTemplate = redisTemplate;
	}

	/**
	 * 注入工程前缀
	 *
	 * @param commonCacheKey
	 */
	public static void setCommonCacheKey(String commonCacheKey) {
		MybatisRedisCache.commonCacheKey = commonCacheKey + ":";
	}
}
