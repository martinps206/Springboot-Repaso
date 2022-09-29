package com.fundamentos.springboot.fundamentos.bean;

public class MyBeanFactorialImplement implements MyBeanFactorial{

    @Override
    public int printFactorial(int number) {
        int fact = 1;
         for (int i = 1; i <= number; i++){
            fact = fact * i;
        }
        return fact;
    }
}
