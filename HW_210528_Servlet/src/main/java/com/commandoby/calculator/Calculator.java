package com.commandoby.calculator;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

public class Calculator {
    public static final String[] SIGNNAME = {"", "Addition", "Subtraction", "Multiplication", "Division"};

    public static int signReader(String text) {
        Pattern formPattern = Pattern.compile("\\s*\\d+\\s*(\\+|-|\\*|/)\\s*\\d+\\s*");
        if (!text.matches(formPattern.pattern())) return 0;
        String[] signs = text.trim().split("\\d+");
        for (String s : signs) {
            if (s.matches("\\s*\\W\\s*")) {
                switch (s.trim()) {
                    case "+":
                        return 1;
                    case "-":
                        return 2;
                    case "*":
                        return 3;
                    case "/":
                        return 4;
                }
            }
        }
        return 0;
    }

    public static List<Integer> reader(String text) {
        List<Integer> result = new ArrayList<>();
        String[] textResult = text.split("\\W|\\s");
        for (String s : textResult) {
            try {
                if (s.matches("\\d+")) result.add(Integer.valueOf(s));
            } catch (NumberFormatException e) {
                System.out.println(e.getMessage());
            }
        }
        return result;
    }

    public static int result(int sign, int a, int b) {
        switch (sign) {
            case 1:
                return a + b;
            case 2:
                return a - b;
            case 3:
                return a * b;
            case 4:
                try {
                    return a / b;
                } catch (ArithmeticException e) {
                    System.out.println(e.getMessage());
                }
        }
        return 0;
    }
}
