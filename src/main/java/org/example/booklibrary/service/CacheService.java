package org.example.booklibrary.service;

import org.springframework.stereotype.Service;

@Service
public interface CacheService {
		String getByKey(String key);

		String writeCache(String key, Object result);

		String destroyCache(String key);

		String writeCacheAtTime(String key, Object result, long time, int timeUnit);
}
