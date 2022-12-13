package com.design.resChain.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.io.Serializable;

/**
 * @author yzy
 * @create 2022-12-13-21:16
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Request implements Serializable {
    // 请求等级
    private Integer level;
}
