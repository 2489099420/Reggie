package com.gltedu.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.gltedu.entity.Dish;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Update;

import java.util.List;

/**
 * @author 巩乐天
 * @version 1.0
 */
@Mapper
public interface DishMapper extends BaseMapper<Dish> {

}
