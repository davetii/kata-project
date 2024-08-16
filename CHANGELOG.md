# Changelog

All notable changes to this project will be documented in this file.


## [0.0.11] - 2024-08-15
- Adding more org, level, hireDate to API

## [0.0.10] - 2024-08-14
- Handling 404's better
- Improving Cucumber tests

## [0.0.9] - 2024-08-14
- Adding validation to PersonWrite API

## [0.0.8] - 2024-08-13
- Adding docker image creation step to all maven projects

## [0.0.7] - 2024-08-13
- centralizing openapi schemas
 
## [0.0.6] - 2024-08-12
- Redis Phase 2
	Added Redis lookup to Personread
	
## [0.0.5] - 2024-08-11
- Redis Phase 1
	Added Redis docker support
	ReadConsumer writes and deletes to redis

## [0.0.4] - 2024-08-10
- Added ReadConsumer
	reads Events from PersonWrite, writes to Personread mongo instance

## [0.0.3] - 2024-08-10
- Added PersonRead App
	Cucumber test, read API, Mongo DB

## [0.0.2] - 2024-06-26
- Kafka Support in PersonWrite
- Mocking Kafka publish in PersonWrite tests and Cucumber


## [0.0.1] - 2024-06-26

### Added
- personread yaml v1.0
- personwrite yaml v1.0
- reportservice yaml v1.0
