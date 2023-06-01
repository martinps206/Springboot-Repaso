package com.martinps.bean;

public class MyOperationImplement implements MyOperation {
    @Override
    public int sum(int number) {
        return number*2 + 1;
    }
}
