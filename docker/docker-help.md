## Create topic
`docker exec -it kafka-dev /opt/kafka/bin/kafka-topics.sh --create --topic personwrite --bootstrap-server localhost:9092`

## publish a message
`docker exec -it kafka-dev /opt/kafka/bin/kafka-console-producer.sh --topic personwrite --bootstrap-server localhost:9092`

## read a topic
`docker exec -it kafka-dev /opt/kafka/bin/kafka-console-consumer.sh --topic personwrite --from-beginning --bootstrap-server localhost:9092`