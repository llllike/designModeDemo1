package com.design.strategy.pojo;

import com.design.strategy.factory.PayFactory;
import com.design.strategy.pay.Pay;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.io.Serializable;

/**
 * @author yzy
 * @create 2022-12-13-18:58
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Order implements Serializable {
    private String uid;
    private double money;
    public void pay(String payType){
        Pay pay = PayFactory.getMethod(payType);
        pay.pay(uid,money);
    }
}
