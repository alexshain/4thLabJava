package org.example.factory.storages;

import org.example.factory.model.Motor;

import java.util.ArrayDeque;
import java.util.Queue;

public final class MotorStorage extends Storage {
    public final Queue<Motor> motorToBeConsumed;
    private final int maxStorageMotor;

    public MotorStorage(int maxStorageMotor) {
        this.motorToBeConsumed = new ArrayDeque<>(maxStorageMotor);
        this.maxStorageMotor = maxStorageMotor;
    }

    public synchronized void produce(final Motor motor) {
        try {
            while (this.motorToBeConsumed.size() >= maxStorageMotor) {
                super.wait();
            }
            this.motorToBeConsumed.add(motor);
            super.notify();
        } catch (InterruptedException interruptedException) {
            Thread.currentThread().interrupt();
        }
    }

    public synchronized Motor consume() {
        try {
            while (motorToBeConsumed.isEmpty()) {
                super.wait();
            }
            super.notify();
            return motorToBeConsumed.poll();
        } catch (InterruptedException interruptedException) {
            Thread.currentThread().interrupt();
            throw new RuntimeException(interruptedException);
        }
    }

    @Override
    public String getQuantity() {
        Integer asd = motorToBeConsumed.size();
        return asd.toString();
    }
}
