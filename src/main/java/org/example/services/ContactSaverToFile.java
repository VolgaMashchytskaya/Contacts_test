package org.example.services;

import org.example.entity.Contact;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;


import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

@Component
public class ContactSaverToFile {
    @Value("${contacts.path.save}")
    private String pathToSave;

    public void saveDataToFile(List<Contact> contacts) {
        try {
            BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(pathToSave));
            for (int i = 0; i < contacts.size(); i++) {
                if (i == contacts.size() - 1) {
                    bufferedWriter.write(contacts.get(i).getFullName() + ";" + contacts.get(i).getPhoneNumber() + ";" + contacts.get(i).getEmail());
                } else {
                    bufferedWriter.write(contacts.get(i).getFullName() + ";" + contacts.get(i).getPhoneNumber() + ";" + contacts.get(i).getEmail() + "\n");
                }
            }
            bufferedWriter.flush();
            System.out.println("Файл успешно сохранен");
        } catch (IOException e) {
            System.out.println("Не удалось найти файл, проверьте правильность пути !");
        }
    }
}