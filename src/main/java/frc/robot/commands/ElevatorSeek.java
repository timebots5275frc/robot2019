/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import com.ctre.phoenix.motorcontrol.ControlMode;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.ElevatorPosition;
import frc.robot.subsystems.Elevator;

public class ElevatorSeek extends Command {

  Elevator elevator = null;
  public ElevatorPosition desiredpos;
  public double maxOutput;
  double holdProportion = .2;
  /**
   *  Constructor
   * @param _elevator the elevator to move
   * @param _pos the desired output value (as defined in frc.robot.ElevatorPosition)
   * @param _maxOutput the maximum output value we can write to the elevator's motor - pay attention to positive/negative
   */
  public ElevatorSeek(Elevator _elevator, ElevatorPosition _pos, double _maxOutput){
    desiredpos = _pos;
    elevator = _elevator;
    maxOutput = _maxOutput;
    requires(elevator);
  }
    /**
   *  Constructor
   * @param _elevator the elevator to move
   * @param _pos the desired output value (as defined in frc.robot.ElevatorPosition)
   * @param _maxOutput the maximum output value we can write to the elevator's motor - pay attention to positive/negative
   * @param _holdProportion the percentage of maxOutput to hold the motor at after the elevator has reached its end position
   */
  public ElevatorSeek(Elevator _elevator, ElevatorPosition _pos, double _maxOutput, double _holdProportion){
    desiredpos = _pos;
    elevator = _elevator;
    maxOutput = _maxOutput;
    holdProportion = _holdProportion;
    requires(elevator);
  }


  // Called just before this Command runs the first time
  @Override
  protected void initialize() {
    System.out.println("ElevatorSeek initialize, pos: " + desiredpos + " elevator vport: " + elevator.victor.getDeviceID());
  }

  // Called repeatedly when this Command is scheduled to run
  @Override
  protected void execute() {
    elevator.victor.set(ControlMode.PercentOutput, maxOutput);
  }

  // Make this return true when this Command no longer needs to run execute()
  @Override
  protected boolean isFinished() {
    if (elevator.getPosition() == desiredpos){
      return true;
    } 
    else return false;
  }

  // Called once after isFinished returns true
  @Override
  protected void end() {
    elevator.victor.set(ControlMode.PercentOutput, maxOutput * holdProportion);
    System.out.println("ElevatorSeek end, pos: " + desiredpos + " elevator vport: " + elevator.victor.getDeviceID());

  }

  // Called when another command which requires one or more of the same
  // subsystems is scheduled to run
  @Override
  protected void interrupted() {
    elevator.victor.set(ControlMode.PercentOutput, 0.0 );
  }
}
