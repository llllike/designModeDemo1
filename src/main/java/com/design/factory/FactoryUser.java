package com.design.factory;

import com.design.factory.pojo.DefaultUser;
import com.design.factory.pojo.Student;
import com.design.factory.pojo.Teacher;
import com.design.factory.pojo.User;

import java.util.HashMap;

/**
 * @author yzy
 * @create 2022-12-13-22:25
 */
public class FactoryUser {
    public User getUser(Integer type){
        switch (type){
            case 1:
                return new Student();
            case 2:
                return new Teacher();
            default:
                return new DefaultUser();
        }
    }
}
