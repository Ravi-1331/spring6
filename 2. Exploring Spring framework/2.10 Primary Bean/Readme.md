# Primary Bean in Spring ⭐

## 📌 What is a Primary Bean?

When using **autowiring by type**, Spring can face a problem if **multiple Beans have the same type**.

For example:

```text
Computer
 ├── Laptop
 └── Desktop
```

Both implement `Computer`, so Spring doesn't know which one to inject.

👉 We can solve this by marking one Bean as **`primary="true"`**.

---

## 🔹 Example

```xml
<bean id="alien1" class="org.example.Alien"
      autowire="byType">

    <property name="age" value="21"/>
</bean>

<bean id="comp" class="org.example.Laptop"
      primary="true"/>

<bean id="comp1" class="org.example.Desktop"/>
```

Here:

* `Laptop` implements `Computer`
* `Desktop` implements `Computer`
* Both are available in the container
* `Laptop` is marked as **primary**

```xml
primary="true"
```

So Spring chooses **Laptop** when autowiring `Computer`.

---

## 🔍 How Spring Decides

```text
Alien
  ↓
Computer comp
  ↓
Multiple Beans found
  ↓
Laptop → primary="true" ⭐
Desktop
  ↓
Laptop injected
```

Spring effectively does:

```java
alien.setComp(laptop);
```

---

## ⭐ Primary vs Explicit `ref`

The **primary Bean is only the default choice**.

If we explicitly specify another Bean using `ref`, Spring uses that Bean instead.

```xml
<bean id="alien1" class="org.example.Alien"
      autowire="byType">

    <property name="comp" ref="comp1"/>
</bean>

<bean id="comp" class="org.example.Laptop"
      primary="true"/>

<bean id="comp1" class="org.example.Desktop"/>
```

Even though `Laptop` is primary:

```xml
primary="true"
```

the explicit:

```xml
ref="comp1"
```

tells Spring to inject **Desktop**.

### 🧠 Priority

```text
Explicit ref
    ↓
Primary Bean
    ↓
Autowiring by type
```

---

## 🆚 Without Primary vs With Primary

### ❌ Without Primary

```text
Computer
 ├── Laptop
 └── Desktop
```

Spring → **Which one should I inject?** ❌

### ✅ With Primary

```text
Computer
 ├── Laptop ⭐ PRIMARY
 └── Desktop
```

Spring → **Inject Laptop** ✅

---

## 🧠 Quick Revision

```xml
primary="true"
```

means:

> ⭐ **"Prefer this Bean when autowiring by type."**

### Remember:

* **Multiple Beans + `byType`** → conflict
* **One `primary="true"`** → conflict resolved
* **Explicit `ref`** → overrides the primary choice

---

## ⭐ One-Line Summary

**`primary="true"` tells Spring which Bean to prefer when multiple compatible Beans are available for autowiring by type.**
