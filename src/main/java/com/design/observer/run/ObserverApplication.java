package com.design.observer.run;

import com.design.observer.pojo.Observed;
import com.design.observer.pojo.ObserverEmail;
import com.design.observer.pojo.ObserverIM;
import com.design.observer.pojo.ObserverPhone;

/**
 * @author yzy
 * @create 2022-12-13-22:09
 */
public class ObserverApplication {
    public static void main(String[] args) {
        Observed observed = new Observed(1);
        observed.addObserver(new ObserverEmail());
        observed.addObserver(new ObserverIM());
        observed.addObserver(new ObserverPhone());
        observed.handle(1);
    }
}
