package com.ravi.app.service;

import com.ravi.app.model.Laptop;
import org.springframework.stereotype.Service;

@Service
public class LaptopService {

    public void addLaptop(Laptop lap) {
        System.out.println("Method called");
    }

    public boolean isGoodForProc(Laptop lap){
        return true;
    }
}
