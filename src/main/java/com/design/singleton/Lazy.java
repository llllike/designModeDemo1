package com.design.singleton;

/**
 * @author yzy
 * @create 2022-12-13-22:32
 */
public class Lazy {
    private static Lock lock;
    public static Lock getLock(){
        if (lock==null){
            lock=new Lock();
            return lock;
        }else {
            return lock;
        }
    }
}
