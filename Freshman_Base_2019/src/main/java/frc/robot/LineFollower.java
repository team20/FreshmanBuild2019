package frc.robot;

import edu.wpi.first.wpilibj.AnalogInput;

public class LineFollower {
    AnalogInput blue;
    AnalogInput yellow;
    AnalogInput white;
    AnalogInput green;
    AnalogInput orange;
    int blueValue;
    int yellowValue;
    int whiteValue;
    int greenValue;
    int orangeValue;
    static Drive drive;
    boolean startTimeSet;
    double startTime;

    public LineFollower() {
        drive = new Drive();
        startTimeSet = false;
        startTime = 0.0;
    }

    public void getValues() {
        // blue = new AnalogInput(0); //outside values
        yellow = new AnalogInput(1); //inside values, good values
        white = new AnalogInput(3); //outside values, bad values
        green = new AnalogInput(2); //inside values, good values
        orange = new AnalogInput(0); //inside values
        // blueValue = blue.getValue();
        yellowValue = yellow.getValue();
        whiteValue = white.getValue();
        greenValue = green.getValue();
        orangeValue = orange.getValue();
    }
    
    public void linay() {
        // System.out.println("Blue: " + blueValue);
        System.out.println("Yellow: " + yellowValue);
        System.out.println("White: " + whiteValue);
        System.out.println("Green: " + greenValue);
        System.out.println("Orange: " + orangeValue);
    }

    public void wheresLinay() {
        if (yellowValue > 1000 && greenValue > 1000 && orangeValue > 1000) { //we're on target
            System.out.println("Locked on target. I repeat: Locked on target.");
            if (Robot.driverJoy.getRawButton(7) == true) {
                drive.moveDistance(18, .5);
            }
        }
        else if (yellowValue < 1000 || greenValue < 1000 || orangeValue < 1000) { //we need to make a turn to find target
            System.out.println("NOT on target. I repeat: NOT on target. Death is imminent.");
            System.out.println("Yellow: " + yellowValue);
            System.out.println("Green: " + greenValue);
            System.out.println("Orange: " + orangeValue);
            if (Robot.driverJoy.getRawButton(8) == true) {
                drive.turnAngle(1);
            }
            else {
                drive.moveDistance(0, 0);
            }
        }
    }
}