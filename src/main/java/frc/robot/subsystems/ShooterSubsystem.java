
package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

import static frc.robot.Constants.MechanismConstants.shooterMotorPort;

public class ShooterSubsystem extends SubsystemBase {

    private boolean isRunning = false;
    private boolean isOpen = false;
    private boolean isUp = false;
    private final WPI_TalonSRX shooterMotor = new WPI_TalonSRX(shooterMotorPort);
    private final DoubleSolenoid dumperSolenoid = new DoubleSolenoid(2,3);
    private final DoubleSolenoid gateSolenoid = new DoubleSolenoid(0,1);

    public ShooterSubsystem() {
        shooterMotor.setInverted(true);
        //final motor_placeholder shooterMotor;
        //final pneumatic_placeholder shooterPiston;

    }

    public void startShooter() {
        shooterMotor.set(1);
        System.out.println("Low goal shooter now shooting!");
        isRunning = true;
//        solenoidlift.set(DoubleSolenoid.Value.kForward);
    }

    public void stopShooter() {
        shooterMotor.set(0);
        System.out.println("Low goal shooter now not shooting!");
        isRunning = false;
//        solenoidlift.set((DoubleSolenoid.Value.kReverse));
    }

    public void dumperUp() {
        dumperSolenoid.set(DoubleSolenoid.Value.kForward);
        isUp = true;
    }

    public void dumperDown() {
        dumperSolenoid.set((DoubleSolenoid.Value.kReverse));
        isUp = false;
    }

    public void gateUp() {
        gateSolenoid.set(DoubleSolenoid.Value.kForward);
        isOpen = true;
    }

    public void gateDown() {
        gateSolenoid.set(DoubleSolenoid.Value.kReverse);
        isOpen = false;
    }

    public void toggleGate() {
        if (isOpen) {
            gateDown();
        } else {
            gateUp();
        }
    }

    public void toggleShooter() {
        if (isRunning) {
            stopShooter();
        } else {
            startShooter();
        }
    }

    public void toggleDumper() {
        if (isUp) {
            dumperDown();
        } else {
            dumperUp();
        }
    }

    @Override
    public void periodic() {
        // This method will be called once per scheduler run
    }

    public void invert() {
    }
}