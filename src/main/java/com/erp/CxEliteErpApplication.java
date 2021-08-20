package com.erp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@EnableTransactionManagement
public class CxEliteErpApplication {

    public static void main(String[] args) {
        SpringApplication.run(CxEliteErpApplication.class, args);
    }

}
