package software.daveturner.personwrite.service;

import software.daveturner.kataeventlib.KataEvent;

public interface EventProducer {
    void publishMessage(KataEvent event);
}
