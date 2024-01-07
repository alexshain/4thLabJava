package org.example.factory.model;

import org.example.factory.log.LogFileWriter;
import org.example.factory.storages.CarStorage;

import java.util.concurrent.TimeUnit;

import static java.lang.Thread.currentThread;

public class Dealer implements Runnable {
    public CarStorage carStorage;
    private static final String massage = "Car '%s' is consumed.\n";
    public int SECONDS_SLEEP;
    private final Integer DEALER_INDEX;

    public Dealer(CarStorage carStorage, Integer dealerIndex, int SECONDS_SLEEP) {
        this.carStorage = carStorage;
        this.DEALER_INDEX = dealerIndex;
        this.SECONDS_SLEEP = SECONDS_SLEEP;
    }

    @Override
    public void run() {
        try {
            while (!currentThread().isInterrupted()) {
                TimeUnit.SECONDS.sleep(SECONDS_SLEEP);
                Car car = carStorage.consume();
                (new LogFileWriter(DEALER_INDEX, car)).write();
                System.out.printf(massage, car);
            }
        } catch (InterruptedException interruptedException) {
            currentThread().interrupt();
        }
    }

    public void setSECONDS_SLEEP(int SECONDS_SLEEP) {
        this.SECONDS_SLEEP = SECONDS_SLEEP*5;
    }
}
