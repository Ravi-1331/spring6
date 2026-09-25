# Schema and Data Files in Spring Boot 🗄️

## 📌 About This Project

Spring Boot can automatically execute **SQL files during application startup** to create database tables and insert initial data.

We use two files:

```text
schema.sql → Creates database structure
data.sql   → Inserts initial data
```

---

## 🔹 1. `schema.sql`

`schema.sql` is used to **create the database structure**, such as tables and columns.

```sql
CREATE TABLE student (
    rollno INT PRIMARY KEY,
    name VARCHAR(50),
    marks INT
);
```

### ⭐ Remember

**`schema.sql` runs first** and creates the required table structure.

---

## 🔹 2. `data.sql`

`data.sql` is used to **insert initial/seed data** after the table has been created.

```sql
INSERT INTO student (rollno, name, marks)
VALUES (101, 'Kiran', 79);

INSERT INTO student (rollno, name, marks)
VALUES (102, 'Harsh', 68);

INSERT INTO student (rollno, name, marks)
VALUES (103, 'Sushil', 82);
```

### ⭐ Remember

**`data.sql` runs after `schema.sql`** and populates the table with initial data.

---

## 📁 File Location

Both files should be placed inside:

```text
src
└── main
    └── resources
        ├── schema.sql
        └── data.sql
```

Spring Boot automatically detects these standard filenames.

---

## 🔄 Execution Flow

```text
Spring Boot Starts
       ↓
  schema.sql
       ↓
Create student table
       ↓
   data.sql
       ↓
Insert initial records
       ↓
Application Ready
```

---

## 🔹 Using Custom SQL File Names

We can also use custom names such as:

```text
my_custom_schema.sql
my_custom_data.sql
```

Then configure their locations in `application.properties`:

```properties
spring.sql.init.schema-locations=classpath:my_custom_schema.sql
spring.sql.init.data-locations=classpath:my_custom_data.sql
```

### 🧠 Important

`classpath:` means Spring Boot looks for the file inside the application's **classpath**, typically `src/main/resources`.

---

## 📁 Example Project Structure

```text
src/main
├── java
│   └── com.telusko.SpringJDBCEx
│       ├── model
│       │   └── Student.java
│       ├── repo
│       │   └── StudentRepo.java
│       ├── service
│       │   └── StudentService.java
│       └── Application.java
│
└── resources
    ├── schema.sql
    ├── data.sql
    └── application.properties
```

---

## 🧠 Quick Revision

```text
schema.sql → CREATE TABLE
data.sql   → INSERT initial data
```

### ⭐ One-Line Summary

**`schema.sql` creates the database structure, while `data.sql` inserts initial data automatically when the Spring Boot application starts.**
