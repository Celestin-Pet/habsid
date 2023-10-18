using System;

public class Calculator
{
    public static void Main(string[] args)
    {
        try
        {
            Console.WriteLine("Enter a mathematical expression:");
            string input = Console.ReadLine();
            double result = ArithmeticExpressionEvaluator.EvaluateExpression(input);
            Console.WriteLine($"Result: {result}");
        }
        catch (Exception ex)
        {
            Console.WriteLine($"Error: {ex.Message}");
        }
    }
}

public class ArithmeticExpressionEvaluator
{
    public static double EvaluateExpression(string input)
    {
        string[] operators = new string[] { "+", "-", "*", "/" };
        string[] elements = input.Split(operators, StringSplitOptions.RemoveEmptyEntries);
        if (elements.Length < 2 || elements.Length > 3)
        {
            throw new ArgumentException("Invalid input format. Please use the correct format.");
        }

        double[] numbers = new double[elements.Length];
        for (int i = 0; i < elements.Length; i++)
        {
            if (!double.TryParse(elements[i], out numbers[i]) || numbers[i] < 1 || numbers[i] > 10)
            {
                throw new ArgumentException("Invalid number input. Numbers must be integers from 1 to 10.");
            }
        }

        char op = input.FirstOrDefault(c => operators.Contains(c.ToString()));
        if (op == '\0')
        {
            throw new ArgumentException("Invalid operator.");
        }

        double result = PerformOperation(numbers, op);
        return result;
    }

    private static double PerformOperation(double[] numbers, char op)
    {
        double result = 0;
        switch (op)
        {
            case '+':
                result = numbers.Length == 3 ? numbers[0] + numbers[1] - numbers[2] : numbers[0] + numbers[1];
                break;
            case '-':
                result = numbers.Length == 3 ? numbers[0] - numbers[1] + numbers[2] : numbers[0] - numbers[1];
                break;
            case '*':
                result = numbers[0] * numbers[1] - numbers[2];
                break;
            case '/':
                result = numbers[0] / numbers[1] * numbers[2];
                break;
            default:
                throw new ArgumentException("Invalid operator.");
        }
        return result;
    }
}
