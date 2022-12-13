package com.design.template.cycle;

/**
 * @author yzy
 * @create 2022-12-13-21:56
 */
public abstract class Bicycle {
    public void unlock(){
        System.out.println("密码开锁");
    }
    public void ride(){
        System.out.println("开始骑行");
    }
    public void use(){
        unlock();
        ride();
    }
}
