package com.gltedu.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.gltedu.entity.ShoppingCart;
import com.gltedu.mapper.ShoppingCartMapper;
import com.gltedu.service.ShoppingCartService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * @author 巩乐天
 * @version 1.0
 */
@Service
@Slf4j
public class ShoppingCartServiceImpl extends ServiceImpl<ShoppingCartMapper, ShoppingCart> implements ShoppingCartService {
}
