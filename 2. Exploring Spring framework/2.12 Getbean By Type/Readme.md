# getBean() By Type in Spring 🔍

## 📌 What is `getBean()`?

`getBean()` is used to **retrieve a Bean/object from the Spring IoC container**.

Spring provides different ways to retrieve a Bean.

---

# 1️⃣ `getBean(String id)`

We can retrieve a Bean using its **Bean ID**.

```java
Alien obj1 = (Alien) context.getBean("alien1");
```

Here:

```text
"alien1" → Bean ID
(Alien)  → Type casting
```

By default, this version returns an **`Object`**, so we need to type-cast it.

### 🧠 Remember

```java
context.getBean("alien1");
```

➡️ **ID → Object → Casting required**

---

# 2️⃣ `getBean(String id, Class<T> type)`

We can provide both the **Bean ID and class type**.

```java
Alien obj1 = context.getBean("alien1", Alien.class);
```

Here Spring knows that we want an `Alien`, so **no manual casting is required**.

### 🆚 Comparison

```java
// Casting required
Alien obj1 = (Alien) context.getBean("alien1");

// No casting required
Alien obj2 = context.getBean("alien1", Alien.class);
```

### 🧠 Remember

```text
ID + Type → No casting
```

---

# 3️⃣ `getBean(Class<T> type)`

We can also retrieve a Bean **directly by its class type**.

```java
Alien obj1 = context.getBean(Alien.class);
```

Spring searches the container for a Bean of type `Alien`.

### ⚠️ Important

This works when there is **only one matching Bean** of that type.

For example:

```xml
<bean id="alien1" class="org.example.Alien"/>
```

Then:

```java
Alien obj = context.getBean(Alien.class);
```

✅ Works.

---

## ❌ Multiple Beans of Same Type

Suppose:

```xml
<bean id="alien1" class="org.example.Alien"/>
<bean id="alien2" class="org.example.Alien"/>
```

Now:

```java
Alien obj = context.getBean(Alien.class);
```

Spring finds:

```text
Alien
 ├── alien1
 └── alien2
```

Spring cannot decide which Bean to return.

❌ It throws:

```text
NoUniqueBeanDefinitionException
```

In this situation, use the **Bean ID**:

```java
Alien obj = context.getBean("alien1", Alien.class);
```

---

# 🆚 Three Ways to Use `getBean()`

| Method                   | Example                          | Casting? |
| ------------------------ | -------------------------------- | -------- |
| `getBean(String)`        | `getBean("alien1")`              | ✅ Yes    |
| `getBean(String, Class)` | `getBean("alien1", Alien.class)` | ❌ No     |
| `getBean(Class)`         | `getBean(Alien.class)`           | ❌ No     |

---

# 🧠 Quick Revision

```java
// 1. By ID
Alien obj = (Alien) context.getBean("alien1");

// 2. By ID + Type
Alien obj = context.getBean("alien1", Alien.class);

// 3. By Type
Alien obj = context.getBean(Alien.class);
```

### ⭐ Easy Memory Trick

```text
ID only       → Casting required
ID + Type     → No casting
Type only     → No casting, but must have one matching Bean
```

## ⭐ One-Line Summary

**`getBean()` retrieves Beans from the Spring container, either by ID, by ID + type, or directly by type.**
