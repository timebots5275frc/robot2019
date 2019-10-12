/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import frc.robot.RobotMap;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.DoubleSolenoid;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;


/**
 * Add your docs here.
 */
public class Intake extends Subsystem {
  // Put methods for controlling this subsystem
  // here. Call these from Commands.

  DoubleSolenoid dSolenoid1 = new DoubleSolenoid(RobotMap.Intake_dSolenoid1_Deploy, RobotMap.Intake_dSolenoid1_Retract);
  public boolean solenoidOn = false;

  TalonSRX intakeMotor =  new TalonSRX(RobotMap.BALL_INTAKE_TALON);

  public void deploy(){
    dSolenoid1.set(DoubleSolenoid.Value.kForward);
    solenoidOn = true;
  }

  public void undeploy(){
    dSolenoid1.set(DoubleSolenoid.Value.kReverse);
    solenoidOn = false;
  }

  public void setMotor(double speed) {
    if (-1 <= speed && speed <= 1) {
      intakeMotor.set(ControlMode.PercentOutput, speed);
    }
  }

  @Override
  public void initDefaultCommand() {
    // Set the default command for a subsystem here.
    // setDefaultCommand(new MySpecialCommand());
  }
}