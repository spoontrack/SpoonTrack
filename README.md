# SpoonTrack

Application currently retrieves data about all currently listed pubs in the UK.

# Class Data

### Methods

```java
 .retrievePubs()
```

## Usage

### Setup
```java
SpoonTrack spoon = new SpoonTrack();
```


### Get

**Pubs** - Retrieves a list of all currently listed Wetherspoons locations.
```java
List<Pub> pubs = spoon.retrievePubs()

Pub pub = pub.get(0);
pub.getName();

```

## Download

[![](https://jitpack.io/v/spoontrack/SpoonTrack.svg)](https://jitpack.io/#spoontrack/SpoonTrack)
 
Replace `VERSION` with the JitPack version number you can see above.
 
#### Gradle
```gradle
repositories {
    maven { url 'https://jitpack.io' }
}
```

```gradle
dependencies {
    implementation 'com.github.spoontrack:SpoonTrack:VERSION'
}
```
 
#### Maven
```xml
<repositories>
    <repository>
        <id>jitpack.io</id>
        <url>https://jitpack.io</url>
    </repository>
</repositories>
```  

```xml
<dependency>
    <groupId>com.github.spoontrack</groupId>
    <artifactId>SpoonTrack</artifactId>
    <version>VERSION</version>
</dependency>
```


## Dependencies

| name | version |
|:---|:---|
| [SLF4J](https://github.com/qos-ch/slf4j) | 2.0.0-alpha0 |
| [OkHttp](https://github.com/square/okhttp/) | 4.0.1 |
| [jsoup](https://github.com/jhy/jsoup) | 1.12.1 |