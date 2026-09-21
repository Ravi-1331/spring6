# Inner Bean in Spring 🔒

## 📌 What is an Inner Bean?

An **Inner Bean** is a Bean defined **inside another Bean**.

It is useful when an object is needed **only by the outer Bean** and does not need to be reused anywhere else.

```text
Outer Bean
   ↓
  Alien
   ↓
Inner Bean
   ↓
 Laptop
```

---

## 🔹 Example

```xml id="7i8yq0"
<bean id="alien1" class="org.example.Alien">

    <property name="age" value="21"/>

    <!-- Inner Bean -->
    <property name="comp">
        <bean class="org.example.Laptop"/>
    </property>

</bean>
```

Here:

* **Outer Bean** → `Alien`
* **Inner Bean** → `Laptop`
* `Laptop` is injected into the `comp` property of `Alien`.

Spring effectively does:

```java id="u3y6n8"
Laptop laptop = new Laptop();
alien.setComp(laptop);
```

---

# ⭐ Key Features

### 1️⃣ Used only inside the Outer Bean

The inner `Laptop` is created specifically for `Alien`.

It is **not intended to be accessed separately** from the Spring container.

---

### 2️⃣ No `id` Required

Normally we define:

```xml id="t6v8s7"
<bean id="lap" class="org.example.Laptop"/>
```

But an Inner Bean doesn't need an ID:

```xml id="u2p4s9"
<bean class="org.example.Laptop"/>
```

Because there is no need to retrieve it independently.

---

### 3️⃣ Created Along With the Outer Bean

When Spring creates:

```text id="f5zj8m"
Alien
 ↓
Laptop (Inner Bean)
```

the inner Bean is created as part of the outer Bean's dependency setup.

---

# 🆚 Normal Bean vs Inner Bean

### Normal Bean

```xml id="5q2c1h"
<bean id="lap" class="org.example.Laptop"/>

<bean id="alien1" class="org.example.Alien">
    <property name="comp" ref="lap"/>
</bean>
```

`Laptop` has an ID and can be referenced elsewhere.

### Inner Bean

```xml id="8c5q3j"
<bean id="alien1" class="org.example.Alien">
    <property name="comp">
        <bean class="org.example.Laptop"/>
    </property>
</bean>
```

`Laptop` is **embedded inside `Alien`** and is not separately referenced.

---

## 🧠 Quick Revision

```text id="j3p4w7"
Inner Bean
    ↓
Bean inside another Bean
    ↓
Used only by Outer Bean
    ↓
No ID required
```

### ⭐ Easy Memory Trick

**Outer Bean = owner 🏠**
**Inner Bean = private dependency 🔒**

---

## ⭐ One-Line Summary

**An Inner Bean is a Bean defined inside another Bean when the dependency is private to the outer Bean and doesn't need to be reused elsewhere.**
