package com.gltedu.dto;

import com.gltedu.entity.Setmeal;
import com.gltedu.entity.SetmealDish;
import lombok.Data;
import java.util.List;

@Data
public class SetmealDto extends Setmeal {

    private List<SetmealDish> setmealDishes;

    private String categoryName;
}
