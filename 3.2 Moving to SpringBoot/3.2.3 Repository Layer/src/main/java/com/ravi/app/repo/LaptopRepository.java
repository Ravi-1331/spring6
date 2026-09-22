package com.ravi.app.repo;

import com.ravi.app.model.Laptop;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

@Repository
public class LaptopRepository {
    public void save(Laptop lap){
        System.out.println("saved in db....");
    }
}
