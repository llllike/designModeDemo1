package com.design.singleton;

/**
 * @author yzy
 * @create 2022-12-13-22:31
 */
public class Hungry {
    private static Lock lock=new Lock();
    public static Lock getLock(){
        return lock;
    }
}
