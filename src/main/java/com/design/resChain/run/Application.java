package com.design.resChain.run;

import com.design.resChain.company.Boss;
import com.design.resChain.company.Employee;
import com.design.resChain.company.Leader;
import com.design.resChain.pojo.Request;

/**
 * @author yzy
 * @create 2022-12-13-21:26
 */
public class Application {
    public static void main(String[] args) {
        Request request1 = new Request(1);
        Request request2 = new Request(2);
        Request request3 = new Request(3);
        Boss boss = new Boss();
        Employee employee = new Employee();
        Leader leader = new Leader();
        employee.setNextUser(leader);
        leader.setNextUser(boss);

        employee.handle(request1);
        System.out.println("=====");
        employee.handle(request2);
        System.out.println("=====");
        employee.handle(request3);
    }
}
