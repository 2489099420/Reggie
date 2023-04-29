package com.gltedu.dto;

import com.gltedu.entity.Dish;
import com.gltedu.entity.DishFlavor;
import com.gltedu.entity.Dish;
import com.gltedu.entity.DishFlavor;
import lombok.Data;
import java.util.ArrayList;
import java.util.List;

@Data
public class DishDto extends Dish {

    private List<DishFlavor> flavors = new ArrayList<>();

    private String categoryName;

    private Integer copies;
}
