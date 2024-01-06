package org.example.factory.model;

public class Body {
    private final String BODY_ID;

    public Body(String id) {
        this.BODY_ID = id;
    }

    @Override
    public String toString() {
        return this.getClass().getName() + "[BODY_ID = " + this.BODY_ID + "]";
    }

    public String getBODY_ID() {
        return BODY_ID;
    }
}
