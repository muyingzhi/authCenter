package com.tianjian.cache;

import org.apache.commons.lang.RandomStringUtils;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;

import java.util.Set;

public abstract class SessionManager {

	private static Logger logger = LogManager.getLogger(SessionManager.class);
	
//	private static ConfigurationManager manager = null;
	
	private static CacheClient cache = null;

	public static int TIME_OUT = 60*40;
	
	private static Set<String> keys = new java.util.concurrent.ConcurrentSkipListSet<String>();
	
	static{
		try {
			//manager = new ConfigurationManager("redis");
			cache = new LocalMapClient();
		} catch (Exception e) {
			logger.error(e);
		} 
	}
	
	public static Object get(String key){
		return get(key, Object.class);
	}
	
	private static String createKey(String key){
		
		RequestAttributes attributes = RequestContextHolder.currentRequestAttributes();
		String skey = null;
		String sessionid = null;

		if(sessionid == null && attributes != null){
			sessionid = attributes.getSessionId();
		}
		
		keys.add(key);

		skey = sessionid + "_" + key;
		logger.debug("create session key="+skey);
		return skey;
	}
	
	public synchronized static void removeBySessionId(String sessionId){
		for(String key:keys){
			cache.del(sessionId+ "_" + key);
		}
		cache.cleanup();
	}
	
	public synchronized static void copy(String oldSessionId,String newSeesionId){
		byte[] bkey = null;
		
		for(String key:keys){
			bkey = Transcoder.encodeString(oldSessionId+"_"+key);
			
			cache.set(Transcoder.encodeString(newSeesionId+"_"+key),cache.get(bkey));
			cache.expire(newSeesionId+"_"+key, TIME_OUT);
		}
	}
	
	public synchronized static void refresh(){
		for(String key:keys){
			expire(key,TIME_OUT);
		}
	}
	
	public synchronized static void put(String key,Object value){
		try{
			byte[] bkey = Transcoder.encodeString(createKey(key));
			
			byte[] bval = Transcoder.encodeObject(value);
			cache.set(bkey, bval);
			cache.expire(bkey, TIME_OUT);
		}catch(Exception e){
			logger.error(e,e);
		}
	}

	public synchronized static void put(String key,Object value,int seconds){
		try{
	
			byte[] bkey = Transcoder.encodeString(createKey(key));
			
			byte[] bval = Transcoder.encodeObject(value);
			cache.set(bkey, bval);
			cache.expire(bkey, seconds);
		}catch(Exception e){
			logger.error(e,e);
		}
	}
	public synchronized static <T> T  get(String key,Class<T> clazz){
		try{
			byte[] bkey = Transcoder.encodeString(createKey(key));
			
			byte[] bval = cache.get(bkey);
			
			if(bval != null){
				return Transcoder.decodeObject(bval, clazz);
			}else{
				cache.del(bkey);
			}
		}catch(Exception e){
			logger.error(e,e);
		}
		return null;
	}
	
	public static void generateAccessToken(int seconds){
		put("ACCESS_TOKEN", RandomStringUtils.randomAlphanumeric(12),seconds);
	}
	
	public static String getAccessToken(){
		return get("ACCESS_TOKEN",String.class);
	}
	
	public static long expire(String key,int seconds){
		logger.debug("增加session时间  key="+key);
		return cache.expire(createKey(key), seconds);
	}
	
	public static void del(String key){
		cache.del(createKey(key));
	}
	
	public static void cleanUp(){
		cache.cleanup();
	}
}