#!/bin/bash
cd `dirname $0`/..

if [ -z "${SONATYPE_USERNAME}" ]
then
    echo "error: please set SONATYPE_USERNAME environment variable"
    exit 1
fi

if [ -z "${SONATYPE_PASSWORD}" ]
then
    echo "error: please set SONATYPE_PASSWORD environment variable"
    exit 1
fi

if [ ! -z "${TRAVIS_TAG}" ]
then
    echo "on a tag -> set pom.xml <version> to ${TRAVIS_TAG}"
    ${MVN_HOME}/bin/mvn --settings .travis/settings.xml versions:set -DnewVersion=${TRAVIS_TAG} 1>/dev/null 2>/dev/null
    #${MVN_HOME}/bin/mvn --settings .travis/settings.xml clean deploy -DskipTests=true -B -U
else
    echo "not on a tag -> keep snapshot version in pom.xml"
    ${MVN_HOME}/bin/mvn --settings .travis/settings.xml clean deploy -DskipTests=true -B -U
fi
