package frc.robot;

//import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import com.kauailabs.navx.frc.AHRS;

import edu.wpi.first.cameraserver.CameraServer;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.SerialPort;
import edu.wpi.first.wpilibj.TimedRobot;
//import edu.wpi.first.wpilibj.Timer;

public class Robot extends TimedRobot {
    static Joystick driverJoy;
    static Joystick operatorJoy;

    static boolean firstTime = true;

    final int firstDistance = 120;
    final int secondDistance = 60;

    static int autostage = 0;

    static AHRS NAVXgyro = new AHRS(SerialPort.Port.kMXP);

    static Drive drivingClass = new Drive();
    static Intake spinningIntake = new Intake();
    static Shooter shootNow = new Shooter();
    static Pneumatics maticsMatics = new Pneumatics();
    static LineFollower testTest = new LineFollower();
    static AutoCompilation autos = new AutoCompilation();

    @Override
    public void robotInit() {
        driverJoy = new Joystick(0);
        operatorJoy = new Joystick(1);

        CameraServer.getInstance().startAutomaticCapture();

        NAVXgyro.reset();

        drivingClass.initQuadrature();

    }

    @Override
    public void autonomousInit() {

        NAVXgyro.reset();
        firstTime = true;

    }

    @Override
    public void autonomousPeriodic() {

        autos.firstAuto(120, .5, -60, 60, .5);
        autos.secondAuto(.5, false, true, false, false);

    }

    @Override
    public void teleopPeriodic() {
    
        drivingClass.move(driverJoy.getRawAxis(1), driverJoy.getRawAxis(2), driverJoy.getRawAxis(3));
        spinningIntake.spinIntake(operatorJoy.getRawAxis(1));
        shootNow.driverShoot(driverJoy.getRawButton(4), driverJoy.getRawButton(2), driverJoy.getRawButton(1), driverJoy.getRawButton(3));
        shootNow.operatorShoot(operatorJoy.getRawButton(4), operatorJoy.getRawButton(2), operatorJoy.getRawButton(1), operatorJoy.getRawButton(3));
        maticsMatics.openGrabbyClaw(operatorJoy.getRawAxis(2));
        maticsMatics.closeGrabbyClaw(operatorJoy.getRawAxis(3));
        maticsMatics.hatchCollector(operatorJoy.getRawAxis(5));
    }

    @Override
    public void testInit() {
    }

    @Override
    public void testPeriodic() {
        testTest.linay();
        testTest.getValues();
    //    testTest.wheresLinay();
    }
}