package frc.robot;

import edu.wpi.first.wpilibj.Timer;

public class AutoCompilation {

    boolean startTimeSet;
    double startTime;

    public AutoCompilation() {
        startTimeSet = false;
        startTime = 0;
    }

    public void firstAuto(double firstDistance, double firstSpeed, double setpoint, double secondDistance,
            double secondSpeed) {
        if (Robot.firstTime) {
            Robot.firstTime = false;
            Robot.NAVXgyro.reset();
            Robot.autostage = 0;
        }
        if (Robot.autostage == 0) {
            if (Robot.drivingClass.moveDistance(firstDistance, firstSpeed)) {
                Robot.autostage++;
            }
        } else if (Robot.autostage == 1) {
            if (Robot.drivingClass.turnAngle(setpoint)) {
                Robot.autostage++;
            }
        } else if (Robot.autostage == 2) {
            if (Robot.drivingClass.moveDistance(secondDistance, secondSpeed)) {
                Robot.autostage++;
            }
        }
    }

    public void secondAuto(double intakeSpeed, boolean stop, boolean half, boolean full, boolean back) {
        if (Robot.firstTime) {
            Robot.firstTime = false;
            Robot.NAVXgyro.reset();
            Robot.autostage = 0;
        }
        if (startTimeSet == false) {
            startTime = Timer.getFPGATimestamp();
            startTimeSet = true;
        }
        if (Robot.autostage == 0) {
            if (Timer.getFPGATimestamp() - startTime <= 2) {
                Robot.spinningIntake.spinIntake(intakeSpeed);
            } else {
                Robot.autostage++;
            }
        } else if (Robot.autostage == 1) {
            if (Timer.getFPGATimestamp() - startTime <= 3) {
                Robot.shootNow.driverShoot(stop, half, full, back);
            } else {
                Robot.autostage++;
            }
        }
    }
}