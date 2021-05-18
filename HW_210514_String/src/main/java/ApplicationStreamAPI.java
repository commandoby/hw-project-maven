import Exceptions.WrongCommandException;

import java.util.ArrayList;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ApplicationStreamAPI {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        boolean active = true;
        String command;
        while (active) {
            System.out.print("Введине команду (ip, data, http, exit): ");
            command = scanner.nextLine().trim();
            try {
                switch (command) {
                    case "exit":
                        active = false;
                        break;
                    case "ip":
                        System.out.print("Введите IP адрес (пример 172.168.0.4): ");
                        System.out.println(isIp(scanner.nextLine().trim()));
                        break;
                    case "data":
                        System.out.print("Введите дату в формате \'YYYY/MM/DD HH:MM:SS\': ");
                        System.out.println(isData(scanner.nextLine().trim()));
                        break;
                    case "http":
                        System.out.print("Введите ссылку (например https://teachmeskills.by/): ");
                        System.out.println(isHttp(scanner.nextLine().trim()));
                        break;
                    default:
                        throw new WrongCommandException("Неверная команда: " + command);
                }
            } catch (WrongCommandException e) {
                System.out.println(e);
            }
        }
    }

    static String isIp(String ip) {
        Pattern pattern = Pattern.compile("[.]");
        ArrayList<Integer> ipNumbers = new ArrayList<>();
        for (String s : pattern.split(ip)) {
            try {
                ipNumbers.add(Integer.valueOf(s));
            } catch (NumberFormatException e) {
                return "Неверный формат IP адреса: неправильные символы.";
            }
        }
        if (ipNumbers.size() != 4)
            return "Неверный формат IP адреса: неверный набор чисел.";
        if (ipNumbers.stream().anyMatch(number -> number > 255 || number < 0))
            return "Неверный формат IP адреса: неверный размер чисел.";
        return "IP Адрес \'" + ip + "\' верен.";
    }

    static String isData(String data) {
        Pattern pattern = Pattern.compile("(\\s|/|:)");
        ArrayList<Integer> dataList = new ArrayList<>();
        for (String s : pattern.split(data)) {
            try {
                dataList.add(Integer.valueOf(s));
            } catch (NumberFormatException e) {
                return "Неверный формат символов даты.";
            }
        }
        if (dataList.size() != 6) return "Неверный формат даты.";
        if (dataList.stream().anyMatch(day -> day < 0))
            return "Неверный формат даты: отрицательное значение.";
        if (dataList.get(1) > 12) return "Неверный формат даты: неверный месяц.";
        if (dataList.get(2) > 31) return "Неверный формат даты: неверный день.";
        if (dataList.get(3) >= 24) return "Неверный формат даты: неверный час.";
        if (dataList.get(4) >= 60) return "Неверный формат даты: неверная минута.";
        if (dataList.get(5) >= 60) return "Неверный формат даты: неверная секунда.";

        return "Дата \'" + data + "\' верна.";
    }

    static String isHttp(String link) {
        Pattern pattern = Pattern.compile("\\s*https?://.{2,}\\.(com|ru|by)/?");
        if (link.matches(pattern.pattern())) {
            return "Ссылка верна.";
        }
        return "Ссылка не верна.";
    }
}
