package org.example.factory.suppliers;

import org.example.factory.model.Motor;
import org.example.factory.storages.MotorStorage;

import java.util.concurrent.TimeUnit;

import static java.lang.Thread.currentThread;

public final class MotorSupplier extends Supplier implements Runnable {
    private static final String massage = "Motor '%s' is produced.\n";
    public int SECONDS_SLEEP = 4;
    private static final int INITIAL_NEXT_MOTOR_INDEX = 1;
    private static final String name = "Motor#%d";

    private int nextMotorIndex;
    private final MotorStorage motorStorage;

    public MotorSupplier(MotorStorage motorStorage) {
        this.nextMotorIndex = INITIAL_NEXT_MOTOR_INDEX;
        this.motorStorage = motorStorage;
    }

    public Motor create() {
        return new Motor(String.format(name, this.nextMotorIndex++));
    }

    @Override
    public void run() {
        try {
            while (!currentThread().isInterrupted()) {
                final Motor produceMotor = create();
                TimeUnit.SECONDS.sleep(SECONDS_SLEEP);
                this.motorStorage.produce(produceMotor);
                System.out.printf(massage, produceMotor);
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
