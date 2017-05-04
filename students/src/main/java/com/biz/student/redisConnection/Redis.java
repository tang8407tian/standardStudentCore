package com.biz.student.redisConnection;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

public class Redis {
	public Jedis getJedis() {
		JedisPool pools =  new JedisPool(new JedisPoolConfig(),"127.0.0.1");
		Jedis jedis = pools.getResource();
		
		
		return jedis;
	}	
}
