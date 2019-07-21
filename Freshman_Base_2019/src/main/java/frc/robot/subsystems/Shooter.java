package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.VictorSPX;

import edu.wpi.first.wpilibj.Timer;
import frc.robot.Robot;

public class Shooter {
    static VictorSPX shooter;

    public Shooter() {
        shooter = new VictorSPX(6);
    }

    public static void setSpeed(double speed) {
        shooter.set(ControlMode.PercentOutput, speed);
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
        else {
            shooter.set(ControlMode.PercentOutput, 0);
        }
    }

    public static void autoShoot() {
        if ((Timer.getFPGATimestamp() - Robot.startTime) <= 4) {
            shooter.set(ControlMode.PercentOutput, -1);
        }
        else if ((Timer.getFPGATimestamp() - Robot.startTime) > 4) {
            shooter.set(ControlMode.PercentOutput, 0);
        }
        else {
        }
    }
}