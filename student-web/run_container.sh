#!/bin/bash
set -e
API_HOST='172.31.22.126'
docker run -d -p 80:80 -e STUDENT_API_URL=http://$API_HOST:8080 -v "$PWD":/var/www/html php:7.0-apache
