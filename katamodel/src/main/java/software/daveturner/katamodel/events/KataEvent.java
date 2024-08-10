package software.daveturner.katamodel.events;

public class KataEvent {
    protected String eventType;
    protected String eventVersion;
    protected String source;
    private final String body;

    public KataEvent(String body) {
        this.eventType = "KataEvent";
        this.eventVersion = "1.0";
        this.source = "base";
        this.body = body;
    }

    public String getBody() {
        return body;
    }
}
