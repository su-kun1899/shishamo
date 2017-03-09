# mysql-visualizer
Mysql Metadata Visualizer

## Build status

Defalut branch:   
* [![CircleCI](https://circleci.com/gh/su-kun1899/mysql-visualizer/tree/master.svg?style=svg)](https://circleci.com/gh/su-kun1899/mysql-visualizer/tree/master)

## Embedded MySql

You can use embedded mysql server for demo, testing, development.

Like this:  
- `java -jar mysql-visualizer.jar --mysqlvisualizer.embedded.mysql=true`
- `./mvnw spring-boot:run -Drun.arguments="--mysqlvisualizer.embedded.mysql=true"`  

Also you can custormize the configuration by `src/main/resources/embedded-mysql.yml`
