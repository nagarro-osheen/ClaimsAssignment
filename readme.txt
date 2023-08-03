Steps to run:

1. Run keycloak as authorization server. 
	- bin/kc.bat start-dev
	- import realm from realm-export.json
	- change default port in keycloak.conf as microrocks would be on 8080. Use http-port=8083
2. Install Microcks
	- Running without auth
	- docker compose -f docker-compose-devmode.yml up -d
	- http://localhost:8080/
3. Docker should be running
	- docker service start
4. Start kafka, kafka-ui, zookeeper, zipkin
	- docker-compose up
	- zipkin: http://localhost:9411/
	- kafka-ui: http://localhost:8070/
5. create a postgres database for claims microservice 
	- create database claims
6. Start spring boot microservices 
	- workflow
	- claims
7. start zipkin server for distributed tracing
	- for 
8. start frontend
	cd claims-microservices\ng-keycloak
	npm install
	npm run start
	