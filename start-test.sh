#!/bin/bash
git pull origin develop
docker-compose stop
docker-compose --env-file ../config/.env.dev up --build -d