package software.daveturner.katamodel.events;


import lombok.Data;

import java.io.Serializable;


@Data
public class KataEvent implements Serializable {
    protected String eventType;
    protected String eventVersion;
    protected String source;
    protected String body;

    public KataEvent() {
        this.eventType = "KataEvent";
        this.eventVersion = "1.0";
        this.source = "base";
    }
}
