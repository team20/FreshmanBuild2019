package frc.robot;

//import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import com.kauailabs.navx.frc.AHRS;

import edu.wpi.first.cameraserver.CameraServer;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.SerialPort;
import edu.
wpi.first.wpilibj.TimedRobot;
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
    static RealAutos autos = new RealAutos();

    @Override
    public void robotInit() {
        driverJoy = new Joystick(0);
        operatorJoy = new Joystick(1);
        // CameraServer.getInstance().startAutomaticCapture();
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

        autos.firstAuto(1, 0, false, true, false, false, true, false, false, false, 84, .5, 60);
    }

    @Override
    public void teleopPeriodic() {
    
        drivingClass.move(driverJoy.getRawAxis(1), driverJoy.getRawAxis(2), driverJoy.getRawAxis(3));
        spinningIntake.spinIntake(operatorJoy.getRawAxis(5));
        shootNow.operatorShoot(operatorJoy.getRawButton(4), operatorJoy.getRawButton(2), operatorJoy.getRawButton(1), operatorJoy.getRawButton(3));
        maticsMatics.openGrabbyClaw(operatorJoy.getRawAxis(2));
        maticsMatics.closeGrabbyClaw(operatorJoy.getRawAxis(3));
        maticsMatics.hatchCollector(operatorJoy.getRawAxis(1));
     //   testTest.wheresLinay();
     // drivingClass.tankDrive(driverJoy.getRawAxis(1), driverJoy.getRawAxis(5));
      // y is stop
      // b is half forward

      // a is full forward
      // x is full backwards
    }


    @Override
    public void testInit() {
    }

    @Override
    public void testPeriodic() {
        testTest.linay(); 
        testTest.getValues();
    //    spinningIntake.spinIntake(1);
    //    testTest.wheresLinay();
    }
}