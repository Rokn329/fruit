package com.fruit.sorb.controller;

import com.fruit.sorb.manager.pojo.Item;
import com.fruit.sorb.manager.pojo.ItemDesc;
import com.fruit.sorb.service.ItemService;
import com.fruit.sorb.vo.EasyUIResult;
import com.fruit.sorb.vo.SysResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @ClassName: ItemController
 * @Description:
 * @Author: lokn
 * @Date: 2019/1/5 22:48
 */
@Controller
@RequestMapping("/item")
public class ItemController {

    private static final Logger LOG = LoggerFactory.getLogger(ItemController.class);

    @Autowired
    private ItemService itemService;

    /**
     * @title: findItemList
     * @description: 分页查询item信息
     * @params: [page-第几页, rows-当前页最多多少行]
     * @return: com.fruit.sorb.vo.EasyUIResult
     * @author: lokn
     * @date: 2019/1/8 22:21
     * @version: v1.0
     */
    @RequestMapping("/query")
    @ResponseBody
    public EasyUIResult findItemList(int page, int rows) {
        return itemService.findItemList(page, rows);
    }

    /**
     * @title: findItemName
     * @description:
     * @params: [itemCatId]
     * @return: void
     * @author: lokn
     * @date: 2019/1/8 23:22
     * @version: v1.0
     */
    /*@RequestMapping("/queryItemName")
    public void findItemName(Long itemCatId, HttpServletResponse response) {
        String name = itemService.findItemName(itemCatId);
        response.setContentType("text/html;charset=UTF-8");
        try {
            response.getWriter().write(name);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }*/

    @RequestMapping(value = "/queryItemName", produces = "text/html;charset=UTF-8")
    @ResponseBody
    public String findItemCatName(Long itemCatId) {
        String itemName = itemService.findItemName(itemCatId);
        return itemName;
    }

    /**
     * 商品信息保存
     * @param item
     * @param desc 商品描述信息
     */
    @RequestMapping("/save")
    @ResponseBody
    public SysResult saveItem(Item item, String desc) {
        try {
            itemService.saveItem(item, desc);
            return SysResult.build(200, "商品新增成功");
        } catch (Exception e) {
            e.printStackTrace();
            LOG.error("新增商品失败，异常信息为：{}", e.getMessage());
            return SysResult.build(201, "新增商品失败！请联系管理员");
        }
    }

    /**
     * 商品信息更新操作
     * @param item
     * @return
     */
    @RequestMapping("/update")
    @ResponseBody
    public SysResult updateItem(Item item, String desc) {
        try {
            itemService.updateItem(item, desc);
            return SysResult.build(200, "商品信息更新成功");
        } catch (Exception e) {
            e.printStackTrace();
            LOG.error("商品信息更新失败，异常信息为：{}", e.getMessage());
            return SysResult.build(201, "商品信息更新失败，请联系管理员");
        }
    }

    @RequestMapping("/delete")
    @ResponseBody
    public SysResult deleteItem(Long[] ids) {
        try {
            itemService.deleteItem(ids);
            return SysResult.build(200, "商品信息删除成功");
        } catch (Exception e) {
            e.printStackTrace();
            LOG.error("商品信息删除失败，异常信息为：{}", e.getMessage());
            return SysResult.build(201, "商品信息删除失败，请联系管理员");
        }

    }

    /**
     * 查询商品描述信息
     * @param itemId
     * @return
     */
    @RequestMapping("/query/item/desc/{itemId}")
    @ResponseBody
    public SysResult findItemDesc(@PathVariable Long itemId) {
        try {
            ItemDesc itemDesc = itemService.findItemDesc(itemId);
            return SysResult.oK(itemDesc);
        } catch (Exception e) {
            e.printStackTrace();
            LOG.error("商品描述查询失败，异常信息：{}", e.getMessage());
            return SysResult.build(201, "商品描述查询失败，请联系管理员");
        }
    }

}
