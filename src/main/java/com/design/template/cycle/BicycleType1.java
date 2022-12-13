package com.design.template.cycle;

/**
 * @author yzy
 * @create 2022-12-13-21:57
 */
public class BicycleType1 extends Bicycle{
    @Override
    public void unlock() {
        System.out.println("扫码开锁");
    }
}
