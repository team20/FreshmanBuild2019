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
    static boolean buttonEnable = false, buttonPrev = false;

    final int firstDistance = 120;
    final int secondDistance = 60;

    static int autostage = 0;

    static AHRS NAVXgyro = new AHRS(SerialPort.Port.kMXP);

    static Drive drivingClass = new Drive();
    static Intake spinningIntake = new Intake();
    static Shooter shootNow = new Shooter();
    static Pneumatics maticsMatics = new Pneumatics();
    static LineFollower lineFollower = new LineFollower();
    static RealAutos autos = new RealAutos();

    boolean startAuto = false;
    boolean startAutoSet = false;

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

        // if (startAuto == false && autos.firstAuto(true, false, false, 1, 0, 60, .25, 60)) {
        //     startAuto = true;
        // }
        // spinningIntake.spinIntake(true, false, true);
        //
        // shootNow.operatorShoot(operatorJoy.getRawAxis(1),
        // operatorJoy.getRawAxis(5)/2);
        // if (startAuto == false && drivingClass.moveDistance(24.0, .25) == true) {
        // startAuto = true;
        // }
        // drivingClass.turnAngle(60);

    }

    @Override
    public void teleopPeriodic() {

        drivingClass.move(driverJoy.getRawAxis(1)*.3, driverJoy.getRawAxis(2)*.3, driverJoy.getRawAxis(3)*.3);
        spinningIntake.spinIntake(driverJoy.getRawButton(1), driverJoy.getRawButton(4), driverJoy.getRawButton(2));
        shootNow.operatorShoot(operatorJoy.getRawAxis(1), operatorJoy.getRawAxis(5));
        maticsMatics.openGrabbyClaw(operatorJoy.getRawAxis(2));
        maticsMatics.hatchCollector(operatorJoy.getRawButton(3), operatorJoy.getRawButton(1));
    }

    @Override
    public void testInit() {

    }

    @Override
    public void testPeriodic() {
    }
}