package me.elspeth;

public class Translation {

    private String id;
    private String source;
    private String target;
    private String note;

    public Translation() {
    }

    public Translation(String id) {
        this.id = id;
    }

    public Translation(String id, String source, String target, String note) {
        this.id = id;
        this.source = source;
        this.target = target;
        this.note = note;
    }

    public String getId() {
        return id;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public String getTarget() {
        return target;
    }

    public void setTarget(String target) {
        this.target = target;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    @Override
    public String toString() {
        return "Translation{" +
                "id='" + id + '\'' +
                ", source='" + source + '\'' +
                ", target='" + target + '\'' +
                ", note='" + note + '\'' +
                '}';
    }
}
