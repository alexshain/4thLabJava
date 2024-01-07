package org.example.factory.storages;

import org.example.factory.model.Body;

import java.util.ArrayDeque;
import java.util.Queue;

public final class BodyStorage extends Storage {
    private final Queue<Body> bodyToBeConsumed;
    private final int maxStorageBody;

    public BodyStorage(int maxStorageBody) {
        this.bodyToBeConsumed = new ArrayDeque<>(maxStorageBody);
        this.maxStorageBody = maxStorageBody;
    }

    public synchronized void produce(final Body body) {
        try {
            while (this.bodyToBeConsumed.size() >= maxStorageBody) {
                super.wait();
            }
            this.bodyToBeConsumed.add(body);
            super.notify();
        } catch (InterruptedException interruptedException) {
            Thread.currentThread().interrupt();
        }
    }

    public synchronized Body consume() {
        try {
            while (bodyToBeConsumed.isEmpty()) {
                super.wait();
            }
            super.notify();
            return bodyToBeConsumed.poll();
        } catch (InterruptedException interruptedException) {
            Thread.currentThread().interrupt();
            throw new RuntimeException(interruptedException);
        }
    }

    @Override
    public String getQuantity() {
        Integer asd = bodyToBeConsumed.size();
        return asd.toString();
    }
}
