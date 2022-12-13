package com.design.strategy.pay;

/**
 * @author yzy
 * @create 2022-12-13-19:07
 */
public class AliPay implements Pay{
    @Override
    public String getName() {
        return "ALIPAY";
    }

    @Override
    public Double queryBalance(String uid) {
        return 90.0;
    }
}
