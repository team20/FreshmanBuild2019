package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;

public class Drivetrain {

    private static TalonSRX frontLeft;
    private static TalonSRX frontRight;
    private static TalonSRX backLeft;
    private static TalonSRX backRight;


    static {
        //Initialize motors and set master and slave
        frontLeft = new TalonSRX(1);
        frontRight = new TalonSRX(2);
        backLeft = new TalonSRX(3);
        backRight = new TalonSRX(4);
        frontLeft.follow(backLeft);
        frontRight.follow(backRight);
        backRight.configOpenloopRamp(0.4);
        backLeft.configOpenloopRamp(0.4);
        backRight.setSensorPhase(true);
        backLeft.setSensorPhase(true);
    }

    public static void drive(double speedStraight, double speedRight, double speedLeft) {
        backLeft.set(ControlMode.PercentOutput, (speedStraight - speedRight + speedLeft));
        backRight.set(ControlMode.PercentOutput, (-speedStraight - speedRight + speedLeft));
    }

}