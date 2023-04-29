package com.gltedu.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.gltedu.entity.Orders;

/**
 * @author 巩乐天
 * @version 1.0
 */
public interface OrderService extends IService<Orders> {
    /*
    * 用户下单
    * */
    public void submit(Orders orders);
}
