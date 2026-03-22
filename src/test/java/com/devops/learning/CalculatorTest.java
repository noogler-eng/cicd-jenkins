package com.devops.learning;

// import junit5 annotations
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions.*;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CalculatorTest {
    // same calculator object will be used in each test
    private Calculator calculator;

    @BeforeEach
    public void setUp(){
        // for every test creating an fresh object
        calculator = new Calculator();
    }


    @Test
    public void testAdd(){
        int a = 3, b = 4;
        int result = calculator.add(a, b);
        System.out.println("result: " + result);
        assertEquals(7, result);
    }
}
