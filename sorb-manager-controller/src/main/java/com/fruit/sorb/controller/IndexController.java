package com.fruit.sorb.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class IndexController {

    @RequestMapping("/page/{index}")
    public String getIndex(@PathVariable String index) {
        return index;
    }

}
