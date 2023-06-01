package com.martinps.bean;

import java.util.Scanner;

public class MyBeanWithDependencyImplemen implements MyBeanWithDependency{
    Scanner sc = new Scanner(System.in);
    private MyOperation myOperation;

    public MyBeanWithDependencyImplemen(MyOperation myOperation) {
        this.myOperation = myOperation;
    }

    @Override
    public void printWithDepndecy() {
        System.out.println("Ingrese un numero que desea agregarle esu consecutivo");
        int number = sc.nextInt();
        System.out.println("Resultado : " + myOperation.sum(number));
        System.out.println("Desarrollando desde la implementacion de una bean con dependencia - printWithDepndecy");
    }
}
