package org.example;

import org.springframework.stereotype.Component;

@Component("com1")
//@Component
public class Desktop implements Computer {
    public Desktop(){
        System.out.println("Desktop object created");
    }
    @Override
    public void compile(){
        System.out.println("Compiling using Desktop");
    }
}
