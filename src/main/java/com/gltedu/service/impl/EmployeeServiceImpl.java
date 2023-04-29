package com.gltedu.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.gltedu.entity.Employee;
import com.gltedu.mapper.EmployeeMapper;
import com.gltedu.service.EmployeeService;
import org.springframework.stereotype.Service;

/**
 * @author 巩乐天
 * @version 1.0
 */
@Service
public class EmployeeServiceImpl extends ServiceImpl<EmployeeMapper,Employee> implements EmployeeService {

}
