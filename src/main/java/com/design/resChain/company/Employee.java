package com.design.resChain.company;

import com.design.resChain.pojo.Request;

/**
 * @author yzy
 * @create 2022-12-13-21:23
 */
public class Employee extends User{
    public Employee() {
        super(1);
    }
    @Override
    public void report(Request request) {
        System.out.println("员工处理请求");
    }
}
