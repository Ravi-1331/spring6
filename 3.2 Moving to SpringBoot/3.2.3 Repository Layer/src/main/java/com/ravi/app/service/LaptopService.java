package com.ravi.app.service;

import com.ravi.app.LaptopRepository;
import com.ravi.app.model.Laptop;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LaptopService {

    @Autowired
    private LaptopRepository repo;

    public void addLaptop(Laptop lap) {
        repo.save(lap);
    }

    public boolean isGoodForProc(Laptop lap){
        return true;
    }
}
