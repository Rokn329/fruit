package com.fruit.sorb.service;

import com.fruit.sorb.manager.mapper.ItemCatMapper;
import com.fruit.sorb.manager.pojo.ItemCat;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @ClassName: ItemCatService
 * @Description:
 * @Author: lokn
 * @Date: 2019/1/12 23:03
 */
@Service
public class ItemCatService extends BaseService<ItemCat> {

    @Autowired
    private ItemCatMapper itemCatMapper;

    public List<ItemCat> findItemCatList(Long parentId) {
        ItemCat itemCat = new ItemCat();
        itemCat.setParentId(parentId);
        return itemCatMapper.select(itemCat);
    }

}
