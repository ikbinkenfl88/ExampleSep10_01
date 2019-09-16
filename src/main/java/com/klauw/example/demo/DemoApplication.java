package com.klauw.example.demo;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectReader;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;
import org.springframework.context.ConfigurableApplicationContext;

import java.util.concurrent.TimeUnit;

/*
 * Hystrixx demo
 */
@SpringBootApplication
//@EnableEurekaServer
@EnableCircuitBreaker
public class DemoApplication {

    public static void main(String[] args) throws InterruptedException {
        ConfigurableApplicationContext ctx = SpringApplication.run(DemoApplication.class, args);

        MyService myService = ctx.getBean(MyService.class);

//        System.out.println("-- calling doSomething(2) --");
//        myService.doSomething(2);
//        System.out.println("-- calling doSomething(0) --");
//        myService.doSomething(0);
//        System.out.println("-- calling doSomething(5) --");
//        myService.doSomething(5);
//        System.out.println("-- calling doSomething2(2) --");
//        myService.doSomething2(2);

        System.out.println("-- calling doSomething(1) 40 times --");
        int n = 40;
        for (int i = 0; i < n; i++) {
            myService.doSomething(i < (n * 0.6) ? 0 : 2);
            TimeUnit.MILLISECONDS.sleep(100);
        }
        TimeUnit.SECONDS.sleep(6);

        System.out.println("-- final call --");
        myService.doSomething(2);

    }

}
