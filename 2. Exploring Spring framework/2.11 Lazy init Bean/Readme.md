# Lazy Init Bean in Spring 💤

## 📌 Eager Initialization

By default, Spring creates **all singleton Beans when the ApplicationContext starts**.

```java
ApplicationContext context =
        new ClassPathXmlApplicationContext("spring.xml");
```

At this point, Spring creates the Beans defined in `spring.xml`, even if you don't immediately use them.

```text
ApplicationContext starts
        ↓
All singleton Beans created
        ↓
Application ready
```

This is called **Eager Initialization**.

---

# 💤 Lazy Initialization

**Lazy initialization** means Spring **delays creating a Bean until it is actually needed**.

Use:

```xml
lazy-init="true"
```

Example:

```xml
<bean id="comp1"
      class="org.example.Desktop"
      lazy-init="true"/>
```

Now Spring does **not** create the `Desktop` object when the ApplicationContext starts.

It creates it only when:

```java
Desktop obj = (Desktop) context.getBean("comp1");
```

is called.

---

## 🔍 Eager vs Lazy

### Eager Bean

```xml
<bean id="comp" class="org.example.Laptop"/>
```

```text
ApplicationContext starts
        ↓
Laptop object created immediately
```

### Lazy Bean

```xml
<bean id="comp1"
      class="org.example.Desktop"
      lazy-init="true"/>
```

```text
ApplicationContext starts
        ↓
Desktop NOT created
        ↓
getBean("comp1")
        ↓
Desktop object created
```

---

# 🧪 Example

```xml
<bean id="alien1"
      class="org.example.Alien"
      autowire="byType">

    <property name="age" value="21"/>
</bean>

<bean id="comp"
      class="org.example.Laptop"
      primary="true"/>

<bean id="comp1"
      class="org.example.Desktop"
      lazy-init="true"/>
```

When this runs:

```java
ApplicationContext context =
        new ClassPathXmlApplicationContext("spring.xml");
```

### Spring creates:

```text
Alien   → Created immediately ✅
Laptop  → Created immediately ✅
Desktop → Not created yet ❌
```

Then:

```java
Desktop obj = (Desktop) context.getBean("comp1");
```

Now:

```text
Desktop → Created ✅
```

---

# ⭐ Lazy Bean is Still Singleton

`lazy-init="true"` **does not change the Bean scope**.

The default scope is still **singleton**.

```java
Desktop obj1 = (Desktop) context.getBean("comp1");
Desktop obj2 = (Desktop) context.getBean("comp1");
```

Both references point to the **same object**:

```text
obj1
 ↓
 ┌──────────┐
 │ Desktop  │
 └──────────┘
 ↑
obj2
```

So:

> **Lazy = when the object is created**
> **Singleton = how many objects are created**

---

# ⚠️ Important: Dependency Overrides Lazy Initialization

Suppose an **eager Bean depends on a lazy Bean**:

```xml
<bean id="alien1"
      class="org.example.Alien">

    <property name="age" value="21"/>
    <property name="comp" ref="comp1"/>
</bean>

<bean id="comp"
      class="org.example.Laptop"
      primary="true"/>

<bean id="comp1"
      class="org.example.Desktop"
      lazy-init="true"/>
```

Here `comp1` is marked:

```xml
lazy-init="true"
```

But `alien1` needs `comp1`:

```xml
ref="comp1"
```

Therefore, when Spring creates `alien1`, it **must create `comp1` as well**.

```text
ApplicationContext starts
        ↓
Create Alien
        ↓
Alien needs comp1
        ↓
Create Desktop
        ↓
Inject Desktop into Alien
```

So **lazy initialization is not absolute**. If another eagerly created Bean requires the lazy Bean, Spring creates it to satisfy that dependency.

---

# 🧠 Quick Revision

### Default

```xml
<bean id="alien" class="org.example.Alien"/>
```

➡️ Created when **ApplicationContext starts**.

### Lazy

```xml
<bean id="alien"
      class="org.example.Alien"
      lazy-init="true"/>
```

➡️ Created when **`getBean()` is called**.

### Remember

```text
Eager → Create at startup
Lazy  → Create when needed
```

⭐ **Lazy initialization can improve startup time when there are Beans that aren't needed immediately.**

---

## ⭐ One-Line Summary

**`lazy-init="true"` tells Spring to delay Bean creation until the Bean is requested or required as a dependency.**
