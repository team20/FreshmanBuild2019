package frc.robot;

public class RealAutos {
    static AutoCompilation autos;
    double autostage;

    public RealAutos() {
        autos = new AutoCompilation();
        autostage = 0;
    }

    public boolean firstAuto(boolean forward, boolean reverse, boolean stop, double shoot, double halfShoot, double distance, double speed, double setpoint) {
        if (Robot.autostage == 0) {
            if (autos.pickUpTheBall(forward, reverse, stop) == true) {
                Robot.autostage++;
                return false;
            }
            return false;
        } else if (Robot.autostage == 1) {
            if (autos.shootTheBall(shoot, halfShoot) == true) {
                Robot.autostage++;
                return false;
            }
            return false;
        } else if (Robot.autostage == 2) {
            if (Robot.drivingClass.moveDistance(distance, speed) == true) {
                ++Robot.autostage;
                return false;
            }
            return false;
        } else if (Robot.autostage == 3) {
            if (Robot.drivingClass.turnAngle(setpoint) == true) {
                Robot.autostage++;
                return true;
            }
        } return true;
    }
}