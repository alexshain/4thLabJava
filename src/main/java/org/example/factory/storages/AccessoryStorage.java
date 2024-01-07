package org.example.factory.storages;

import org.example.factory.model.Accessory;

import java.util.ArrayDeque;
import java.util.Queue;

public final class AccessoryStorage extends Storage {
    private final Queue<Accessory> accessoryToBeConsumed;
    private final int maxStorageAccessory;

    public AccessoryStorage(int maxStorageAccessory) {
        this.accessoryToBeConsumed = new ArrayDeque<>(maxStorageAccessory);
        this.maxStorageAccessory = maxStorageAccessory;
    }

    public synchronized void produce(final Accessory accessory) {
        try {
            while (this.accessoryToBeConsumed.size() >= maxStorageAccessory) {
                super.wait();
            }
            this.accessoryToBeConsumed.add(accessory);
            super.notify();
        } catch (InterruptedException interruptedException) {
            Thread.currentThread().interrupt();
        }
    }

    public synchronized Accessory consume() {
        try {
            while (accessoryToBeConsumed.isEmpty()) {
                super.wait();
            }
            super.notify();
            return accessoryToBeConsumed.poll();
        } catch (InterruptedException interruptedException) {
            Thread.currentThread().interrupt();
            throw new RuntimeException(interruptedException);
        }
    }

    @Override
    public String getQuantity() {
        Integer asd = accessoryToBeConsumed.size();
        return asd.toString();
    }
}
