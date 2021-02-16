#!/bin/bash
TOMCAT_HOME="/home2/google/tomcat"
HTML_HOME="/home2/google/tomcat/webapps/ROOT"
FROM_DEPLOY_WAR="/home2/google/daniel-spring-boot-0.0.1-SNAPSHOT.war"
TO_DEPLOY_WAR="/home2/google/tomcat/webapps/ROOT/daniel-spring-boot-0.0.1-SNAPSHOT.war"
DEPLOY_WAR="daniel-spring-boot-0.0.1-SNAPSHOT.war"

echo -e "\ntomcat stop..."
cd $TOMCAT_HOME
./bin/catalina.sh stop

echo -e "\nmoving deploy..."
mv $FROM_DEPLOY_WAR $HTML_HOME

echo -e "\ndeploy war..."
cd $HTML_HOME
jar xvf $TO_DEPLOY_WAR

echo -e "\nremove war..."
rm -rf $DEPLOY_WAR

echo -e "\ntomcat restart..."
cd $TOMCAT_HOME
./bin/catalina.sh start
