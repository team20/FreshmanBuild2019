package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;

public class Intake {
    private static TalonSRX intake;
    static {
        // Initialize the intake motor
        intake = new TalonSRX(5);
    }

    public static void run(boolean reverse) {
        intake.set(ControlMode.PercentOutput, reverse ? 1 : -1);
    }

    public static void stop() {
        intake.set(ControlMode.PercentOutput, 0);
    }

}
