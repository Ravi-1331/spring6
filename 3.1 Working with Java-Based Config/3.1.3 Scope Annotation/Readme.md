# Scope in Spring 🔄

## 📌 What is Bean Scope?

**Bean Scope** defines **how many objects Spring creates** and how long they are managed.

The two commonly used scopes are:

* **Singleton** → One shared object
* **Prototype** → New object every time it is requested

---

# 1️⃣ Singleton Scope

**Singleton is the default scope in Spring.**

Only **one instance** of a Bean is created within the Spring container.

```java
@Bean
public Desktop desktopSingleton() {
    return new Desktop();
}
```

Calling `getBean()` multiple times returns the **same object**:

```java
Desktop dt1 =
    context.getBean("desktopSingleton", Desktop.class);

Desktop dt2 =
    context.getBean("desktopSingleton", Desktop.class);

System.out.println(dt1 == dt2);
```

Output:

```text
true
```

```text
getBean() ──┐
            ↓
       ┌─────────┐
       │ Desktop │
       └─────────┘
            ↑
getBean() ──┘
```

### 🧠 Remember

**Singleton → One Bean instance → Shared**

---

# 2️⃣ Prototype Scope

With **prototype scope**, Spring creates a **new instance every time the Bean is requested**.

Use:

```java
@Scope("prototype")
```

Example:

```java
@Bean
@Scope("prototype")
public Desktop desktopPrototype() {
    return new Desktop();
}
```

Now:

```java
Desktop dt3 =
    context.getBean("desktopPrototype", Desktop.class);

Desktop dt4 =
    context.getBean("desktopPrototype", Desktop.class);

System.out.println(dt3 == dt4);
```

Output:

```text
false
```

Because:

```text
getBean() → Desktop Object 1
getBean() → Desktop Object 2
```

---

# 🧪 Complete Example

### `AppConfig.java`

```java
@Configuration
public class AppConfig {

    // Singleton scope (default)
    @Bean
    public Desktop desktopSingleton() {
        return new Desktop();
    }

    // Prototype scope
    @Bean
    @Scope("prototype")
    public Desktop desktopPrototype() {
        return new Desktop();
    }
}
```

### `App.java`

```java
public class App {

    public static void main(String[] args) {

        ApplicationContext context =
                new AnnotationConfigApplicationContext(AppConfig.class);

        // Singleton
        Desktop dt1 =
                context.getBean("desktopSingleton", Desktop.class);

        Desktop dt2 =
                context.getBean("desktopSingleton", Desktop.class);

        System.out.println(dt1 == dt2);  // true


        // Prototype
        Desktop dt3 =
                context.getBean("desktopPrototype", Desktop.class);

        Desktop dt4 =
                context.getBean("desktopPrototype", Desktop.class);

        System.out.println(dt3 == dt4);  // false
    }
}
```

---

# 🆚 Singleton vs Prototype

| Singleton             | Prototype                          |
| --------------------- | ---------------------------------- |
| **Default scope**     | Must specify `@Scope("prototype")` |
| One instance          | New instance for each `getBean()`  |
| Same object is reused | Different objects are created      |
| `dt1 == dt2` → `true` | `dt3 == dt4` → `false`             |

---

## 🧠 Quick Revision

```text
Singleton
   ↓
One object
   ↓
Shared across getBean() calls
```

```text
Prototype
   ↓
New object
   ↓
Every getBean() call
```

### ⭐ Easy Memory Trick

**Singleton = Same 🟢**
**Prototype = New 🔵**

---

## ⭐ One-Line Summary

**Singleton creates one shared Bean instance, while Prototype creates a new Bean instance every time the Bean is requested.**
