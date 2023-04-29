package com.gltedu.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.gltedu.common.CustomException;
import com.gltedu.dto.SetmealDto;
import com.gltedu.entity.Setmeal;
import com.gltedu.entity.SetmealDish;
import com.gltedu.mapper.SetmealMapper;
import com.gltedu.service.SetmealDishService;
import com.gltedu.service.SetmealService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author 巩乐天
 * @version 1.0
 */
@Service
@Slf4j
public class SetmealServiceImpl extends ServiceImpl<SetmealMapper, Setmeal> implements SetmealService {
    @Autowired
    private SetmealDishService setmealDishService;
    /*
    * 新增套餐 同时需要保存套餐和菜品的关联关系
    * */
    public void saveWithDish(SetmealDto setmealDto){
        //保存套餐的基本信息，操作setmeal,执行insert操作
        this.save(setmealDto);

        List<SetmealDish> setmealDishList = setmealDto.getSetmealDishes();
        setmealDishList.stream().map((item) -> {
            item.setSetmealId(setmealDto.getId());
            return item;
        }).collect(Collectors.toList());

        //保存套餐和菜品的关联信息 操作setmeal_dish  执行insert操作
        setmealDishService.saveBatch(setmealDishList);
    }


    /*
    * 删除套餐  同时删除套餐和菜品的关联数据
    * */
    public void removeWithDish(List<Long> ids) {
        //select count(*) from setmeal where id in (1,2,3) and status = 1;
        //查询套餐状态，确定是否可以删除
        LambdaQueryWrapper<Setmeal> queryWrapper = new LambdaQueryWrapper();
        queryWrapper.in(Setmeal::getId,ids);
        queryWrapper.eq(Setmeal::getStatus,1);

        Long count = this.count(queryWrapper);
        if(count > 0){
            //如果不能删除  抛出一个业务异常
            throw new CustomException("套餐正在售卖中，不能删除");
        }
        //如果可以删除 先删除套餐表中的数据 ---setmeal
        this.removeByIds(ids);

        //delete from setmeal_dish where setmeal_id in (1,2,3)
        LambdaQueryWrapper<SetmealDish> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        lambdaQueryWrapper.in(SetmealDish::getSetmealId,ids);
        //删除关系表中的数据 ---setmeal_dish
        setmealDishService.remove(lambdaQueryWrapper);

    }
}