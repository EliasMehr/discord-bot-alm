package Handlers;

import java.text.DecimalFormat;

public class MathHandlers {

    public boolean isOperator(String str) {
        if(str.length() == 1) {
            return switch (str) {
                case "+", "-", "*", "/" -> true;
                default -> false;
            };
        }
        return false;
    }

    public String calcResult(String[] args) {
        double firstNumber = Double.parseDouble(args[1]);
        double secondNumber = Double.parseDouble(args[3]);
        double result = 0;
        switch(args[2]) {
            case "+" -> result = firstNumber + secondNumber;
            case "-" -> result = firstNumber - secondNumber;
            case "/" -> result = firstNumber / secondNumber;
            case "*" -> result = firstNumber * secondNumber;
        }
        DecimalFormat format = new DecimalFormat("0.#");
        return format.format(result);
    }


}
