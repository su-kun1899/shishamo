#!/usr/bin/env bash

readonly SCHEMA="sample"
readonly USER="root"
readonly PASSWORD=""
readonly JDBC_URL="jdbc:mysql://localhost:13306/${SCHEMA}"

./mvnw spring-boot:run \
    -Dspring.datasource.schema=${SCHEMA} \
    -Dspring.datasource.username=${USER} \
    -Dspring.datasource.password=${PASSWORD} \
    -Dspring.datasource.url=${JDBC_URL}
