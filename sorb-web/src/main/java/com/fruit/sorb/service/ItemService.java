package com.fruit.sorb.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fruit.sorb.pojo.Item;
import com.fruit.sorb.pojo.ItemDesc;
import com.fruit.sorb.spring.extend.PropertyConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ItemService {

    @Autowired
    private HttpClientService httpClientService;

    @PropertyConfig
    private String MANAGE_URL;

    private static final Logger LOGGER = LoggerFactory.getLogger(ItemService.class);

    private static final ObjectMapper MAPPER = new ObjectMapper();

    public Item getItemById(Long itemId) {
        String url = MANAGE_URL + "/web/item/" + itemId;
        try {
            String json = httpClientService.doGet(url);
            Item item = MAPPER.readValue(json, Item.class);
            return item;
        } catch (Exception e) {
            e.printStackTrace();
            LOGGER.error(e.getMessage());
        }
        return null;

    }

    /**
     * 查询商品描述信息
     * @param itemId
     * @return
     */
    public ItemDesc getItemDescByItemId(Long itemId) {
        String url = MANAGE_URL + "/web/item/desc/" + itemId;
        try {
            String jsonDate = httpClientService.doGet(url);
            return MAPPER.readValue(jsonDate, ItemDesc.class);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

}
