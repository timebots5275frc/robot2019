/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.command.Subsystem;

import edu.wpi.first.wpilibj.Compressor;

/**
 * Add your docs here.
 */
public class CompressorControl extends Subsystem {
  // Put methods for controlling this subsystem
  // here. Call these from Commands.

  Compressor comp1 = new Compressor(0);

  @Override
  public void initDefaultCommand() {
    // Set the default command for a subsystem here.
    // setDefaultCommand(new MySpecialCommand());
  }

  public void open() {
    comp1.start();
  }

  public void close() {
    comp1.stop();
  }
  
}
