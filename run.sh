#!/bin/bash

# ./build.sh && java -jar borsa-service/target/server-0.0.1.jar

# Borsa Service
APP_NAME=server
APP_MAVEN_VERSION=$(mvn help:evaluate -Dexpression=project.version | grep -e '^[^\[]')
#JAR=${APP_NAME}-${APP_MAVEN_VERSION}-jar-with-dependencies.jar
JAR=${APP_NAME}-${APP_MAVEN_VERSION}.jar
# run and log to file then place in background
java -jar ./borsa-service/target/${JAR} > borsa-service.log 2>&1 &
SERVICE_PID=$!
echo ${SERVICE_PID} > borsa-service.pid

# Borsa React App
# see https://stackoverflow.com/questions/16333790/node-js-quick-file-server-static-files-over-http
# for serving static files locally to mimic staging
# run and log to file then place in background
npx http-server --cors -p 3000 ./borsa-app/build/ > borsa-app.log 2>&1 &
APP_PID=$!
echo ${APP_PID} > borsa-app.pid

echo "Running Service (pid=${SERVICE_PID}) and App (pid=${APP_PID}) in background."
