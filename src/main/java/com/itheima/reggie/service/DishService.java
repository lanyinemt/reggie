package com.itheima.reggie.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.itheima.reggie.dto.DishDto;
import com.itheima.reggie.entity.Dish;

public interface DishService extends IService<Dish> {
    // 新增菜品，同时插入口味数据 同时操作两张表
    public void saveWithFlavor(DishDto dishDto);

    public DishDto getByIdWithFlavor(Long id);

    //更新菜品信息 同时修该口味信息
    public void updateWithFlavor(DishDto dishDto);
}
