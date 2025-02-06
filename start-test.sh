#!/bin/bash
git pull origin develop
docker-compose stop
docker-compose --env-file dev.env up --build -d