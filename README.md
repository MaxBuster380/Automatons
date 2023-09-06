# Automatons

- I - Introduction
- II - Why make this project ?
- III - Download
- IV - About

### I - Introduction

"A finite automaton [...] is an abstract machine that can be in exactly one of a finite number of states at any given time." - [Wikipedia](https://en.wikipedia.org/wiki/Finite-state_machine)

This project aims to develop a simple library for creating and using finite-state machines, also known as automatons.

An automaton is a set of states connected to each other via events, allowing a state to change depending on what events it receives.

The most known use of automatons is the **regular expression**, where the events are the letters successively being read.

### II - Why make this project ?

I've noticed that, in software development, a lot of bugs come from the user being allowed to do too much, for example when they continue to use the application while a crucial download is ongoing.

In other terms, the user is allowed to do everything by default, and it's up to the developer to notice and block certain actions.

My goal with this project is to flip that around : making everything forbidden by default and specifying what you're allowed to do. In the example above, it would mean that the user can't use the application because it is not in a state where they can ("Currently Downloading").

It also serves as practice for library building and project exporting.

### III - Download

__Gradle__

In `build.gradle` :

```gradle
allprojects {
	repositories {
		/*...*/
		maven { url 'https://jitpack.io' }
	}
}
```

```gradle
dependencies {
    implementation 'com.github.MaxBuster380:Automatons:alpha-1.2.1'
}
```

__Maven__ 

In `pom.xml` :
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
    <groupId>com.github.MaxBuster380</groupId>
    <artifactId>Automatons</artifactId>
    <version>alpha-1.2.1</version>
</dependency>
```

### IV - About

By MaxBuster380

[Github Repository](https://github.com/MaxBuster380/Automatons)

