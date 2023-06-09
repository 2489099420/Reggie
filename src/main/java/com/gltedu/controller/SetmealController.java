package com.gltedu.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.gltedu.common.R;
import com.gltedu.dto.DishDto;
import com.gltedu.dto.SetmealDto;
import com.gltedu.entity.Category;
import com.gltedu.entity.Setmeal;
import com.gltedu.service.CategoryService;
import com.gltedu.service.SetmealDishService;
import com.gltedu.service.SetmealService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author 巩乐天
 * @version 1.0
 */
@RestController
@Slf4j
@RequestMapping("/setmeal")
public class SetmealController {
    @Autowired
    private SetmealService setmealService;

    @Autowired
    private CategoryService categoryService;
    @Autowired
    private SetmealDishService setmealDishService;

    /*
     * 新增套餐
     * */
    @PostMapping
    @CacheEvict(value = "setmealCache",allEntries = true)//删除setmealCache分类下的所有数据
    public R save(@RequestBody SetmealDto setmealDto) {
        log.info("套餐信息:{}", setmealDto);
        setmealService.saveWithDish(setmealDto);
        return R.success("新增成功");
    }

    /*
     * 套餐分页查询
     * */
    @GetMapping("/page")
    public R page(int page, int pageSize, String name) {
        //分页构造器对象
        Page<Setmeal> pageInfo = new Page<>(page, pageSize);
        Page<SetmealDto> pageDto = new Page<>();

        LambdaQueryWrapper<Setmeal> queryWrapper = new LambdaQueryWrapper<>();
        //添加查询条件 根据name进行like模糊查询
        queryWrapper.like(name != null, Setmeal::getName, name);
        //添加排序条件，根据更新时间降序排列
        queryWrapper.orderByDesc(Setmeal::getUpdateTime);

        setmealService.page(pageInfo, queryWrapper);

        //对象拷贝
        BeanUtils.copyProperties(pageInfo, pageDto, "records");
        List<Setmeal> records = pageInfo.getRecords();
        List<SetmealDto> list = records.stream().map((item) -> {
            SetmealDto setmealDto = new SetmealDto();
            //对象拷贝
            BeanUtils.copyProperties(item, setmealDto);
            //分类id
            Long categoryId = item.getCategoryId();
            //根据分类id查询分类对象
            Category category = categoryService.getById(categoryId);
            if (category != null) {
                //分类名称
                String categoryName = category.getName();
                setmealDto.setCategoryName(categoryName);
            }
            return setmealDto;
        }).collect(Collectors.toList());

        pageDto.setRecords(list);
        return R.success(pageDto);
    }

    /*
     * 删除套餐
     * */
    @DeleteMapping
    @CacheEvict(value = "setmealCache",allEntries = true)//删除setmealCache分类下的所有数据
    public R delete(@RequestParam List ids) {
        log.info("ids:{}", ids);
        setmealService.removeWithDish(ids);
        return R.success("套餐数据删除成功 ");
    }


    /*
    * 根据条件查询套餐数据
    * */
    @GetMapping("/list")
    @Cacheable(value = "setmealCache",key = "#setmeal.categoryId + '_' + #setmeal.status")
    public R list(Setmeal setmeal){
        LambdaQueryWrapper<Setmeal> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(setmeal.getCategoryId() != null,Setmeal::getCategoryId,setmeal.getCategoryId());
        queryWrapper.eq(setmeal.getStatus() != null,Setmeal::getStatus,setmeal.getStatus());
        queryWrapper.orderByDesc(Setmeal::getUpdateTime);

        List<Setmeal> list = setmealService.list(queryWrapper);

        return R.success(list);

    }

    /*
    * 停售套餐
    * */
    @PostMapping("/status/0")
    @CacheEvict(value = "setmealCache",allEntries = true)//删除setmealCache分类下的所有数据
    public R statusDish(@RequestParam List<Long> ids){
        UpdateWrapper updateWrapper = new UpdateWrapper();
        updateWrapper.in("id",ids);
        updateWrapper.set("status","0");
        setmealService.update(null,updateWrapper);

        return R.success("菜品停售成功");
    }
    /*
    * 启售套餐
    * */
    @PostMapping("/status/1")
    @CacheEvict(value = "setmealCache",allEntries = true)//删除setmealCache分类下的所有数据
    public R status1Dish(@RequestParam List<Long> ids){
        UpdateWrapper updateWrapper = new UpdateWrapper();
        updateWrapper.in("id",ids);
        updateWrapper.set("status","1");
        setmealService.update(null,updateWrapper);

        return R.success("菜品启售成功");
    }

    /*
    * 修改套餐
    * */
    @PutMapping
    @CacheEvict(value = "setmealCache",allEntries = true)//删除setmealCache分类下的所有数据
    public R putSetmeal(@RequestBody SetmealDto setmealDto){
        setmealService.updateWithDish(setmealDto);
        return R.success("修改成功");
    }

    /*
     * 根据id查询套餐信息和对应的套餐菜品口味信息
     * */
    @GetMapping("/{id}")
    public R get(@PathVariable Long id){
        SetmealDto setmealDto = setmealService.getByIdWithFlavor(id);

        return R.success(setmealDto);
    }
}
