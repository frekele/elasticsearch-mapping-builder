#!/bin/bash
cd `dirname $0`/..

if [ -z "${GPG_PASSPHRASE}" ]
then
    echo "Error: Please set GPG_PASSPHRASE environment variable."
    exit 1
fi

if [ -z "${SONATYPE_USERNAME}" ]
then
    echo "Error: Please set SONATYPE_USERNAME environment variable."
    exit 1
fi

if [ -z "${SONATYPE_PASSWORD}" ]
then
    echo "Error: Please set SONATYPE_PASSWORD environment variable."
    exit 1
fi

if [ ! -z "${TRAVIS_TAG}" ]
then
    echo "TRAVIS_TAG=${TRAVIS_TAG}."
    echo "Setting new version into pom.xml to ${TRAVIS_TAG}."
    #To avoid Travis error, you need to redirect output to / dev / null
    ${MVN_HOME}/bin/mvn --settings .travis/settings.xml versions:set -DnewVersion=${TRAVIS_TAG} 1>/dev/null 2>/dev/null
    echo "DEPLOYING RELEASE!"
    ${MVN_HOME}/bin/mvn --settings .travis/settings.xml clean deploy -DskipTests=true -B -U
    echo "DEPLOYED RELEASE!"
else
    echo "Snapshot version in pom.xml."
    echo "DEPLOYING SNAPSHOT!"
    ${MVN_HOME}/bin/mvn --settings .travis/settings.xml clean deploy -DskipTests=true -B -U
    echo "DEPLOYED SNAPSHOT!"
fi
