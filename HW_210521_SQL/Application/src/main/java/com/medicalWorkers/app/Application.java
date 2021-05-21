package com.medicalWorkers.app;

import com.SQLClassies.CRUDClass;
import com.SQLClassies.Worker;
import com.medicalWorkers.app.Exceptions.WrongCommandException;

import java.util.List;
import java.util.Scanner;
import java.util.regex.Pattern;

public class Application {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String command = "";
        boolean active = true;

        CRUDClass.insertWorker("Ivan", "Ivanov", "doctor", "surgery", 32);
        CRUDClass.insertWorker("Svetlana", "Sidorova", "doctor", "surgery", 42);
        CRUDClass.insertWorker("Petr", "Petrov", "therapist", "therapeutic department", 36);
        CRUDClass.insertWorker("Polina", "Petrova", "nurse", "surgery", 28);
        CRUDClass.insertWorker("Sidor", "Sidorov", "cleaner", "hospital", 41);

        System.out.println("Добро пожаловать в базу данных медицинских работников.");
        while (active) {
            System.out.print("Введите соманду (get, insert, update, delete, help, exit): ");
            try {
                command = scanner.nextLine();
                String text = action(command);
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

    static String action (String command) throws WrongCommandException {
        Pattern pattern = Pattern.compile("\\s*(get|insert|update position|" +
                "update department|update years|delete|help|exit)\\s*.*");
        if (!command.matches(pattern.pattern())) return "Невеная команда.";

        String[] splitCommand = command.trim().split("\\s+");
        switch (splitCommand[0]) {
            case "exit":
                CRUDClass.closeConnection();
                return "Досвидания.";
            case "help":
                return """
                        Доступные команды:
                        Получить всех медработников: get
                        Получить всех работников и отсортировать: get id/name/surname/position/department/years
                        Получить конкретного работника: get <имя> <фамилия>
                        Добавить работника: insert <имя> <фамилия> <должность> <отделение> <возраст>
                        Изменить работнику должность: update position <id> <новая должность>
                        Изменить работнику отделение: update department <id> <новое отделение>
                        Изменить работнику возраст: update years <id> <новый возраст>
                        Удалить работника: delete <id>
                        Попасть сюда: help
                        Выход: exit""";
            case "get":
                return get(splitCommand);
            case "insert":
                if (splitCommand.length == 6) {
                    CRUDClass.insertWorker(splitCommand[1], splitCommand[2], splitCommand[3],
                            splitCommand[4], Integer.parseInt(splitCommand[5]));
                    return "Работник " + splitCommand[1] + " " + splitCommand[2] + " добавлен.";
                } else {
                    //return "Ошибка добавления работника.";
                    throw new WrongCommandException("Ошибка добавления работника.");
                }
            case "update":
                if (splitCommand.length == 4) {
                    switch (splitCommand[1]) {
                        case "position":
                            CRUDClass.updateWorkerPosition(Integer.parseInt(splitCommand[2]), splitCommand[3]);
                            return "Работнику с id " + splitCommand[2] + " обновлена должность.";
                        case "department":
                            CRUDClass.updateWorkerDepartment(Integer.parseInt(splitCommand[2]), splitCommand[3]);
                            return "Работнику с id " + splitCommand[2] + " обновлено отделение.";
                        case "years":
                            CRUDClass.updateWorkerYears(Integer.parseInt(splitCommand[2]), Integer.parseInt(splitCommand[3]));
                            return "Работнику с id " + splitCommand[2] + " обновлен возраст.";
                    }
                } else {
                    //return "Ошибка изменения работника.";
                    throw new WrongCommandException("Ошибка изменения работника.");
                }
            case "delete":
                CRUDClass.deleteWorker(Integer.parseInt(splitCommand[1]));
                return "Работник с id " + splitCommand[1] + " удалён.";
        }
        //return "Что-то пошло не так.";
        throw new WrongCommandException("Что-то пошло не так.");
    }

    static String get(String[] splitCommand) throws WrongCommandException {
        if (splitCommand.length == 1) {
            printWorkers(CRUDClass.getAllWorkers(0));
            return "Вот все медицинские работники";
        }
        switch (splitCommand[1]) {
            case "id":
                printWorkers(CRUDClass.getAllWorkers(1));
                return "Вот все медицинские работники, отсортированные по id.";
            case "name":
                printWorkers(CRUDClass.getAllWorkers(2));
                return "Вот все медицинские работники, отсортированные по имени.";
            case "surname":
                printWorkers(CRUDClass.getAllWorkers(3));
                return "Вот все медицинские работники, отсортированные по фамилии.";
            case "position":
                printWorkers(CRUDClass.getAllWorkers(4));
                return "Вот все медицинские работники, отсортированные по должности.";
            case "department":
                printWorkers(CRUDClass.getAllWorkers(5));
                return "Вот все медицинские работники, отсортированные по отделению.";
            case "years":
                printWorkers(CRUDClass.getAllWorkers(6));
                return "Вот все медицинские работники, отсортированные по возрасту.";
            default:
                if (splitCommand.length == 3) {
                    printWorkers(CRUDClass.getWorker(splitCommand[1], splitCommand[2]));
                    return "Вот найденные совпадения.";
                }
        }
        //return "Что-то пошло не так c получением работников.";
        throw new WrongCommandException("Что-то пошло не так c получением работников.");
    }

    static void printWorkers(List<Worker> workers) {
        if (workers != null && !workers.isEmpty()) {
            for (Worker w : workers) System.out.println(w);
        }
    }
}
