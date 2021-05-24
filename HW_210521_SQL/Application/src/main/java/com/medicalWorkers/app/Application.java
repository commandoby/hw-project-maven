package com.medicalWorkers.app;

import com.SQLClassies.CRUDUtil;
import com.medicalWorkers.app.Exceptions.WrongCommandException;

import java.util.Scanner;

public class Application {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String command = "";
        boolean active = true;

        CRUDUtil.insertWorker("Ivan", "Ivanov", "doctor", "surgery", 32);
        CRUDUtil.insertWorker("Svetlana", "Sidorova", "doctor", "surgery", 42);
        CRUDUtil.insertWorker("Petr", "Petrov", "therapist", "therapeutic department", 36);
        CRUDUtil.insertWorker("Polina", "Petrova", "nurse", "surgery", 28);
        CRUDUtil.insertWorker("Sidor", "Sidorov", "cleaner", "hospital", 41);

        System.out.println("Добро пожаловать в базу данных медицинских работников.");
        while (active) {
            System.out.print("Введите соманду (get, insert, update, delete, help, exit): ");
            try {
                command = scanner.nextLine();
                String text = AppMenu.action(command);
                System.out.println(text);
                if (text == "Досвидания.") active = false;
            } catch (WrongCommandException e) {
                System.out.println(e.getMessage());
            } catch (NumberFormatException e) {
                System.out.println("Неверный формат числа.");
            } catch (ArrayIndexOutOfBoundsException e) {
                System.out.println("Неверное число параметров.");
            }
        }
    }
}
