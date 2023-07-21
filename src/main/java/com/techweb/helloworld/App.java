package com.techweb.helloworld;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

import com.crypto88.dbcore.dto.Account;
import com.crypto88.dbcore.service.DbCoreServices;

@SpringBootApplication
public class App {

    @Autowired
    private DbCoreServices dbCoreServices;

    public static void main(String[] args) {
        ConfigurableApplicationContext context = SpringApplication.run(App.class, args);
        App app = context.getBean(App.class);
        app.executeSampleCode();
        context.close();
    }

    public void executeSampleCode() {
        // Sample code to test the dbcore service
        // Assuming input.getUserId() returns the user ID to search for

        int userId = 1; // Replace this with the actual user ID you want to search for

        Account account = dbCoreServices.getAccountService().findByUserId(userId).stream().findFirst().orElse(null);

        if (account != null) {
            Date maxPricingDt = dbCoreServices.getAccountHoldingService().getMaxPricingDt(account.getId());
            System.out.println("Account ID: " + account.getId());
            System.out.println("Max Pricing Date: " + maxPricingDt);
        } else {
            System.out.println("Account not found for the given user ID.");
        }
    }
}
