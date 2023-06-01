package com.martinps.component;

import org.springframework.stereotype.Component;

@Component
public class ComponentTwoImplement implements ComponentDependency {

    @Override
    public void saludar() {
        System.out.println("Martin Paliza Sanchez desarrollador de software - ComponentTwoImplement");
    }
}
