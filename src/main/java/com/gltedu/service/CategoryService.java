package com.gltedu.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.gltedu.entity.Category;

/**
 * @author 巩乐天
 * @version 1.0
 */
public interface CategoryService extends IService<Category> {
    public void remove(Long id);
}
