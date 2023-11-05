package org.example;


import org.example.config.DefaultAppConfig;
import org.example.config.InitAppConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.stereotype.Component;

@Component
public class Main {
    public static void main(String[] args) {
//        ClassPathResource resource = new ClassPathResource("application.properties");
//        Properties properties = null;
//        try {
//            properties = PropertiesLoaderUtils.loadProperties(resource);
//        } catch (IOException e) {
//            System.out.println("Ошибка чтения проперти файла");
//        }
//        String activeProfile = properties.getProperty("spring.profiles.active");
//        System.setProperty("spring.profiles.active", activeProfile);
        ApplicationContext applicationContext = new AnnotationConfigApplicationContext(DefaultAppConfig.class, InitAppConfig.class);
        applicationContext.getBean("application", org.example.Application.class).start();
    }
}