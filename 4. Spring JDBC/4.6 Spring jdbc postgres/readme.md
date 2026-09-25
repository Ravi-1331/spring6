# Spring JDBC with PostgreSQL 🐘

## 📌 About This Project

This project shows how to replace the **H2 in-memory database** with **PostgreSQL** in a Spring Boot application using **Spring JDBC**.

### 🔄 Database Change

```text
Before:
Spring Boot → Spring JDBC → H2

Now:
Spring Boot → Spring JDBC → PostgreSQL
```

### ⭐ Why PostgreSQL?

**PostgreSQL** is an open-source **relational database (RDBMS)** used for storing persistent application data.

Unlike H2, the data is stored persistently in PostgreSQL.

---

## 🔹 1. Add PostgreSQL Dependency

Remove the H2 dependency and add the PostgreSQL JDBC driver in `pom.xml`:

```xml
<dependency>
    <groupId>org.postgresql</groupId>
    <artifactId>postgresql</artifactId>
    <scope>runtime</scope>
</dependency>
```

### ⭐ Important

**PostgreSQL dependency replaces the H2 dependency.**

---

## 🔹 2. Configure `application.properties`

Location:

```text
src/main/resources/application.properties
```

```properties
# PostgreSQL database connection URL
spring.datasource.url=jdbc:postgresql://localhost:5432/telusko

# Database username
spring.datasource.username=postgres

# Database password
spring.datasource.password=root

# PostgreSQL JDBC Driver
spring.datasource.driver-class-name=org.postgresql.Driver
```

---

## 🔹 Configuration Explained

### `spring.datasource.url`

```properties
spring.datasource.url=jdbc:postgresql://localhost:5432/telusko
```

* `localhost` → PostgreSQL is running on the local machine
* `5432` → Default PostgreSQL port
* `telusko` → Database name

### `spring.datasource.username`

```properties
spring.datasource.username=postgres
```

Username used to connect to PostgreSQL.

### `spring.datasource.password`

```properties
spring.datasource.password=root
```

Password for the PostgreSQL user.

### `spring.datasource.driver-class-name`

```properties
spring.datasource.driver-class-name=org.postgresql.Driver
```

Specifies the **PostgreSQL JDBC Driver**.

---

## 🔄 Connection Flow

```text
Spring Boot Application
        ↓
    Spring JDBC
        ↓
   JDBC Driver
        ↓
   PostgreSQL
        ↓
    Database
```

---

## 🧠 H2 vs PostgreSQL

| H2                                     | PostgreSQL                          |
| -------------------------------------- | ----------------------------------- |
| In-memory database                     | Persistent database                 |
| Mainly useful for testing/development  | Commonly used for real applications |
| Data normally disappears after restart | Data remains stored                 |
| Easy setup                             | Requires PostgreSQL server/database |

---

## 📁 Important Files

```text
src/main
├── java
│   └── com.telusko.SpringJDBCEx
│       ├── model
│       ├── repo
│       ├── service
│       └── Application.java
│
└── resources
    ├── application.properties
    ├── schema.sql
    └── data.sql
```

### ⭐ One-Line Summary

**This project connects a Spring Boot application to PostgreSQL using Spring JDBC, replacing the H2 in-memory database with a persistent relational database.**
