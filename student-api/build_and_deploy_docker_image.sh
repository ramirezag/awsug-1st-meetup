#!/bin/bash
set -e
echo -e "\e[1m\e[34m============= Building student api. =============\e[0m"
#mvn clean install
branch_name="$(git symbolic-ref HEAD 2>/dev/null)" ||
branch_name="(unnamed branch)"     # detached HEAD
branch_name=${branch_name##refs/heads/}
if [ "$branch_name" == "master" ]
then
    branch_name="latest"
fi
image_name=ramirezag/student-api:$branch_name
echo -e "\e[1m\e[34m============= Building docker image $image_name =============\e[0m"
docker build -t $image_name .
echo -e "\e[1m\e[34m============= Pushing docker image $image_name =============\e[0m"
docker push $image_name
