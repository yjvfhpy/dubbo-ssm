/**
 * 
 */
package com.mical.provider.service.impl;

import java.util.concurrent.atomic.AtomicInteger;

import org.springframework.stereotype.Service;

import com.mical.api.ICacheService;

/**
 * @author micalliu
 * @date 2017年10月26日
 */

@Service(value = "cacheService")
public class CacheService implements ICacheService {

	private final AtomicInteger i = new AtomicInteger();
	
	/* 
	 */
	@Override
	public String findCache(String id) {

		return "request: " + id + ", response: " + i.getAndIncrement();
	}
}
