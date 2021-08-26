package com.michilay.service;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@Service
public class ScheduledService {

//    秒 分 时 日 月 周几
//
    @Scheduled(cron = "0/2 * * * * ?")
    public void hello(){
        System.out.println("hello被执行了");
    }
}
