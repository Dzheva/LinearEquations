package org.example;

import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;
import java.util.Stack;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static org.example.LexemeReader.expr;
import static org.example.LexemeReader.lexAnalyze;

public class Service {

    private final Repository repository = new Repository();

    public static boolean isValidMathEquation(String equation) {
        String regex = "^(?!.*[-+*/]{2,})[-+*/\\d.x()=]+$";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(equation);

        return matcher.matches();
    }

    public static boolean isValidParentheses(String equation) {
        Stack<Character> stack = new Stack<>();

        for (char ch : equation.toCharArray()) {
            if (ch == '(') {
                stack.push(ch);
            } else if (ch == ')') {
                if (stack.isEmpty() || stack.pop() != '(') {
                    return false;
                }
            }
        }

        return stack.isEmpty();
    }

    public static boolean isCorrectRoot(String equation, double x) {
        String[] sides = equation.split("=");

        if (sides.length != 2) {
            System.out.println("Incorrect equation format.");
            return false;
        }

        double leftSide = evaluateExpression(sides[0], x);
        double rightSide = evaluateExpression(sides[1], x);
        double epsilon = 1e-9;

        if (Math.abs(leftSide - rightSide) < epsilon) {
            return true;
        } else {
            return false;
        }
    }

    public static double evaluateExpression(String expression, double x) {
        expression = expression.replace("x", String.valueOf(x));
        List<LexemeReader.Lexeme> lexemes = lexAnalyze(expression);
        LexemeReader.LexemeBuffer lexemeBuffer = new LexemeReader.LexemeBuffer(lexemes);
        var result = expr(lexemeBuffer);

        return result;
    }

    public void createEquation(String equation) {
        if (isValidParentheses(equation)) {
            System.out.println("The placement of parentheses in the equation is correct.");
            if (isValidMathEquation(equation)) {
                System.out.println("A valid mathematical expression has been entered.");
                repository.saveEquation(equation);
                System.out.println("The equation has saved in the database.");

                checkRoot(equation);
            } else {
                System.out.println("An incorrect mathematical expression was entered.");
            }
        } else {
            System.out.println("The placement of parentheses in the equation is incorrect.");
        }
    }

    public void getAllEquationsByRoot(double root) {
        repository.getAllEquationsByRoot(root);
    }

    private void checkRoot(String equation) {
        try (Scanner scanner = new Scanner(System.in)) {
            System.out.println("Enter the value of X:");
            double x = 0.0;
            try {
                x = scanner.nextDouble();
                System.out.println("x = " + x);
            } catch (InputMismatchException e) {
                System.out.println("Incorrect value entered of X.");
            }

            if (isCorrectRoot(equation, x)) {
                System.out.println("X is the root of the equation.");
                repository.saveRoot(equation, x);
                System.out.println("The root has saved in the database.");
            } else {
                System.out.println("X isn't the root of the equation.");
            }
        }
    }
}
