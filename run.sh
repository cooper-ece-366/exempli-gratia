#!/bin/bash

APP_NAME=server
APP_MAVEN_VERSION=$(mvn help:evaluate -Dexpression=project.version | grep -e '^[^\[]')
JAR=${APP_NAME}-${APP_MAVEN_VERSION}-jar-with-dependencies.jar
java -jar ./borsa-service/target/${JAR}
