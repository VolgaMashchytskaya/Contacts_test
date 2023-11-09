package org.example.services;

import org.example.config.DefaultAppConfig;
import org.example.config.InitAppConfig;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.support.PropertiesLoaderUtils;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.Properties;

@PropertySource("classpath:application.properties")
public class ProfileSwitch {

    @Value("${spring.profiles.active}")
    static
    String profile;

    public static void profileSwitch (){
        ClassPathResource resource = new ClassPathResource("application.properties");
        Properties properties = null;
        try {
            properties = PropertiesLoaderUtils.loadProperties(resource);
        } catch (IOException e) {
            System.out.println("Ошибка чтения проперти файла");
        }

        String activeProfile = properties.getProperty("spring.profiles.active");
        System.setProperty("spring.profiles.active", activeProfile);

//        AnnotationConfigApplicationContext sp = new AnnotationConfigApplicationContext();
//        sp.getEnvironment().setActiveProfiles("init");
//        sp.register(DefaultAppConfig.class, InitAppConfig.class);
//        sp.refresh();
    }
}
