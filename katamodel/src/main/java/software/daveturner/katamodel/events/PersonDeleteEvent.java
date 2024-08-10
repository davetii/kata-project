package software.daveturner.katamodel.events;

public class PersonDeleteEvent extends KataEvent {
    public PersonDeleteEvent(String body) {
        super(body);
        this.eventType = "PersonDeleteEvent";
        this.eventVersion = "1.0";
        this.source = "PersonWrite";
    }
}