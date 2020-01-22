
package frc.robot.subsystems;

import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class ShooterSubsystem extends SubsystemBase {
    public boolean isRunning = false;

    public void FlatShooterSubsystem() {

        //final motor_placeholder shooterMotor;
        //final pneumatic_placeholder shooterPiston;

    }

    public void startShooter() {
        //turnOnMotor();
        //openPistonGate();
        System.out.println("Low goal shooter now shooting!");
        isRunning = true;
    }

    public void stopShooter() {
        //closePistonGate();
        //turnOffMotor();
        System.out.println("Low goal shooter now not shooting!");
        isRunning = false;
    }

    public void toggleShooter() {
        if (isRunning) {
            stopShooter();
        } else {
            startShooter();
        }
    }


    @Override
    public void periodic() {
        // This method will be called once per scheduler run
    }
}
