package org.example.factory.view;

import javax.swing.*;

public class SlidePainter extends JPanel {
    JSlider slider = new JSlider(1, 6, 4);

    public SlidePainter(String lable) {
        slider.setOrientation(JSlider.HORIZONTAL);
        slider.setPaintTicks(true);
        slider.setInverted(true);
        slider.setMajorTickSpacing(1);
        slider.setPaintTicks(true);
        add(new JLabel(lable));
        add(slider);
    }

    public JSlider getSlider() {
        return slider;
    }
}
