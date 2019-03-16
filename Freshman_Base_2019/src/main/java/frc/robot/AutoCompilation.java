package frc.robot;

import edu.wpi.first.wpilibj.Timer;

public class AutoCompilation {

    boolean startTimeSet;
    double startTime;

    public AutoCompilation() {
        startTimeSet = false;
        startTime = 0;
    }

    public boolean pickUpTheBall(double intakeSpeed, double stopSpeed) {
        if (Robot.firstTime) {
            Robot.firstTime = false;
            Robot.NAVXgyro.reset();
        }
        if (Timer.getFPGATimestamp() - startTime <= .5) {
            Robot.spinningIntake.spinIntake(intakeSpeed);
            return false;
        }
        else {
            Robot.spinningIntake.spinIntake(stopSpeed);
            return true;
        }
    }
    
    public boolean shootTheBall(boolean stop, boolean half, boolean full, boolean back, boolean fullStop, boolean fullHalf, boolean fullFull, boolean fullBack) {
        if (Robot.firstTime) {
            Robot.firstTime = false;
            Robot.NAVXgyro.reset();
        }
        if (Timer.getFPGATimestamp() - startTime <= 2) {
            Robot.shootNow.operatorShoot(stop, half, full, back);
            return false;
        }
        else {
            Robot.shootNow.operatorShoot(fullStop, fullHalf, fullFull, fullBack);
            return true;
        }
    }
}