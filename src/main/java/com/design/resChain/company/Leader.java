package com.design.resChain.company;

import com.design.resChain.pojo.Request;

/**
 * @author yzy
 * @create 2022-12-13-21:24
 */
public class Leader extends User{
    public Leader() {
        super(2);
    }
    @Override
    public void report(Request request) {
        System.out.println("组长处理请求");
    }
}
