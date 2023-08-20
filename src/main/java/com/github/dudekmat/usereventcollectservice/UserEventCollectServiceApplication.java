package com.github.dudekmat.usereventcollectservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;

@SpringBootApplication
@EnableAsync
public class UserEventCollectServiceApplication {

  public static void main(String[] args) {
    SpringApplication.run(UserEventCollectServiceApplication.class, args);
  }

}
