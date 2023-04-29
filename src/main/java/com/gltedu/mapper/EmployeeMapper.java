package com.gltedu.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.gltedu.entity.Employee;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author 巩乐天
 * @version 1.0
 */
@Mapper
public interface EmployeeMapper extends BaseMapper<Employee> {
}
