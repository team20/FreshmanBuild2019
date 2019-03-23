package frc.robot;

import edu.wpi.first.wpilibj.Compressor;
import edu.wpi.first.wpilibj.DoubleSolenoid;

public class Pneumatics {

    DoubleSolenoid grabbyClaw;
    DoubleSolenoid hatchCollector;

    public Pneumatics() {
        Compressor compressor = new Compressor();
        compressor.setClosedLoopControl(true);
        grabbyClaw = new DoubleSolenoid(2, 3); //first number is forward channel, second number is reverse channel
        hatchCollector = new DoubleSolenoid(0, 1);
    }

    public void openGrabbyClaw(double grabbyClosePosition) {
        if (grabbyClosePosition > 0.1) {
            grabbyClaw.set(DoubleSolenoid.Value.kForward);
        }
        else {
            grabbyClaw.set(DoubleSolenoid.Value.kReverse);
        }
    }

    // public void closeGrabbyClaw(double grabbyClosePosition) {
    //     if (grabbyClosePosition > 0.1) {
    //         grabbyClaw.set(DoubleSolenoid.Value.kReverse);
    //     }
    // }

    public void hatchCollector(boolean goForward, boolean goBackwards) {
        if (goForward == true) {
            hatchCollector.set(DoubleSolenoid.Value.kReverse);
        } else if (goBackwards == true) {
            hatchCollector.set(DoubleSolenoid.Value.kForward);
        }
    }
}