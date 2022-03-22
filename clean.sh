#!/bin/bash

APP_VERSION_FILE=./app.version
RESOURCES_DIR=./borsa-service/src/main/resources
APP_PROP_FILE=${RESOURCES_DIR}/application.properties

echo "Removing built project artifacts..."
/bin/rm -rf ./build
echo "Done."
echo "Maven build clean..."
mvn clean

[ -e ${APP_VERSION_FILE} ] && echo "Deleting ${APP_VERSION_FILE} ..." && /bin/rm -f ${APP_VERSION_FILE} && echo "Deleted."
[ -e ${APP_PROP_FILE} ] && echo "Deleting ${APP_PROP_FILE} ..." && /bin/rm -f ${APP_PROP_FILE} && echo "Deleted."

echo "Done."
