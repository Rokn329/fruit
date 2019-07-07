package com.fruit.sorb.controller;

import com.fruit.sorb.service.WebItemCatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/web/itemcat")
public class WebItemCatController {

    @Autowired
    private WebItemCatService webItemCatService;

    @RequestMapping("/all")
    @ResponseBody
    public MappingJacksonValue findItemCatList(String callback) {
        MappingJacksonValue jkv = new MappingJacksonValue(webItemCatService.findItemCatList());
        jkv.setJsonpFunction(callback);
        return jkv;
    }

}
