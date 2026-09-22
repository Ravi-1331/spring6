# `@Scope` and `@Value` Annotations 🔧

## 🔹 1. `@Scope` Annotation

`@Scope` defines **how and when Spring creates Bean instances**.

### Default Scope → Singleton

By default, Spring creates **one instance** of a Bean per ApplicationContext.

```java
@Component
@Scope("singleton")
public class Desktop {
}
```

### Prototype Scope

`@Scope("prototype")` creates a **new instance every time the Bean is requested from the Spring container**.

```java
@Component
@Primary
@Scope("prototype")
public class Desktop implements Computer {

    public Desktop() {
        System.out.println("Desktop Object Created..");
    }

    @Override
    public void compile() {
        System.out.println("Compiling using Desktop");
    }
}
```

### Example

```java
Desktop d1 = context.getBean(Desktop.class);
Desktop d2 = context.getBean(Desktop.class);

System.out.println(d1 == d2); // false
```

Because prototype creates a new object for each `getBean()` request.

> **Note:** `prototype` means a new instance when obtained from the container. If a prototype Bean is injected into a singleton Bean, Spring does not automatically create a new instance every time you access that singleton's field.

### Common Scopes

| Scope         | Meaning                               |
| ------------- | ------------------------------------- |
| `singleton`   | One instance per ApplicationContext ⭐ |
| `prototype`   | New instance when requested           |
| `request`     | One instance per HTTP request         |
| `session`     | One instance per HTTP session         |
| `application` | One instance per ServletContext       |

---

# 🔹 2. `@Value` Annotation

`@Value` is used to **inject values into Spring-managed Beans**.

### Example

```java
@Component
public class Alien {

    @Value("21")
    private int age;

    private Computer computer;

    public Alien() {
        System.out.println("Alien Object Created");
    }
}
```

Here:

```java
@Value("21")
private int age;
```

Spring injects:

```text
age = 21
```

---

## 🔹 `@Value` with Property File

Instead of hardcoding a value:

```java
@Value("21")
private int age;
```

You can read it from `application.properties`:

```properties
alien.age=21
```

Then:

```java
@Value("${alien.age}")
private int age;
```

This makes the value **configurable without changing Java code**.

---

## 🔹 `@Value` Can Inject

```java
@Value("21")
private int age;

@Value("Ravi")
private String name;

@Value("${alien.age}")
private int configuredAge;
```

It can be used with:

* Hardcoded values
* Property-file values
* Expressions

---

## 🧠 Quick Revision

```text
@Scope("singleton") → Same Bean instance
@Scope("prototype") → New instance when requested

@Value("21")         → Inject value
@Value("${alien.age}") → Read from property/config
```

### ⭐ One-Line Summary

**`@Scope` controls the Bean's lifecycle/scope, while `@Value` injects values or configuration properties into Spring Beans.**
