package org.example.booklibrary.service.Impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.example.booklibrary.constants.Cache;
import org.example.booklibrary.service.CacheService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import redis.clients.jedis.Jedis;

@Service
@RequiredArgsConstructor
public class CacheServiceImpl implements CacheService {
	private static final Logger LOGGER = LoggerFactory.getLogger(CacheServiceImpl.class);

    @Autowired
    private Jedis jedisConnectionFactory;

    private final ObjectMapper objectMapper;

		@Override
		public String getByKey(String key) {
        return jedisConnectionFactory.get(key);
    }
		@Override
		public String writeCache(String key, Object result) {
        try {
            String json = objectMapper.writeValueAsString(result);
            jedisConnectionFactory.set(key, json);
        } catch (Exception e) {
            return Cache.ERROR;
        }
        return Cache.DONE;
    }
		@Override
		public String destroyCache(String key) {
        try {
            jedisConnectionFactory.del(key);
        } catch (Exception e) {
            return Cache.ERROR;
        }
        return Cache.DONE;
    }
		@Override
		public String writeCacheAtTime(String key, Object result, long time, int timeUnit) {
        if (timeUnit == Cache.TimeUnit_SECONDS) {
            // đơn vị giây
        } else if (timeUnit == Cache.TimeUnit_MINUTE) {
            // đơn vị phút
            time = time * Cache.TimeUnit_MINUTE;
        } else if (timeUnit == Cache.TimeUnit_HOUR) {
            // đơn vị giờ
            time = time * Cache.TimeUnit_HOUR;
        }
        try {
            String json = objectMapper.writeValueAsString(result);
            LOGGER.info("Writing "+json);
            jedisConnectionFactory.set(key, json);
            jedisConnectionFactory.expire(key, time);

            LOGGER.info("Write success! key: "+key);
        } catch (Exception e) {
            return Cache.ERROR;
        }
        return Cache.DONE;
    }

}
