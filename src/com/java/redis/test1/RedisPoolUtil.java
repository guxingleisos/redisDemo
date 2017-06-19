package com.java.redis.test1;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

/**
 * @ClassName: RedisPoolUtil
 * @Description: ToDo
 * @author: 邓乔
 * @create 2017-03-21 21:46
 **/
public class RedisPoolUtil {
    private static JedisPool jedisPool=null;
    private RedisPoolUtil(){}
    public static JedisPool getJedisPoolInstance(){
        if(null==jedisPool){
            synchronized (RedisPoolUtil.class){
                if(null == jedisPool){
                    JedisPoolConfig config=new JedisPoolConfig();
                    config.setMaxActive(200);
                    config.setMaxIdle(30);
                    config.setMaxWait(200*1000);
                    config.setTestOnBorrow(true);//new一个Jedis对象时是否测试连通性

                    jedisPool=new JedisPool(config,"192.168.31.128",6380);
                }
            }
        }
        return jedisPool;
    }

    public static void release(JedisPool jedisPool, Jedis jedis){
         if(jedis!=null){
             jedisPool.returnResourceObject(jedis);
         }
    }

}
