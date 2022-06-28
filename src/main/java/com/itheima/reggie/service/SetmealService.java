package com.itheima.reggie.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.itheima.reggie.dto.SetmealDto;
import com.itheima.reggie.entity.Setmeal;

public interface SetmealService extends IService<Setmeal> {
    /**
     * 新增套餐 同时保存套餐和菜品的关联关系（操作两张表）
     * @param setmealDto
     */
    public void saveWithDish(SetmealDto setmealDto);
}
