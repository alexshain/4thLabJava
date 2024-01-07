package org.example.factory.suppliers;

import org.example.factory.model.Body;
import org.example.factory.storages.BodyStorage;

import java.util.concurrent.TimeUnit;

import static java.lang.Thread.currentThread;

public final class BodySupplier extends Supplier implements Runnable {
    private static final String massage = "Body '%s' is produced.\n";
    public int SECONDS_SLEEP = 4;
    private static final int INITIAL_NEXT_BODY_INDEX = 1;
    private static final String name = "Body#%d";
    private int nextBodyIndex;
    private final BodyStorage bodyStorage;

    public BodySupplier(BodyStorage bodyStorage) {
        this.nextBodyIndex = INITIAL_NEXT_BODY_INDEX;
        this.bodyStorage = bodyStorage;
    }

    public Body create() {
        return new Body(String.format(name, this.nextBodyIndex++));
    }

    @Override
    public void run() {
        try {
            while (!currentThread().isInterrupted()) {
                final Body produceBody = create();
                TimeUnit.SECONDS.sleep(SECONDS_SLEEP);
                this.bodyStorage.produce(produceBody);
                System.out.printf(massage, produceBody);
            }
        } catch (InterruptedException interruptedException) {
            currentThread().interrupt();
        }
    }

    @Override
    public void setSECONDS_SLEEP(int SECONDS_SLEEP) {
        this.SECONDS_SLEEP = SECONDS_SLEEP;
    }
}
