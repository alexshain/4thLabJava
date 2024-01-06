package org.example.factory.view;

import org.example.factory.suppliers.Supplier;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

public class SliderSupplierListener implements ChangeListener {
    public int value = 4;
    public Supplier supplier;

    public SliderSupplierListener(Supplier supplier) {
        this.supplier = supplier;
    }

    @Override
    public void stateChanged(ChangeEvent e) {
        value = ((JSlider)e.getSource()).getValue();
        supplier.setSECONDS_SLEEP(value);
    }
}
