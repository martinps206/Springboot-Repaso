package com.fundamentos.springboot.fundamentos.bean;

import java.util.Scanner;

public class MyBeanWithDependencySumImplement implements MyBeanWhithDependencySum{
    Scanner sc = new Scanner(System.in);
    private MyBeanFactorial myBeanFactorial;

    public MyBeanWithDependencySumImplement(MyBeanFactorial myBeanFactorial) {
        this.myBeanFactorial = myBeanFactorial;
    }

    @Override
    public int sum(int number) {
        int total = 0;
        for (int i = 1; i<=number; i++){
            total = total + i;
        }
        return total;
    }

    @Override
    public void Result(){
        System.out.println("Ingrese un numero para mostrar la sumatoria y el factorial");
        int number = sc.nextInt();
        System.out.println("La sumatoria es : " + this.sum(number));
        System.out.println("El factorial es : " + myBeanFactorial.printFactorial(number));

    }
}
