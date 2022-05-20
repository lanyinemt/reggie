package com.itheima.reggie.common;

/**
 * 用于保持和获取用户的id, 作用范围是一个线程之内
 * @author yin
 * @date 2022/05/18 22:07
 **/
public class BaseContext {
    private static ThreadLocal<Long> threadLocal = new ThreadLocal<>();

    public static void setCurrentId(Long id){
        threadLocal.set(id);
    }

    public static Long getCurrentId(){
        return threadLocal.get();
    }
}
