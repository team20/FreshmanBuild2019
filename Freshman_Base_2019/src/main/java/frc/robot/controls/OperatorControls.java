package frc.robot.controls;

//import frc.robot.HatchCollector;
//import frc.robot.Shooter;

public class OperatorControls extends LogitechControls {

    String forward;
    String backward;
    String open;
    String closed;

    public OperatorControls(int port) {
        super(port);
    }

    public void operatorControls() {
        controls();
    }

    public void controls() {
        if (getLeftYAxis() > 0.5) {
            frc.robot.subsystems.Shooter.setSpeed(1);
        }
        if (getLeftYAxis() < -0.5) {
            frc.robot.subsystems.Shooter.setSpeed(-1);
        }
        if (getRightYAxis() > 0.5) {
            frc.robot.subsystems.Shooter.setSpeed(0.6);
        }
        if (getRightYAxis() < -0.5) {
            frc.robot.subsystems.Shooter.setSpeed(-0.6);
        }
        if (getLeftYAxis() < 0.5 && getLeftYAxis() > -0.5 && getRightYAxis() < 0.5 && getRightYAxis() > -0.5) {
            frc.robot.subsystems.Shooter.setSpeed(0);
        }
        if (getRightTrigger() > 0.1) {
            frc.robot.subsystems.HatchCollector.setPosition(closed);
        }
        if (getRightTrigger() < 0.1) {
            frc.robot.subsystems.HatchCollector.setPosition(open);
        }
        if (getXButton()) {
            frc.robot.subsystems.HatchCollector.setPosition(forward);
        }
        if (getAButton()) {
            frc.robot.subsystems.HatchCollector.setPosition(backward);
        }
    }
}