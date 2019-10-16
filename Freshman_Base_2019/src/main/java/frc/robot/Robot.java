package frc.robot;

import edu.wpi.first.cameraserver.CameraServer;
import edu.wpi.first.wpilibj.TimedRobot;
import frc.robot.controls.DriverControls;
import frc.robot.controls.OperatorControls;


public class Robot extends TimedRobot {

    private DriverControls driverControls;
    private OperatorControls operatorControls;


    @Override
    public void robotInit() {
        driverControls = new DriverControls(1);
        operatorControls = new OperatorControls(2);
        CameraServer.getInstance().startAutomaticCapture();
    }

    @Override
    public void autonomousInit() {
    }

    @Override
    public void autonomousPeriodic() {
        driverControls.controls();
        operatorControls.controls();
    }

    @Override
    public void teleopPeriodic() {
        driverControls.controls();
        operatorControls.controls();
    }

    @Override
    public void testInit() {
    }

    @Override
    public void testPeriodic() {
    }
}