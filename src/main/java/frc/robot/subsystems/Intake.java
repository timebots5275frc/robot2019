/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.command.Subsystem;
import frc.robot.RobotMap;
import edu.wpi.first.wpilibj.DoubleSolenoid;

/**
 * Add your docs here.
 */
public class Intake extends Subsystem {
  // Put methods for controlling this subsystem
  // here. Call these from Commands.
  DoubleSolenoid dSolenoid1 = new DoubleSolenoid(RobotMap.Intake_dSolenoid1_Deploy, RobotMap.Intake_dSolenoid1_Retract);
  public boolean solenoidOn = false;


  public void deploy(){
    dSolenoid1.set(DoubleSolenoid.Value.kForward);
    solenoidOn = true;
  }

  public void retract(){
    dSolenoid1.set(DoubleSolenoid.Value.kReverse);
    solenoidOn = false;
  }

  public void Off(){
    dSolenoid1.set(DoubleSolenoid.Value.kOff);
    solenoidOn = false;
  }

  @Override
  public void initDefaultCommand() {
    // Set the default command for a subsystem here.
    // setDefaultCommand(new MySpecialCommand());
  }
}
