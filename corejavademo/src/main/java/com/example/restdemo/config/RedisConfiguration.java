package com.example.restdemo.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.script.DefaultRedisScript;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import redis.clients.jedis.JedisPoolConfig;

@Configuration
public class RedisConfiguration {

	private static Logger logger = LoggerFactory.getLogger(RedisConfiguration.class);

	@Value("${spring.redis.host}")
	private String host;
	@Value("${spring.redis.password}")
	private String password;
	@Value("${spring.redis.port}")
	private int port;
	@Value("${spring.redis.maxWaitMillis}")
	private int maxWaitMillis;
	@Value("${spring.redis.maxTotal}")
	private int maxTotal;
	@Value("${spring.redis.testOnBorrow}")
	private boolean testOnBorrow;
	@Value("${spring.redis.testOnReturn}")
	private boolean testOnReturn;
	@Value("${spring.redis.timeout}")
	private int timeout;
	// @Value("${spring.redis.database}")
	// private int database;
	@Value("${spring.redis.maxIdle}")
	private int maxIdle;
	// @Value("${spring.redis.pool.min-idle}")
	// private int minIdle;

	/**
	 * 连接池配置
	 * 
	 * @Description:
	 * @return
	 */
	@Bean
	public JedisPoolConfig jedisPoolConfig() {
		JedisPoolConfig jedisPoolConfig = new JedisPoolConfig();
		jedisPoolConfig.setMaxIdle(maxIdle);
		// jedisPoolConfig.setMinIdle(minIdle);
		jedisPoolConfig.setMaxWaitMillis(maxWaitMillis);
		jedisPoolConfig.setMaxTotal(maxTotal);
		jedisPoolConfig.setTestOnBorrow(testOnBorrow);
		jedisPoolConfig.setTestOnReturn(testOnReturn);
		return jedisPoolConfig;
	}
	/**
	 * redis连接的基础设置
	 * 
	 * @Description:
	 * @return
	 */
	@Primary
	@Bean(name = "redisConnectionFactory")
	public RedisConnectionFactory redisConnectionFactory() {
		JedisConnectionFactory factory = new JedisConnectionFactory();
		factory.setHostName(host);
		factory.setPort(port);
		factory.setPassword(password);
		// 存储的库
		factory.setDatabase(3);
		// 设置连接超时时间
		factory.setTimeout(timeout);
		factory.setUsePool(true);
		factory.setPoolConfig(jedisPoolConfig());
		logger.info("redis factory3 inited finish...host:" + host + ",port:" + port);
		return factory;
	}

	@Primary
	@Bean(name = "redisTemplate")
	public RedisTemplate<String, Object> redisTemplate(RedisConnectionFactory factory) {
		RedisTemplate<String, Object> redisTemplate = new RedisTemplate<String, Object>();
		redisTemplate.setConnectionFactory(redisConnectionFactory());
		setRedisTemplateSerializer(redisTemplate);
		logger.info("redis restTemplate3 inited finish...");
		return redisTemplate;
	}


	/////////////////////////////////// database 1
	/**
	 * redis连接的基础设置1
	 * 
	 * @Description:
	 * @return
	 */
	@Bean(name = "redisConnectionFactory1")
	public RedisConnectionFactory redisConnectionFactory1() {
		JedisConnectionFactory factory = new JedisConnectionFactory();
		factory.setHostName(host);
		factory.setPort(port);
		factory.setPassword(password);
		// 存储的库
		factory.setDatabase(1);
		// 设置连接超时时间
		factory.setTimeout(timeout);
		factory.setUsePool(true);
		factory.setPoolConfig(jedisPoolConfig());
		logger.info("redis factory1 inited finish...host:" + host + ",port:" + port);
		return factory;
	}

	@Bean(name = "redisTemplate1")
	public RedisTemplate<String, Object> redisTemplate1(RedisConnectionFactory factory) {
		RedisTemplate<String, Object> redisTemplate = new RedisTemplate<String, Object>();
		redisTemplate.setConnectionFactory(redisConnectionFactory1());
		setRedisTemplateSerializer(redisTemplate);
		logger.info("redis restTemplate1 inited finish...");
		return redisTemplate;
	}


	/////////////////////////////////// database 0
	/**
	 * redis连接的基础设置0
	 * 
	 * @Description:
	 * @return
	 */
	@Bean(name = "redisConnectionFactory0")
	public RedisConnectionFactory redisConnectionFactory0() {
		JedisConnectionFactory factory = new JedisConnectionFactory();
		factory.setHostName(host);
		factory.setPort(port);
		factory.setPassword(password);
		// 存储的库
		factory.setDatabase(0);
		// 设置连接超时时间
		factory.setTimeout(timeout);
		factory.setUsePool(true);
		factory.setPoolConfig(jedisPoolConfig());
		logger.info("redis factory0 inited finish...host:" + host + ",port:" + port);
		return factory;
	}

	@Bean(name = "redisTemplate0")
	public RedisTemplate<String, Object> redisTemplate0(RedisConnectionFactory factory) {
		RedisTemplate<String, Object> redisTemplate = new RedisTemplate<String, Object>();
		redisTemplate.setConnectionFactory(redisConnectionFactory0());
		setRedisTemplateSerializer(redisTemplate);
		logger.info("redis restTemplate0 inited finish...");
		return redisTemplate;
	}

	/////////////////////////////////// database 8
	/**
	 * redis连接的基础设置8
	 * 
	 * @Description:
	 * @return
	 */
	@Bean(name = "redisConnectionFactory8")
	public RedisConnectionFactory redisConnectionFactory8() {
		JedisConnectionFactory factory = new JedisConnectionFactory();
		factory.setHostName(host);
		factory.setPort(port);
		factory.setPassword(password);
		// 存储的库
		factory.setDatabase(8);
		// 设置连接超时时间
		factory.setTimeout(timeout);
		factory.setUsePool(true);
		factory.setPoolConfig(jedisPoolConfig());
		logger.info("redis factory8 inited finish...host:" + host + ",port:" + port);
		return factory;
	}

	@Bean(name = "redisTemplate8")
	public RedisTemplate<String, Object> redisTemplate8(RedisConnectionFactory factory) {
		RedisTemplate<String, Object> redisTemplate = new RedisTemplate<String, Object>();
		redisTemplate.setConnectionFactory(redisConnectionFactory8());
		setRedisTemplateSerializer(redisTemplate);
		logger.info("redis restTemplate8 inited finish...");
		return redisTemplate;
	}
	
	/**
	 * 设置序列化方式
	 */
	private void setRedisTemplateSerializer(RedisTemplate<String, Object> redisTemplate) {
		StringRedisSerializer redisSerializer = new StringRedisSerializer();
		// 设置Key的序列化方式
		redisTemplate.setKeySerializer(redisSerializer);
		redisTemplate.setHashKeySerializer(redisSerializer);
		// JdkSerializationRedisSerializer序列化方式;
		// JdkSerializationRedisSerializer jdkRedisSerializer=new
		// JdkSerializationRedisSerializer();
		// redisTemplate.setValueSerializer(jdkRedisSerializer);
		// redisTemplate.setHashValueSerializer(jdkRedisSerializer);
		// 设置value的序列化方式
		redisTemplate.setValueSerializer(redisSerializer);
		redisTemplate.setHashValueSerializer(redisSerializer);
		redisTemplate.afterPropertiesSet();
	}
	@Bean
	public DefaultRedisScript<Long> defaultRedisScript() {
		DefaultRedisScript<Long> defaultRedisScript = new DefaultRedisScript<>();
		defaultRedisScript.setResultType(Long.class);
		defaultRedisScript.setScriptText("if redis.call('get', KEYS[1]) == KEYS[2] then return redis.call('del', KEYS[1]) else return 0 end");
//        defaultRedisScript.setScriptSource(new ResourceScriptSource(new ClassPathResource("delete.lua")));
		return defaultRedisScript;
	}
}