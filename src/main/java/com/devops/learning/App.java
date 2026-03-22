package com.devops.learning;

public class App 
{
    public static void main( String[] args ) {
        System.out.println( "Hello World!" );

        Calculator cal = new Calculator();
        System.out.println("java calculator -- ");

        int addResult = cal.add(5, 3);
        System.out.println("5 + 3 == " + addResult);

        int subResult = cal.subtract(5, 3);
        System.out.println("5 - 3 == " + subResult);

        int mulResult = cal.multiply(5, 3);
        System.out.println("5 * 3 == " + mulResult);

        double divResult = cal.divide(5, 3);
        System.out.println("5 / 3 == " + divResult);

        boolean evenResult = cal.isEven(5);
        System.out.println("5 == " + evenResult);

        long factResult = cal.factorial(5);
        System.out.println("5! == " + factResult);

        System.out.println("=== App Complete! ===");
    }
}
