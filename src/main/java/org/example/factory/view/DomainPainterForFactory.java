package org.example.factory.view;

import org.example.factory.storages.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class DomainPainterForFactory extends JPanel implements ActionListener {

    private String label = "";
    private int shift = 0;
    public String count;
    Timer timer = new Timer(100, this);
    Storage storage;
    public DomainPainterForFactory(String label, MotorStorage motorStorage) {
        this.label = label;
        shift = 15;
        this.storage = motorStorage;
        timer.start();
    }

    public DomainPainterForFactory(String label, BodyStorage bodyStorage) {
        this.label = label;
        shift = 15;
        this.storage = bodyStorage;
        timer.start();
    }

    public DomainPainterForFactory(String label, AccessoryStorage accessoryStorage) {
        this.label = label;
        shift = 15;
        this.storage = accessoryStorage;
        timer.start();
    }

    public DomainPainterForFactory(CarStorage carStorage) {
        this.storage = carStorage;
        timer.start();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.setColor(Color.BLACK);
        g.setFont(new Font("Arial", Font.PLAIN, 12));
        g.drawString(label, 10, 10);
        g.setColor(Color.BLACK);
        g.fillRect(0, shift, 80, 40);
        g.setColor(Color.CYAN);
        g.setFont(new Font("Arial", Font.PLAIN, 12));
        count = storage.getQuantity();
        g.drawString(count, 20, 20+shift);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        repaint();
    }
}
