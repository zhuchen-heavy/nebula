package com.nebula.client;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.concurrent.ConcurrentHashMap;

@SpringBootApplication
public class StructureApplication {

    public static void main(String[] args) {
        SpringApplication.run(StructureApplication.class, args);

        ConcurrentHashMap hashMap = new ConcurrentHashMap();
        hashMap.put("1", "2");
    }

}

