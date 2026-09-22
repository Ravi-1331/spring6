# Service Class in Spring Boot 🔧

## 🔹 What is a Service Class?

The **Service Layer** contains the **business logic** of the application.

It usually works between the **Controller** and **Repository** layers.

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

## 🔹 `@Service` Annotation

`@Service` tells Spring that the class is a **service component** and should be managed as a Bean.

```java
@Service
public class LaptopService {
    
    public void add(Laptop laptop) {
        System.out.println("method called");
    }

    public boolean isGoodForProg(Laptop laptop) {
        return true;
    }
}
```

Spring automatically detects this class through component scanning.

---

## 🔹 Responsibilities of Service Layer

* 🧠 Contains **business logic**
* ⚙️ Processes and handles data
* 🔄 Connects Controller with Repository
* 📦 Can use other Spring Beans/services

---

## 🔹 Example

### `LaptopService.java`

```java
package com.telusko.app.service;

import org.springframework.stereotype.Service;

@Service
public class LaptopService {

    public void add(Laptop laptop) {
        System.out.println("method called");
    }

    public boolean isGoodForProg(Laptop laptop) {
        return true;
    }
}
```

### `Laptop.java`

```java
package com.telusko.app.model;

import org.springframework.stereotype.Component;

@Component
public class Laptop implements Computer {

    public void compile() {
        System.out.println("Compiling in Laptop");
    }
}
```

### `SpringBootDemoApplication.java`

```java
package com.telusko.app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

@SpringBootApplication
public class SpringBootDemoApplication {

    public static void main(String[] args) {

        ApplicationContext context =
                SpringApplication.run(
                        SpringBootDemoApplication.class, args);

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
method called
```

---

## 📁 Project Structure

A common Spring Boot structure is:

```text
<img width="374" height="442" alt="image" src="https://github.com/user-attachments/assets/d9ac60f4-08a2-4466-9b3c-d3091f70fddd" />

```

### 🧠 Remember

```text
model      → Data / Objects
service    → Business Logic
repository → Database Operations
controller → Handles Requests
```

### ⭐ One-Line Summary

**`@Service` marks a class as a Spring Bean that contains business logic and typically sits between the Controller and Repository layers.**
