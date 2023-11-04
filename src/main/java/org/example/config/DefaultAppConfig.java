package org.example.config;

import org.example.Application;
import org.example.services.ContactSaverToFile;
import org.example.services.ContactsUploaderFromFile;
import org.example.services.DefaultPhonebookTransaction;
import org.example.services.PhonebookTransactions;
import org.springframework.context.annotation.*;

import java.io.IOException;

@ComponentScan("org.example")
@Configuration
@PropertySource("classpath:application.properties")
@Profile("default")
public class DefaultAppConfig {

    @Bean
    public PhonebookTransactions phonebookTransactions(){
        return new DefaultPhonebookTransaction();
    }

    @Bean
    public Application application(){
        System.out.println("Default app");
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
