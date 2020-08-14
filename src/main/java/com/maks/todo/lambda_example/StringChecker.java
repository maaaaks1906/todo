package com.maks.todo.lambda_example;

@FunctionalInterface
public interface StringChecker {
    boolean check(String s);

    static void sayHello(){
        System.out.println("Hello");
    }
}

