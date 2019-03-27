/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.command.Subsystem;
import frc.robot.RobotMap;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import com.ctre.phoenix.motorcontrol.LimitSwitchNormal;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;

/**
 * Add your docs here.
 */
public class Arm extends Subsystem {
  // Put methods for controlling this subsystem
  // here. Call these from Commands.
  public TalonSRX _talon = new TalonSRX(RobotMap.ARM_TALON);
  public DigitalInput zeroSwitch;
  public int zeroedpos;
  int LOWER_LIMIT = -1100;
  double command = 0;
  boolean zeroed = false;
  public void init(double F, double P, double I, double D, DigitalInput mzeroSwitch){
    _talon.configSelectedFeedbackSensor(FeedbackDevice.CTRE_MagEncoder_Absolute, 0, 30);
    _talon.configNominalOutputForward(0, 30);
		_talon.configNominalOutputReverse(0, 30);
		_talon.configPeakOutputForward(1, 30);
    _talon.configPeakOutputReverse(-1, 30);
    _talon.configAllowableClosedloopError(0, 0, 30);
    _talon.config_kF(0, F, 30);
		_talon.config_kP(0, P, 30);
		_talon.config_kI(0, I, 30);
    _talon.config_kD(0, D, 30);
    // 845 90deg dist
    zeroSwitch = mzeroSwitch;
    zeroed = false;
    zeroedpos = _talon.getSensorCollection().getQuadraturePosition();
    command = _talon.getSelectedSensorPosition();
    
    if (!zeroSwitch.get()){
      setZero();
    }


  }
  public void setZero(){
    zeroedpos = 0;
    command = 0;
    zeroed = true;
    _talon.setSelectedSensorPosition(zeroedpos, 0, 30);
  }
  public void resetZero(){
    zeroed = false;
  }
  /**
   * Returns the value of the normally open switch (when true, switch is closed) 
   */
  public boolean atStop(){
    return !zeroSwitch.get();
  }
  public double loop(double counts){
    _talon.set(ControlMode.Position, counts);
    return _talon.getMotorOutputPercent();
  }
  public double jLoop(double armVal){
    //if (armVal < 0.0) armVal = 0;
    armVal = armVal * -1;
    armVal = (armVal * 1024);
    return loop(armVal);
  }

  public double incrementalLoop(double increment){
    if ((atStop() && (increment > 0)) && zeroed){
      increment = 0;
    }
    if ((command < LOWER_LIMIT) && (increment < 0) && zeroed){
      increment = 0;
    }
    command += increment;
    if (zeroed && (command > 0)){
      command = 0;
    }
    if (zeroed && (command < LOWER_LIMIT)){
      command = LOWER_LIMIT;
    }
    return loop(command);
  }
  public int getPosition(){
    return _talon.getSelectedSensorPosition();
  }



  @Override
  public void initDefaultCommand() {
    // Set the default command for a subsystem here.
    // setDefaultCommand(new MySpecialCommand());
  }
}
