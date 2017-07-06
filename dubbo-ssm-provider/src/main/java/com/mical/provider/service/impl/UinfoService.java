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
import com.mical.provider.cache.cluster.RedisClusterCache;
import com.mical.provider.mapper.IUinfoDao;

@Service(value = "uinfoService")
public class UinfoService implements IUinfoService {

	private final Logger logger = LoggerFactory.getLogger(this.getClass());

	@Resource
	private IUinfoDao uinfoDao;

	@Resource
	private RedisClusterCache clusterCache;

	@Resource
	private RedisCache cache;
	
	@Override
	public List<Uinfo> sel() {
		String cache_key = RedisCache.CAHCENAME + "|users";
		List<Uinfo> result_cache = clusterCache.getListCache(cache_key, Uinfo.class);
		if (result_cache == null) {
			result_cache = uinfoDao.sel();
			clusterCache.putListCacheWithExpireTime(cache_key, result_cache, RedisCache.CAHCETIME);
			logger.info("put cache with key:" + cache_key);
		} else {
			logger.info("get cache with key:" + cache_key);
		}

		logger.info(clusterCache.getCache("mical", String.class));
		cache.putCache("test" + new Random().nextInt(100), "local" + new Random().nextInt(100));
		return result_cache;
	}

	@Override
	public String saysay(String str) {

		return str;
	}

}
