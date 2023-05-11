package com.gltedu.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.gltedu.dto.SetmealDto;
import com.gltedu.entity.Setmeal;

import java.util.List;

/**
 * @author 巩乐天
 * @version 1.0
 */
public interface SetmealService extends IService<Setmeal> {
    /**
     * 新增套餐，同时需要保存套餐和菜品的关联关系
     * @param setmealDto
     */
    public void saveWithDish(SetmealDto setmealDto);

    /**
     * 删除套餐，同时需要删除套餐和菜品的关联数据
     *
     * @param ids
     */
    public void removeWithDish(List<Long> ids);


    public SetmealDto getByIdWithFlavor(Long id);

    public void updateWithDish(SetmealDto setmealDto);
}
