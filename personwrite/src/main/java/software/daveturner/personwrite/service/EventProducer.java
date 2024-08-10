package software.daveturner.personwrite.service;

import software.daveturner.katamodel.events.KataEvent;

public interface EventProducer {
    void publishMessage(KataEvent event);
}
