# `@Primary` and `@Qualifier` in Spring ⭐

## 📌 Why Do We Need Them?

Suppose Spring has **two Beans of the same type**:

```text
Computer
 ├── Laptop
 └── Desktop
```

And `Alien` needs:

```java
Computer computer;
```

Spring doesn't know which Bean to inject. ❌

We can solve this using:

* **`@Qualifier`** → explicitly choose a Bean
* **`@Primary`** → mark one Bean as the default choice

---

# 1️⃣ `@Qualifier`

`@Qualifier` tells Spring **exactly which Bean to inject**.

### Configuration

```java
@Configuration
public class AppConfig {

    @Bean
    public Desktop desktop() {
        return new Desktop();
    }

    @Bean
    public Laptop laptop() {
        return new Laptop();
    }
}
```

Both Beans implement `Computer`.

### Alien

```java
@Component
class Alien {

    private Computer computer;

    @Autowired
    public Alien(@Qualifier("laptop") Computer computer) {
        this.computer = computer;
    }

    public void code() {
        System.out.println("Coding...");
        computer.compile();
    }
}
```

Here:

```java
@Qualifier("laptop")
```

means:

> ⭐ **Inject the Bean whose name is `laptop`.**

So:

```text
Alien
  ↓
Computer
  ↓
@Qualifier("laptop")
  ↓
Laptop Bean
```

Output:

```text
Coding...
Compiling using Laptop
```

---

# 2️⃣ `@Primary`

Instead of explicitly choosing a Bean every time, we can mark **one Bean as the default**.

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

Now if Alien has:

```java
@Autowired
public Alien(Computer computer) {
    this.computer = computer;
}
```

Spring sees:

```text
Computer
 ├── Laptop ⭐ @Primary
 └── Desktop
```

So Spring injects **Laptop**.

---

# 🆚 `@Primary` vs `@Qualifier`

| `@Primary`                                | `@Qualifier`                                 |
| ----------------------------------------- | -------------------------------------------- |
| Defines the **default** Bean              | Explicitly chooses a Bean                    |
| Used when no qualifier is specified       | Overrides the default choice                 |
| Good for the commonly used implementation | Good when you need a specific implementation |
| `@Primary`                                | `@Qualifier("laptop")`                       |

### Easy Example

```text
@Primary
   ↓
"Use Laptop by default"

@Qualifier("desktop")
   ↓
"For this particular injection, use Desktop"
```

---

# ⭐ `@Qualifier` Overrides `@Primary`

Suppose:

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

Laptop is the primary Bean.

But:

```java
@Autowired
public Alien(@Qualifier("desktop") Computer computer) {
    this.computer = computer;
}
```

Spring injects **Desktop**, because `@Qualifier` explicitly selects it.

### Priority

```text
@Qualifier
    ↓
@Primary
    ↓
No preference → Error if multiple candidates
```

---

# 🔍 Complete Example

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

### Without `@Qualifier`

```java
@Autowired
public Alien(Computer computer) {
    this.computer = computer;
}
```

➡️ **Laptop** is injected because it is `@Primary`.

### With `@Qualifier`

```java
@Autowired
public Alien(@Qualifier("desktop") Computer computer) {
    this.computer = computer;
}
```

➡️ **Desktop** is injected because `@Qualifier` explicitly selects it.

---

# 🧠 Quick Revision

### Multiple Beans

```text
Computer
 ├── Laptop
 └── Desktop
```

### `@Primary`

```java
@Primary
@Bean
public Laptop laptop() {
    return new Laptop();
}
```

➡️ **Default choice**

### `@Qualifier`

```java
@Qualifier("desktop")
```

➡️ **Specific choice**

---

## ⭐ One-Line Summary

**`@Primary` tells Spring which Bean to choose by default, while `@Qualifier` tells Spring exactly which Bean to inject when multiple Beans of the same type exist.**
