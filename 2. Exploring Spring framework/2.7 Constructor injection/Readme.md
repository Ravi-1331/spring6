# Constructor Injection in Spring

## 📌 What is Constructor Injection?

**Constructor Injection** is a way of providing **values or dependencies to a Bean through its constructor** when the object is created.

> ⭐ **Setter Injection → dependency is injected through a setter method.**
> ⭐ **Constructor Injection → dependency is injected through a constructor.**

---

## 🔑 `<constructor-arg>` Tag

Spring uses the `<constructor-arg>` tag to pass values or other Beans to a constructor.

It mainly uses:

* `value` → for **normal/primitive values**
* `ref` → for **another Spring Bean**

---

# 💻 Example

### `Alien.java`

```java
package org.example;

import java.beans.ConstructorProperties;

public class Alien {

    private int age;
    private Laptop lap;

    public Alien() {
        System.out.println("Object Created");
    }

    @ConstructorProperties({"age", "lap"})
    public Alien(int age, Laptop lap) {
        System.out.println("Parameterized Constructor Called");
        this.age = age;
        this.lap = lap;
    }

    public int getAge() {
        return age;
    }

    public void code() {
        System.out.println("Coding");
        lap.compile();
    }
}
```

---

### `Laptop.java`

```java
package org.example;

public class Laptop {

    public Laptop() {
        System.out.println("Laptop object created");
    }

    public void compile() {
        System.out.println("Compiling");
    }
}
```

---

# ⚙️ `spring.xml`

```xml
<bean id="alien1" class="org.example.Alien">

    <constructor-arg value="21"/>
    <constructor-arg ref="lap1"/>

</bean>

<bean id="lap1" class="org.example.Laptop"/>
```

### What happens here?

```xml
<constructor-arg value="21"/>
```

➡️ Passes **21** to the `age` parameter.

```xml
<constructor-arg ref="lap1"/>
```

➡️ Passes the **Laptop Bean** to the `lap` parameter.

So Spring effectively does:

```java
Laptop laptop = ...;

Alien alien = new Alien(21, laptop);
```

---

# 🔄 How It Works

```text
spring.xml
    ↓
Spring creates Laptop Bean
    ↓
Spring finds Alien constructor
    ↓
21 → age
Laptop Bean → lap
    ↓
Alien(21, laptop)
    ↓
Alien Object Created
```

> ⭐ **The dependency is provided at the time of object creation.**

---

# 🆚 Setter vs Constructor Injection

### Setter Injection

```java
Alien obj = new Alien();

obj.setAge(21);
obj.setLap(laptop);
```

The object is created **first**, then values are injected.

### Constructor Injection

```java
Alien obj = new Alien(21, laptop);
```

The values/dependencies are provided **during object creation**.

---

# 🧪 `App.java`

```java
package org.example;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class App {

    public static void main(String[] args) {

        ApplicationContext context =
                new ClassPathXmlApplicationContext("spring.xml");

        Alien obj =
                (Alien) context.getBean("alien1");

        System.out.println(obj.getAge());

        obj.code();
    }
}
```

### Output

```text
Laptop object created
Parameterized Constructor Called
21
Coding
Compiling
```

---

# 🔧 Handling Constructor Arguments

Sometimes Spring needs help identifying which argument belongs to which constructor parameter.

There are **3 useful attributes**:

## 1️⃣ `type`

Used to specify the **data type** of an argument.

```xml
<constructor-arg type="int" value="21"/>
<constructor-arg type="org.example.Laptop" ref="lap1"/>
```

---

## 2️⃣ `index`

Used to specify the **position of the constructor argument**.

> ⚠️ Index starts from **0**.

```xml
<constructor-arg index="0" value="21"/>
<constructor-arg index="1" ref="lap1"/>
```

So:

```text
index 0 → age
index 1 → lap
```

---

## 3️⃣ `name`

Used to specify the **constructor parameter name**.

```xml
<constructor-arg name="age" value="21"/>
<constructor-arg name="lap" ref="lap1"/>
```

---

# ⭐ `@ConstructorProperties`

We can use `@ConstructorProperties` to tell Spring the exact names of the constructor parameters.

```java
@ConstructorProperties({"age", "lap"})
public Alien(int age, Laptop lap) {

    this.age = age;
    this.lap = lap;
}
```

This tells Spring:

```text
age → first constructor parameter
lap  → second constructor parameter
```

It can help Spring correctly map constructor arguments **by name**, including when XML arguments are not written in the same order.

---

# 🧠 Quick Revision

Remember:

```text
Constructor Injection
        ↓
<constructor-arg>
        ↓
 ┌───────────────┐
 │ value         │ → Normal value
 │ ref           │ → Another Bean
 │ type          │ → Argument type
 │ index         │ → Argument position
 │ name          │ → Parameter name
 └───────────────┘
```

### Example:

```xml
<constructor-arg value="21"/>
<constructor-arg ref="lap1"/>
```

Means:

```java
new Alien(21, laptop);
```

---

# 🎯 Why Constructor Injection?

Constructor Injection is useful when a dependency is **required for the object to work properly**.

It ensures the required dependency is provided **when the object is created**.

---

## ⭐ One-Line Summary

> **Constructor Injection means Spring provides values or Bean dependencies through a constructor while creating the object.**

