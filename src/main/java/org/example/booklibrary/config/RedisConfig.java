package org.example.booklibrary.config;
import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.example.booklibrary.service.Impl.CacheServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.GenericToStringSerializer;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.JdkSerializationRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import redis.clients.jedis.Jedis;

@Configuration
public class RedisConfig {
  //    @Bean
  //    JedisConnectionFactory jedisConnectionFactory() {
  //        JedisConnectionFactory jedisConFactory = new JedisConnectionFactory();
  //        jedisConFactory.setHostName("localhost");
  //        jedisConFactory.setPort(6379);
  //        return jedisConFactory;
  //    }
  //    @Bean
  //    public RedisTemplate<String, Object> redisTemplate() {
  //        RedisTemplate<String, Object> template = new RedisTemplate<>();
  //        template.setConnectionFactory(jedisConnectionFactory());
  //        return template;
  //    }
  private static final Logger LOGGER = LoggerFactory.getLogger(RedisConfig.class);
  @Value("${booklib.redis.url}")
  private String redisURI;

  @Bean
  Jedis jedisConnectionFactory() {
    Jedis jedis = new Jedis(redisURI);
    LOGGER.info("Redis is connected: {}", jedis.isConnected());
    return jedis;
  }
}