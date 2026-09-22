# Autowiring: Field, Setter & Constructor 🔗

## 🔹 What is Autowiring?

**Autowiring** allows Spring to automatically find and inject the required dependency **by type**.

Spring supports 3 common ways:

1. **Field Injection**
2. **Setter Injection**
3. **Constructor Injection**

---

## 🔹 1. Field Autowiring

Dependency is injected directly into the field using `@Autowired`.

```java
@Component
public class Alien {

    @Autowired
    private Computer computer;

    public void code() {
        System.out.println("Coding...");
        computer.compile();
    }
}
```

### 🧠 How it works

```text
@Autowired
     ↓
Find Bean of type Computer
     ↓
Inject it into computer field
```

### ✅ Pros

* Very simple
* Less code

### ❌ Cons

* Harder to unit test
* Dependency is hidden inside the class
* Cannot make the injected field `final`

---

## 🔹 2. Setter Autowiring

Dependency is injected through a **setter method**.

```java
@Component
public class Alien {

    private Computer computer;

    @Autowired
    public void setComputer(Computer computer) {
        this.computer = computer;
    }

    public void code() {
        System.out.println("Coding...");
        computer.compile();
    }
}
```

Spring effectively calls:

```java
alien.setComputer(desktop);
```

### ✅ Pros

* Easy to change dependency
* Better testability than field injection
* Useful for optional/changeable dependencies

### ❌ Cons

* More code
* Dependency can be changed after object creation

---

## 🔹 3. Constructor Autowiring ⭐

Dependency is injected through the **constructor**.

```java
@Component
public class Alien {

    private final Computer computer;

    @Autowired
    public Alien(Computer computer) {
        this.computer = computer;
    }

    public void code() {
        System.out.println("Coding...");
        computer.compile();
    }
}
```

In modern Spring, if the class has **only one constructor**, `@Autowired` can be omitted:

```java
@Component
public class Alien {

    private final Computer computer;

    public Alien(Computer computer) {
        this.computer = computer;
    }
}
```

### ✅ Pros

* Dependency is available when object is created
* Supports `final` fields
* Easy to test
* Makes required dependencies clear
* Commonly preferred for required dependencies

### ❌ Cons

* Slightly more code

---

## 🔹 Configuration Example

```java
@Configuration
public class AppConfig {

    @Bean
    public Desktop desktop() {
        return new Desktop();
    }
}
```

```java
public interface Computer {
    void compile();
}
```

```java
@Component
public class Desktop implements Computer {

    @Override
    public void compile() {
        System.out.println("Compiling using Desktop");
    }
}
```

Spring finds the `Computer` dependency and injects the `Desktop` Bean because `Desktop` implements `Computer`.

---

## 🔹 Comparison

| Type        | Injection           | Testing  | `final` | Common Use              |
| ----------- | ------------------- | -------- | ------- | ----------------------- |
| Field       | Directly into field | ❌ Harder | ❌ No    | Simple cases            |
| Setter      | Through setter      | ✅ Good   | ❌ No    | Optional/changeable     |
| Constructor | Through constructor | ✅ Easy   | ✅ Yes   | Required dependencies ⭐ |

---

## 🧠 Quick Revision

```text
Field       → @Autowired on field
Setter      → @Autowired on setter
Constructor → @Autowired on constructor
```

### ⭐ One-Line Summary

**Constructor injection is generally preferred for required dependencies, while setter injection is useful for optional/changeable dependencies and field injection is the simplest but less test-friendly approach.**
