/**
 * 
 */
package com.mical.api;

/**
 * @Description:结果缓存 1，用于加速热门数据的访问速度，Dubbo 提供声明式缓存，以减少用户加缓存的工作量 @
 * https://dubbo.gitbooks.io/dubbo-user-book/demos/result-cache.html
 * @author micalliu
 * @date 2017年10月26日
 */
public interface ICacheService {
	String findCache(String id);
}
