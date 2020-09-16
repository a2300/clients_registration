#!/usr/bin/env bash

echo "PostgreSQL INIT..."

psql -c 'create database clientdb'
psql -d clientdb -c 'CREATE TABLE IF NOT EXISTS CLIENT (CLIENT_ID UUID PRIMARY KEY,NAME VARCHAR(255) NOT NULL,ADDRESS VARCHAR(1024) NOT NULL)'
echo "PostgreSQL INIT...Done"