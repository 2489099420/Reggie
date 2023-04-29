package com.gltedu.common;

/**
 * @author 巩乐天
 * @version 1.0
 */
/*
* 基于ThreadLocal封装工具类 用户保存和获取当前登录用户id
* */
public class BaseContext {
    private static ThreadLocal<Long> threadLocal = new ThreadLocal<Long>();

    public static void setCurrentID(long id) {
        threadLocal.set(id);
    }

    public static Long getCurrentId() {
        return threadLocal.get();
    }
}
