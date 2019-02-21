package com.fruit.sorb.controller;

import com.fruit.sorb.manager.pojo.ItemCat;
import com.fruit.sorb.service.ItemCatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * @ClassName: ItemCatController
 * @Description:
 * @Author: lokn
 * @Date: 2019/2/17 21:26
 */
@Controller
@RequestMapping("/item/cat")
public class ItemCatController {

    @Autowired
    private ItemCatService itemCatService;

    @RequestMapping("/list")
    @ResponseBody
    public List<ItemCat> findItemCatList(@RequestParam(value = "id", defaultValue = "0") Long parentId) {
        List<ItemCat> itemCatList = itemCatService.findItemCatList(parentId);
        return itemCatList;
    }

}
