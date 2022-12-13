package com.design.observer.pojo;

/**
 * @author yzy
 * @create 2022-12-13-22:16
 */
public class ObserverPhone implements Observer{
    @Override
    public void doThing() {
        System.out.println("发送短信");
    }
}
