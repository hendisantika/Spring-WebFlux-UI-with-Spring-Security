#!/bin/bash

# Spring WebFlux Microservices Stop Script
# This script stops all running services

echo "======================================"
echo "Stopping All Services"
echo "======================================"
echo ""

# Check if PID file exists
if [ -f .service-pids ]; then
    PIDS=$(cat .service-pids)
    echo "Stopping services with PIDs: $PIDS"

    for PID in $PIDS; do
        if ps -p $PID > /dev/null 2>&1; then
            echo "Stopping process $PID..."
            kill $PID
        else
            echo "Process $PID is not running"
        fi
    done

    # Remove PID file
    rm .service-pids
    echo ""
    echo "All services stopped!"
else
    echo "No .service-pids file found. Attempting to find and stop Java processes..."

    # Find and kill Spring Boot applications
    pkill -f "spring-boot:run"

    if [ $? -eq 0 ]; then
        echo "Spring Boot services stopped!"
    else
        echo "No running Spring Boot services found."
    fi
fi

echo ""
echo "You can verify by checking: ps aux | grep spring-boot"
