package org.example.factory.view;

import org.example.factory.storages.TaskPoolRunner;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class DomainPainterForWorkers extends JPanel implements ActionListener {
    public String count;
    Timer timer = new Timer(100, this);
    TaskPoolRunner taskPoolRunner;

    public DomainPainterForWorkers(TaskPoolRunner taskPoolRunner) {
        this.taskPoolRunner = taskPoolRunner;
        timer.start();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.setColor(Color.BLACK);
        g.setFont(new Font("Arial", Font.PLAIN, 12));
        g.setColor(Color.BLACK);
        g.fillRect(0, 0, 80, 40);
        g.setColor(Color.CYAN);
        g.setFont(new Font("Arial", Font.PLAIN, 12));
        count = taskPoolRunner.getQueue().toString();
        g.drawString(count, 20, 20);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        repaint();
    }
}
