package com.maks.todo.lambda_example;

public class Main {

    public static void main(String[] args) {
        StringChecker stringChecker = new StringChecker() {
            @Override
            public boolean check(String s) {
                return !s.isEmpty();
            }
        };

        StringChecker stringChecker2 = str -> !str.isEmpty();

        System.out.println(stringChecker);
    }

}
