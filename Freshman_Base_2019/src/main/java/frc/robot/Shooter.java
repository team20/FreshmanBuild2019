package frc.robot;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;

public class Shooter {
    TalonSRX shooter;

    public Shooter() {
        shooter = new TalonSRX(8);
    }

    public void driverShoot(boolean noShooter, boolean halfShooter, boolean fullShooter, boolean backShooter) {
        if (noShooter == true) {
            shooter.set(ControlMode.PercentOutput, 0);
        } else if (halfShooter == true) {
            shooter.set(ControlMode.PercentOutput, .5);
        } else if (fullShooter == true) {
            shooter.set(ControlMode.PercentOutput, 1);
        } else if (backShooter == true) {
            shooter.set(ControlMode.PercentOutput, -.5);
        }
    }

    public void operatorShoot(boolean operatorNoShooter, boolean operatorHalfShooter, boolean operatorFullShooter,
            boolean operatorBackShooter) {
        if (operatorNoShooter == true) {
            shooter.set(ControlMode.PercentOutput, 0);
        } else if (operatorHalfShooter == true) {
            shooter.set(ControlMode.PercentOutput, .5);
        } else if (operatorFullShooter == true) {
            shooter.set(ControlMode.PercentOutput, 1);
        } else if (operatorBackShooter == true) {
            shooter.set(ControlMode.PercentOutput, -.5);
        }
    }
}