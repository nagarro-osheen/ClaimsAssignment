Steps to run:

1. Run keycloak as authorization server. 
	- bin/kc.bat start-dev
	- import realm from realm-export.json
2. Docker should be running
	- docker service start
3. Start kafka, zookeeper
	- docker-compose up
4. create a postgres database for claims microservice 
	- create database claims
5. Start spring boot microservices 
	- workflow
	- claims
6. start zipkin server for distributed tracing
	- for 
7. start frontend
	cd claims-microservices\ng-keycloak
	npm install
	npm run start
	
Extra:
1. Setup Debver
2. 