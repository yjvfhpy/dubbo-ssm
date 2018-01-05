package com.mical.provider.service.impl;

import java.util.List;
import java.util.Random;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.mical.api.IUinfoService;
import com.mical.entity.Uinfo;
import com.mical.provider.cache.RedisCache;
import com.mical.provider.mapper.IUinfoDao;

@Service(value = "uinfoService")
public class UinfoService implements IUinfoService {

	private final Logger logger = LoggerFactory.getLogger(this.getClass());

	@Resource
	private IUinfoDao uinfoDao;

/*	@Resource
	private RedisClusterCache clusterCache;*/

	@Resource
	private RedisCache cache;

	@Override
	public List<Uinfo> sel() {
		String cache_key = RedisCache.CAHCENAME + "|users";
		List<Uinfo> result = cache.getListCache(cache_key, Uinfo.class);
		if (result == null) {
			result = uinfoDao.sel();
			cache.putListCacheWithExpireTime(cache_key, result, RedisCache.CAHCETIME);
			logger.info("put cache with key:" + cache_key);
		} else {
			logger.info("get cache with key:" + cache_key);
		}

		int rd = new Random().nextInt(100);
		cache.putCache("test" + rd, "local" + rd);
		logger.info(cache.getCache("test" + rd, String.class));

		return result;
	}

	@Override
	public String saysay(String str) {

		return str;
	}

}
