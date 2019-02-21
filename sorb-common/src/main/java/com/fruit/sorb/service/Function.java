package com.fruit.sorb.service;

/**
 * @ClassName: Function
 * @Description:
 * @Author: lokn
 * @Date: 2019/2/14 22:03
 */
public interface Function<E, T> {
    public T execute(E e);
}
