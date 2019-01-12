package com.fruit.sorb.service;

import com.fruit.sorb.mapper.SysMapper;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * @ClassName: BaseService
 * @Description:
 * @Author: lokn
 * @Date: 2019/1/12 23:07
 */
public abstract class BaseService<T> {

    @Autowired
    private SysMapper<T> sysMapper;

    /**
     *  根据主键查询数据
     * @param id
     * @return
     */
    public T queryById(String id) {
        return this.sysMapper.selectByPrimaryKey(id);
    }

    /**
     *  根据条件查询，多条件之间是and关系
     * @param t
     * @return
     */
    public List<T> queryListByWhere(T t) {
        return this.sysMapper.select(t);
    }

    /**
     *  根据条件查询单条数据
     * @param t
     * @return
     */
    public T queryByWhere(T t) {
        List<T> list = this.queryListByWhere(t);
        if (list != null && list.size() > 0) {
            return list.get(0);
        }
        return null;
    }

    /**
     *  查询所有数据
     * @return
     */
    public List<T> queryAll() {
       return this.queryAll();
    }

    /**
     *  新增数据，使用不为null的字段
     * @param t
     */
    public void insertSelective(T t) {
        this.sysMapper.insertSelective(t);
    }

    /**
     *  新增数据，使用所有字段
     * @param t
     */
    public void insert(T t) {
        this.sysMapper.select(t);
    }

    /**
     *  根据id删除
     * @param id
     * @return
     */
    public Integer deleteById(Object id) {
        return this.sysMapper.deleteByPrimaryKey(id);
    }

    /**
     * 根据ids删除
     * @param ids
     * @return
     */
    public Integer deleteByIds(Object[] ids) {
        return this.sysMapper.deleteByIDS(ids);
    }

    /**
     *  根据条件删除
     * @param t
     * @return
     */
    public Integer deleteByWhere(T t) {
        return this.sysMapper.delete(t);
    }

    /**
     *  根据主键id更新数据
     * @param t
     * @return
     */
    public Integer update(T t) {
        return this.sysMapper.updateByPrimaryKey(t);
    }

    /**
     *  根据主键id更新数据
     * @param t
     * @return
     */
    public Integer updateSelective(T t) {
        return this.sysMapper.updateByPrimaryKeySelective(t);
    }
}
