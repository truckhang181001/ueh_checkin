package event;

public class Event {
    private int eventId;
    private String name;
    private String desc;
    private String address;

    public Event(int eventId, String name, String desc, String address) {
        this.eventId = eventId;
        this.name = name;
        this.desc = desc;
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

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
