# Using Annotations in Spring Boot 🚀

## 🔹 Why Annotations in Spring Boot?

Spring Boot supports Spring annotations and uses **auto-configuration + component scanning** to reduce configuration and boilerplate code.

Common annotations:

| Annotation               | Purpose                                    |
| ------------------------ | ------------------------------------------ |
| `@Component`             | Creates a Spring-managed Bean              |
| `@Value`                 | Injects values/configuration               |
| `@Autowired`             | Injects dependencies                       |
| `@Qualifier`             | Selects a specific Bean                    |
| `@Primary`               | Defines the default Bean                   |
| `@Bean`                  | Creates a Bean from a method               |
| `@SpringBootApplication` | Main Spring Boot configuration/entry point |

---

## 🔹 1. `@Component`

Marks a class as a Spring-managed Bean.

```java
@Component
public class Alien {
    
    public void code() {
        System.out.println("Coding...");
    }
}
```

Spring automatically detects it through component scanning.

---

## 🔹 2. `@Value`

Used to inject a value into a field.

```java
@Value("25")
private int age;
```

So:

```text
age = 25
```

It can also read from configuration:

```java
@Value("${alien.age}")
private int age;
```

---

## 🔹 3. `@Autowired`

Automatically injects a required dependency.

```java
@Autowired
public void setComp(Computer comp) {
    this.comp = comp;
}
```

Spring finds a suitable `Computer` Bean and passes it to the setter.

---

## 🔹 4. `@Qualifier`

Used when **multiple Beans of the same type** exist.

```java
@Autowired
@Qualifier("laptop")
public void setComp(Computer comp) {
    this.comp = comp;
}
```

Here Spring specifically selects the Bean named `laptop`.

```text
Computer
 ├── Desktop  → @Primary
 └── Laptop   → @Qualifier("laptop") ⭐ selected
```

---

## 🔹 5. `@Primary`

Marks a Bean as the **default choice** when multiple Beans of the same type exist.

```java
@Component
@Primary
public class Desktop implements Computer {

    public void compile() {
        System.out.println("Compiling in Desktop");
    }
}
```

Without a `@Qualifier`, Spring will prefer `Desktop`.

**Remember:**

```text
@Qualifier → Specific choice ⭐
@Primary   → Default choice
```

---

# 🔹 Complete Example

### `Computer.java`

```java
public interface Computer {
    void compile();
}
```

### `Desktop.java`

```java
@Component
@Primary
public class Desktop implements Computer {

    public void compile() {
        System.out.println("Compiling in Desktop");
    }
}
```

### `Laptop.java`

```java
@Component
public class Laptop implements Computer {

    public void compile() {
        System.out.println("Compiling in Laptop");
    }
}
```

### `Alien.java`

```java
@Component
public class Alien {

    @Value("25")
    private int age;

    private Computer comp;

    public int getAge() {
        return age;
    }

    @Autowired
    @Qualifier("laptop")
    public void setComp(Computer comp) {
        this.comp = comp;
    }

    public void code() {
        comp.compile();
    }
}
```

### `SpringBootDemoApplication.java`

```java
@SpringBootApplication
public class SpringBootDemoApplication {

    public static void main(String[] args) {

        ApplicationContext context =
                SpringApplication.run(
                        SpringBootDemoApplication.class, args);

        Alien obj = context.getBean(Alien.class);

        System.out.println(obj.getAge());
        obj.code();
    }
}
```

### Output

```text
25
Compiling in Laptop
```

Even though `Desktop` is `@Primary`, `Laptop` is selected because:

```java
@Qualifier("laptop")
```

has more specific selection behavior.

---

## 🔹 `@SpringBootApplication`

This is the main annotation used on the Spring Boot application class.

```java
@SpringBootApplication
public class SpringBootDemoApplication {
}
```

It combines three important capabilities:

```text
@SpringBootConfiguration
        +
@EnableAutoConfiguration
        +
@ComponentScan
```

So Spring Boot can automatically:

* Configure the application
* Enable auto-configuration
* Scan and find `@Component`, `@Service`, `@Repository`, `@Controller`, etc.

---

## 🧠 Quick Revision

```text
@Component            → Create/manage Bean
@Value                 → Inject value
@Autowired             → Inject dependency
@Qualifier             → Select specific Bean
@Primary               → Default Bean
@Bean                  → Create Bean using method
@SpringBootApplication → Main Spring Boot configuration
```

### ⭐ One-Line Summary

**Spring Boot annotations reduce configuration by allowing Spring to automatically create Beans, inject dependencies, read values, and configure the application.**
