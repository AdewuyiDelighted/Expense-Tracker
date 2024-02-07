package org.example;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Date;

@SpringBootApplication
@EnableScheduling
public class Main {
    public static void main(String[] args) {
        SpringApplication.run(Main.class, args);
        System.out.println("Hello world!");
    }

//    @Scheduled(fixedDelay = 5000L)
//    public static void testCode() {
//        System.out.println("My fixedDelay schedule " + LocalTime.now());
//    }
//    @Scheduled(fixedRate = 5000L)
//    public static void readCode(){
//        System.out.println("My fixedRate schedule "+ LocalTime.now());
//    }
//    @Scheduled(cron = "0 0 0 * * *")
//    public static void readCodeCron(){
//        System.out.println("My fixedRate schedule "+ LocalTime.now());
//    }



}