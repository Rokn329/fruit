package com.fruit.sorb.manager.mapper;

import com.fruit.sorb.manager.pojo.Item;
import com.fruit.sorb.mapper.SysMapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ItemMapper extends SysMapper<Item> {

    /**查询全部商品信息，根据日期倒叙排列*/
    List<Item> findItemList();

    /**查询商品总数*/
    int findItemCount();

    /**分页查询商品数*/
    List<Item> findPageInfoList(@Param("startNum") int startNum, @Param("rows") int rows);

    /**查询叶子类中文信息*/
    String findItemName(Long itemCatId);
}
