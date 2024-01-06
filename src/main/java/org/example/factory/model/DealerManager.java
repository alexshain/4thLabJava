package org.example.factory.model;

import org.example.factory.storages.AccessoryStorage;
import org.example.factory.storages.CarStorage;
import org.example.factory.suppliers.AccessorySupplier;

import java.util.ArrayList;
import java.util.List;

public class DealerManager {
    public final int dealers;
    public final CarStorage carStorage;
    public int SECONDS_SLEEP = 4;
    public List<Dealer> dealersThreads = new ArrayList<>();

    public DealerManager(int dealers, CarStorage carStorage) {
        this.dealers = dealers;
        this.carStorage = carStorage;
    }

    public void startThreads() {
        for(int i=0;i<dealers;i++) {
            dealersThreads.add(new Dealer(carStorage, i+1, SECONDS_SLEEP));
            (new Thread(dealersThreads.get(i))).start();
        }
    }

    public void setSECONDS_SLEEP(int SECONDS_SLEEP) {
        for(int i=0;i<dealers;i++) {
            dealersThreads.get(i).setSECONDS_SLEEP(SECONDS_SLEEP);
        }
    }
}
