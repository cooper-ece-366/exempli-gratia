#!/bin/bash

# Borsa React App
APP_PID=$(cat ./borsa-app.pid | tr -d "\n")
kill ${APP_PID}

# Borsa Service
SERVICE_PID=$(cat ./borsa-service.pid | tr -d "\n")
kill ${SERVICE_PID}

rm *.pid
rm *.log

echo "Stopped Service (pid=${SERVICE_PID}) and App (pid=${APP_PID})."
