package com.devops.learning;

public class Calculator {
    public int add(int a, int b){
        return a + b;
    }

    public int subtract(int a, int b){
        return a - b;
    }

    public int multiply(int a, int b) {
        return a * b;
    }

    public double divide(double a, double b){
        if(b == 0){
            throw new ArithmeticException("zero divide not possible");
        }

        return a / b;
    }

    public boolean isEven(int n){
        return n % 2 == 0;
    }

    public long factorial(int n){
        if(n < 0){
            throw new IllegalArgumentException("wrong args");
        }

        if(n == 0 || n == 1){
            return 1;
        }

        return n * factorial(n - 1);
    }
}
