# Ref Attribute in Spring

## 📌 What is `ref`?

The **`ref` attribute** is used in Spring to **inject one Bean into another Bean**.

In simple words:

> ⭐ **`value` is used for normal values, while `ref` is used to inject another object (Bean).**

---

## 🔑 `value` vs `ref`

### `value` → Injects a normal value

```xml
<property name="age" value="21"/>
```

This is similar to:

```java
obj.setAge(21);
```

### `ref` → Injects another Bean

```xml
<property name="lap" ref="lap1"/>
```

This means:

**Find the Bean with ID `lap1` and inject that object into the `lap` property.**

---

# 💻 Example

### `Alien.java`

```java
package org.example;

public class Alien {

    private int age;
    private Laptop lap;

    public Alien() {
        System.out.println("Alien object created");
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public void setLap(Laptop lap) {
        this.lap = lap;
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

    <property name="age" value="21"/>
    <property name="lap" ref="lap1"/>

</bean>

<bean id="lap1" class="org.example.Laptop"/>
```

### What does this mean?

```xml
<property name="lap" ref="lap1"/>
```

* `name="lap"` → Alien's **`lap` property**
* `ref="lap1"` → Bean ID of the **Laptop object**

Spring essentially does:

```java
Laptop laptop = context.getBean("lap1");

Alien alien = context.getBean("alien1");

alien.setLap(laptop);
```

So the relationship becomes:

```text
Alien
  |
  | lap
  ↓
Laptop
```

---

# 🔄 How It Works

When Spring starts:

```text
spring.xml
    ↓
Creates Alien Bean
    ↓
Creates Laptop Bean
    ↓
Finds ref="lap1"
    ↓
Injects Laptop object into Alien
    ↓
Alien can use Laptop
```

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
Alien object created
Laptop object created
21
Coding
Compiling
```

---

# ⚠️ Important Point

The Bean referenced by `ref` **must exist** in the Spring configuration.

For example:

```xml
<property name="lap" ref="lap1"/>
```

requires:

```xml
<bean id="lap1" class="org.example.Laptop"/>
```

If `lap1` is not defined, Spring will throw an **error while initializing the container**.

---

# 🔥 Multiple Beans of Same Class

Suppose we have:

```xml
<bean id="lap1" class="org.example.Laptop"/>
<bean id="lap2" class="org.example.Laptop"/>
```

Spring uses the **Bean ID** to know which object should be injected.

```xml
<property name="lap" ref="lap2"/>
```

means:

> Inject **`lap2`**, not `lap1`.

```text
lap1 → Laptop Object 1

lap2 → Laptop Object 2
             ↑
             |
          injected
             |
Alien ←──────┘
```

---

# 🧠 Quick Revision

### Remember:

```text
value → normal value
ref   → another Bean/object
```

Example:

```xml
<property name="age" value="21"/>
```

➡️ Injects **21**

```xml
<property name="lap" ref="lap1"/>
```

➡️ Injects **Laptop Bean `lap1`**

---

## ⭐ One-Line Summary

> **The `ref` attribute is used for Setter Injection when we want to inject one Spring Bean (object) into another Bean.**
