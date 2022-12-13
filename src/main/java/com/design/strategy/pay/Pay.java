package com.design.strategy.pay;

/**
 * @author yzy
 * @create 2022-12-13-19:02
 */
public interface Pay {
    String getName();
    Double queryBalance(String uid);
    // 默认的支付方法
    default void pay(String uid,Double price){
        Double currentAmount  = queryBalance(uid);
        if(currentAmount < price){
            System.out.println(uid + " 余额不足");
        }else{
            System.out.println(uid + " 支付成功");
        }
    }
}
