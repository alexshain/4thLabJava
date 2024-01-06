package org.example.factory.storages;

import org.example.factory.model.Car;

import java.util.ArrayDeque;
import java.util.Queue;

public class CarStorage extends Storage {
    public final Queue<Car> carToBeConsumed;
    private final int maxStorageCar;
    public int amountOfConsumedCars = 0;
    public int amountOfCompletedCars = 0;

    public CarStorage(int maxStorageCar) {
        this.carToBeConsumed = new ArrayDeque<>(maxStorageCar);
        this.maxStorageCar = maxStorageCar;
    }

    public synchronized void produce(final Car car) {
        try {
            while (this.carToBeConsumed.size() >= maxStorageCar) {
                super.wait();
            }
            this.carToBeConsumed.add(car);
            amountOfCompletedCars++;
            super.notify();
        } catch (InterruptedException interruptedException) {
            Thread.currentThread().interrupt();
        }
    }

    public Integer getAmountOfConsumedCars() {
        return amountOfConsumedCars;
    }

    public Integer getAmountOfCompletedCars() {
        return amountOfCompletedCars;
    }

    public synchronized Car consume() {
        try {
            while (carToBeConsumed.isEmpty()) {
                super.wait();
            }
            super.notify();
            amountOfConsumedCars++;
            return carToBeConsumed.poll();
        } catch (InterruptedException interruptedException) {
            Thread.currentThread().interrupt();
            throw new RuntimeException(interruptedException);
        }
    }

    @Override
    public String getQuantity() {
        Integer asd = carToBeConsumed.size();
        return asd.toString();
    }
}
