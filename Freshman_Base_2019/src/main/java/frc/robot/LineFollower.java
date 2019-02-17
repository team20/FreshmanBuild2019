package frc.robot;

import edu.wpi.first.wpilibj.AnalogInput;

public class LineFollower {
    AnalogInput ai;
    AnalogInput ai2;
    AnalogInput ai3;
    AnalogInput ai4;

    public LineFollower() {
        ai = new AnalogInput(0);
        ai2 = new AnalogInput(1);
        ai3 = new AnalogInput(2);
        ai4 = new AnalogInput(3);
    }

    public void liney() {
        int raw = ai.getValue();
        int raw2 = ai2.getValue();
        int raw3 = ai3.getValue();
        int raw4 = ai4.getValue();
        System.out.println("0: " + raw);
        System.out.println("1: " + raw2);
        System.out.println("2: " + raw3);
        System.out.println("3: " + raw4);

    }
}