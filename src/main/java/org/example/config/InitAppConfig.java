package org.example.config;

import org.example.Application;
import org.example.services.*;
import org.springframework.context.annotation.*;

import java.io.IOException;

@Configuration
@PropertySource("application-init.properties")
@Profile("init")
@ComponentScan("org.example")
public class InitAppConfig {

    @Bean
    public PhonebookTransactions phonebookTransactions(){
        System.out.println("init app");
        return new InitPhonebookTransaction();
    }


    @Bean
    public Application application(){
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
