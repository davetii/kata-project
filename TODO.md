# Todo

* Create PersonReadKafkaConsumer
  * populates mongo tables
* Create Redis Service
* PersonRead Redis Implementation
* Refactor PersonData in Katamodel (probably not needed)
* Change PersonReadKafkaConsumer to write to redis
* Report Service
  * API end point, h2 + cucumber tests
  * runtime with postgres liquibase
  * implement DB versioning with liquibase
* Create ReportKafkaConsumer
  * Populates postgres tables
* Change PersonRead to check redis first BEFORE going to Mongo read
* Create docker image for PersonWrite
  * maven install
* Create docker image for PersonRead
  * maven install
* Create docker image for ReportService
  * maven install   
* Create docker image for ReportConsumer
  * maven install
* Create docker image for PersonReadConsumer
  * maven install 
* generate docker-compose for kata project
* convert to K8S
* Convert PersonRead to SpringBoot reactive
* expand kata to include the following tech stacks
  * Drool rule engine
  * client service with circuit breaker
  * react front end 
  * Websocket broker
  * rabbitmq
  * Pulsar
	
