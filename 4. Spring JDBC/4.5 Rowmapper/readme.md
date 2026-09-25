# RowMapper in Spring JDBC 🔄

## 📌 About This Project

This project demonstrates how to use **`RowMapper`** with `JdbcTemplate` to retrieve data from a database and convert each database row into a **Java object**.

```text
Database Row
     ↓
 ResultSet
     ↓
 RowMapper
     ↓
 Student Object
```

---

## 🔹 What is `RowMapper`?

`RowMapper` is a **functional interface** in Spring JDBC used to map each row of a `ResultSet` to a Java object.

It is mainly used with **SELECT queries**.

### ⭐ Important Method

```java
mapRow(ResultSet rs, int rowNum)
```

* `rs` → Contains the data of the current database row.
* `rowNum` → Current row number.
* Returns → Java object created from that row.

---

## 🔹 `JdbcTemplate.query()`

`query()` is used for **SELECT operations**.

```java
jdbc.query(sql, mapper);
```

It takes:

1. **SQL Query**
2. **RowMapper**

---

## 🔹 `findAll()` Using Lambda

```java
public List<Student> findAll() {

    String sql = "SELECT * FROM student";

    RowMapper<Student> mapper = (rs, rowNum) -> {

        Student s = new Student();

        s.setRollNo(rs.getInt("rollno"));
        s.setName(rs.getString("name"));
        s.setMarks(rs.getInt("marks"));

        return s;
    };

    return jdbc.query(sql, mapper);
}
```

### 🔄 What happens?

Suppose the database contains:

```text
rollno | name   | marks
-----------------------
101    | Kiran  | 79
102    | Harsh  | 68
103    | Sushil | 82
```

`RowMapper` converts every row into a `Student` object:

```text
Database Row
     ↓
RowMapper
     ↓
Student Object
```

So the result becomes:

```text
List<Student>
```

---

## 🔹 Without Lambda

The same thing can be written using an anonymous `RowMapper` class:

```java
RowMapper<Student> mapper = new RowMapper<Student>() {

    @Override
    public Student mapRow(ResultSet rs, int rowNum)
            throws SQLException {

        Student s = new Student();

        s.setRollNo(rs.getInt("rollno"));
        s.setName(rs.getString("name"));
        s.setMarks(rs.getInt("marks"));

        return s;
    }
};
```

### ⭐ Lambda is shorter

Because `RowMapper` is a **functional interface**, we can use a lambda expression:

```java
(rs, rowNum) -> { ... }
```

---

## 🔹 Complete Flow

```text
jdbc.query()
      ↓
Execute SELECT query
      ↓
ResultSet
      ↓
RowMapper.mapRow()
      ↓
Create Student object
      ↓
List<Student>
```

---

## 🧠 Quick Revision

```text
update() → INSERT / UPDATE / DELETE

query()  → SELECT

RowMapper → Database Row → Java Object
```

### ⭐ One-Line Summary

**`RowMapper` converts each row returned by a SELECT query into a Java object, while `JdbcTemplate.query()` collects those objects into a List.**
