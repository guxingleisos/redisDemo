package com.java.redis.test;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.Transaction;

/**
 * @ClassName: RedisTransaction
 * @Description: ToDo
 * @author: 邓乔
 * @create 2017-03-21 20:55
 **/
public class RedisTransaction {

    public boolean tranMethod() throws InterruptedException {

        Jedis jedis=new Jedis("192.168.31.128",6381);
         int balance;//余额
         int debt;//欠债
         int autoDesc=20;//消费金额
        //假设redis中已经存在balance和debt了
        jedis.watch("balance");
        balance=Integer.parseInt(jedis.get("balance"));
        Thread.sleep(7000);
        if(balance<autoDesc){
            jedis.unwatch();
            System.out.println("balance modify!");
            return false;
        }
        else {
          Transaction transaction= jedis.multi();
            transaction.decrBy("balance",autoDesc);
            transaction.incrBy("debt",autoDesc);
            transaction.exec();
            balance=Integer.parseInt(jedis.get("balance"));
            debt=Integer.parseInt(jedis.get("debt"));
            System.out.println("*****balance:"+balance);
            System.out.println("*****debt:"+debt);
           return true;

        }
    }

    public static void main(String[] args) throws InterruptedException {
          RedisTransaction test=new RedisTransaction();
        System.out.println("**********main tranMethod:"+test.tranMethod());
    }
}
