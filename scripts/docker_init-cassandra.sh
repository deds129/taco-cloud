#!/usr/bin/env bash

#create doker network for cassandra
docker network create cassandra-net

#run cassandra with 1 cluster in post 9042
docker run --name my-cassandra --network cassandra-net -p 9042:9042 -d cassandra:latest

#create cassandra keyspace
docker run -it --network cassandra-net --rm cassandra cqlsh my-cassandra

docker exec create keyspace tacocloud with replication={'class':'SimpleStrategy', 'replication_factor':1} and durable_writes=true;