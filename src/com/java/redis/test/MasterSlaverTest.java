package com.java.redis.test;

import redis.clients.jedis.Jedis;

/**
 * @ClassName: MasterSlaverTest
 * @Description: ToDo
 * @author: 邓乔
 * @create 2017-03-21 21:21
 **/
public class MasterSlaverTest {
    public static void main(String[] args) {
        Jedis jedis_M=new Jedis("192.168.31.128",6380);//主机
        Jedis jedis_S=new Jedis("192.168.31.128",6381);//从机

        jedis_S.slaveof("192.168.31.128",6380);

        jedis_M.set("key1","112335");

        String result=jedis_S.get("key1");
		System.out.println(result)
   
    }
}
