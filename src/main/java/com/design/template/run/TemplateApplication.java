package com.design.template.run;

import com.design.template.cycle.BicycleType1;
import com.design.template.cycle.BicycleType2;

/**
 * @author yzy
 * @create 2022-12-13-21:59
 */
public class TemplateApplication {
    public static void main(String[] args) {
        BicycleType1 bicycleType1 = new BicycleType1();
        BicycleType2 bicycleType2 = new BicycleType2();
        bicycleType1.use();
        System.out.println("=============");
        bicycleType2.use();
    }
}
