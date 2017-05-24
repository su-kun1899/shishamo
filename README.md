# shishamo
[![CircleCI](https://circleci.com/gh/su-kun1899/shishamo/tree/master.svg?style=svg)](https://circleci.com/gh/su-kun1899/shishamo/tree/master)
[![Quality Gate](https://sonarqube.com/api/badges/gate?key=red.sukun1899:shishamo)](https://sonarqube.com/dashboard?id=red.sukun1899%3Ashishamo)

shishamo is [MySQL](https://www.mysql.com/) metadata visualizer.

"shishamo" means capelin in Japanese, which is known as dolphin's bait.  
Sakila will definitely like it too.

## Embedded MySql

You can use embedded mysql server for demo, testing, development.

Example:  
- `java -jar -Dshishamo.embedded.mysql=true shishamo.jar`
- `./mvnw spring-boot:run -Dshishamo.embedded.mysql=true`  

Also you can custormize the configuration by `src/main/resources/embedded-mysql.yml`  

Notice:
Command line argument has more priority than configuration file.
