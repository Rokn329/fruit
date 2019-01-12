package com.fruit.sorb.service;

import com.fruit.sorb.manager.mapper.ItemMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @ClassName: CommonFindCountService
 * @Description:
 * @Author: lokn
 * @Date: 2019/1/12 22:10
 */
@Service
public class CommonFindCountService {

    @Autowired
    private ItemMapper itemMapper;

    public int findCount() {
       return itemMapper.findCount();
    }

}
