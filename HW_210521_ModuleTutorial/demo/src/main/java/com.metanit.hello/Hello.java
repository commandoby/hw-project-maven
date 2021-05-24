package com.metanit.hello;

import javax.swing.JOptionPane;

import com.metanit.factorial.Factorial;

public class Hello {
    public static void main(String[] args) {
        for (int i = 0; i < 11; i++){
            int x = Factorial.calculate(i);
            String text = "Factorial of " + i + " is equal to " + x + ".";
            JOptionPane.showMessageDialog(null, text);
        }
    }
}
