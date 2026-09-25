# Student Service and Repository 🎓

## 📌 About This Project

This project demonstrates a basic **MVC-style structure** in Spring Boot using **Spring JDBC**.

The main goal is to understand how **Model → Repository → Service → Application** layers work together.

---

## 🔹 Project Flow

```text
Application
     ↓
  Service
     ↓
 Repository
     ↓
 Database
```

### Layers

* **Model** → Represents student data.
* **Repository** → Handles database-related operations.
* **Service** → Contains business logic and calls the Repository.
* **Application** → Starts Spring Boot and coordinates the operations.

---

## 🔹 1. Model Layer — `Student.java`

```java
@Component
@Scope("prototype")
public class Student {

    private int rollNo;
    private String name;
    private int marks;

    // Getters and Setters
}
```

**Student object represents one student record.**

```text
Student
 ├── rollNo
 ├── name
 └── marks
```

---

## 🔹 2. Repository Layer — `StudentRepo.java`

```java
@Repository
public class StudentRepo {

    public void save(Student s) {
        System.out.println("Student added to the database.");
    }

    public List<Student> findAll() {
        List<Student> students = new ArrayList<>();
        return students;
    }
}
```

**Repository is responsible for database operations.**

Currently, `save()` and `findAll()` are only demonstrating the flow; actual JDBC database operations will be added later.

---

## 🔹 3. Service Layer — `StudentService.java`

```java
@Service
public class StudentService {

    private StudentRepo repo;

    @Autowired
    public void setRepo(StudentRepo repo) {
        this.repo = repo;
    }

    public void addStudent(Student s) {
        repo.save(s);
    }

    public List<Student> getStudents() {
        return repo.findAll();
    }
}
```

### ⭐ Important

The **Service does not directly interact with the database**.

It calls the Repository:

```text
StudentService
      ↓
 StudentRepo
      ↓
 Database
```

---

## 🔹 4. Application Layer — `Application.java`

```java
@SpringBootApplication
public class Application {

    public static void main(String[] args) {

        ApplicationContext context =
                SpringApplication.run(Application.class, args);

        Student s = context.getBean(Student.class);

        StudentService service =
                context.getBean(StudentService.class);

        s.setRollNo(101);
        s.setName("Navin");
        s.setMarks(78);

        service.addStudent(s);

        List<Student> students =
                service.getStudents();

        System.out.println(students);
    }
}
```

---

## 📁 Project Structure

```text
src/main/java
└── com.telusko.SpringJDBCEx
    │
    ├── model
    │   └── Student.java
    │
    ├── repo
    │   └── StudentRepo.java
    │
    ├── service
    │   └── StudentService.java
    │
    └── Application.java
```

---

## 🧠 What I Learned

```text
Model      → Represents data
Repository → Database operations
Service    → Business logic
Application → Starts and coordinates the application
```

### ⭐ Key Concept

**The Service layer calls the Repository layer instead of directly handling database operations.**

### 🔥 Overall Flow

```text
Student Object
      ↓
StudentService.addStudent()
      ↓
StudentRepo.save()
      ↓
Database
```

### ⭐ One-Line Summary

**This project demonstrates how Model, Repository, and Service layers work together in a Spring Boot application using Spring JDBC.**
