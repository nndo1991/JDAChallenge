# JDAChallenge
New repository for the JDA code challenge, created on **20/DEC/2019** by Fernando Garza


This is a Java application built with [Apache Maven](https://maven.apache.org/)

To build, run unit tests, and package the application execute:

```
mvn clean compile test -Dtest=ServiceTests,RepositoryTests package
```

This will generate the JAR file under *target/ImageProcessor-0.0.1-SNAPSHOT.jar*, which you can execute as:

```
java -jar ImageProcessor-0.0.1-SNAPSHOT.jar C:/path/to/source/URL/file.txt C:/path/to/store/images/
```