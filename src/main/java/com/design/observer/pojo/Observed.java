package com.design.observer.pojo;

import java.util.HashSet;

/**
 * @author yzy
 * @create 2022-12-13-22:09
 */
public class Observed {
    private HashSet<Observer> observers=new HashSet<>();
    private int state;
    public Observed(int state){
        this.state=state;
    }
    public void addObserver(Observer observer){
        observers.add(observer);
    }
    public void handle(int state){
        if (state!=this.state){
            System.out.println("还不需要通知");
        }else {
            observers.forEach(Observer::doThing);
        }
    }
}
