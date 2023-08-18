package org.example;

import java.util.Scanner;

public class App {

    public static void main(String[] args) {
        Service service = new Service();

        Scanner scanner = new Scanner(System.in);

        System.out.println("Menu:");
        System.out.println("Enter 1 - to input the equation and its root.");
        System.out.println("Enter 2 - to find the equations by the root.");

        int function = scanner.nextInt();

        switch (function) {
            case 0:
                System.exit(0);
                break;
            case 1:
                System.out.println("Enter a mathematical equation:");
                String equation = scanner.next();
                service.createEquation(equation);
                break;
            case 2:
                System.out.println("Enter a root:");
                double root = scanner.nextDouble();
                System.out.println("root = " + root);
                service.getAllEquationsByRoot(root);
                break;
            default:
                System.out.println("Wrong input, enter only 0, 1, or 2.");
        }
        scanner.close();
    }
}
