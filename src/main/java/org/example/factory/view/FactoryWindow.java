package org.example.factory.view;

import org.example.factory.model.DealerManager;
import org.example.factory.storages.*;
import org.example.factory.suppliers.AccessorySupplierManager;
import org.example.factory.suppliers.BodySupplier;
import org.example.factory.suppliers.MotorSupplier;

import javax.swing.*;
import java.awt.*;

public class FactoryWindow {
    JFrame window = new JFrame();
    Container container;
    GridBagConstraints constraints = new GridBagConstraints();
    public FactoryWindow(BodyStorage bodyStorage, MotorStorage motorStorage,
                         AccessoryStorage accessoryStorage, CarStorage carStorage, TaskPoolRunner taskPoolRunner) {
        window.setTitle("Factory");
        window.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        container = window.getContentPane();
        container.setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);
        container.setLayout(new GridBagLayout());
        showLabels();
        showDomainsForCount(bodyStorage, motorStorage, accessoryStorage, carStorage, taskPoolRunner);
        //showSliders();
    }

    public void showLabels() {
        constraints.fill = GridBagConstraints.HORIZONTAL;
        constraints.weightx = 1.;
        JLabel label = new JLabel("Completed components");
        constraints.insets    = new Insets(0, 10, 0, 10);
        constraints.gridx = 0;
        constraints.gridy = 0;
        container.add(label, constraints);

        label = new JLabel("Components on storage");
        constraints.insets    = new Insets(0, 10, 0, 10);
        constraints.gridx = 1;
        container.add(label, constraints);

        label = new JLabel("Suppliers rate");
        constraints.insets    = new Insets(0, 70, 0, 10);
        constraints.gridy = 0;
        constraints.gridx = 2;
        container.add(label, constraints);

        label = new JLabel("Completed cars");
        constraints.insets    = new Insets(30, 10, 0, 0);
        constraints.gridx = 0;
        constraints.gridy = 4;
        container.add(label, constraints);

        label = new JLabel("Traded cars");
        constraints.insets    = new Insets(30, 20, 0, 0);
        constraints.gridx = 1;
        constraints.gridy = 4;
        container.add(label, constraints);

        label = new JLabel("Cars in storage");
        constraints.insets    = new Insets(0, 10, 0, 0);
        constraints.gridx = 0;
        constraints.gridy = 6;
        container.add(label, constraints);

        label = new JLabel("Waiting");
        constraints.weighty   = 1.0;
        constraints.insets    = new Insets(0, 20, 0, 0);
        constraints.gridx = 1;
        constraints.gridy = 6;
        container.add(label, constraints);
    }

    public void showDomainsForCount(BodyStorage bodyStorage, MotorStorage mStorage,
                                    AccessoryStorage aStorage, CarStorage carStorage, TaskPoolRunner taskPoolRunner) {
        constraints.insets    = new Insets(0, 10, 0, 0);
        constraints.ipady = 50;
        constraints.gridy = 1;
        constraints.gridx = 0;
        container.add(new DomainPainterForFactory("Body", bodyStorage), constraints);

        constraints.insets    = new Insets(0, 10, 0, 0);
        constraints.ipady = 50;
        constraints.gridy = 1;
        constraints.gridx = 1;
        container.add(new DomainPainterForFactory("Body", bodyStorage), constraints);

        constraints.insets    = new Insets(0, 10, 0, 0);
        constraints.ipady = 50;
        constraints.gridy = 2;
        constraints.gridx = 0;
        container.add(new DomainPainterForFactory("Motor", mStorage), constraints);

        constraints.insets    = new Insets(0, 10, 0, 0);
        constraints.ipady = 50;
        constraints.gridy = 2;
        constraints.gridx = 1;
        container.add(new DomainPainterForFactory("Motor", mStorage), constraints);

        constraints.insets    = new Insets(0, 10, 0, 0);
        constraints.ipady = 50;
        constraints.gridy = 3;
        constraints.gridx = 0;
        container.add(new DomainPainterForFactory("Accessory", aStorage), constraints);

        constraints.insets    = new Insets(0, 10, 0, 0);
        constraints.ipady = 50;
        constraints.gridy = 3;
        constraints.gridx = 1;
        container.add(new DomainPainterForFactory("Accessory", aStorage), constraints);

        constraints.insets    = new Insets(0, 10, 0, 0);
        constraints.ipady = 50;
        constraints.ipadx = 10;
        constraints.gridy = 7;
        constraints.gridx = 0;
        container.add(new DomainPainterForFactory(carStorage), constraints);

        constraints.insets    = new Insets(0, 10, 0, 0);
        constraints.ipady = 50;
        constraints.gridy = 5;
        constraints.gridx = 1;
        container.add(new DomainPainterForDealers(carStorage), constraints);

        constraints.insets    = new Insets(0, 10, 0, 0);
        constraints.ipady = 50;
        constraints.gridy = 5;
        constraints.gridx = 0;
        container.add(new DomainPainterForCompletedCars(carStorage), constraints);

        constraints.insets    = new Insets(0, 10, 0, 0);
        constraints.ipady = 50;
        constraints.gridy = 7;
        constraints.gridx = 1;
        container.add(new DomainPainterForWorkers(taskPoolRunner), constraints);
    }

    public void showSliders(BodySupplier bodySupplier, MotorSupplier motorSupplier,
                            AccessorySupplierManager accessorySupplierManager, DealerManager dealerManager) {
        constraints.gridx = 2;
        constraints.gridy = 1;
        SlidePainter bodySlider = new SlidePainter("Body");
        SliderSupplierListener sliderListener = new SliderSupplierListener(bodySupplier);
        bodySlider.getSlider().addChangeListener(sliderListener);
        container.add(bodySlider, constraints);

        constraints.gridx = 2;
        constraints.gridy = 2;
        SlidePainter motorSlider = new SlidePainter("Motor");
        sliderListener = new SliderSupplierListener(motorSupplier);
        motorSlider.getSlider().addChangeListener(sliderListener);
        container.add(motorSlider, constraints);

        constraints.gridx = 2;
        constraints.gridy = 3;
        SlidePainter accessorySlider = new SlidePainter("Accessory");
        sliderListener = new SliderSupplierListener(accessorySupplierManager);
        accessorySlider.getSlider().addChangeListener(sliderListener);
        container.add(accessorySlider, constraints);

        constraints.gridx = 2;
        constraints.gridy = 4;
        SlidePainter tradeSlider = new SlidePainter("Trade rate");
        SliderDealerListener sliderDealerListener = new SliderDealerListener(dealerManager);
        tradeSlider.getSlider().addChangeListener(sliderDealerListener);
        container.add(tradeSlider, constraints);
    }

    public void setBaseProperties() {
        window.setLocation(400,50);
        window.pack();
        window.setVisible(true);
    }
}