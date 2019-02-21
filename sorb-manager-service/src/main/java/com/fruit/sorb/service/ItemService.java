package com.fruit.sorb.service;

import com.fruit.sorb.manager.mapper.ItemDescMapper;
import com.fruit.sorb.manager.mapper.ItemMapper;
import com.fruit.sorb.manager.pojo.Item;
import com.fruit.sorb.manager.pojo.ItemDesc;
import com.fruit.sorb.vo.EasyUIResult;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * @ClassName: ItemService
 * @Description:
 * @Author: lokn
 * @Date: 2019/1/5 22:48
 */
@Service
public class ItemService {

    @Autowired
    private ItemMapper itemMapper;

    @Autowired
    private ItemDescMapper itemDescMapper;

    /**
     * @title: findItemList
     * @description: 
     * @params: [page, rows]
     * @return: java.util.List<com.fruit.sorb.manager.pojo.Item>
     * @author: lokn
     * @date: 2019/1/6 20:42
     * @version: v1.0
     */
    public EasyUIResult findItemList(int page, int rows) {
        // 分页插件实现分页功能
        // 分页开关，只有设置该参数才能实现分页功能
        PageHelper.startPage(page, rows);
        List<Item> itemList = itemMapper.findItemList();
        // 自己计算全部信息数
        PageInfo<Item> info = new PageInfo<Item>(itemList);
        return new EasyUIResult(info.getTotal(), info.getList());

        // 手动分页查询
        /*
        // 商品总数
        int title = itemMapper.findItemCount();
        int startNum = (page - 1) * rows;
        List<Item> itemList = itemMapper.findPageInfoList(startNum, rows);
        EasyUIResult result = new EasyUIResult();
        result.setTotal(title);
        result.setRows(itemList);
        return result;*/
    }

    /**
     * @title: findItemName
     * @description:
     * @params: [itemCatId]
     * @return: java.lang.String
     * @author: lokn
     * @date: 2019/1/8 23:23
     * @version: v1.0
     */
    public String findItemName(Long itemCatId) {
        return itemMapper.findItemName(itemCatId);
    }

    /**
     * 商品信息保存操作
     * @param item
     * @param desc
     */
    public void saveItem(Item item, String desc) {
        item.setCreated(new Date());
        item.setUpdated(item.getCreated());
        itemMapper.insertSelective(item);

        ItemDesc itemDesc = new ItemDesc();
        itemDesc.setItemId(item.getId());
        itemDesc.setItemDesc(desc);
        itemDesc.setCreated(item.getCreated());
        itemDesc.setUpdated(item.getUpdated());
        itemDescMapper.insert(itemDesc);
    }

    /**
     * 商品信息更新操作
     * @param item
     */
    public void updateItem(Item item) {
        item.setUpdated(item.getCreated());
        itemMapper.updateByPrimaryKeySelective(item);
    }

    /**
     * 商品删除
     * @param ids
     */
    public void deleteItem(Long[] ids) {
        itemMapper.deleteByIDS(ids);
    }

    /**
     * 查询商品描述信息
     * @param itemId
     */
    public ItemDesc findItemDesc(Long itemId) {
        return itemDescMapper.selectByPrimaryKey(itemId);
    }
}
