Steps to run:

1. Run keycloak as authorization server. 
	- keycloak-15.1.1\keycloak-15.1.1\bin\standalone.bat 
	- bin/kc.bat start-dev
	- import realm from realm-export.json
2. Docker daemon should be running- sudo dockerd
3. Start kafka, zookeeper
	- docker-compose up
- create a postgres database for claims microservice 
	- create database claims
- Start spring boot microservices 
	- workflow
	- claims
- start zipkin server for distributed tracing
	- for 
- start frontend
	cd claims-microservices\ng-keycloak
	npm install
	npm run start