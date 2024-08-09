package software.daveturner.kataeventlib;

public class KataEvent {
    protected String eventType;
    protected String eventVersion;
    private final String body;

    public KataEvent(String body) {
        this.eventType = "KataEvent";
        this.eventVersion = "1.0";
        this.body = body;
    }




}
