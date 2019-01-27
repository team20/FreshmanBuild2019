package frc.robot;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import com.kauailabs.navx.frc.AHRS;

import edu.wpi.first.cameraserver.CameraServer;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.PIDController;
import edu.wpi.first.wpilibj.PIDOutput;
import edu.wpi.first.wpilibj.SerialPort;
import edu.wpi.first.wpilibj.TimedRobot;

public class Robot extends TimedRobot implements PIDOutput {
    Joystick driverJoy;
    TalonSRX fl = new TalonSRX(2); // front left motor
    TalonSRX fr = new TalonSRX(4); // front right motor
    TalonSRX bl = new TalonSRX(3); // back left motor
    TalonSRX br = new TalonSRX(1); // back right motor
    boolean startTimeSet = false;
    double startTime = 0;
    double yawAngle = 0.0;
    boolean firstTime = true;
    double goalAngle = 0.0;
    double PIDValue = 0.0;

    AHRS NAVXgyro = new AHRS(SerialPort.Port.kMXP);

    PIDController PID = new PIDController(.01, 0, .01, NAVXgyro, this);

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
    
        NAVXgyro.reset();
        
    }
    @Override
    public void autonomousInit() {

        NAVXgyro.reset();
        firstTime = true;
        PID.setAbsoluteTolerance(5);
        PID.setInputRange(-180, 180);
        PID.setOutputRange(-1, 1);
        PID.setContinuous();
        PID.enable();

    }

    @Override
    public void autonomousPeriodic () {
        if (firstTime) {
            firstTime = false;
            NAVXgyro.reset();
            goalAngle = NAVXgyro.getYaw();
            goalAngle = 90 + goalAngle;
            System.out.println(goalAngle);
            PID.setSetpoint(90);
        }

        yawAngle = NAVXgyro.getYaw();

        System.out.println("Current angle " + yawAngle);
        System.out.println(goalAngle);

        bl.set(ControlMode.PercentOutput, (PIDValue));
        br.set(ControlMode.PercentOutput, (PIDValue));
        System.out.println(PIDValue);

    // if (!PID.onTarget()) {
    // }
    // else {
    // bl.set(ControlMode.PercentOutput, (0));
    // br.set(ControlMode.PercentOutput, (0));
    // PID.disable();
    // }
    // if (startTimeSet == false) {
    // startTime = Timer.getFPGATimestamp();
    // startTimeSet = true; */
    // if (Timer.getFPGATimestamp() - startTime < 5) {
    // if (Timer.getFPGATimestamp() - startTime < 2) {
    // bl.set(ControlMode.PercentOutput, (.25));
    // br.set(ControlMode.PercentOutput, (-.25));
    // }
    // else {  
    // bl.set(ControlMode.PercentOutput, (.2));
    // br.set(ControlMode.PercentOutput, (-.5));
    // }
    // else {
    // bl.set(ControlMode.PercentOutput, (0));
    // br.set(ControlMode.PercentOutput, (0));
    // }
    // if (startTimeSet == true && Timer.getFPGATimestamp() - startTime > 5) {
    // startTimeSet = false;
    // }
    
    }

    @Override
    public void teleopPeriodic () { //When you get to start moving the robot
        PID.disable();
        double straight = (driverJoy.getRawAxis(1)); // amount up or down on the left toggle
        double leftTurn = (driverJoy.getRawAxis(3));
        double rightTurn = (driverJoy.getRawAxis(2));

        //Arcade Mode
        bl.set(ControlMode.PercentOutput, (-straight - rightTurn + leftTurn));
        br.set(ControlMode.PercentOutput, (straight - rightTurn + leftTurn));
   }

    @Override
    public void pidWrite(double output) {
        PIDValue = output;
    }
}