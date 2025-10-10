#!/bin/bash

# Spring WebFlux Microservices Startup Script
# This script starts all services in the correct order with proper delays

echo "======================================"
echo "Spring WebFlux Microservices Startup"
echo "======================================"
echo ""

# Color codes for output
GREEN='\033[0;32m'
YELLOW='\033[1;33m'
NC='\033[0m' # No Color

# Start Eureka Discovery Server
echo -e "${YELLOW}[1/5] Starting Spring Discovery Server (Port 8761)...${NC}"
cd spring-discovery-server
mvn spring-boot:run > ../logs/discovery-server.log 2>&1 &
DISCOVERY_PID=$!
cd ..
echo -e "${GREEN}Discovery Server started with PID: $DISCOVERY_PID${NC}"
echo "Waiting 30 seconds for Discovery Server to be ready..."
sleep 30

# Start Customer Service
echo ""
echo -e "${YELLOW}[2/5] Starting Customer Service (Port 8082)...${NC}"
cd customer-service
mvn spring-boot:run > ../logs/customer-service.log 2>&1 &
CUSTOMER_PID=$!
cd ..
echo -e "${GREEN}Customer Service started with PID: $CUSTOMER_PID${NC}"
echo "Waiting 15 seconds for Customer Service to register..."
sleep 15

# Start Spring Gateway
echo ""
echo -e "${YELLOW}[3/5] Starting Spring Gateway (Port 8081)...${NC}"
cd spring-gateway
mvn spring-boot:run > ../logs/spring-gateway.log 2>&1 &
GATEWAY_PID=$!
cd ..
echo -e "${GREEN}Spring Gateway started with PID: $GATEWAY_PID${NC}"
echo "Waiting 15 seconds for Gateway to register..."
sleep 15

# Start Spring Web (Optional)
echo ""
echo -e "${YELLOW}[4/5] Starting Spring Web UI (Port 9080)...${NC}"
cd spring-web
mvn spring-boot:run > ../logs/spring-web.log 2>&1 &
WEB_PID=$!
cd ..
echo -e "${GREEN}Spring Web started with PID: $WEB_PID${NC}"
echo "Waiting 10 seconds..."
sleep 10

# Start Spring Web Flux (Optional)
echo ""
echo -e "${YELLOW}[5/5] Starting Spring Web Flux UI (Port 9081)...${NC}"
cd spring-web-flux
mvn spring-boot:run > ../logs/spring-web-flux.log 2>&1 &
WEBFLUX_PID=$!
cd ..
echo -e "${GREEN}Spring Web Flux started with PID: $WEBFLUX_PID${NC}"

echo ""
echo "======================================"
echo "All Services Started!"
echo "======================================"
echo ""
echo "Service PIDs:"
echo "  Discovery Server: $DISCOVERY_PID"
echo "  Customer Service: $CUSTOMER_PID"
echo "  Spring Gateway:   $GATEWAY_PID"
echo "  Spring Web:       $WEB_PID"
echo "  Spring Web Flux:  $WEBFLUX_PID"
echo ""
echo "Service URLs:"
echo "  Discovery Server: http://localhost:8761 (admin/password)"
echo "  Customer Service: http://localhost:8082"
echo "  Spring Gateway:   http://localhost:8081"
echo "  Spring Web:       http://localhost:9080"
echo "  Spring Web Flux:  http://localhost:9081"
echo ""
echo "Logs are being written to the 'logs' directory"
echo ""
echo "To stop all services, run: ./stop-all-services.sh"
echo "Or manually kill processes with: kill $DISCOVERY_PID $CUSTOMER_PID $GATEWAY_PID $WEB_PID $WEBFLUX_PID"
echo ""

# Save PIDs to file for stop script
echo "$DISCOVERY_PID $CUSTOMER_PID $GATEWAY_PID $WEB_PID $WEBFLUX_PID" > .service-pids
