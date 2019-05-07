package frc.robot;

//import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import com.kauailabs.navx.frc.AHRS;

import edu.wpi.first.cameraserver.CameraServer;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.SerialPort;
import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.Timer;
import java.lang.Math;

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
    static AutoCompilation tryAutosAgain = new AutoCompilation();

    boolean startAuto = false;
    boolean startAutoSet = false;

    boolean startTimeSet = false;
    boolean startTimeSetTwo = false;
    static double startTime = 0;
    static double goNow = 0;

    double pi = Math.PI;

    @Override
    public void robotInit() {
        driverJoy = new Joystick(1);
        operatorJoy = new Joystick(0);
        CameraServer.getInstance().startAutomaticCapture();
        NAVXgyro.reset();
        drivingClass.initQuadrature();
    }

    @Override
    public void autonomousInit() {

        if (startTimeSet == false) {
            startTime = Timer.getFPGATimestamp();
            startTimeSet = true;
        }

        if (startTimeSetTwo == false) {
            goNow = Timer.getFPGATimestamp();
            startTimeSetTwo = true;
        }
    }

    @Override
    public void autonomousPeriodic() {
        if (!drivingClass.moveDistance(60, .25)) {
            drivingClass.moveDistance(60, .25);
        } else if (drivingClass.moveDistance(60, .25)) {
            shootNow.autoShoot();
        }

        //drivingClass.spin((22.5 * pi), .25);
    }

    @Override
    public void teleopPeriodic() {

        drivingClass.move(driverJoy.getRawAxis(1) * .3, driverJoy.getRawAxis(2) * .3, driverJoy.getRawAxis(3) * .3);
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

// Need to put LED code into the regular code