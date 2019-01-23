/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.SpeedControllerGroup;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import com.ctre.phoenix.motorcontrol.can.WPI_VictorSPX;

/**
 * Add your docs here.
 */
public class DriveTrain extends Subsystem {
  // Put methods for controlling this subsystem
  // here. Call these from Commands.

  //Assigns motors ports
  WPI_TalonSRX frontLeft = new WPI_TalonSRX(0);
  WPI_TalonSRX frontRight = new WPI_TalonSRX(1);
  WPI_VictorSPX backLeft = new WPI_VictorSPX(0);
  WPI_VictorSPX backRight = new WPI_VictorSPX(1);

  SpeedControllerGroup leftSCG = new SpeedControllerGroup(frontLeft, backLeft);
  SpeedControllerGroup rightSCG = new SpeedControllerGroup(frontRight, backRight);

  DifferentialDrive drive = new DifferentialDrive(leftSCG, rightSCG);
  

  @Override
  public void initDefaultCommand() {
    // Set the default command for a subsystem here.
    // setDefaultCommand(new MySpecialCommand());
  }
}
