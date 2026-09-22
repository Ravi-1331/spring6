# Repository Layer in Spring Boot 🗄️

## 🔹 What is Repository Layer?

The **Repository Layer** is responsible for interacting with the **database**.

It handles operations such as:

* 💾 Saving data
* 🔍 Fetching data
* ✏️ Updating data
* 🗑️ Deleting data

Typical Spring Boot flow:

```text
Controller
    ↓
 Service
    ↓
Repository
    ↓
 Database
```

---

## 🔹 `@Repository`

`@Repository` marks a class as a **Spring-managed Bean** used for data access.

```java
@Repository
public class LaptopRepository {
}
```

It also helps Spring translate certain database-related exceptions into Spring's data-access exceptions.

---

## 🔹 `LaptopRepository.java`

```java
package com.ravi.app.repo;

import org.springframework.stereotype.Repository;
import com.ravi.app.model.Laptop;

@Repository
public class LaptopRepository {

    public void save(Laptop lap) {
        System.out.println("Saved in Database..");
    }
}
```

Here:

```java
save(Laptop lap)
```

simulates saving a Laptop into the database.

---

## 🔹 `LaptopService.java`

The Service calls the Repository to perform the database operation.

```java
package com.ravi.app.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ravi.app.model.Laptop;
import com.ravi.app.repo.LaptopRepository;

@Service
public class LaptopService {

    @Autowired
    private LaptopRepository repo;

    public void add(Laptop laptop) {
        repo.save(laptop);
    }

    public boolean isGoodForProg(Laptop lap) {
        return true;
    }
}
```

### 🧠 Flow

```text
service.add(laptop)
       ↓
repo.save(laptop)
       ↓
"Saved in Database.."
```

---

## 🔹 `Laptop.java`

```java
package com.ravi.app.model;

import org.springframework.stereotype.Component;

@Component
public class Laptop implements Computer {

    public void compile() {
        System.out.println("Compiling in Laptop");
    }
}
```

---

## 🔹 `SpringBootFirstApplication.java`

```java
package com.ravi.app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

@SpringBootApplication
public class SpringBootFirstApplication {

    public static void main(String[] args) {

        ApplicationContext context =
                SpringApplication.run(
                        SpringBootFirstApplication.class, args);

        LaptopService service =
                context.getBean(LaptopService.class);

        Laptop laptop =
                context.getBean(Laptop.class);

        service.add(laptop);
    }
}
```

### Output

```text
Saved in Database..
```

---

## 📁 Project Structure

```text
src
└── main
    └── java
        └── com.ravi.app
            │
            ├── model
            │   ├── Alien.java
            │   ├── Computer.java
            │   ├── Desktop.java
            │   └── Laptop.java
            │
            ├── repo
            │   └── LaptopRepository.java
            │
            ├── service
            │   └── LaptopService.java
            │
            └── SpringBootFirstApplication.java
```

### 🧠 Remember

```text
Model      → Data / Objects
Service    → Business Logic
Repository → Database Operations
Controller → Handles Requests
```

### ⭐ One-Line Summary

**`@Repository` marks the data-access layer, and the Service uses the Repository to perform database operations.**
