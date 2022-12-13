package com.design.singleton;

/**
 * @author yzy
 * @create 2022-12-13-22:37
 */
public class Inside {
    public static class InsideIn{
        public static final Lock LOCK=new Lock();
    }
    public static Lock getLock(){
        return InsideIn.LOCK;
    }
}
