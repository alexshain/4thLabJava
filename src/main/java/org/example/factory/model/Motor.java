package org.example.factory.model;

public class Motor {
    private final String MOTOR_ID;

    public Motor(String id) {
        this.MOTOR_ID = id;
    }

    @Override
    public String toString() {
        return this.getClass().getName() + "[MOTOR_ID = " + this.MOTOR_ID + "]";
    }

    public String getMOTOR_ID() {
        return MOTOR_ID;
    }
}
