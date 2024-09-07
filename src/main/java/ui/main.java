package ui;

import calc.SimpleCalc;

public class main {
    public static void main(String[] args) {
        SimpleCalc calc = SimpleCalc.getInstance();
        String input = "3+-3+-(5+5)";
        System.out.println("calc.calc(input) = " + calc.calc(input));
    }
}
