# `@Primary` Annotation ⭐

## 🔹 What is `@Primary`?

When multiple Beans have the **same type**, Spring may not know which Bean to inject.

`@Primary` tells Spring:

> **Use this Bean as the default choice when no `@Qualifier` is specified.**

---

## 🔹 Example

```java
@Configuration
public class AppConfig {

    @Bean
    @Primary
    public Laptop laptop() {
        return new Laptop();
    }

    @Bean
    public Desktop desktop() {
        return new Desktop();
    }
}
```

Both `Laptop` and `Desktop` implement `Computer`:

```text
Computer
   ├── Laptop   ⭐ @Primary
   └── Desktop
```

Now if we have:

```java
@Component
public class Alien {

    private final Computer computer;

    public Alien(Computer computer) {
        this.computer = computer;
    }
}
```

Spring injects:

```text
Computer → Laptop
```

because `Laptop` is marked `@Primary`.

---

## 🔹 `@Primary` vs `@Qualifier`

If both are used, **`@Qualifier` has higher priority**.

### Configuration

```java
@Bean
@Primary
public Laptop laptop() {
    return new Laptop();
}

@Bean
public Desktop desktop() {
    return new Desktop();
}
```

Without `@Qualifier`:

```java
public Alien(Computer computer) {
    this.computer = computer;
}
```

➡️ **Laptop is injected** because it is `@Primary`.

---

With `@Qualifier`:

```java
public Alien(@Qualifier("desktop") Computer computer) {
    this.computer = computer;
}
```

➡️ **Desktop is injected**, even though Laptop is `@Primary`.

### 🧠 Remember

```text
@Primary   → Default choice
@Qualifier → Specific choice ⭐
```

**`@Qualifier` takes precedence over `@Primary`.**

---

## 🔹 Quick Revision

| Situation                     | Bean Selected       |
| ----------------------------- | ------------------- |
| One Bean                      | That Bean           |
| Multiple Beans + `@Primary`   | Primary Bean        |
| Multiple Beans + `@Qualifier` | Qualified Bean      |
| `@Primary` + `@Qualifier`     | `@Qualifier` Bean ⭐ |

### ⭐ One-Line Summary

**`@Primary` defines the default Bean, while `@Qualifier` explicitly selects a specific Bean and takes precedence over `@Primary`.**
