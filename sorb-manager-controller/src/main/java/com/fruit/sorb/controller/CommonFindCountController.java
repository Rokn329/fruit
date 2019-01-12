package com.fruit.sorb.controller;

import com.fruit.sorb.service.CommonFindCountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @ClassName: CommonFindCountController
 * @Description:
 * @Author: lokn
 * @Date: 2019/1/12 22:09
 */
@Controller
@RequestMapping("/find")
public class CommonFindCountController {

    @Autowired
    private CommonFindCountService commonFindCountService;

    @RequestMapping("/count")
    @ResponseBody
    public int findCount() {

        return commonFindCountService.findCount();

    }


}
