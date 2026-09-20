# Bean Scopes in Spring

## 📌 What is Bean Scope?

**Bean Scope** decides **how many objects Spring creates** for a Bean and **how those objects are shared**.

---

## 🔑 Types of Bean Scopes

### 1️⃣ Singleton — Default

**Only ONE object is created** for a Bean inside the Spring Container.

Every time we use `getBean()`, we get the **same object**.

```java
Alien obj1 = (Alien) context.getBean("alien1");
Alien obj2 = (Alien) context.getBean("alien1");
```

```text
obj1 ─────┐
          ├──→ Same Alien Object
obj2 ─────┘
```

> ⭐ **Singleton is the default scope in Spring.**

---

### 2️⃣ Prototype

**A new object is created every time `getBean()` is called.**

```java
Alien obj1 = (Alien) context.getBean("alien1");
Alien obj2 = (Alien) context.getBean("alien1");
```

```text
obj1 ───→ Alien Object 1

obj2 ───→ Alien Object 2
```

So:

**`obj1 != obj2`**

---

### 3️⃣ Request 🌐

A **new Bean object is created for every HTTP request**.

> Used mainly in **Spring Web applications**.

---

### 4️⃣ Session 🌐

A **new Bean object is created for every HTTP session**.

> Used mainly in **Spring Web applications**.

---

# ⭐ Singleton Example

### `spring.xml`

```xml
<bean id="alien1" class="org.example.Alien"/>
```

By default, this Bean has **Singleton scope**.

### `App.java`

```java
ApplicationContext context =
        new ClassPathXmlApplicationContext("spring.xml");

Alien obj1 = (Alien) context.getBean("alien1");

obj1.age = 21;

Alien obj2 = (Alien) context.getBean("alien1");

System.out.println(obj2.age);
```

### Output

```text
21
```

Why?

Because `obj1` and `obj2` refer to the **same object**.

```text
obj1 ─────┐
          ↓
      Alien Object
          ↑
obj2 ─────┘
```

---

# 🔄 Changing Bean Scope

We can specify the scope using the `scope` attribute.

### Singleton

```xml
<bean id="alien1"
      class="org.example.Alien"
      scope="singleton"/>
```

Or simply:

```xml
<bean id="alien1"
      class="org.example.Alien"/>
```

**Singleton is the default.**

---

### Prototype

```xml
<bean id="alien1"
      class="org.example.Alien"
      scope="prototype"/>
```

Now:

```java
context.getBean("alien1");
context.getBean("alien1");
```

creates **two different objects**.

---

# 🧠 Quick Revision

| Scope         | Objects Created                  |
| ------------- | -------------------------------- |
| **Singleton** | One object                       |
| **Prototype** | New object for every `getBean()` |
| **Request**   | One object per HTTP request      |
| **Session**   | One object per HTTP session      |

> 🔥 **Remember:**
> **Singleton = Same object**
> **Prototype = New object**

---

## 🎯 Why Bean Scopes Matter?

Bean scopes control the **lifecycle and sharing of objects** in a Spring application.

For example:

* **Singleton** → when one shared object is sufficient.
* **Prototype** → when you need a fresh object each time.

### ⭐ One-Line Summary

> **Bean Scope tells Spring how many instances of a Bean should be created and how they should be shared.**
