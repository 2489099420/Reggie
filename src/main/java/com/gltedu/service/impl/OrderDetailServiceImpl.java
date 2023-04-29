package com.gltedu.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.gltedu.entity.OrderDetail;
import com.gltedu.mapper.OrderDetailMapper;
import com.gltedu.service.OrderDetailService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * @author 巩乐天
 * @version 1.0
 */
@Service
@Slf4j
public class OrderDetailServiceImpl extends ServiceImpl<OrderDetailMapper, OrderDetail> implements OrderDetailService {
}
