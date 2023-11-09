package org.example;


import org.example.config.DefaultAppConfig;
import org.example.config.InitAppConfig;
import org.example.services.ProfileSwitch;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.support.PropertiesLoaderUtils;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.Properties;

public class Main {

    public static void main(String[] args) {
        ProfileSwitch profileSwitch = new ProfileSwitch();

        profileSwitch.profileSwitch();
        ApplicationContext applicationContext = new AnnotationConfigApplicationContext(DefaultAppConfig.class, InitAppConfig.class);
        applicationContext.getBean("application", org.example.Application.class).start();
    }
}