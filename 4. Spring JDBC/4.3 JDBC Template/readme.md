# JdbcTemplate in Spring JDBC 🗄️

## 📌 About This Project

This project introduces **JdbcTemplate**, which simplifies database operations in Spring Boot.

It provides methods to execute SQL operations such as:

* **INSERT**
* **UPDATE**
* **DELETE**
* **SELECT**

---

## 🔹 What is `JdbcTemplate`?

`JdbcTemplate` is a Spring JDBC class that helps us **interact with the database without writing low-level JDBC code**.

```text
Java Code
    ↓
JdbcTemplate
    ↓
Database
```

Spring handles much of the JDBC boilerplate code for us.

---

## 🔹 `update()` Method

The `update()` method is used for:

```text
INSERT
UPDATE
DELETE
```

It takes:

1. **SQL Query**
2. **Query Values**

Example:

```java
int rows = jdbc.update(sql, values);
```

It returns an `int` representing the **number of rows affected**.

---

## 🔹 `StudentRepo.java`

```java
package com.telusko.SpringJDBCEx.repo;

@Repository
public class StudentRepo {

    private JdbcTemplate jdbc;

    public JdbcTemplate getJdbc() {
        return jdbc;
    }

    @Autowired
    public void setJdbc(JdbcTemplate jdbc) {
        this.jdbc = jdbc;
    }

    public void save(Student s) {

        String sql =
            "INSERT INTO student (rollno, name, marks) VALUES (?, ?, ?)";

        int rows = jdbc.update(
            sql,
            s.getRollNo(),
            s.getName(),
            s.getMarks()
        );

        System.out.println(rows + " row(s) affected.");
    }
}
```

---

## 🔹 How It Works

### 1️⃣ Inject `JdbcTemplate`

```java
@Autowired
public void setJdbc(JdbcTemplate jdbc) {
    this.jdbc = jdbc;
}
```

Spring automatically provides the `JdbcTemplate` object.

### 2️⃣ Write SQL Query

```java
String sql =
    "INSERT INTO student (rollno, name, marks) VALUES (?, ?, ?)";
```

`?` represents values that will be supplied later.

### 3️⃣ Execute Query

```java
int rows = jdbc.update(
    sql,
    s.getRollNo(),
    s.getName(),
    s.getMarks()
);
```

The values are inserted into the database.

### 4️⃣ Check Rows Affected

If one student is inserted:

```text
1 row(s) affected.
```

---

## 🧠 Important Concept

```text
Student Object
      ↓
StudentRepo
      ↓
JdbcTemplate
      ↓
SQL Query
      ↓
Database
```

### ⭐ Remember

**`JdbcTemplate.update()` is mainly used for INSERT, UPDATE, and DELETE operations and returns the number of affected rows.**

### 🔥 Key Point

Instead of manually handling:

```text
Connection
PreparedStatement
ResultSet
Exception handling
```

**JdbcTemplate handles much of the JDBC boilerplate for us.**

### ⭐ One-Line Summary

**JdbcTemplate makes database operations easier by allowing us to execute SQL queries with simple Spring JDBC methods.**
