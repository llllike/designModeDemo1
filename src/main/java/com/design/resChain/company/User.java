package com.design.resChain.company;

import com.design.resChain.pojo.Request;

import java.util.Objects;

/**
 * @author yzy
 * @create 2022-12-13-21:18
 */
public abstract class User {
    Integer level;
    private User nextUser;
    public User(Integer level){
        // 每个对象差异化处理
        this.level=level;
    }
    public final void handle(Request request){
        if (Objects.equals(request.getLevel(), level)){
            this.report(request);
        }else {
            this.nextUser.handle(request);
        }
    }
    public void setNextUser(User user){
        this.nextUser=user;
    }
    public abstract void report(Request request);
}
