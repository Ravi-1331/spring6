# Autowiring in Spring 🔗

## 📌 What is Autowiring?

**Autowiring** allows Spring to automatically find and inject a required **dependency** into a Bean.

Instead of manually creating and passing the dependency:

```java
Alien alien = new Alien();
alien.setComputer(new Desktop());
```

Spring handles the dependency injection automatically.

---

# 🔧 Ways of Autowiring

There are **3 common ways**:

1. **Field Injection**
2. **Setter Injection**
3. **Constructor Injection** ⭐

Spring generally recommends **constructor injection** for required dependencies.

---

# 1️⃣ Field-Level Autowiring

With field injection, `@Autowired` is placed directly on the field.

```java
@Component
class Alien {

    @Autowired
    private Computer computer;

    public void code() {
        System.out.println("Coding...");
        computer.compile();
    }
}
```

Spring finds a Bean implementing `Computer` and injects it into:

```java
private Computer computer;
```

### Example

```java
@Configuration
public class AppConfig {

    @Bean
    public Alien alien() {
        return new Alien();
    }

    @Bean
    public Desktop desktop() {
        return new Desktop();
    }
}
```

```java
interface Computer {
    void compile();
}

class Desktop implements Computer {

    public void compile() {
        System.out.println("Compiling using Desktop");
    }
}
```

### Flow

```text
Alien
  ↓
@Autowired Computer
  ↓
Spring searches by type
  ↓
Desktop implements Computer
  ↓
Desktop injected into Alien
```

### ✅ Advantage

* Very simple
* Less code

### ❌ Disadvantage

* Dependency is hidden inside the class
* More difficult to unit test compared with constructor injection
* Cannot easily create the object with its required dependency through the constructor

---

# 2️⃣ Setter-Based Autowiring

Here, `@Autowired` is placed on the **setter method**.

```java
@Component
class Alien {

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

Spring automatically calls:

```java
alien.setComputer(desktop);
```

### Flow

```text
Spring creates Alien
        ↓
Finds Computer Bean
        ↓
Calls setComputer()
        ↓
Dependency injected
```

### ✅ Advantages

* Easy to change the dependency
* Easier to test than field injection
* Useful when the dependency is optional or can be changed

### ❌ Disadvantage

* More code than field injection
* The object can temporarily exist without its dependency

---

# 3️⃣ Constructor-Based Autowiring ⭐

The dependency is provided through the **constructor**.

```java
@Component
class Alien {

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

Spring finds a `Computer` Bean and passes it to the constructor.

Effectively:

```java
Computer computer = new Desktop();
Alien alien = new Alien(computer);
```

---

## ⭐ `@Autowired` Can Be Omitted

If a class has **only one constructor**, modern Spring can automatically use that constructor.

So this is enough:

```java
@Component
class Alien {

    private final Computer computer;

    public Alien(Computer computer) {
        this.computer = computer;
    }
}
```

No:

```java
@Autowired
```

is required on the constructor.

---

# 🆚 Three Types of Autowiring

| Type              | Where `@Autowired` is Used | Main Idea                    |
| ----------------- | -------------------------- | ---------------------------- |
| **Field**         | Field                      | Inject directly into field   |
| **Setter**        | Setter method              | Inject through setter        |
| **Constructor** ⭐ | Constructor                | Inject while creating object |

---

# 🧠 Which One to Remember?

```text
Field
  ↓
Easy but less testable

Setter
  ↓
Flexible / optional dependency

Constructor ⭐
  ↓
Required dependency + easier testing
```

### Constructor Injection Example

```java
private final Computer computer;

public Alien(Computer computer) {
    this.computer = computer;
}
```

The `final` field cannot be changed after construction, which helps make the dependency **immutable**.

---

# ⚠️ Important: Autowiring is By Type

Suppose:

```java
interface Computer {
    void compile();
}
```

and:

```java
class Laptop implements Computer { }
class Desktop implements Computer { }
```

If both are Spring Beans:

```text
Computer
 ├── Laptop
 └── Desktop
```

and Alien has:

```java
@Autowired
private Computer computer;
```

Spring has **multiple candidates** and cannot automatically decide which one to inject.

You can solve this using **`@Primary`**, **`@Qualifier`**, or another explicit configuration.

---

# 🧠 Quick Revision

### Field Injection

```java
@Autowired
private Computer computer;
```

### Setter Injection

```java
@Autowired
public void setComputer(Computer computer) {
    this.computer = computer;
}
```

### Constructor Injection ⭐

```java
public Alien(Computer computer) {
    this.computer = computer;
}
```

### Easy Memory Trick

**Field → directly into field**
**Setter → through setter**
**Constructor → during object creation**

---

## ⭐ One-Line Summary

**Autowiring lets Spring automatically inject dependencies by type; it can be done through fields, setters, or constructors, with constructor injection commonly preferred for required dependencies.**
