#!/bin/sh

set -e
set -x

cd employeemgmt-service
./mvnw clean test
