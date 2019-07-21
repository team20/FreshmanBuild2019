package frc.robot.subsystems;

import edu.wpi.first.wpilibj.Compressor;
import edu.wpi.first.wpilibj.DoubleSolenoid;

public class HatchCollector {

    static DoubleSolenoid claw;
    static DoubleSolenoid safetyPosition;

    public HatchCollector() {
        Compressor compressor = new Compressor();
        compressor.setClosedLoopControl(true);
        claw = new DoubleSolenoid(2, 3); //first number is forward channel, second number is reverse channel
        safetyPosition = new DoubleSolenoid(0, 1);
    }

    public static void setPosition(String position) {
        if(position == "closed") {
            claw.set(DoubleSolenoid.Value.kForward);
        }
        if(position == "open") {
            claw.set(DoubleSolenoid.Value.kReverse);
        }
        if(position == "forward") {
            safetyPosition.set(DoubleSolenoid.Value.kReverse);
        }
        if(position == "backward") {
            safetyPosition.set(DoubleSolenoid.Value.kForward);
        }
    }
    
    public void openClaw(double open) {
        if (open > 0.1) {
            claw.set(DoubleSolenoid.Value.kForward);
        }
        else {
            claw.set(DoubleSolenoid.Value.kReverse);
        }
    }

    public void moveToSafety(boolean goForward, boolean goBackwards) {
        if (goForward == true) {
            safetyPosition.set(DoubleSolenoid.Value.kReverse);
        } else if (goBackwards == true) {
            safetyPosition.set(DoubleSolenoid.Value.kForward);
        }
    }
}