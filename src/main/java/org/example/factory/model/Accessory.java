package org.example.factory.model;

public class Accessory {
    private final String ACCESSORY_ID;

    public Accessory(String id) {
        this.ACCESSORY_ID = id;
    }

    @Override
    public String toString() {
        return this.getClass().getName() + "[ACCESSORY_ID = " + this.ACCESSORY_ID + "]";
    }

    public String getACCESSORY_ID() {
        return ACCESSORY_ID;
    }
}
