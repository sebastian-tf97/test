#!/bin/sh

set -e
set -x

cd employeemgmt-service
./mvnw clean package

cp target/*.jar ../build/
