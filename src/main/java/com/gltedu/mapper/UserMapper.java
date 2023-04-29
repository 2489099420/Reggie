package com.gltedu.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.gltedu.entity.User;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author 巩乐天
 * @version 1.0
 */
@Mapper
public interface UserMapper extends BaseMapper<User> {
}
