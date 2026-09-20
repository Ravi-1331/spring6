# Spring XML Configuration – Basic Project

## 📌 About This Project

This is a basic Spring Framework project created to understand how **Spring IoC (Inversion of Control)** works using **XML-based configuration**.

The project demonstrates how Spring creates and manages objects (Beans) instead of creating objects manually using the `new` keyword.

---

## 🛠️ Technologies Used

* Java
* Spring Framework
* Maven
* XML Configuration

---

## 📂 Project Structure

```text
spring1
│
├── pom.xml
│
└── src
    └── main
        ├── java
        │   └── org
        │       └── example
        │           ├── App.java
        │           └── Alien.java
        │
        └── resources
            └── spring.xml
```

---

## 📄 Files and Their Purpose

### 1. Alien.java

This is a simple Java class.

```java
public class Alien {
    public void code() {
        System.out.println("Coding..");
    }
}
```

It contains the `code()` method that prints:

```text
Coding..
```

---

### 2. spring.xml

This file contains the Spring Bean configuration.

```xml
<bean id="alien" class="org.example.Alien">
</bean>
```

Here:

* `id="alien"` → Name used to retrieve the Bean.
* `class="org.example.Alien"` → Tells Spring which Java class should be used.
* Spring creates and manages the `Alien` object.

---

### 3. App.java

This is the main class where the Spring container is created.

```java
ApplicationContext context =
        new ClassPathXmlApplicationContext("spring.xml");
```

Spring reads `spring.xml` and creates the configured Bean.

Then:

```java
Alien obj = (Alien) context.getBean("alien");
```

gets the `Alien` object from the Spring container.

Finally:

```java
obj.code();
```

calls the method.

Output:

```text
Coding..
```

---

## 🔑 Important Concept: IoC

Normally, without Spring, we create an object ourselves:

```java
Alien obj = new Alien();
```

With Spring:

```java
Alien obj = (Alien) context.getBean("alien");
```

The important difference is:

**Without Spring:**

```text
Developer → creates object
```

**With Spring:**

```text
Spring Container → creates and manages object
```

This is called **Inversion of Control (IoC)**.

---

## 🧠 What I Learned From This Project

1. What Spring Framework is.
2. What a Spring Container is.
3. What `ApplicationContext` does.
4. What a Spring Bean is.
5. How to configure a Bean using XML.
6. How `ClassPathXmlApplicationContext` loads `spring.xml`.
7. How to retrieve a Bean using `getBean()`.
8. Basic understanding of IoC.
9. Why Spring reduces direct object creation using `new`.

---

## 🎯 Why This Is Useful

In larger applications, manually creating and managing every object can make the code tightly coupled.

Spring manages objects for us and provides features such as:

* IoC
* Dependency Injection
* Bean management
* Loose coupling
* Configuration management
* Easier testing and maintenance

This small project is the **basic foundation for understanding Spring and Spring Boot**.

---

## ⚠️ Important Point

The package name in the XML must match the actual Java class package.

Java:

```java
package org.example;
```

Therefore XML must contain:

```xml
class="org.example.Alien"
```

Not:

```xml
class="com.example.Alien"
```

Otherwise Spring will not be able to create the Bean.

---
