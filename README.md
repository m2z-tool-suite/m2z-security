# M2Z - Backend security library

Security library for Spring Boot RESTful microservices in M2Z Tool Suite

[![](https://jitpack.io/v/m2z-tool-suite/m2z-security.svg)](https://jitpack.io/#m2z-tool-suite/m2z-security)

## Using with Gradle

To use it in your Gradle build add:
```gradle
allprojects {
  repositories {
    maven { url 'https://jitpack.io' }
  }
}
```

and the dependency:

```gradle
dependencies {
  implementation("com.github.m2z-tool-suite:m2z-security:Tag")
}
```

## Using with Maven

To use it in your Maven build add:
```xml
<repositories>
    <repository>
        <id>jitpack.io</id>
        <url>https://jitpack.io</url>
    </repository>
</repositories>
```

and the dependency:

```xml
<dependency>
    <groupId>com.github.m2z-tool-suite</groupId>
    <artifactId>m2z-security</artifactId>
    <version>Tag</version>
</dependency>
```
