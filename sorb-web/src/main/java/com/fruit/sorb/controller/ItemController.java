package com.fruit.sorb.controller;

import com.fruit.sorb.pojo.Item;
import com.fruit.sorb.pojo.ItemDesc;
import com.fruit.sorb.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class ItemController {

    @Autowired
    private ItemService itemService;

    /**
     * 获取商品详细信息
     * @param itemId
     * @param model
     * @return
     */
    @RequestMapping("/items/{itemId}")
    public String getItemInfo(@PathVariable Long itemId, Model model) {
        Item item = itemService.getItemById(itemId);
        model.addAttribute("item", item);

        ItemDesc itemDesc = itemService.getItemDescByItemId(itemId);
        model.addAttribute("itemDesc", itemDesc);
        return "item";
    }


}
