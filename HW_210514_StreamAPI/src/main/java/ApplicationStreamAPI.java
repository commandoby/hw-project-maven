import Exeptions.UncorrectedCommandException;

import java.util.ArrayList;
import java.util.Scanner;

public class ApplicationStreamAPI {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        boolean active = true;
        String command;
        while (active) {
            System.out.print("Введине команду (ip, data, http, exit): ");
            command = scanner.nextLine();
            try {
                switch (command) {
                    case "exit":
                        active = false;
                        break;
                    case "ip":
                        System.out.print("Введите IP адрес (пример 172.168.0.4): ");
                        System.out.println(isIp(scanner.nextLine().trim()));
                    default:
                        throw new UncorrectedCommandException("Неверная команда.");
                }
            } catch (UncorrectedCommandException e) {
                System.out.println(e);
            }
        }
    }

    static String isIp(String text) {
        ArrayList<Integer> ipNumbers = new ArrayList<>();
        for (String s : text.split("\\.")) {
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
        return "IP Адрес верен.";
    }
}
