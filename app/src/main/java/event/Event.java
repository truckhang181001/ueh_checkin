package event;

public class Event {
    private int eventId;
    private String name;
    private String address;

    public Event(int eventId, String name, String address) {
        this.eventId = eventId;
        this.name = name;
        this.address = address;
    }

    public int getEventId() {
        return eventId;
    }

    public void setEventId(int eventId) {
        this.eventId = eventId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
