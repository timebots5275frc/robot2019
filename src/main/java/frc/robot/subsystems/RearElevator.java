/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.command.Subsystem;
import frc.robot.subsystems.Elevator;
import edu.wpi.first.wpilibj.DigitalInput;
import com.ctre.phoenix.motorcontrol.can.VictorSPX;
import frc.robot.commands.ElevatorSeek;

/**
 * Add your docs here.
 */
public class RearElevator extends Elevator {
  // Put methods for controlling this subsystem
  // here. Call these from Commands.
  public RearElevator(){
    System.err.println("An empty elevator object is being created - use caution!");
  }
  /**
   * Constructor
   * @param _highSwitch high (on when deployed) switch
   * @param _lowSwitch low (on when retracted) switch
   * @param _victor VictorSPX motor controller object
   */
  public RearElevator(DigitalInput _highSwitch, DigitalInput _lowSwitch, VictorSPX _victor){
    highSwitch =_highSwitch;
    lowSwitch = _lowSwitch;
    victor = _victor;
  }
    /**
   * Constructor
   * @param _highSwitch high (on when deployed, unless invertSwitches is true) switch
   * @param _lowSwitch low (on when retracted, unless invertSwitches is true) switch
   * @param _invertSwitches true if switches are /off/ when expected to be on
   * @param _victor VictorSPX motor controller object
   */
  public RearElevator(DigitalInput _highSwitch, DigitalInput _lowSwitch, VictorSPX _victor, boolean _invertSwitches){
    this(_highSwitch, _lowSwitch, _victor);
    invertSwitches = _invertSwitches;
  }

  /**
   * Constructor (this is clapped because you can't pass the Elevator object as a parameter to the ElevatorSeek constructor)
   * @param _highSwitch high (on when deployed, unless invertSwitches is true) switch
   * @param _lowSwitch low (on when retracted, unless invertSwitches is true) switch
   * @param _invertSwitches true if switches are /off/ when expected to be on
   * @param _victor VictorSPX motor controller object
   * @param _deployCommand ElevatorSeek object for deploying the elevator
   * @param _retractCommand ElevatorSeek object for retracting the elevator
   */
  public RearElevator(DigitalInput _highSwitch, DigitalInput _lowSwitch, VictorSPX _victor, boolean _invertSwitches, ElevatorSeek _deployCommand, ElevatorSeek _retractCommand){
    this(_highSwitch, _lowSwitch, _victor, _invertSwitches);
    deployCommand = new ElevatorSeek(this, _deployCommand.desiredpos, _deployCommand.maxOutput);
    retractCommand = new ElevatorSeek(this, _retractCommand.desiredpos, _retractCommand.maxOutput);
  }
}
