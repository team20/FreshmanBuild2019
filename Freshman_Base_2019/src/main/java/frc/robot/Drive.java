package frc.robot;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import edu.wpi.first.wpilibj.PIDController;
import edu.wpi.first.wpilibj.PIDOutput;

public class Drive implements PIDOutput {

    TalonSRX fl;
    TalonSRX fr;
    TalonSRX bl;
    TalonSRX br;
    double distance_traveled;
    final int ticksPerInch = 233;
    boolean firstTime;
    PIDController PID;
    double PIDValue;
    final int kTimeoutMs = 30;
    final boolean kDiscontinuityPresent = true;
    final int kBookEnd_0 = 910;
    final int kBookEnd_1 = 1137;

    public Drive() {
        fl = new TalonSRX(9);
        fr = new TalonSRX(2);
        bl = new TalonSRX(8);
        br = new TalonSRX(3);
        fl.follow(bl);
        fr.follow(br);
        br.setSensorPhase(true); // TODO: might need to change these to false
        bl.setSensorPhase(true);
        bl.configSelectedFeedbackSensor(FeedbackDevice.CTRE_MagEncoder_Absolute, 0, 1000);
        br.configSelectedFeedbackSensor(FeedbackDevice.CTRE_MagEncoder_Absolute, 0, 1000);
        bl.config_kP(0, .01);
        br.config_kP(0, .01);
        bl.config_kI(0, 0.0);
        br.config_kI(0, 0.0);
        bl.config_kD(0, 0.0);
        br.config_kD(0, 0.0);
        distance_traveled = br.getSelectedSensorPosition();
        firstTime = false;
        
        PID = new PIDController(.01, 0.0, .01, Robot.NAVXgyro, this);
        
        PID.setAbsoluteTolerance(5);
        PID.setInputRange(-180, 180);
        PID.setOutputRange(-1, 1);
        PID.setContinuous();
        PID.enable();

        PIDValue = 0;

    }

    public void move(double straight, double rightTurn, double leftTurn) {
        bl.set(ControlMode.PercentOutput, (straight - rightTurn + leftTurn));
        br.set(ControlMode.PercentOutput, (-straight - rightTurn + leftTurn));
    }

    public boolean moveDistance(double distance, double speed) {
        if (distance_traveled < distance) {
            br.set(ControlMode.Position, distance * ticksPerInch);
            bl.set(ControlMode.Position, distance * ticksPerInch);
            return false;
        } else {
            br.set(ControlMode.PercentOutput, (0));
            bl.set(ControlMode.PercentOutput, (0));
            Robot.NAVXgyro.reset();
            return true;
        }
    }

    public boolean turnAngle(double setpoint) {
        if (firstTime == false) {
            firstTime = true;
            PID.setSetpoint(setpoint);
        }
        if (!PID.onTarget()) {
            System.out.println("Angle: " + PIDValue);
            bl.set(ControlMode.PercentOutput, (PIDValue));
            br.set(ControlMode.PercentOutput, (PIDValue));
            return false;
        } else {
            bl.set(ControlMode.PercentOutput, (0));
            br.set(ControlMode.PercentOutput, (0));
            Robot.NAVXgyro.reset();
            PID.disable();
            return true;
        }
    }

    public void pidWrite(double output) {
        PIDValue = output;
    }

    public void initQuadrature() {
        int pulseWidth = br.getSensorCollection().getPulseWidthPosition();
        if (kDiscontinuityPresent) {
            int newCenter;
            newCenter = (kBookEnd_0 + kBookEnd_1) / 2;
            newCenter &= 0xFFF;
            pulseWidth -= newCenter;
        }
        br.getSensorCollection().setQuadraturePosition(pulseWidth, kTimeoutMs);
    }
}