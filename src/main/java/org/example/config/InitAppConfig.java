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
    public PhonebookTransactions phonebookTransactions() throws IOException {
        return new InitPhonebookTransaction(contactsUploaderFromFile(),contactSaverToFile());
    }


    @Bean
    public Application application() throws IOException {
        System.out.println("init applic");
        return new Application(phonebookTransactions());
    }

    @Bean
    public ContactSaverToFile contactSaverToFile(){
        return new ContactSaverToFile();
    }

    @Bean
    public ContactsUploaderFromFile contactsUploaderFromFile() throws IOException {
        System.out.println("init contactsUploaderFromFile");
        return new ContactsUploaderFromFile();
    }

}
