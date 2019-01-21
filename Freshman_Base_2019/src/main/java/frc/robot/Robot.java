package frc.robot;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.DigitalModule;
import edu.wpi.first.wpilibj.I2C;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.Talon;
import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.cameraserver.CameraServer;

public class Robot extends TimedRobot {
    I2C i2c;
    byte[] toSend = new byte[1];
    Joystick driverJoy;
   // Joystick operatorJoy;
    Talon fl = new Talon(2); // front left motor
    Talon fr = new Talon(4); // front right motor
    Talon bl = new Talon(3); // back left motor
    Talon br = new Talon(1); // back right motor
  
    @Override
    public void robotInit() { //When the robot starts
        DigitalModule module = DigitalModule.getInstance(2);
        i2c = module.getI2C(168);
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
    public void teleopPeriodic () { //When you get to start moving the robot
        boolean on = false;
        System.out.println("Starting Operator Control");
        while (isTeleopPeriodic()){
            if (on)
                toSend[0] = 76;
            else
                toSend[0] = 72;
            on = ! on;
            i2c.transcation(toSend, 1, null, 0);
            Timer.delay(.0005);
        }
        double straight = (driverJoy.getRawAxis(1)); // amount up or down on the left toggle
        double leftTurn = (driverJoy.getRawAxis(3));
        double rightTurn = (driverJoy.getRawAxis(2));

        //Arcade Mode
        bl.set(ControlMode.PercentOutput, (-straight - rightTurn + leftTurn));
        br.set(ControlMode.PercentOutput, (straight - rightTurn + leftTurn));
   }
}