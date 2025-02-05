#!/bin/bash
git pull origin master
docker-compose stop
docker-compose up --env-file prod.env --build -d