# Autowiring in Spring 🔗

## 📌 What is Autowiring?

**Autowiring** is a Spring mechanism that automatically **injects dependencies** into a Bean.

Normally, we manually connect Beans using `ref`:

```xml
<property name="comp" ref="comp"/>
```

With **autowiring**, Spring finds the required dependency automatically based on **name or type**.

---

## 🔌 Wiring vs Autowiring

### Manual Wiring

```xml
<bean id="alien1" class="org.example.Alien">
    <property name="comp" ref="comp"/>
</bean>

<bean id="comp" class="org.example.Laptop"/>
```

Here, we explicitly tell Spring:

> Inject the `comp` Bean into the `comp` property.

### Autowiring

```xml
<bean id="alien1" class="org.example.Alien" autowire="byName"/>

<bean id="comp" class="org.example.Laptop"/>
```

Spring automatically finds the dependency.

---

# ⚙️ Autowiring Modes

Spring provides different autowiring modes:

| Mode          | How Spring Finds Dependency              |
| ------------- | ---------------------------------------- |
| `byName`      | Matches **property name** with Bean `id` |
| `byType`      | Matches **property type** with Bean type |
| `constructor` | Uses constructor parameters              |
| `autodetect`  | Constructor first, then `byType`         |

> ⭐ **Most important:** `byName` and `byType`.

---

# 1️⃣ Autowiring by Name

Spring looks for a Bean whose **ID matches the property name**.

### `Alien.java`

```java
private Computer comp;

public void setComp(Computer comp) {
    this.comp = comp;
}
```

### `spring.xml`

```xml
<bean id="alien1" class="org.example.Alien"
      autowire="byName">

    <property name="age" value="21"/>
</bean>

<bean id="comp" class="org.example.Laptop"/>
```

### 🔍 What happens?

Alien has a property:

```java
comp
```

Spring searches for a Bean with:

```xml
id="comp"
```

It finds:

```xml
<bean id="comp" class="org.example.Laptop"/>
```

So Spring automatically performs:

```java
alien.setComp(laptop);
```

### 🧠 Remember

**byName → Property Name = Bean ID**

```text
comp property
     ↓
id="comp"
     ↓
Laptop injected
```

---

## ⭐ Explicit Property Has Priority

If you manually specify `ref`, Spring uses that instead of autowiring.

```xml
<bean id="alien1" class="org.example.Alien"
      autowire="byName">

    <property name="age" value="21"/>
    <property name="comp" ref="comp1"/>
</bean>

<bean id="comp" class="org.example.Laptop"/>
<bean id="comp1" class="org.example.Desktop"/>
```

Here:

```xml
ref="comp1"
```

explicitly tells Spring to inject `Desktop`.

So:

```text
Manual ref
   ↓
comp1
   ↓
Desktop
```

---

# 2️⃣ Autowiring by Type

Spring looks at the **property type** and finds a compatible Bean.

Suppose:

```java
private Computer comp;
```

The property type is:

```java
Computer
```

And:

```java
public class Laptop implements Computer
```

### `spring.xml`

```xml
<bean id="alien1" class="org.example.Alien"
      autowire="byType">

    <property name="age" value="21"/>
</bean>

<bean id="comp" class="org.example.Laptop"/>
```

Spring sees:

```text
Alien.comp
   ↓
Computer type
   ↓
Laptop implements Computer
   ↓
Laptop injected
```

So Spring effectively does:

```java
alien.setComp(laptop);
```

### 🧠 Remember

**byType → Property Type = Bean Type**

---

# ⚠️ Multiple Beans with `byType`

Suppose we have:

```xml
<bean id="laptop" class="org.example.Laptop"/>
<bean id="desktop" class="org.example.Desktop"/>
```

And both implement:

```java
Computer
```

But Alien has:

```java
private Computer comp;
```

Now Spring finds **two possible Beans**:

```text
Computer
 ├── Laptop
 └── Desktop
```

Spring cannot decide which one to inject.

❌ This causes an **autowiring conflict/error**.

### Solutions

You can:

* Use **`byName`**
* Use a **primary Bean**
* Provide an **explicit `ref`**

---

# 🆚 byName vs byType

| `byName`                      | `byType`                                              |
| ----------------------------- | ----------------------------------------------------- |
| Matches **property name**     | Matches **property type**                             |
| Uses Bean `id`                | Uses Java type                                        |
| `comp` → `id="comp"`          | `Computer` → `Laptop`                                 |
| Can distinguish Beans by name | Can cause conflict if multiple compatible Beans exist |

---

# 🧠 Quick Revision

```text
Autowiring
   ↓
Spring automatically injects dependencies
```

### `byName`

```text
Property Name = Bean ID
```

### `byType`

```text
Property Type = Bean Type
```

### Manual wiring

```xml
<property name="comp" ref="comp"/>
```

### Autowiring

```xml
<bean ... autowire="byName"/>
```

---

## ⭐ One-Line Summary

**Autowiring allows Spring to automatically connect dependencies using the Bean's name, type, or constructor instead of manually specifying `ref`.**
