package frc.robot;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;

public class Intake {
    TalonSRX intake;
    double intakeSpeed;

    public Intake() {
        intake = new TalonSRX(5);
    }

    public void spinIntake(double intakeSpeed) {
        intake.set(ControlMode.PercentOutput, intakeSpeed);
    }
}