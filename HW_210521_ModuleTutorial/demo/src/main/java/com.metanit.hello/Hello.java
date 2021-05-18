package com.metanit.hello;

import javax.swing.JOptionPane;

import com.metanit.factorial.Factorial;

public class Hello {
    public static void main(String[] args) {
        //System.out.println("Hello Demo Module!");
        //JOptionPane.showMessageDialog(null, "Hello Demo Module!");
        int a = 5;
        int b = Factorial.calculate(a);
        String text = "Factorial of " + a + " is equal to " + b + ".";
        JOptionPane.showMessageDialog(null, text);
    }
}
