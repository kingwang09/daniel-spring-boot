#!/bin/bash
TOMCAT_HOME="/home2/google/tomcat/bin"
HTML_HOME="/home2/google/public_html"
DEPLOY_WAR="daniel-spring-boot-0.0.1-SNAPSHOT.war"

echo -e "\ntomcat stop..."
cd $TOMCAT_HOME
./catalina.sh stop

echo -e "\nmoving deploy..."
mv $DEPLOY_WAR $HTML_HOME

echo -e "\ndeploy war..."
cd $HTML_HOME
jar xvf $DEPLOY_WAR

echo -e "\nremove war..."
rm -rf $DEPLOY_WAR

echo -e "\ntomcat restart..."
cd $TOMCAT_HOME
./catalina.sh start
