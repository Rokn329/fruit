package com.fruit.sorb;

import org.apache.commons.pool2.impl.GenericObjectPoolConfig;
import org.junit.Test;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisShardInfo;
import redis.clients.jedis.ShardedJedis;
import redis.clients.jedis.ShardedJedisPool;

import java.util.ArrayList;
import java.util.List;

/**
 * @ClassName: Redis
 * @Description:
 * @Author: lokn
 * @Date: 2019/4/22 23:02
 */
public class TestJedis {

    // 连接单个节点
    @Test
    public void test() {
        /*
           步骤：
           1、创建Jedis对象
           2、需要配置ip+dport
           3、调用相关命令，get name
           4、释放资源
         */
        Jedis jedis = new Jedis("192.168.118.170", 6380);
        String name = jedis.get("name");
        jedis.append("age", "20");
        String age = jedis.get("age");
        jedis.close();
        System.out.println("redis_name = " + name);
        System.out.println("age = " + age);
    }

    // 分片连接redis的所有节点
    @Test
    public void shard() {
        GenericObjectPoolConfig config = new GenericObjectPoolConfig();
        config.setMaxTotal(50);
        List<JedisShardInfo> shardInfoList = new ArrayList<>();
        JedisShardInfo shard1 = new JedisShardInfo("192.168.118.170", 6379);
        JedisShardInfo shard2 = new JedisShardInfo("192.168.118.170", 6380);
        JedisShardInfo shard3 = new JedisShardInfo("192.168.118.170", 6381);

        shardInfoList.add(shard1);
        shardInfoList.add(shard2);
        shardInfoList.add(shard3);
        
        ShardedJedisPool pool = new ShardedJedisPool(config, shardInfoList);
        ShardedJedis resource = pool.getResource();
        String name = resource.get("name");

        System.out.println(name);

        pool.returnResource(resource);
        pool.close();
    }

}
