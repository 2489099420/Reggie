package com.gltedu.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.fasterxml.jackson.databind.ser.Serializers;
import com.gltedu.entity.Setmeal;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author 巩乐天
 * @version 1.0
 */
@Mapper
public interface SetmealMapper extends BaseMapper<Setmeal> {
}
