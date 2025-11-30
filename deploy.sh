#!/bin/bash

# Define app name
APP_NAME="cleanarch-app"

echo "Building Docker image..."
docker build -t $APP_NAME .

echo "Stopping existing container (if any)..."
docker stop $APP_NAME 2>/dev/null || true
docker rm $APP_NAME 2>/dev/null || true

echo "Running new container..."
# Run in detached mode (-d), map port 8080 to 8080
docker run -d -p 8080:8080 --name $APP_NAME $APP_NAME

echo "Deploy successful! Application is running."
echo "You can check logs with: docker logs -f $APP_NAME"
