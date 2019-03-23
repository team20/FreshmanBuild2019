package frc.robot;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;

public class Intake {
    TalonSRX intake;
    double intakeSpeed;
    TalonSRX shooter;

    public Intake() {
        intake = new TalonSRX(5);
    }

    public void spinIntake(boolean forward, boolean reverse, boolean stop) {
        if (forward == true) { //a = 1, b = 2, x = 3, y =4
            intake.set(ControlMode.PercentOutput, -1);
            // Robot.shootNow.shooter.set(ControlMode.PercentOutput, speed);
        }
        else if (reverse == true) {
            intake.set(ControlMode.PercentOutput, 1);
            // Robot.shootNow.shooter.set(ControlMode.PercentOutput, -1);
        }
        else if (stop == true) {
            intake.set(ControlMode.PercentOutput, 0);
        }
    }
}