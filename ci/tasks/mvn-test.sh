#!/bin/sh

set -e
set -x

cd git-repo
./mvnw clean test
