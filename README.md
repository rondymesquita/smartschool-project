# Smartschool Project

## Requirements
- Gradle 1.8+
- Maven 7
- Tomcat 7+
- Mysql or Postgres Database

#### Building and Running
* Configure you database credentials in **smartschool-core/src/main/resources/application.properties**
* Go to **smartschool-core** via terminal and execute
```
$ gradle install
```
* Go to **smartschool-ws** via terminal and execute
```
$ gradle eclipse
```

- Now, run you project in Tomcat Container
- To generate **war** package, go to **smartschool-ws** and execute
```
$ gradle package
```
Gradle will generate **war** package at **smartschool-ws/build**