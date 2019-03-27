package frc.robot;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;

public class Shooter {
    TalonSRX shooter;

    public Shooter() {
        shooter = new TalonSRX(6);
    }

    public void operatorShoot(double go, double halfShoot) {
        if (Math.abs(go) > 0.5) {
            shooter.set(ControlMode.PercentOutput, -1);
        } else if (Math.abs(go) < -0.5) {
            shooter.set(ControlMode.PercentOutput, 1);
        } else if (Math.abs(halfShoot) > 0.5) {
            shooter.set(ControlMode.PercentOutput, -.5);
        } else if (Math.abs(halfShoot) < -0.5) {
            shooter.set(ControlMode.PercentOutput, .5);
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

    public void go(double speed) {
        shooter.set(ControlMode.PercentOutput, speed);
    }
}