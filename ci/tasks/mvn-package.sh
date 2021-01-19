#!/bin/sh

set -e
set -x

cd git-repo
./mvnw clean package

cd ..
cp git-repo/target/*.jar build/
ls build