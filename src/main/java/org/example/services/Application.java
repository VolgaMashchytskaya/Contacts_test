package org.example;

import org.example.services.PhonebookTransactions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Scanner;


@Component
public class Application {

    private PhonebookTransactions phonebookTransactions;

    @Autowired
    public Application(PhonebookTransactions phonebookTransactions) {
        this.phonebookTransactions = phonebookTransactions;
    }

    private boolean appIsEnabled = true;

    public void start() {
        while (appIsEnabled) {
            System.out.println("Выберите операцию для работы с контактами: " + "\n" + "1 - Показать все контакты" + "\n" + "2- Найти контакт по имени" + "\n" + "3- Добавить контакт" +
                    "\n" + "4- Удалить контакт по email" + "\n" + "5- Сохранить контакты в файл" + "\n" + "6 - Выйти из приложения");

            int input = readUserInputFromConsole();

            switch (input) {
                case 1: {
                    phonebookTransactions.showAllContacts();
                    break;
                }
                case 2: {
                    phonebookTransactions.findContactByName();
                    break;
                }
                case 3: {
                    phonebookTransactions.addContactToList();
                    break;
                }
                case 4: {
                    phonebookTransactions.deleteContactByMail();
                    break;
                }
                case 5: {
                    phonebookTransactions.saveContactsToFile();
                    break;
                }
                case 6: {
                    return;
                }
                default: {
                    System.out.println("Введены неверные данные попробуйте еще раз!");
                }
            }
        }
    }

    public int readUserInputFromConsole() {
        try {
            //BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
            //int userInput = Integer.parseInt(bufferedReader.readLine());
            Scanner console = new Scanner(System.in);
            return console.nextInt();
        } catch (Throwable e) {
            System.out.println("Ошибка чтения с консоли !");
            return 0;
        }
    }
}

