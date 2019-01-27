package com.pahlsoft.example.elasticapm.hello;


import co.elastic.apm.api.ElasticApm;
import org.h2.constant.ErrorCode;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.sql.DatabaseMetaData;
import java.sql.SQLException;

@RestController
public class GreetingController {

    @RequestMapping("/submitOrder")
    public String submitOrder() {
        ElasticApm.currentTransaction().setUser("kimchy","support@elastic.co","dudeness");
        try {
            DBCaller.init();
        } catch (Exception e) {
            ElasticApm.captureException(e);
        }
        return "Thanks for your order";
    }

    @RequestMapping("/login")
    public String login() {
        ElasticApm.currentTransaction().setUser("kimchy","support@elastic.co","dudeness");
        try {
            DBCaller.init();
        } catch (Exception e) {
            ElasticApm.captureException(e);
        }
        return "Welcome back, please... buy something.";
    }

    @RequestMapping("/reviewCart")
    public String reviewCart() {
        ElasticApm.currentTransaction().setUser("kimchy","support@elastic.co","dudeness");
        try {
            DBCaller.init();
        } catch (Exception e) {
            ElasticApm.captureException(e);
        }
        return "There's nothing in your cart";
    }

    @RequestMapping("/somethingBad")
    public String somethingBad() {
        ElasticApm.currentTransaction().setUser("kimchy","support@elastic.co","dudeness");
        try {
            throw new SQLException("Bad SQL Stuff", "25", new SQLException());
        } catch (Exception e) {
            ElasticApm.captureException(e);
        }
        return "Something Bad Happened.";
    }
}