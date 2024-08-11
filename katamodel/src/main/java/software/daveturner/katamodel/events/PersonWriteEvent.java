package software.daveturner.katamodel.events;

public class PersonWriteEvent extends KataEvent{

    public PersonWriteEvent() {
        this.eventType = "PersonWriteEvent";
        this.eventVersion = "1.0";
        this.source = "PersonWrite";
    }
}
