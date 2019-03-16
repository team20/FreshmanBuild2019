package frc.robot;

public class RealAutos {
    static AutoCompilation autos;
    double autostage;

    public RealAutos() {
        autos = new AutoCompilation();
        autostage = 0;
    }

    public void firstAuto(double intakeSpeed, double stopSpeed, boolean stop, boolean half, boolean full, boolean back,
            boolean fullStop, boolean fullHalf, boolean fullFull, boolean fullBack, double distance, double speed,
            double setpoint) {
        if (Robot.autostage == 0) {
            if (autos.pickUpTheBall(intakeSpeed, stopSpeed) == true) {
                Robot.autostage++;
            }
        } else if (Robot.autostage == 1) {
            if (autos.shootTheBall(stop, half, full, back, fullStop, fullHalf, fullFull, fullBack) == true) {
                Robot.autostage++;
            }
        } else if (Robot.autostage == 2) {
            if (Robot.drivingClass.moveDistance(distance, speed) == true) {
                Robot.autostage++;
            }
        } else if (Robot.autostage == 3) {
            if (Robot.drivingClass.turnAngle(setpoint) == true) {
                Robot.autostage++;
            }
        }
    }

    public void secondAuto(double intakeSpeed, double stopSpeed, boolean stop, boolean half, boolean full, boolean back,
    boolean fullStop, boolean fullHalf, boolean fullFull, boolean fullBack, double distance, double speed,
    double setpoint) {
        if (Robot.autostage == 0) {
            if (autos.shootTheBall(stop, half, full, back, fullStop, fullHalf, fullFull, fullBack) == true) {
                Robot.autostage++;
            }
        } else if (Robot.autostage == 1) {
            if (autos.pickUpTheBall(intakeSpeed, stopSpeed) == true) {
                Robot.autostage++;
            }
        } else if (Robot.autostage == 2) {
            if (Robot.drivingClass.moveDistance(distance, speed) == true) {
                Robot.autostage++;
            }
        } else if (Robot.autostage == 3) {
            if (Robot.drivingClass.turnAngle(setpoint) == true) {
                Robot.autostage++;
            }
        }
    }
}