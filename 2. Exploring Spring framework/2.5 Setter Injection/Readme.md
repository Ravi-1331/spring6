# Setter Injection in Spring

## 📌 What is Setter Injection?

**Setter Injection** is a way of providing **dependencies or values to a Spring Bean through setter methods**.

Spring uses the `<property>` tag in `spring.xml` to call the corresponding **setter method** and inject the value.

---

## 🔑 Basic Example

### `Alien.java`

```java
package org.example;

public class Alien {

    private int age;

    public Alien() {
        System.out.println("Object Created");
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        System.out.println("Setter called");
        this.age = age;
    }

    public void code() {
        System.out.println("Coding");
    }
}
```

---

### `spring.xml`

```xml
<bean id="alien1" class="org.example.Alien">

    <property name="age" value="21"></property>

</bean>
```

Here:

* `name="age"` → refers to the **`age` property** in `Alien`.
* `value="21"` → value that Spring will inject.
* Spring looks for the corresponding setter → **`setAge(21)`**

---

## 🔄 How It Works

When this runs:

```java
ApplicationContext context =
        new ClassPathXmlApplicationContext("spring.xml");
```

Spring performs:

```text
spring.xml
    ↓
Creates Alien object
    ↓
Calls setAge(21)
    ↓
age = 21
```

So internally, it is similar to:

```java
Alien obj = new Alien();

obj.setAge(21);
```

> ⭐ **Spring creates the object and automatically calls the setter method to inject the value.**

---

## 📤 Output

```text
Object Created
Setter called
```

If we then print:

```java
System.out.println(obj.getAge());
```

Output:

```text
21
```

---

# 🧩 `<property>` Tag

The `<property>` tag is used inside `<bean>`:

```xml
<property name="age" value="21"/>
```

### `name`

```xml
name="age"
```

Means Spring should inject the value into the **`age` property**.

### `value`

```xml
value="21"
```

Means Spring should pass **21** to the setter.

So:

```xml
<property name="age" value="21"/>
```

becomes approximately:

```java
obj.setAge(21);
```

---

# 📝 What Values Can Be Injected?

The `value` attribute can be used for simple values such as:

* 🔢 `int`
* 🔢 `double`
* ✅ `boolean`
* 🔤 `String`
* and other basic values Spring can convert to the property's type.

Example:

```xml
<property name="age" value="21"/>
<property name="name" value="Ravi"/>
<property name="active" value="true"/>
```

---

# 🎯 When is Setter Injection Useful?

Setter Injection is useful when a Bean has **optional or configurable properties**.

For example:

```java
Alien obj = new Alien();
obj.setAge(21);
```

The `age` value can be configured through the Spring XML file without changing the Java code.

---

# 🧠 Quick Revision

Remember:

```text
<property>
    ↓
name
    ↓
Property / variable
    ↓
Setter method
    ↓
Value injected
```

Example:

```xml
<property name="age" value="21"/>
```

↓

```java
setAge(21);
```

### ⭐ One-Line Summary

> **Setter Injection means Spring creates the Bean and injects a value or dependency by calling its setter method.**
