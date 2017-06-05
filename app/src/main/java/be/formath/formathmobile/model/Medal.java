package be.formath.formathmobile.model;

import java.util.GregorianCalendar;

public class Medal {
    private String category, level;
    private GregorianCalendar obtainingDateTime;
    private MedalType type;

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }

    public GregorianCalendar getObtainingDateTime() {
        return obtainingDateTime;
    }

    public void setObtainingDateTime(GregorianCalendar obtainingDateTime) {
        this.obtainingDateTime = obtainingDateTime;
    }

    public MedalType getType() {
        return type;
    }

    public void setType(MedalType type) {
        this.type = type;
    }
}
