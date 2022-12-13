package com.design.singleton;

/**
 * @author yzy
 * @create 2022-12-13-22:34
 */
public class CheckLock {
    private static Lock lock;
    public static Lock getLock(){
        if (lock==null){
            synchronized (CheckLock.class) {
                if (lock == null) {
                    lock=new Lock();
                    return lock;
                }
            }
        }
        return lock;
    }
}
