package frc.robot;

import edu.wpi.first.wpilibj.Timer;

public class AutoCompilation {

    boolean startTimeSet;
    double startTime;

    public AutoCompilation() {
        startTimeSet = false;
        startTime = 0;
    }

    public boolean pickUpTheBall(boolean forward, boolean reverse, boolean stop) {
        if (Robot.firstTime) {
            Robot.firstTime = false;
            Robot.NAVXgyro.reset();
        }
        if (Timer.getFPGATimestamp() - startTime <= 5) {
            Robot.spinningIntake.spinIntake(forward, reverse, stop);
            return false;
        }
        else {
            Robot.spinningIntake.spinIntake(false, false, false);
            return true;
        }
    }
    
    public boolean shootTheBall(double shoot, double halfShoot) {
        if (Robot.firstTime) {
            Robot.firstTime = false;
            Robot.NAVXgyro.reset();
        }
        if (Timer.getFPGATimestamp() - startTime <= 2) {
            Robot.shootNow.operatorShoot(shoot, halfShoot);;
            return false;
        }
        else {
            Robot.shootNow.operatorShoot(0,0);
            return true;
        }
    }
}