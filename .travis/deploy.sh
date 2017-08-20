#!/bin/bash
cd `dirname $0`/..

if [ -z "${SONATYPE_USERNAME}" ]
then
    echo "error: please set SONATYPE_USERNAME and SONATYPE_PASSWORD environment variable"
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
    ${MVN_HOME}/bin/mvn --settings .travis/settings.xml org.codehaus.mojo:versions-maven-plugin:2.4:set -DnewVersion=${TRAVIS_TAG} 1>/dev/null 2>/dev/null
else
    echo "not on a tag -> keep snapshot version in pom.xml"
fi

${MVN_HOME}/bin/mvn clean deploy --settings .travis/settings.xml -B -U
