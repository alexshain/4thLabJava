package org.example.factory.log;

import org.example.factory.model.Car;

import java.io.FileWriter;
import java.io.IOException;

public class LogFileWriter {
    private final int dealerID;
    private final Car car;
    public LogFileWriter(int dealerID, Car car) {
        this.car = car;
        this.dealerID = dealerID;
    }

    public void write() {
        try(FileWriter writer = new FileWriter("/Users/sasha/IdeaProjects/factory/logfile.txt", true))
        {
            String text = "<"+ java.time.LocalTime.now() + ">: Dealer<" + dealerID + ">: Auto <" + car.getCAR_ID()
                    + "> (Body: <" + car.getBody().getBODY_ID() + ">, Motor: <" + car.getMotor().getMOTOR_ID()
                    + ">, Accessory: <" + car.getAccessory().getACCESSORY_ID() + ">)";
            writer.write(text);

        }
        catch(IOException ex) {
            System.out.println(ex.getMessage());
        }
    }
}
