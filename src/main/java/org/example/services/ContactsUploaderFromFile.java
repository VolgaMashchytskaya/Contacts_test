package org.example.services;

import org.example.entity.Contact;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

@Component
public class ContactsUploaderFromFile {
    @Value("${contacts.path.download}")
    String pathToUpload;

    public ContactsUploaderFromFile() throws IOException {
    }

    public List<Contact> uploadContactsFromFile() {
        List<Contact> contacts = new ArrayList<>();
        try {

            BufferedReader bufferedReader = new BufferedReader(new FileReader(pathToUpload));
            String line = bufferedReader.readLine();
            while ((line = bufferedReader.readLine()) != null) {
                String[] data = line.split(";");
                contacts.add(new Contact(data[0], data[1], data[2]));
            }
        } catch (FileNotFoundException e) {
            System.out.println("Не удалось найти файл, проверьте правильность пути !");
        } catch (IOException e) {
            System.out.println("Не удалось считать файл ошибка во время чтения файла !");
        }
        return contacts;

    }
}