package frc.robot;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.TimedRobot;
import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import edu.wpi.first.cameraserver.CameraServer;
import edu.wpi.first.wpilibj.Timer;

public class Robot extends TimedRobot {
    Joystick driverJoy;
    TalonSRX fl = new TalonSRX(2); // front left motor
    TalonSRX fr = new TalonSRX(4); // front right motor
    TalonSRX bl = new TalonSRX(3); // back left motor
    TalonSRX br = new TalonSRX(1); // back right motor
    boolean startTimeSet = false;
    double startTime = 0;

    @Override
    public void robotInit() { //When the robot starts
        driverJoy = new Joystick(0); // initializes the main controller
        bl.configSelectedFeedbackSensor(com.ctre.phoenix.motorcontrol.FeedbackDevice.QuadEncoder, 0, 1000);
        bl.setSensorPhase(false);
        br.configSelectedFeedbackSensor(com.ctre.phoenix.motorcontrol.FeedbackDevice.QuadEncoder, 0, 1000);
        br.setSensorPhase(false);
        fl.follow(bl);
        fr.follow(br);
        CameraServer.getInstance().startAutomaticCapture();
    } 

    @Override
    public void autonomousPeriodic () {
        if (startTimeSet == false) {
            startTime = Timer.getFPGATimestamp();
            startTimeSet = true;
        }
        if (Timer.getFPGATimestamp() - startTime < 2) {
            bl.set(ControlMode.PercentOutput, (.25));
            br.set(ControlMode.PercentOutput, (-.25));
        }
        else {
            bl.set(ControlMode.PercentOutput, (0));
            br.set(ControlMode.PercentOutput, (0));
        }
    }

    @Override
    public void teleopPeriodic () { //When you get to start moving the robot
        double straight = (driverJoy.getRawAxis(1)); // amount up or down on the left toggle
        double leftTurn = (driverJoy.getRawAxis(3));
        double rightTurn = (driverJoy.getRawAxis(2));

        //Arcade Mode
        bl.set(ControlMode.PercentOutput, (-straight - rightTurn + leftTurn));
        br.set(ControlMode.PercentOutput, (straight - rightTurn + leftTurn));
   }
}