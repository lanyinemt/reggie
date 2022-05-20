package com.itheima.reggie.service.Impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.itheima.reggie.entity.Dish;
import com.itheima.reggie.mapper.DishMapper;
import com.itheima.reggie.service.DishService;
import org.springframework.stereotype.Service;

/**
 * @author yin
 * @date 2022/05/19 21:35
 **/
@Service
public class DishSerivceImpl extends ServiceImpl<DishMapper, Dish> implements DishService {
}
