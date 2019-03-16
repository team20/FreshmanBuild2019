package frc.robot;

import edu.wpi.first.wpilibj.Compressor;
import edu.wpi.first.wpilibj.DoubleSolenoid;

public class Pneumatics {

    DoubleSolenoid grabbyClaw;
    DoubleSolenoid hatchCollector;

    public Pneumatics() {
        Compressor compressor = new Compressor();
        compressor.setClosedLoopControl(true);
        grabbyClaw = new DoubleSolenoid(0, 1); //first number is forward channel, second number is reverse channel
        hatchCollector = new DoubleSolenoid(2, 3);
    }

    public void openGrabbyClaw(double grabbyOpenPosition) {
        if (grabbyOpenPosition > 0.0) {
            grabbyClaw.set(DoubleSolenoid.Value.kForward);
        }
    }

    public void closeGrabbyClaw(double grabbyClosePosition) {
        if (grabbyClosePosition > 0.0) {
            grabbyClaw.set(DoubleSolenoid.Value.kReverse);
        }
    }

    public void hatchCollector(double hatchPosition) {
        if (hatchPosition > 0.0) {
            hatchCollector.set(DoubleSolenoid.Value.kForward);
        } else if (hatchPosition < 0.0) {
            hatchCollector.set(DoubleSolenoid.Value.kReverse);
        }
    }
}