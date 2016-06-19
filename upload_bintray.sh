#! /bin/bash

echo "$TRAVIS_BRANCH"
if [ "$TRAVIS_BRANCH" = "master" ]; then
  ./gradlew bintrayUpload
fi
