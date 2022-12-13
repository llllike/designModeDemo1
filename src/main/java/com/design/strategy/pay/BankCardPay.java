package com.design.strategy.pay;

/**
 * @author yzy
 * @create 2022-12-13-19:07
 */
public class BankCardPay implements Pay{
    @Override
    public String getName() {
        return "BANKCARD_PAY";
    }

    @Override
    public Double queryBalance(String uid) {
        return 100.0;
    }
}
