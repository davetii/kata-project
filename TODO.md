# Todo

* Read Service
  * API End Point
  * Cucumber Tests
  * Create Mongo Read DB
  * Read Mongo Data
* Report Service
  * API end point, h2 + cucumber tests
  * runtime with postgres liquibase
  * implement DB versioning with liquibase
* Ensure Each Service has unique runtime port
  * Ensure works within intellij
* Create Kafka Broker
* publish PersonWrite
* Create ReportKafkaConsumer
  * Populates postgres tables
* Create PersonReadKafkaConsumer
  * populates mongo tables
* Create Redis Service
* Change PersonReadKafkaConsumer to write to redis
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
  * 
	
