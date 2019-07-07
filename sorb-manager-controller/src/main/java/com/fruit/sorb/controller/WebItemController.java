package com.fruit.sorb.controller;

import com.fruit.sorb.manager.pojo.Item;
import com.fruit.sorb.manager.pojo.ItemDesc;
import com.fruit.sorb.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 商品详细信息查询
 */
@Controller
public class WebItemController {

    @Autowired
    private ItemService itemService;

    @RequestMapping("/web/item/{itemId}")
    @ResponseBody
    public Item getItemInfoByItemId(@PathVariable Long itemId) {
        Item item = itemService.queryById(itemId);
        return item;
    }

    /**
     * 获取商品描述信息
     * @param itemId
     * @return
     */
    @RequestMapping("/web/item/desc/{itemId}")
    @ResponseBody
    public ItemDesc getItemDescInfoByItemId(@PathVariable Long itemId) {
        ItemDesc itemDesc = itemService.findItemDesc(itemId);
        return itemDesc;
    }

}
