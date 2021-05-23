package com.commandoby.calculator;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Calculator {

    public static int signReader (String text) {
        //Pattern pattern = Pattern.compile("\\s*\\d+\\s*\\W\\s*\\d+\\s*");
        if (!text.matches("\\s*\\d+\\s*\\W\\s*\\d+\\s*")) return 0;
        Pattern pattern = Pattern.compile("\\W");
        Matcher matcher = pattern.matcher(text);
        //switch (matcher.find())
        return 0;
    }

    public String reader(String text) {
        return "";
    }
}
