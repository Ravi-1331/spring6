# Creating Interface in Spring

## 📌 What is an Interface?

An **interface** is used to achieve **loose coupling** between classes.

In this example, `Computer` is an interface, and both `Laptop` and `Desktop` implement it.

```text
Computer
   ↑
 ┌─┴──────┐
Laptop   Desktop
```

> ⭐ **The main idea:** `Alien` depends on the **Computer interface**, not directly on `Laptop` or `Desktop`.

---

## 1️⃣ Create `Computer` Interface

```java
public interface Computer {
    void compile();
}
```

The interface defines the method that every implementation must provide.

---

## 2️⃣ Create `Laptop`

```java
public class Laptop implements Computer {

    public void compile() {
        System.out.println("Compiling using Laptop");
    }
}
```

`Laptop` provides its own implementation of `compile()`.

---

## 3️⃣ Create `Desktop`

```java
public class Desktop implements Computer {

    public void compile() {
        System.out.println("Compiling using Desktop");
    }
}
```

`Desktop` also implements `Computer` but provides its own implementation.

---

## 4️⃣ `Alien` Uses the Interface

```java
public class Alien {

    private int age;
    private Computer comp;

    public void setAge(int age) {
        this.age = age;
    }

    public void setComp(Computer comp) {
        this.comp = comp;
    }

    public void code() {
        System.out.println("Coding");
        comp.compile();
    }
}
```

Notice:

```java
private Computer comp;
```

`Alien` does **not** depend directly on:

```java
Laptop
```

or:

```java
Desktop
```

It depends on:

```java
Computer
```

---

# 🔄 How It Works

Spring can inject either implementation:

```text
             Computer
                 ↑
          ┌──────┴──────┐
          ↓             ↓
       Laptop         Desktop
          ↑             ↑
          └─────┬───────┘
                ↓
             Alien
```

So `Alien` can work with **Laptop OR Desktop**.

---

# ⭐ Why Use an Interface?

Without an interface:

```java
private Laptop laptop;
```

`Alien` is tightly connected to `Laptop`.

With an interface:

```java
private Computer comp;
```

`Alien` can work with **any class that implements `Computer`**.

This provides:

* ✅ **Loose Coupling**
* ✅ **Flexibility**
* ✅ **Easy replacement of implementations**
* ✅ **Better maintainability**

---

# 🔥 Important Spring Concept

Spring can decide **which implementation should be injected** through configuration.

For example:

```text
Computer → Laptop
```

or change the configuration to:

```text
Computer → Desktop
```

The `Alien` class doesn't need to change.

> ⭐ **Change the implementation through configuration, not the `Alien` code.**

---

# 🧠 Quick Revision

Remember:

```text
Interface
   ↓
Computer
   ↓
 ┌───────────┐
 ↓           ↓
Laptop     Desktop
```

`Alien`:

```java
private Computer comp;
```

Spring:

```text
Alien → Computer → Laptop
```

or:

```text
Alien → Computer → Desktop
```

### ⭐ One-Line Summary

> **Interfaces allow Spring to inject different implementations into a class, helping achieve loose coupling and flexible application design.**
