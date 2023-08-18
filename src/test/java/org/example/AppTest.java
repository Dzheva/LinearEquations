package org.example;

import junit.framework.TestCase;

import static org.example.Service.*;

/**
 * Unit test for simple App.
 */
public class AppTest extends TestCase
{
    public void testIsValidMathEquation() {
        String[] validEquations = {
                "2*x+5=17",
                "-1.3*5/x=1.2",
                "2*x+5+x+5=10",
        };

        String[] invalidEquations = {
                "3+*4",
                "4*-7"
        };

        for (String equation : validEquations) {
            boolean result = isValidMathEquation(equation);
            System.out.println(equation + " is valid: " + result);
        }

        for (String equation : invalidEquations) {
            boolean result = isValidMathEquation(equation);
            System.out.println(equation + " is valid: " + result);
        }
    }

    public void testIsValidParentheses() {
        String[] validEquations = {
                "(2*x+5=17)",
                "(-1.3*5/x)=1.2",
                "(2*x=10)"
        };

        String[] invalidEquations = {
                "3+4",
                "(4*-7",
                "2*x+(5+x+5=10"
        };

        for (String equation : validEquations) {
            boolean result = isValidParentheses(equation);
            System.out.println(equation + " has valid parentheses: " + result);
        }

        for (String equation : invalidEquations) {
            boolean result = isValidParentheses(equation);
            System.out.println(equation + " has valid parentheses: " + result);
        }
    }

    public void testEvaluateExpression() {
        String[] expressions = {
                "2*x+5",
                "1.3*5/x",
                "2*x+5+x+5"
        };

        double x = 2.0;

        for (String expression : expressions) {
            double result = evaluateExpression(expression, x);
            System.out.println(expression + " evaluated at x=" + x + ": " + result);
        }
    }

    public void testIsCorrectRoot() {
        String[] equations = {
                "2*x+2=6",
                "1.3*5/x=1.2",
                "2*x=4",
        };

        double x = 2.0;

        for (String equation : equations) {
            boolean result = isCorrectRoot(equation, x);
            System.out.println(equation + " has correct root: " + result);
        }
    }
}
