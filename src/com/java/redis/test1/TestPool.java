package com.java.redis.test1;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

/**
 * @ClassName: TestPool
 * @Description: ToDo
 * @author: 邓乔
 * @create 2017-03-21 21:47
 **/
public class TestPool {
    public static void main(String[] args) {
        JedisPool jedisPool= RedisPoolUtil.getJedisPoolInstance();
        JedisPool jedisPool2= RedisPoolUtil.getJedisPoolInstance();
        Jedis jedis=null;
        try {

            System.out.println(jedisPool==jedisPool2);

             jedis= jedisPool.getResource();
            jedis.set("k11","v111");
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            RedisPoolUtil.release(jedisPool,jedis);
        }
    }
}
