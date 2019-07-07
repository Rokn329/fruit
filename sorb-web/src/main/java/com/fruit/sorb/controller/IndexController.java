package com.fruit.sorb.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @ClassName: IndexController
 * @Description:
 * @Author: lokn
 * @Date: 2019/6/13 23:09
 */
@Controller
public class IndexController {

    /**
     * 转向首页
     */
    @RequestMapping("index")
    public String index() {
        return "index";
    }

}
