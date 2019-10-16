package frc.robot.subsystems;

import edu.wpi.first.wpilibj.Compressor;
import edu.wpi.first.wpilibj.DoubleSolenoid;

public class HatchCollector {

    private static DoubleSolenoid claw;
    private static DoubleSolenoid elbow;
    private static Compressor compressor;

    static {
        //Initialize the compressor and solenoids
        compressor = new Compressor();
        compressor.setClosedLoopControl(true);
        claw = new DoubleSolenoid(2, 3);
        elbow = new DoubleSolenoid(0, 1);
    }

    public static void openClaw() {
        claw.set(DoubleSolenoid.Value.kReverse);
    }

    public static void closeClaw() {
        claw.set(DoubleSolenoid.Value.kForward);
    }

    public static void extendElbow() {
        elbow.set(DoubleSolenoid.Value.kReverse);
    }

    public static void retractElbow() {
        elbow.set(DoubleSolenoid.Value.kForward);
    }
}
