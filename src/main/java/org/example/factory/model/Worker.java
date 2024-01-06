package org.example.factory.model;

import org.example.factory.storages.AccessoryStorage;
import org.example.factory.storages.BodyStorage;
import org.example.factory.storages.CarStorage;
import org.example.factory.storages.MotorStorage;

import java.util.concurrent.TimeUnit;

import static java.lang.Thread.currentThread;

public class Worker implements Runnable {
    private static final String massage = "Car '%s' is produced.\n";
    public Body body;
    public Motor motor;
    public Accessory accessory;
    public BodyStorage bodyStorage;
    public MotorStorage motorStorage;
    public AccessoryStorage accessoryStorage;
    private static final int SECONDS_SLEEP = 5;
    private static final Integer INITIAL_NEXT_CAR_INDEX = 1;
    public CarStorage carStorage;
    private int nextCarIndex;

    public Worker(BodyStorage body, MotorStorage motor, AccessoryStorage accessory, CarStorage carStorage) {
        this.bodyStorage = body;
        this.motorStorage = motor;
        this.accessoryStorage = accessory;
        this.carStorage = carStorage;
        this.nextCarIndex = INITIAL_NEXT_CAR_INDEX;
    }

    public Car create() {
        return new Car(body, motor, accessory, String.format("Car#%d", this.nextCarIndex++));
    }

    @Override
    public void run() {
        try {
            TimeUnit.SECONDS.sleep(SECONDS_SLEEP);
            body = bodyStorage.consume();
            motor = motorStorage.consume();
            accessory = accessoryStorage.consume();
            final Car produceCar = create();
            carStorage.produce(produceCar);
            System.out.printf(massage, produceCar);
        } catch (InterruptedException interruptedException) {
            currentThread().interrupt();
        }
    }

    public BodyStorage getBodyStorage() {
        return bodyStorage;
    }

    public MotorStorage getMotorStorage() {
        return motorStorage;
    }

    public AccessoryStorage getAccessoryStorage() {
        return accessoryStorage;
    }
}
