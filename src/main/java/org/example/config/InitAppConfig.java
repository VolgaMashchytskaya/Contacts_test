package org.example.config;

import org.example.Application;
import org.example.services.*;
import org.springframework.context.annotation.*;

import java.io.IOException;

@ComponentScan("org.example")
@Configuration
@PropertySource("classpath:application.properties")
@Profile("init")
public class InitAppConfig {

    @Bean
    public PhonebookTransactions phonebookTransactions(){
        return new InitPhonebookTransaction();
    }


    @Bean
    public Application application(){
        System.out.println("init app");
        return new Application(phonebookTransactions());
    }


    @Bean
    public ContactSaverToFile contactSaverToFile(){
        return new ContactSaverToFile();
    }

    @Bean
    public ContactsUploaderFromFile contactsUploaderFromFile() throws IOException {
        return new ContactsUploaderFromFile();
    }

}
