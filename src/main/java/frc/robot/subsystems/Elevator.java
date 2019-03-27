/**
 * Subsystem representing an elevator consisting of a Victor SPX and two limit switches (one high and one low)
 */
package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.can.VictorSPX;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.SpeedController;
import edu.wpi.first.wpilibj.Victor;
import edu.wpi.first.wpilibj.command.Subsystem;
import frc.robot.ElevatorPosition;
import frc.robot.commands.ElevatorSeek;

/**
 * Add your docs here.
 */
public class Elevator extends Subsystem {
  // Put methods for controlling this subsystem
  // here. Call these from Commands.
  public DigitalInput highSwitch, lowSwitch;
  public VictorSPX victor;
  protected boolean invertSwitches = false;
  public ElevatorSeek deployCommand ; //= new ElevatorSeek(this, ElevatorPosition.HIGH, .5);
  public ElevatorSeek retractCommand; // = new ElevatorSeek(this, ElevatorPosition.LOW, -.5);

  /**
   * Do not use this unless you know what you are doing!
   * Used so that you can create an elevator object in one line
   * (ElevatorSeek objects require an Elevator object to attach to)
   * 
   */
  public Elevator(){
    System.err.println("An empty elevator object is being created - use caution!");
  }
  /**
   * Constructor
   * @param _highSwitch high (on when deployed) switch
   * @param _lowSwitch low (on when retracted) switch
   * @param _victor VictorSPX motor controller object
   */
  public Elevator(DigitalInput _highSwitch, DigitalInput _lowSwitch, VictorSPX _victor){
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
  public Elevator(DigitalInput _highSwitch, DigitalInput _lowSwitch, VictorSPX _victor, boolean _invertSwitches){
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
  public Elevator(DigitalInput _highSwitch, DigitalInput _lowSwitch, VictorSPX _victor, boolean _invertSwitches, ElevatorSeek _deployCommand, ElevatorSeek _retractCommand){
    this(_highSwitch, _lowSwitch, _victor, _invertSwitches);
    deployCommand = new ElevatorSeek(this, _deployCommand.desiredpos, _deployCommand.maxOutput);
    retractCommand = new ElevatorSeek(this, _retractCommand.desiredpos, _retractCommand.maxOutput);
  }

  /**
   * gets the position of the elevator based on the limit switches where
   * HIGH: only high switch is active (on or off depending on invertSwitches)
   * LOW: only low switch is active (on or off depending on invertSwitches)
   * @return an ElevatorPosition object
   */
  public ElevatorPosition getPosition(){
    int i = 0;
    if (_getSwitch(highSwitch)) i += ElevatorPosition.HIGH.ordinal();
    if (_getSwitch(lowSwitch)) i += ElevatorPosition.LOW.ordinal();
    return ElevatorPosition.values()[i];
  }
  public void cancelAll(){
    // deployCommand.cancel();
    // retractCommand.cancel();
  }

  private boolean _getSwitch(DigitalInput input){
    if (invertSwitches) return !input.get();
    else return input.get();
  }


  // we don't need this, but we need to implement it
  @Override
  public void initDefaultCommand() {}
}

