# Creating a Spring JDBC Project 🗄️

## 📌 About This Project

This project introduces **Spring JDBC** and shows how a Spring Boot application can connect and work with a database.

Here we use:

* **Spring JDBC** → To interact with the database
* **H2 Database** → In-memory database for testing
* **Student class** → Represents a database record

---

## 🔹 Dependencies

```xml
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-jdbc</artifactId>
</dependency>

<dependency>
    <groupId>com.h2database</groupId>
    <artifactId>h2</artifactId>
    <scope>runtime</scope>
</dependency>
```

### ⭐ H2 Database

**H2 is an in-memory database**, so the data is available during application runtime and is normally lost when the application stops.

---

## 🔹 Important Concept

In this project:

```text
Student Class → Database Table
Student Object → Table Row
Student Fields → Table Columns
```

Example:

```text
Student
 ├── rollNo
 ├── name
 └── marks
```

---

## 🔹 `Student.java`

```java
package com.example.SpringJDBC.model;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
@Scope("prototype")
public class Student {

    private int rollNo;
    private String name;
    private int marks;

    public int getRollNo() {
        return rollNo;
    }

    public void setRollNo(int rollNo) {
        this.rollNo = rollNo;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getMarks() {
        return marks;
    }

    public void setMarks(int marks) {
        this.marks = marks;
    }
}
```

### 🔹 Why `@Scope("prototype")`?

Every time we request a `Student` Bean using `getBean()`, Spring creates a **new Student object**.

---

## 🔹 `Application.java`

```java
package com.example.SpringJDBC;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

@SpringBootApplication
public class Application {

    public static void main(String[] args) {

        ApplicationContext context =
                SpringApplication.run(Application.class, args);

        Student s = context.getBean(Student.class);

        s.setRollNo(101);
        s.setName("Navin");
        s.setMarks(78);

        addStudent(s);
    }

    public static void addStudent(Student student) {
        // Database operation will be written here
    }
}
```

---

## 🧠 What We Learned

```text
Spring Boot
    ↓
Spring JDBC
    ↓
Database
```

* **Spring JDBC** helps Java/Spring applications communicate with databases.
* **H2** provides a lightweight in-memory database.
* **Student** represents the data we want to store.
* **Prototype scope** creates a new Student object for every `getBean()` call.
* The actual database insert operation will be implemented using **JDBC / JdbcTemplate**.

### ⭐ One-Line Summary

**This project sets up Spring JDBC with H2 and creates a `Student` Bean that will later be used for database operations such as INSERT, SELECT, UPDATE, and DELETE.**
