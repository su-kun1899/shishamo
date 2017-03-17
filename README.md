# mysql-visualizer
Mysql Metadata Visualizer

## Build status

Defalut branch:   
* [![CircleCI](https://circleci.com/gh/su-kun1899/mysql-visualizer/tree/master.svg?style=svg)](https://circleci.com/gh/su-kun1899/mysql-visualizer/tree/master)

## Embedded MySql

You can use embedded mysql server for demo, testing, development.

Example:  
- `java -jar -Dmysqlvisualizer.embedded.mysql=true mysql-visualizer.jar`
- `./mvnw spring-boot:run -Dmysqlvisualizer.embedded.mysql=true`  

Also you can custormize the configuration by `src/main/resources/embedded-mysql.yml`  

Notice:
Command line argument has more priority than configuration file.
