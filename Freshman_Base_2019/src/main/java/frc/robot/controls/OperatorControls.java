package frc.robot.controls;

import frc.robot.subsystems.HatchCollector;
import frc.robot.subsystems.Shooter;

public class OperatorControls extends LogitechControls {

    public OperatorControls(int port) {
        super(port);
    }

    public void controls() {
        /*
        Hatch collector
        Left trigger - closes claw
        "A" - elbow in
        "X" - elbow out

        Shooter
        Left Y axis - shoots full speed
        Right Y axis - shoots half speed
         */

        //Hatch collector
        //Claw
        if (getLeftTrigger() > .1) {
            HatchCollector.closeClaw();
        }else{
            HatchCollector.openClaw();
        }
        //Elbow
        if (getXButton()) {
            HatchCollector.extendElbow();
        }
        if (getAButton()) {
            HatchCollector.retractElbow();
        }

        //Shooter
        double leftYAxis = getLeftYAxis();
        double rightYAxis = getRightYAxis();
        double speed = Math.abs(leftYAxis) > .08 ? leftYAxis : Math.abs(rightYAxis) > .08 ? rightYAxis * .45 : 0;
        Shooter.shoot(speed);
    }
}