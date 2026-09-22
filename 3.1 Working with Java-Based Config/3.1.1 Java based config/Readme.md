# Java-Based Configuration in Spring ☕️

## 📌 What is Java-Based Configuration?

**Java-based configuration** allows us to configure Spring Beans using **Java classes instead of XML**.

Instead of:

```xml
<bean id="desktop" class="org.example.Desktop"/>
```

we can define the Bean directly in Java.

---

# 🔧 Steps

### 1️⃣ Create a Configuration Class

Use:

```java
@Configuration
```

This tells Spring that the class contains **Bean definitions**.

### 2️⃣ Define Beans

Use:

```java
@Bean
```

The method returns the object that Spring should manage.

### 3️⃣ Start Spring Container

Use:

```java
AnnotationConfigApplicationContext
```

and provide the configuration class.

### 4️⃣ Retrieve the Bean

Use:

```java
context.getBean()
```

---

# 1️⃣ Configuration Class

```java
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfig {

    @Bean
    public Desktop desktop() {
        return new Desktop();
    }
}
```

### 🔍 Important

```java
@Bean
public Desktop desktop()
```

The method name:

```text
desktop
```

becomes the **Bean name by default**.

---

# 2️⃣ Bean Class

```java
public class Desktop {

    public Desktop() {
        System.out.println("Desktop Object Created");
    }

    public void compile() {
        System.out.println("Compiling using Desktop");
    }
}
```

---

# 3️⃣ Main Class

```java
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class App {

    public static void main(String[] args) {

        ApplicationContext context =
                new AnnotationConfigApplicationContext(AppConfig.class);

        Desktop dt = context.getBean(Desktop.class);

        dt.compile();
    }
}
```

### Output

```text
Desktop Object Created
Compiling using Desktop
```

---

# 🔄 How It Works

```text
AppConfig
   ↓
@Configuration
   ↓
@Bean
   ↓
Spring Container
   ↓
Desktop Bean created
   ↓
context.getBean(Desktop.class)
   ↓
Desktop object returned
```

---

# 🆚 XML vs Java Configuration

### XML Configuration

```xml
<bean id="desktop" class="org.example.Desktop"/>
```

```java
ApplicationContext context =
    new ClassPathXmlApplicationContext("spring.xml");
```

### Java Configuration

```java
@Bean
public Desktop desktop() {
    return new Desktop();
}
```

```java
ApplicationContext context =
    new AnnotationConfigApplicationContext(AppConfig.class);
```

---

# ⭐ Important Annotations / Classes

| Name                                 | Purpose                                               |
| ------------------------------------ | ----------------------------------------------------- |
| `@Configuration`                     | Marks a class as a **configuration class**            |
| `@Bean`                              | Defines a **Spring Bean**                             |
| `AnnotationConfigApplicationContext` | Creates Spring container using **Java configuration** |
| `getBean()`                          | Retrieves a Bean from the container                   |

---

## 🧠 Quick Revision

```java
@Configuration
public class AppConfig {

    @Bean
    public Desktop desktop() {
        return new Desktop();
    }
}
```

Then:

```java
ApplicationContext context =
    new AnnotationConfigApplicationContext(AppConfig.class);

Desktop dt = context.getBean(Desktop.class);
```

### ⭐ Easy Memory Trick

**`@Configuration` → Configuration class**
**`@Bean` → Create/manage Bean**
**`AnnotationConfigApplicationContext` → Start Spring with Java config**

---

## ⭐ One-Line Summary

**Java-based configuration replaces XML configuration by defining Spring Beans using `@Configuration` and `@Bean` in Java classes.**
