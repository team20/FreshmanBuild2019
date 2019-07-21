package frc.robot.subsystems;

//import com.ctre.phoenix.motorcontrol.ControlMode;

import edu.wpi.first.wpilibj.AnalogInput;
//import frc.robot.Robot;

public class LineFollower {
    AnalogInput blue = new AnalogInput(0);
    AnalogInput yellow = new AnalogInput(1);
    AnalogInput white = new AnalogInput(3);
    AnalogInput green = new AnalogInput(2);
    static Drivetrain drive;
    boolean startTimeSet;
    double startTime;

    public LineFollower() {
        drive = new Drivetrain();
        startTimeSet = false;
        startTime = 0.0;
    }

    public void linay() {
        int yellowValue = yellow.getValue();
        int whiteValue = white.getValue();
        int greenValue = green.getValue();
        int blueValue = blue.getValue();
        System.out.println("Yellow: " + yellowValue);
        System.out.println("White: " + whiteValue);
        System.out.println("Green: " + greenValue);
        System.out.println("Blue: " + blueValue);
    }

    // public void wheresLinay() {
    //     int yellowValue = yellow.getValue();
    //     int greenValue = green.getValue();
    //     if (Robot.driverJoy.getRawButton(7)) {
    //         if (yellowValue < 500) { // we need to turn to the right???
    //             System.out.println("Too far to the left. Making right turn.");
    //             Drivetrain.drive.bl.set(ControlMode.PercentOutput, 0.2);
    //             Drivetrain.drive.br.set(ControlMode.PercentOutput, 0.2);
    //         }
    //         else if (yellowValue > 500) { // we're on target
    //             System.out.println("Locked on target.");
    //             Robot.drive.bl.set(ControlMode.PercentOutput, 0);
    //             Robot.drive.br.set(ControlMode.PercentOutput, 0);
    //         }
    //     }
    //     if (Robot.driverJoy.getRawButton(8)) {
    //         if (yellowValue < 500) { // we need to turn to the left???
    //             System.out.println("Too far to the right. Making left turn.");
    //             Robot.drivingClass.bl.set(ControlMode.PercentOutput, -0.2);
    //             Robot.drivingClass.br.set(ControlMode.PercentOutput, -0.2);
    //         }
    //         else if (yellowValue > 500) {
    //             System.out.println("Locked on target.");
    //             Robot.drivingClass.bl.set(ControlMode.PercentOutput, 0);
    //             Robot.drivingClass.br.set(ControlMode.PercentOutput, 0);
    //         }
    //     }
    // }
}