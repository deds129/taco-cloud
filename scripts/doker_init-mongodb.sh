#!/usr/bin/env bash

#create docker network for mongodb
#docker network create mongo-net

#run mongodb
#docker run --name my-mongo --network mongo-net -p 27017:27017 -d mongo:latest
docker run --name my-mongo -p 27017:27017 -d mongo:latest

docker run -it --rm my-mongo --help