# shishamo
[![CircleCI](https://circleci.com/gh/su-kun1899/shishamo/tree/master.svg?style=svg)](https://circleci.com/gh/su-kun1899/shishamo/tree/master)
[![Quality Gate](https://sonarqube.com/api/badges/gate?key=red.sukun1899:shishamo)](https://sonarqube.com/dashboard?id=red.sukun1899%3Ashishamo)

shishamo is a [MySQL](https://www.mysql.com/) metadata visualizer.

"shishamo" means capelins in Japanese, which is dolphin's favorite.
Sakila definitely likes it too.

## Quick Start

```
git clone git@github.com:su-kun1899/shishamo.git
cd shishamo/
./mvnw spring-boot:run \
    -Dspring.datasource.url=jdbc:mysql://<Your mysql host: localhost>>:<Your mysql port: 3306>/<Your mysql schema> \
    -Dspring.datasource.schema=<Your mysql schema> \
    -Dspring.datasource.username=<Your mysql user> \
    -Dspring.datasource.password=<Your mysql password>
```

URL:
http://localhost:8080/

### Demo with embedded MySql

```
./mvnw spring-boot:run -Dshishamo.embedded.mysql=true
```

URL:
http://localhost:8080/


## Embedded MySql

You can use embedded mysql server for demo, testing, and development.

Example:  
- `java -jar -Dshishamo.embedded.mysql=true shishamo.jar`
- `./mvnw spring-boot:run -Dshishamo.embedded.mysql=true`  

Also you can change the configuration in `src/main/resources/embedded-mysql.yml`  

Notice:
You can override the default configuration by providing arguments on the command line.

## SonarCloud

Shishamo uses [SonarCloud](https://about.sonarcloud.io/) to anlyze project.

It requires to sign up and link up with [CircleCI](https://circleci.com/).

Please refer to the help for details.
