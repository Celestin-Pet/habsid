import java.util.Scanner;

public class Calculator {
    public static void main(String[] args) {
        try {
            Scanner scanner = new Scanner(System.in);
            System.out.println("Enter a mathematical expression :");
            String input = scanner.nextLine();
            double result = ArithmeticExpressionEvaluator.evaluateExpression(input);
            System.out.println("Result: " + result);
        } catch (Exception ex) {
            System.out.println("Error: " + ex.getMessage());
        }
    }
}

public class ArithmeticExpressionEvaluator {
    public static double evaluateExpression(String input) {
        String[] operators = new String[]{"+", "-", "*", "/"};
        String[] elements = input.split("[+\\-*/]");
        if (elements.length < 2 || elements.length > 3) {
            throw new IllegalArgumentException("Invalid input format. Please use the correct format.");
        }

        double[] numbers = new double[elements.length];
        for (int i = 0; i < elements.length; i++) {
            try {
                numbers[i] = Double.parseDouble(elements[i]);
                if (numbers[i] < 1 || numbers[i] > 10) {
                    throw new IllegalArgumentException("Invalid number input. Numbers must be integers from 1 to 10.");
                }
            } catch (NumberFormatException e) {
                throw new IllegalArgumentException("Invalid number input. Numbers must be integers from 1 to 10.");
            }
        }

        char op = input.replaceAll("[^+\\-*/]", "").charAt(0);
        if (op == '\0') {
            throw new IllegalArgumentException("Invalid operator.");
        }

        double result = performOperation(numbers, op);
        return result;
    }

    private static double performOperation(double[] numbers, char op) {
        double result = 0;
        switch (op) {
            case '+':
                result = (numbers.length == 3) ? numbers[0] + numbers[1] - numbers[2] : numbers[0] + numbers[1];
                break;
            case '-':
                result = (numbers.length == 3) ? numbers[0] - numbers[1] + numbers[2] : numbers[0] - numbers[1];
                break;
            case '*':
                result = numbers[0] * numbers[1] - numbers[2];
                break;
            case '/':
                result = numbers[0] / numbers[1] * numbers[2];
                break;
            default:
                throw new IllegalArgumentException("Invalid operator.");
        }
        return result;
    }
}
