package com.fruit.sorb.controller;

import com.fruit.sorb.service.ItemService;
import com.fruit.sorb.vo.EasyUIResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @ClassName: ItemController
 * @Description:
 * @Author: lokn
 * @Date: 2019/1/5 22:48
 */
@Controller
@RequestMapping("/item")
public class ItemController {

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
    @RequestMapping("/queryItemName")
    public void findItemName(Long itemCatId, HttpServletResponse response) {
        String name = itemService.findItemName(itemCatId);
        response.setContentType("text/html;charset=UTF-8");
        try {
            response.getWriter().write(name);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
