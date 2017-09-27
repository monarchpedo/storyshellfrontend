package com.storyshell.dao;

import java.util.Map;

import javax.annotation.PostConstruct;
import javax.inject.Inject;

import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;

/**
 * @author santosh.chourasia
 *
 */
@Repository("redisRepository")
public class RedisRepository {
	
	@Inject
	private RedisTemplate<String, Object> redisTemplate;
	
    private HashOperations<String, String, Object> hashOps;
    
    @PostConstruct
    private void init() {
        hashOps = redisTemplate.opsForHash();
    }
    
    public boolean saveObject(String key, String hashKey, Object obj) {
    	 if(null != key && null!= hashKey && null != obj) {
    		 hashOps.put(key, hashKey, obj);
    		 return true;
    	 }
    	 return false;
    }
    
    public boolean updateObject(String key, String hashKey, Object obj) {
    	if(null != key && null!= hashKey && null != obj) {
   		 hashOps.put(key, hashKey, obj);
   		 return true;
   	 }
   	 return false;
    }
 
    public Object findKey(String key, String hashKey) {
        if (null != key && null != hashKey) {
			return hashOps.get(key, hashKey);
		}
        return null;
    }
 
    public Map<String, Object> findAllKey(String key) {
        if (null != key) {
			return hashOps.entries(key);
		}
        return null;
    }
 
    public boolean deleteStudent(String key, String hashKey) {
        if (null != key && null != hashKey) {
			hashOps.delete(key, hashKey);
			return true;
		}
        return false;
    }
 
    
}
