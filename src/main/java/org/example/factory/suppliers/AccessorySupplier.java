package org.example.factory.suppliers;

import org.example.factory.model.Accessory;
import org.example.factory.storages.AccessoryStorage;

import java.util.concurrent.TimeUnit;

import static java.lang.Thread.currentThread;

public final class AccessorySupplier implements Runnable {
    private static final String massage = "Accessory '%s' is produced.\n";
    public int SECONDS_SLEEP;
    private static final Integer INITIAL_NEXT_ACCESSORY_INDEX = 1;
    private final Integer SUPPLIER_INDEX;
    private int nextAccessoryIndex;
    private final AccessoryStorage accessoryStorage;

    public AccessorySupplier(AccessoryStorage accessoryStorage, Integer SUPPLIER_INDEX, int SECONDS_SLEEP) {
        this.nextAccessoryIndex = INITIAL_NEXT_ACCESSORY_INDEX;
        this.accessoryStorage = accessoryStorage;
        this.SUPPLIER_INDEX = SUPPLIER_INDEX;
        this.SECONDS_SLEEP = SECONDS_SLEEP;
    }

    public Accessory create() {
        return new Accessory(SUPPLIER_INDEX.toString() + "_" + (this.nextAccessoryIndex++));
    }

    @Override
    public void run() {
        try {
            while (!currentThread().isInterrupted()) {
                final Accessory produceAccessory = create();
                TimeUnit.SECONDS.sleep(SECONDS_SLEEP);
                this.accessoryStorage.produce(produceAccessory);
                System.out.printf(massage, produceAccessory);
            }
        } catch (InterruptedException interruptedException) {
            currentThread().interrupt();
        }
    }

    public void setSECONDS_SLEEP(int SECONDS_SLEEP) {
        this.SECONDS_SLEEP = SECONDS_SLEEP;
    }
}