package com.design.strategy.factory;

import com.design.strategy.pay.AliPay;
import com.design.strategy.pay.BankCardPay;
import com.design.strategy.pay.Pay;
import com.design.strategy.pay.WechatPay;
import java.util.HashMap;
import java.util.Map;

public class PayFactory {
    private static final Map<String,Pay> METHOD = new HashMap<>();
    static {
        METHOD.put(MethodKey.ALIPAY,new AliPay());
        METHOD.put(MethodKey.WECHAT_PAY,new WechatPay());
        METHOD.put(MethodKey.BANKCARD_PAY,new BankCardPay());
    }
    private interface MethodKey {
        String ALIPAY = "ALIPAY";
        String WECHAT_PAY = "WECHAT_PAY";
        String BANKCARD_PAY = "BANKCARD_PAY";
    }
    // 根据不同的key，返回对应的支付方式
    public static Pay getMethod(String key){
        if(METHOD.containsKey(key)){
            return METHOD.get(key);
        }
        return new AliPay();
    }
}