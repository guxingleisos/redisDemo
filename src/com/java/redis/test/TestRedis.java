package com.java.redis.test;

import redis.clients.jedis.Client;
import redis.clients.jedis.Jedis;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * @ClassName: TestRedis
 * @Description: ToDo
 * @author: 邓乔
 * @create 2017-03-12 21:52
 **/
public class TestRedis {
    public static void main(String[] args) {
        Jedis jedis=new Jedis("192.168.31.128",6381);
      /*  jedis.set("k2","v2");

        //测试hash类型
        jedis.hset("person","name","zhangsan");
        jedis.hset("person","age","10");

        //测试set类型
        jedis.sadd("set1","a","b","1");

        //测试list类型
        jedis.lpush("list1","aa","bb","cc");*/

        //测试zset：sort set
        Map<Double,String> map1=new HashMap<Double, String>();
        map1.put(26d,"zhangsan");
        map1.put(57d,"Tom");
        map1.put(1d,"jack");
        jedis.zadd("score",map1);
        jedis.zadd("score",44,"Lily");

      Set<String> set=  jedis.keys("*");
        System.out.println("keys:");
        for(String s:set){
            System.out.print(s+",");
        }
        System.out.println();
        System.out.println("--------测试hash类型");
        System.out.println(jedis.hget("person","name"));

        System.out.println("--------测试set类型");
        System.out.println(jedis.smembers("set1"));
        System.out.println(jedis.sismember("set1","aa"));

        System.out.println("--------测试list类型");
        System.out.println("llen:"+jedis.llen("list1"));
        System.out.println("lrange:"+jedis.lrange("list1",0,-1));
        System.out.println("lpop:"+jedis.lpop("list1"));
        System.out.println("rpop:"+jedis.rpop("list1"));

        System.out.println("--------测试zset");
        System.out.println("zrange从小到大分数："+jedis.zrange("score",0,-1));
        System.out.println("zrange从小到大分数："+jedis.zrevrange("score",0,-1));
        System.out.println("分数范围从小到大排序："+jedis.zrangeByScore("score",40,90));


    }
}
