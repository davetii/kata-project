package software.daveturner.personwrite.service;

public interface EventProducer {
    void publishMessage(String jsonString);
}
