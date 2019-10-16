package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.VictorSPX;

public class Shooter {
    private static VictorSPX shooter;

    static {
        //Initialize the shooting motor
        shooter = new VictorSPX(6);
    }

    public static void shoot(double speed) {
        shooter.set(ControlMode.PercentOutput, speed);
    }


}