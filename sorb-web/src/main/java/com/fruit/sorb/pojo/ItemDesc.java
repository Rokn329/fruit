package com.fruit.sorb.pojo;

import com.fruit.sorb.common.po.BasePojo;

/**
 * @ClassName: ItemDesc
 * @Description:
 * @Author: lokn
 * @Date: 2019/2/12 22:35
 */
public class ItemDesc extends BasePojo {
    private Long itemId;
    private String itemDesc;

    public Long getItemId() {
        return itemId;
    }

    public void setItemId(Long itemId) {
        this.itemId = itemId;
    }

    public String getItemDesc() {
        return itemDesc;
    }

    public void setItemDesc(String itemDesc) {
        this.itemDesc = itemDesc;
    }
}
