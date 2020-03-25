#!/usr/bin/env bash

# Triggers a build of https://travis-ci.org/openmrs/openmrs-contrib-qaframework
# In order to use the script you have to set the TRAVIS_ACCESS_TOKEN and TRAVIS_MESSAGE environment variable.

response=$(curl -s -X POST \
  -H "Content-Type: application/json" \
  -H "Accept: application/json" \
  -H "Travis-API-Version: 3" \
  -H "Authorization: token $TRAVIS_ACCESS_TOKEN" \
  -d "{ \"request\": { \"branch\": \"master\", \"message\": \"$TRAVIS_MESSAGE\" } }" \
  https://api.travis-ci.org/repo/openmrs%2Fopenmrs-contrib-qaframework/requests)
 
echo "$response";
