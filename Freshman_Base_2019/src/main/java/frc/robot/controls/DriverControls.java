package frc.robot.controls;

import frc.robot.subsystems.Drivetrain;
import frc.robot.subsystems.Intake;

public class DriverControls extends LogitechControls {

    private double turningMultiplier = .75, slowTurningSpeed = .3;

    public DriverControls(int port) {
        super(port);
    }

    public void controls() {
        /*
         * Drivetrain 
         * Left Y axis - forward and back 
         * Triggers - left and right
         * Bumpers - slower left and right
         * A - run intake forward
         * Y - run intake backwards
         * B - stop intake
         */

        // Drivetrain
        double leftYAxis = getLeftYAxis();
        double rightTrigger = getRightTrigger();
        double leftTrigger = getLeftTrigger();

        boolean rightShoulderButton = getRightShoulderButton();
        boolean leftShoulderButton = getLeftShoulderButton();

        double speedStraight = Math.abs(leftYAxis) > .08 ? leftYAxis : 0;
        double speedRight = rightTrigger > .15 ? rightTrigger * turningMultiplier
                : rightShoulderButton ? slowTurningSpeed : 0;
        double speedLeft = leftTrigger > .15 ? leftTrigger * turningMultiplier
                : leftShoulderButton ? slowTurningSpeed : 0;
        Drivetrain.drive(speedStraight, speedRight, speedLeft);

        // Intake
        if (getAButton()) {
            Intake.run(false);
        }
        if (getYButton()) {
            Intake.run(true);
        }
        if (getBButton()) {
            Intake.stop();
        }
    }
}
