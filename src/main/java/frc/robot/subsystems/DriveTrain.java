/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import frc.robot.RobotMap;
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
  static WPI_TalonSRX frontLeft = new WPI_TalonSRX(RobotMap.LEFT_TALON);
  static WPI_TalonSRX frontRight = new WPI_TalonSRX(RobotMap.RIGHT_TALON);
  static WPI_VictorSPX backLeft = new WPI_VictorSPX(RobotMap.LEFT_VICTOR);
  static WPI_VictorSPX backRight = new WPI_VictorSPX(RobotMap.RIGHT_VICTOR);

  static SpeedControllerGroup leftSCG = new SpeedControllerGroup(frontLeft, backLeft);
  static SpeedControllerGroup rightSCG = new SpeedControllerGroup(frontRight, backRight);

  public static DifferentialDrive drive = new DifferentialDrive(leftSCG, rightSCG);

  
  @Override
  public void initDefaultCommand() {
    // Set the default command for a subsystem here.
    // setDefaultCommand(new MySpecialCommand());
  }
}
