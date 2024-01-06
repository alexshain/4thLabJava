package org.example.factory.suppliers;

import org.example.factory.storages.AccessoryStorage;

import java.util.ArrayList;
import java.util.List;

public class AccessorySupplierManager extends Supplier {
    public final int accessorySuppliers;
    public final AccessoryStorage accessoryStorage;
    public int SECONDS_SLEEP = 4;
    public List<AccessorySupplier> accessoryProducingThreads = new ArrayList<>();

    public AccessorySupplierManager(int accessorySuppliers, AccessoryStorage accessoryStorage) {
        this.accessorySuppliers = accessorySuppliers;
        this.accessoryStorage = accessoryStorage;
    }

    public void startThreads() {
        for(int i=0;i<accessorySuppliers;i++) {
            accessoryProducingThreads.add(new AccessorySupplier(accessoryStorage, i+1, SECONDS_SLEEP));
            (new Thread(accessoryProducingThreads.get(i))).start();
        }
    }

    @Override
    public void setSECONDS_SLEEP(int SECONDS_SLEEP) {
        for(int i=0;i<accessorySuppliers;i++) {
            accessoryProducingThreads.get(i).setSECONDS_SLEEP(SECONDS_SLEEP);
        }
    }
}
