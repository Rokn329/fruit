package com.fruit.sorb.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fruit.sorb.manager.mapper.ItemCatMapper;
import com.fruit.sorb.manager.pojo.ItemCat;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import redis.clients.jedis.JedisCluster;

import java.util.List;

/**
 * @ClassName: ItemCatService
 * @Description:
 * @Author: lokn
 * @Date: 2019/1/12 23:03
 */
@Service
public class ItemCatService extends BaseService<ItemCat> {

    @Autowired
    private ItemCatMapper itemCatMapper;

    @Autowired
    private RedisService redisService;

    @Autowired
    private RedisSentinelService redisSentinelService;
    @Autowired
    private JedisCluster jedisCluster;

    private static final ObjectMapper MAPPER = new ObjectMapper();

    public List<ItemCat> findItemCatList(Long parentId) {
        ItemCat itemCat = new ItemCat();
        itemCat.setParentId(parentId);

        // 判断redis是否有数据
        String ITEM_CAT_KEY = "ITEM_KEY_" + parentId;
//        String jsonData = redisService.get(ITEM_CAT_KEY); // 分片
//        String jsonData = redisSentinelService.get(ITEM_CAT_KEY); // 哨兵
        String jsonData = jedisCluster.get(ITEM_CAT_KEY); // 集群
        if (StringUtils.isEmpty(jsonData)) { // 参数为空，则查询数据库数据
            List<ItemCat> itemCatList = itemCatMapper.select(itemCat);
            if (itemCatList != null && itemCatList.size() > 0) {
                try {
                    String jsonStr = MAPPER.writeValueAsString(itemCatList);
//                    redisService.set(ITEM_CAT_KEY, jsonStr);
//                    redisSentinelService.set(ITEM_CAT_KEY, jsonStr);
                    jedisCluster.set(ITEM_CAT_KEY, jsonStr);
                } catch (JsonProcessingException e) {
                    e.printStackTrace();
                }
            }
            return itemCatList;
        } else { // redis存在缓存数据，直接返回
            try {
                JsonNode jsonNode = MAPPER.readTree(jsonData);
                // 把json对象转换为java对象，List<ItemCat>
                Object obj = null;
                if (jsonNode.isArray() && jsonNode.size() > 0) {
                    obj = MAPPER.readValue(jsonNode.traverse(),
                            MAPPER.getTypeFactory().constructCollectionType(List.class, ItemCat.class));
                }
                return (List<ItemCat>) obj;
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return null;
    }

}
