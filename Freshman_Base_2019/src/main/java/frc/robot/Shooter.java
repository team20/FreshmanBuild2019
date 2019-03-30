package frc.robot;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.VictorSPX;

public class Shooter {
    VictorSPX shooter;

    public Shooter() {
        shooter = new VictorSPX(6);
    }

    public void operatorShoot(double go, double halfShoot) {
        if (go < -0.5) {
            shooter.set(ControlMode.PercentOutput, -1);
        } else if (go > 0.5) {
            shooter.set(ControlMode.PercentOutput, 1);
        } else if (halfShoot < -0.5) {
            shooter.set(ControlMode.PercentOutput, -.6);
        } else if (halfShoot > 0.5) {
            shooter.set(ControlMode.PercentOutput, .6);
        }
        // } else if (Robot.driverJoy.getRawButton(1) == true) {
        //     shooter.set(ControlMode.PercentOutput, -1);
        // } else if (Robot.driverJoy.getRawButton(4) == true) {
        //     shooter.set(ControlMode.PercentOutput, -1);
        // } else if (Robot.driverJoy.getRawButton(2) == true) {
        //     shooter.set(ControlMode.PercentOutput, 0);
        // }
        else {
            shooter.set(ControlMode.PercentOutput, 0);
        }
    }
}