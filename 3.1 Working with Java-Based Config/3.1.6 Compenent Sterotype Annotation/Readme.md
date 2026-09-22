# Component Stereotype Annotations 🌱

## 🔹 What are Stereotype Annotations?

Stereotype annotations tell Spring **what role a class has** and allow Spring to automatically create and manage it as a **Bean**.

They reduce the need to define every Bean manually using XML or `@Bean`.

---

## 🔹 Types of Stereotype Annotations

| Annotation    | Purpose                     |
| ------------- | --------------------------- |
| `@Component`  | General-purpose Spring Bean |
| `@Service`    | Service / business logic    |
| `@Repository` | Database / data access      |
| `@Controller` | Web / presentation layer    |

### 1. `@Component`

Used for a general Spring-managed class.

```java
@Component
public class Alien {
    public void code() {
        System.out.println("Alien is coding!");
    }
}
```

### 2. `@Service`

Used for the **service/business logic layer**.

```java
@Service
public class AlienService {
    public String assist() {
        return "Assistance provided by AlienService.";
    }
}
```

### 3. `@Repository`

Used for the **data access/database layer**.

```java
@Repository
public class AlienRepository {
    public List<String> fetchAliens() {
        return List.of("Alien1", "Alien2");
    }
}
```

### 4. `@Controller`

Used for handling **HTTP requests** in Spring MVC.

```java
@Controller
public class AlienController {

    @GetMapping("/alien")
    public String greet() {
        return "Greetings from Alien!";
    }
}
```

---

## 🔹 `@ComponentScan`

`@ComponentScan` tells Spring **which package to scan** for stereotype annotations.

```java
@Configuration
@ComponentScan("org.example")
public class AppConfig {
}
```

Spring scans `org.example` and its sub-packages and finds classes annotated with:

* `@Component`
* `@Service`
* `@Repository`
* `@Controller`

### Example

```text
org.example
 ├── Alien.java          → @Component
 ├── AlienService.java   → @Service
 ├── AlienRepository.java → @Repository
 └── AlienController.java → @Controller
```

Spring automatically registers these as Beans.

---

## 🔹 Practical Example

### `Alien.java`

```java
package org.example;

import org.springframework.stereotype.Component;

@Component
public class Alien {

    public void code() {
        System.out.println("Alien is coding!");
    }
}
```

### `AppConfig.java`

```java
package org.example;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan("org.example")
public class AppConfig {
}
```

### `MainApp.java`

```java
package org.example;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class MainApp {

    public static void main(String[] args) {

        ApplicationContext context =
                new AnnotationConfigApplicationContext(AppConfig.class);

        Alien alien = context.getBean(Alien.class);

        alien.code();
    }
}
```

### Output

```text
Alien is coding!
```

---

## 🔹 Why Use Stereotype Annotations?

✅ Less configuration
✅ No need to define every Bean using `@Bean`
✅ Automatic Bean creation through component scanning
✅ Clearly shows the role of each class
✅ Reduces boilerplate code

---

## 🧠 Quick Revision

```text
@Component   → General component
@Service     → Business logic
@Repository  → Database/Data access
@Controller  → Web/HTTP requests
@ComponentScan → Finds these classes
@Configuration → Configuration class
```

### ⭐ One-Line Summary

**Stereotype annotations + `@ComponentScan` allow Spring to automatically discover and manage application classes as Beans.**
