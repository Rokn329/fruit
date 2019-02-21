package com.fruit.sorb.manager.pojo;

import com.fruit.sorb.common.po.BasePojo;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @ClassName: ItemCat
 * @Description:
 * @Author: lokn
 * @Date: 2019/1/12 22:49
 */
@Table(name = "tb_item_cat")
public class ItemCat extends BasePojo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; // 分类id
    private Long parentId; // 上级分类id
    private String name; // 分类名称
    private Integer status; // 默认值为1,1正常；2删除
    private Integer sortOrder; // 排序号
    private Boolean isParent; // 表示是否为上级

    public ItemCat() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getParentId() {
        return parentId;
    }

    public void setParentId(Long parentId) {
        this.parentId = parentId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Integer getSortOrder() {
        return sortOrder;
    }

    public void setSortOrder(Integer sortOrder) {
        this.sortOrder = sortOrder;
    }

    public Boolean getParent() {
        return isParent;
    }

    public void setParent(Boolean parent) {
        isParent = parent;
    }

    /*
     * 为了满足EasyUI的树形结构，添加getXxx()方法
     */
    public String getText() {
        return this.name;
    }

    /*
     * 如果是上级菜单，则应该是closed
     * 如果不是上级菜单，则open
     */
    public String getState() {
        return isParent ? "closed" : "open";
    }
}
