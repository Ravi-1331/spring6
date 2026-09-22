# Bean Name in Spring 🏷️

## 📌 What is a Bean Name?

When we create a Bean using `@Bean`, Spring gives it a **default name**.

By default, the **method name becomes the Bean name**.

```java
@Bean
public Desktop desktop() {
    return new Desktop();
}
```

Here:

```text
Method name → desktop
Bean name   → desktop
```

---

# 1️⃣ Default Bean Name

```java
@Configuration
public class AppConfig {

    @Bean
    public Desktop desktop() {
        return new Desktop();
    }
}
```

The Bean can be retrieved using:

```java
Desktop dt = context.getBean("desktop", Desktop.class);
```

---

# 2️⃣ Custom Bean Name

We can give a Bean a custom name using:

```java
@Bean(name = "customName")
```

Example:

```java
@Bean(name = "com2")
public Desktop desktop2() {
    return new Desktop();
}
```

Now the Bean name is:

```text
com2
```

So we can retrieve it using:

```java
Desktop dt = context.getBean("com2", Desktop.class);
```

---

# 3️⃣ Multiple Bean Names ⭐

A single Bean can have **multiple names/aliases**.

```java
@Bean(name = {"com2", "desktop1", "beast"})
public Desktop desktop2() {
    return new Desktop();
}
```

The same Bean can be retrieved using:

```java
context.getBean("com2", Desktop.class);
context.getBean("desktop1", Desktop.class);
context.getBean("beast", Desktop.class);
```

All three names refer to the **same Bean**.

```text
             ┌── com2
             │
One Bean ────┼── desktop1
             │
             └── beast
```

---

# 🧪 Complete Example

### `AppConfig.java`

```java
@Configuration
public class AppConfig {

    // Default Bean name = desktop
    @Bean
    public Desktop desktop() {
        return new Desktop();
    }

    // Multiple Bean names
    @Bean(name = {"com2", "desktop1", "beast"})
    public Desktop desktop2() {
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

        Desktop dt1 =
                context.getBean("desktop", Desktop.class);

        Desktop dt2 =
                context.getBean("desktop1", Desktop.class);

        Desktop dt3 =
                context.getBean("beast", Desktop.class);

        dt1.compile();
        dt2.compile();
        dt3.compile();
    }
}
```

---

# 🧠 Quick Revision

### Default Name

```java
@Bean
public Desktop desktop()
```

➡️ Bean name = **`desktop`**

### Custom Name

```java
@Bean(name = "com2")
```

➡️ Bean name = **`com2`**

### Multiple Names

```java
@Bean(name = {"com2", "desktop1", "beast"})
```

➡️ One Bean can be accessed using **any of these names**.

---

## ⭐ One-Line Summary

**By default, Spring uses the `@Bean` method name as the Bean name, but `@Bean(name = {...})` lets you define custom names or multiple aliases for the same Bean.**
