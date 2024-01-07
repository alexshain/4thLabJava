package org.example;

import org.example.factory.model.DealerManager;
import org.example.factory.model.Worker;
import org.example.factory.storages.*;
import org.example.factory.suppliers.AccessorySupplierManager;
import org.example.factory.suppliers.BodySupplier;
import org.example.factory.suppliers.MotorSupplier;
import org.example.factory.view.FactoryWindow;

import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

public class Main {
    public static void main(String[] args) {
        Properties properties = new Properties();
        try {
            properties.load(new FileReader("/Users/sasha/IdeaProjects/" +
                    "factory/src/main/resources/config.properties"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        final int maxBodyStorageSize = Integer.parseInt(properties.getProperty("StorageBodySize"));
        final int maxMotorStorageSize = Integer.parseInt(properties.getProperty("StorageMotorSize"));
        final int maxAccessoryStorageSize = Integer.parseInt(properties.getProperty("StorageAccessorySize"));
        final int maxCarStorageSize = Integer.parseInt(properties.getProperty("StorageAutoSize"));
        final int AccessorySuppliers = Integer.parseInt(properties.getProperty("AccessorySuppliers"));
        final int amountOfWorkers = Integer.parseInt(properties.getProperty("Workers"));
        final int amountOfDealers = Integer.parseInt(properties.getProperty("Dealers"));

        final BodyStorage bodyStorage = new BodyStorage(maxBodyStorageSize);
        final MotorStorage motorStorage = new MotorStorage(maxMotorStorageSize);
        final AccessoryStorage accessoryStorage = new AccessoryStorage(maxAccessoryStorageSize);
        final CarStorage carStorage = new CarStorage(maxCarStorageSize);

        BodySupplier bodySupplier = new BodySupplier(bodyStorage);
        final Thread bodyProducingThread = new Thread(bodySupplier);
        MotorSupplier motorSupplier = new MotorSupplier(motorStorage);
        final Thread motorProducingThread = new Thread(motorSupplier);

        AccessorySupplierManager accessorySupplierManager = new AccessorySupplierManager(AccessorySuppliers, accessoryStorage);
        accessorySupplierManager.startThreads();

        bodyProducingThread.start();
        motorProducingThread.start();

        Worker worker = new Worker(bodyStorage, motorStorage, accessoryStorage, carStorage);
        TaskPoolRunner taskPoolRunner = new TaskPoolRunner(worker, amountOfWorkers);
        final Thread taskPoolRunnerThread = new Thread(taskPoolRunner);
        taskPoolRunnerThread.start();

        DealerManager dealerManager = new DealerManager(amountOfDealers, carStorage);
        dealerManager.startThreads();

        FactoryWindow factoryWindow = new FactoryWindow(bodyStorage, motorStorage, accessoryStorage, carStorage, taskPoolRunner);
        factoryWindow.showSliders(bodySupplier, motorSupplier, accessorySupplierManager, dealerManager);
        factoryWindow.setBaseProperties();
    }
}