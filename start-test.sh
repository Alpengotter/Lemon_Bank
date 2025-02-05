#!/bin/bash
git pull origin develop
docker-compose stop
docker-compose up --env-file dev.env --build -d