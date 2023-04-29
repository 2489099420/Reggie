package com.gltedu.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.gltedu.mapper.UserMapper;
import com.gltedu.entity.User;
import com.gltedu.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * @author 巩乐天
 * @version 1.0
 */
@Service
@Slf4j
public class UserServiceImpl extends ServiceImpl<UserMapper,User> implements UserService {
}
