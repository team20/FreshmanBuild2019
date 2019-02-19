package frc.robot;

import edu.wpi.first.wpilibj.AnalogInput;

public class LineFollower {
    AnalogInput blue;
    AnalogInput yellow;
    AnalogInput white;
    AnalogInput green;
    int blueValue;
    int yellowValue;
    int whiteValue;
    int greenValue;
    static Drive drive;
    boolean startTimeSet;
    double startTime;

    public LineFollower() {
        drive = new Drive();
        startTimeSet = false;
        startTime = 0.0;
    }

    public void getValues() {
        blue = new AnalogInput(0);
    //    yellow = new AnalogInput(1);
    //    white = new AnalogInput(3);
    //    green = new AnalogInput(2);
        blueValue = blue.getValue();
        // yellowValue = yellow.getValue();
        // whiteValue = white.getValue();
        // greenValue = green.getValue();
    }
    
    public void linay() {
        System.out.println("Blue: " + blueValue);
        // System.out.println("Yellow: " + yellowValue);
        // System.out.println("White: " + whiteValue);
        // System.out.println("Green: " + greenValue);
    }

    public void wheresLinay() {
        if (yellowValue > 1000 && greenValue > 1000) { //we're on target
            System.out.println("Locked on target.");
            if (Robot.driverJoy.getRawButton(7) == true) {
                drive.moveDistance(18, .5);
            }
        }
        else if (yellowValue < 1000 && greenValue < 1000) { //make a turn to find target
            System.out.println("NOT on target. I repeat: NOT on target.");
            System.out.println("Yellow: " + yellowValue);
            System.out.println("Green: " + greenValue);
            if (Robot.driverJoy.getRawButton(8) == true) {
                drive.turnAngle(1);
            }
        }
    }
}