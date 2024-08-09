package software.daveturner.kataeventlib;

public class PersonWriteEvent extends KataEvent{

    public PersonWriteEvent(String body) {
        super(body);
        this.eventType = "PersonWriteEvent";
        this.eventVersion = "1.0";
        this.source = "PersonWrite";
    }
}
