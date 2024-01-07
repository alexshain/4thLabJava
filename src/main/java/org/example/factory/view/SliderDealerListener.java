package org.example.factory.view;

import org.example.factory.model.DealerManager;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

public class SliderDealerListener implements ChangeListener {
    public int value = 4;
    public DealerManager dealerManager;

    public SliderDealerListener(DealerManager dealerManager) {
        this.dealerManager = dealerManager;
    }
    @Override
    public void stateChanged(ChangeEvent e) {
        value = ((JSlider)e.getSource()).getValue();
        dealerManager.setSECONDS_SLEEP(value);
    }
}
