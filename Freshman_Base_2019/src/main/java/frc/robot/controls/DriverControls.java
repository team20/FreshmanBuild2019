package frc.robot.controls;

//import frc.robot.Drivetrain;
//import frc.robot.Intake;

//TRY TOGGLE USING THE D-PAD???

public class DriverControls extends LogitechControls {
    
    double left;
    double right;
    double straight;
        
    public DriverControls(int port) {
        super(port);
    }

    public void driverControls() {
        controls();
    }

    public void controls() {
    straight = 0;
    left = 0;
    right = 0;
    if(getDPadLeft()) {
        if(Math.abs(getLeftYAxis()) > 0.1) {
            straight = getLeftYAxis();
        }
        if(getLeftTrigger() > 0.1) {
            left = getLeftTrigger();
        }
        if(getRightTrigger() > 0.1) {
            right = getRightTrigger();
        }

            frc.robot.subsystems.Drivetrain.drive(straight, right, left);
    }
    else if (getDPadRight()) {
        if(Math.abs(getLeftYAxis()) > 0.1) {
            straight = -getLeftYAxis();
        }
        if(getLeftTrigger() > 0.1) {
            left = -getLeftTrigger();
        }
        if(getRightTrigger() > 0.1) {
            right = -getRightTrigger();
        }

            frc.robot.subsystems.Drivetrain.drive(straight, right, left);
    }   
        if(getAButton()) {
            frc.robot.subsystems.Intake.setSpeed(-1);
        }
        if(getBButton()) {
            frc.robot.subsystems.Intake.stop();
        }
        if(getXButton()) {
            frc.robot.subsystems.Intake.setSpeed(1);
        }
        if(!getAButton()) {
            frc.robot.subsystems.Intake.stop();
        }
        if(!getBButton()) {
            frc.robot.subsystems.Intake.stop();
        }
        if(!getXButton()) {
            frc.robot.subsystems.Intake.stop();
        }
    }
}