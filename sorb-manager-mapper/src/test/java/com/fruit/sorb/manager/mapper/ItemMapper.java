package com.fruit.sorb.manager.mapper;

import com.fruit.sorb.manager.pojo.Item;
import com.fruit.sorb.mapper.SysMapper;

import java.util.List;

public interface ItemMapper extends SysMapper<Item> {

    List<Item> findItemList();

}
