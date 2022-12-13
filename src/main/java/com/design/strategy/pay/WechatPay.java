package com.design.strategy.pay;

/**
 * @author yzy
 * @create 2022-12-13-19:02
 */
public class WechatPay implements Pay{

    @Override
    public String getName() {
        return "WECHAT_PAY";
    }

    @Override
    public Double queryBalance(String uid) {
        return 110.0;
    }
}
