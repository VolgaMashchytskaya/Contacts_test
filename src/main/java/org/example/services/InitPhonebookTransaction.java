package org.example.services;

import org.example.entity.Contact;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

@Component
public class InitPhonebookTransaction implements PhonebookTransactions {

    public InitPhonebookTransaction(ContactsUploaderFromFile contactsUploaderFromFile, ContactSaverToFile contactSaverToFile) {
        this.contactsUploaderFromFile = contactsUploaderFromFile;
        this.contactSaverToFile = contactSaverToFile;
    }

    ContactsUploaderFromFile contactsUploaderFromFile;
    ContactSaverToFile contactSaverToFile;

    List<Contact> contacts = new ArrayList<>();


    public void showAllContacts() {
        System.out.println("Имеющиеся контакты: ");
        contactsUploaderFromFile.uploadContactsFromFile().forEach(contact-> {
            System.out.println("ФИО - " + contact.getFullName());
            System.out.println("Номер телефона -" + contact.getPhoneNumber());
            System.out.println("Почта - " + contact.getEmail());
        });
    }

    public void findContactByName() {
        System.out.println("Введите имя:_____ ");
        Scanner console = new Scanner(System.in);
        String name = console.nextLine();
        System.out.println("Найдены контакты " + name + " : ");
        contacts=contactsUploaderFromFile.uploadContactsFromFile();
        List<Contact> filtered = contacts.stream().filter(contact -> contact.getFullName().contains(name)).toList();
        if (filtered.isEmpty()) {
            System.out.println("Контакты не найдены");
        } else {
            filtered.forEach(contact -> {
                System.out.println("ФИО - " + contact.getFullName());
                System.out.println("Номер телефона -" + contact.getPhoneNumber());
                System.out.println("Почта - " + contact.getEmail());
            });
        }
    }

    public void addContactToList() {
        Scanner console = new Scanner(System.in);
        System.out.print("Введите Ф. И. О.; номер телефона; адрес электронной почты.: ");
        String contactToAdd = console.nextLine();
        String[] data = contactToAdd.split(";");
        Contact contact = new Contact(data[0], data[1], data[2]);
        contacts=contactsUploaderFromFile.uploadContactsFromFile();
        boolean contactIsPresent  = contacts.stream().anyMatch(c -> c.getFullName().equals(contact.getFullName()) && c.getPhoneNumber().equals(contact.getPhoneNumber()) && c.getEmail().equals(contact.getEmail()));
        if(contactIsPresent){
            System.out.println("Такой контакт уже сохранен");
        }else {
            contacts.add(contact);
            System.out.println("Контакт успешно добавлен");
        }

    }

    public void deleteContactByMail() {
        Scanner console = new Scanner(System.in);
        System.out.println("Введите почту ");
        String email = console.nextLine();
        contacts=contactsUploaderFromFile.uploadContactsFromFile();

        if (contacts.removeIf(contact -> contact.getEmail().equals(email))) {
            contactSaverToFile.saveDataToFile(contacts);
            System.out.println("Контакт успешно удален !");
        } else {
            System.out.println("Контакт для удаления не найден");
        }
    }

    public void saveContactsToFile(){
        contactSaverToFile.saveDataToFile(contacts);
    }
}