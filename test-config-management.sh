#!/bin/bash

# Configuration Management Test Script
# This script demonstrates the complete configuration management setup

echo "=== Banking Microservices Configuration Management Test ==="
echo

# Function to check if a service is running
check_service() {
    local url=$1
    local service_name=$2
    if curl -s "$url" > /dev/null; then
        echo "✓ $service_name is running"
    else
        echo "✗ $service_name is not responding"
    fi
}

# Function to test configuration endpoints
test_config() {
    local url=$1
    local service_name=$2
    echo "Testing $service_name configuration:"
    echo "  Config URL: $url"
    response=$(curl -s "$url" | head -c 100)
    echo "  Response: $response..."
    echo
}

echo "Step 1: Testing Config Server"
echo "=============================="
check_service "http://configuser:configpass@localhost:8888/actuator/health" "Config Server"

echo
echo "Step 2: Testing Configuration Retrieval"
echo "========================================"
echo "Retrieving accounts development configuration:"
curl -s -u configuser:configpass "http://localhost:8888/accounts/dev" | grep -o '"accounts.message":"[^"]*"' || echo "Config server not running"

echo
echo "Step 3: Testing Microservices"
echo "============================="
check_service "http://localhost:8080/actuator/health" "Accounts Service"
check_service "http://localhost:9000/actuator/health" "Cards Service"
check_service "http://localhost:8090/actuator/health" "Loans Service"

echo
echo "Step 4: Testing Configuration Endpoints"
echo "======================================="
if curl -s http://localhost:8080/config-info > /dev/null; then
    test_config "http://localhost:8080/config-info" "Accounts"
fi

if curl -s http://localhost:9000/config-info > /dev/null; then
    test_config "http://localhost:9000/config-info" "Cards"
fi

if curl -s http://localhost:8090/config-info > /dev/null; then
    test_config "http://localhost:8090/config-info" "Loans"
fi

echo "=== Test Complete ==="
echo
echo "To start the configuration management system:"
echo "1. Start config server: cd config-server && ./mvnw spring-boot:run"
echo "2. Start microservices: cd accounts && ./mvnw spring-boot:run"
echo "3. Test configuration: curl http://localhost:8080/config-info"
echo
echo "For more information, see CONFIGURATION_MANAGEMENT.md"