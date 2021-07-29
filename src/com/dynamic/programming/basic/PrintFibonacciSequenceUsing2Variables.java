package com.dynamic.programming.basic;

/**
 * How to print using 2 variables isntead of 3 variables?
        The algorithm with 2 variables is: 
        1. print a+b. 
        2. add a to b. 
        3. assign b – a to a. 
 * 
 */
public class PrintFibonacciSequenceUsing2Variables {

    static void fib3Variables(int n) {
        int a = 0, b = 1, c;
        if (n >= 0)
            System.out.print(a + " ");
        if (n >= 1)
            System.out.print(b + " ");
        for (int i = 2; i <= n; i++) {
            c = a + b;
            System.out.print(c + " ");
            a = b;
            b = c;
        }
    }
    
    static void fib2Variables(int n) {
        int a = 0, b = 1;
        if (n >= 0)
            System.out.print(a + " ");
        if (n >= 1)
            System.out.print(b + " ");
        for (int i = 2; i <= n; i++) {
            System.out.print(a + b + " ");
            b = a + b;
            a = b - a;
        }
    }

    // Driver code
    public static void main(String[] args) {
        fib3Variables(9);
        System.out.println("\nprint fibonacci series with 2 variables.....");
        fib2Variables(9);
    }
}
