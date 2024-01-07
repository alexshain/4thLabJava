package org.example.factory.model;

public class Car {
    private final Motor motor;
    private final Body body;
    private final Accessory accessory;
    private final String CAR_ID;

    public Car(Body body, Motor motor, Accessory accessory, String id) {
        this.motor = motor;
        this.body = body;
        this.accessory = accessory;
        this.CAR_ID = id;
    }

    public Motor getMotor() {
        return motor;
    }

    public Body getBody() {
        return body;
    }

    public Accessory getAccessory() {
        return accessory;
    }

    public String getCAR_ID() {
        return CAR_ID;
    }

    @Override
    public String toString() {
        return "[Car_ID = " + this.CAR_ID + "m_id" + getMotor() + "]";
    }
}
