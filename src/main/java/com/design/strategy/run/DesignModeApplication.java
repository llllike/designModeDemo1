package com.design.strategy.run;

import com.design.strategy.pojo.Order;
/**
 * @author yzy
 * @create 2022-12-13-18:44
 */
public class DesignModeApplication {
    public static void main(String[] args) {
        Order order = new Order("1",100);
        order.pay("ALIPAY");
        order.pay("WECHAT_PAY");
        order.pay("BANKCARD_PAY");
    }
}
