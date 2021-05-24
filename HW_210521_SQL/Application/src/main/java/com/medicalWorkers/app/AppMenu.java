package com.medicalWorkers.app;

import com.SQLClassies.CRUDUtil;
import com.SQLClassies.Worker;
import com.medicalWorkers.app.Exceptions.WrongCommandException;

import java.util.List;
import java.util.regex.Pattern;

public class AppMenu {
    static String action(String command) throws WrongCommandException {
        Pattern pattern = Pattern.compile("\\s*(get|insert|update position|" +
                "update department|update years|delete|help|exit)\\s*.*");
        if (!command.matches(pattern.pattern())) return "Невеная команда.";

        String[] splitCommand = command.trim().split("\\s+");
        switch (splitCommand[0]) {
            case "exit":
                CRUDUtil.closeConnection();
                return "Досвидания.";
            case "help":
                return """
                        Доступные команды:
                        Получить всех работников: get
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
                    CRUDUtil.insertWorker(splitCommand[1], splitCommand[2], splitCommand[3],
                            splitCommand[4], Integer.parseInt(splitCommand[5]));
                    return "Работник " + splitCommand[1] + " " + splitCommand[2] + " добавлен.";
                } else {
                    throw new WrongCommandException("Ошибка добавления работника.");
                }
            case "update":
                if (splitCommand.length == 4) {
                    return update(splitCommand[1], Integer.parseInt(splitCommand[2]), splitCommand[3]);
                } else {
                    throw new WrongCommandException("Ошибка изменения работника.");
                }
            case "delete":
                CRUDUtil.deleteWorker(Integer.parseInt(splitCommand[1]));
                return "Работник с id " + splitCommand[1] + " удалён.";
        }
        throw new WrongCommandException("Что-то пошло не так.");
    }

    static String update(String updateParametr, int id, String parametr) throws WrongCommandException {
        switch (updateParametr) {
            case "position":
                CRUDUtil.updateWorkerPosition(id, parametr);
                return "Работнику с id " + id + " обновлена должность.";
            case "department":
                CRUDUtil.updateWorkerDepartment(id, parametr);
                return "Работнику с id " + id + " обновлено отделение.";
            case "years":
                CRUDUtil.updateWorkerYears(id, Integer.parseInt(parametr));
                return "Работнику с id " + id + " обновлен возраст.";
        }
        throw new WrongCommandException("Что-то пошло не так с обновлением.");
    }

    static String get(String[] splitCommand) throws WrongCommandException {
        if (splitCommand.length == 1) {
            printWorkers(CRUDUtil.getAllWorkers(0));
            return "Вот все медицинские работники";
        }
        switch (splitCommand[1]) {
            case "id":
                printWorkers(CRUDUtil.getAllWorkers(1));
                return "Вот все медицинские работники, отсортированные по id.";
            case "name":
                printWorkers(CRUDUtil.getAllWorkers(2));
                return "Вот все медицинские работники, отсортированные по имени.";
            case "surname":
                printWorkers(CRUDUtil.getAllWorkers(3));
                return "Вот все медицинские работники, отсортированные по фамилии.";
            case "position":
                printWorkers(CRUDUtil.getAllWorkers(4));
                return "Вот все медицинские работники, отсортированные по должности.";
            case "department":
                printWorkers(CRUDUtil.getAllWorkers(5));
                return "Вот все медицинские работники, отсортированные по отделению.";
            case "years":
                printWorkers(CRUDUtil.getAllWorkers(6));
                return "Вот все медицинские работники, отсортированные по возрасту.";
            default:
                if (splitCommand.length == 3) {
                    printWorkers(CRUDUtil.getWorker(splitCommand[1], splitCommand[2]));
                    return "Вот найденные совпадения.";
                }
        }
        throw new WrongCommandException("Что-то пошло не так c получением работников.");
    }

    static void printWorkers(List<Worker> workers) {
        if (workers != null && !workers.isEmpty()) {
            for (Worker w : workers) System.out.println(w);
        }
    }
}
