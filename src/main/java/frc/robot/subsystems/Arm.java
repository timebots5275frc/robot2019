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
  TalonSRX _talon = new TalonSRX(RobotMap.ARM_TALON);
  DigitalInput zeroSwitch;
  public int absolutepos;
  public void init(double F, double P, double I, double D, DigitalInput mzeroSwitch){
    _talon.configSelectedFeedbackSensor(FeedbackDevice.CTRE_MagEncoder_Absolute, 0, 30);
    _talon.configNominalOutputForward(0, 30);
		_talon.configNominalOutputReverse(0, 30);
		_talon.configPeakOutputForward(1, 30);
    _talon.configPeakOutputReverse(-1, 30);
    _talon.configAllowableClosedloopError(0, 0, 30);
    _talon.config_kF(0, F, 30);
		_talon.config_kP(5, P, 30);
		_talon.config_kI(0, I, 30);
    _talon.config_kD(0, D, 30);
    // 2821 90deg down 
    zeroSwitch = mzeroSwitch;

    absolutepos = _talon.getSensorCollection().getPulseWidthPosition();
    if (zeroSwitch.get()) absolutepos = 0;
    _talon.setSelectedSensorPosition(absolutepos, 0, 30);


  }
  public double loop(double counts){
    _talon.set(ControlMode.Position, counts);
    return _talon.getMotorOutputPercent();
  }
  public double jLoop(double armVal){
    if (armVal < 0.0) armVal = 0;
    armVal = armVal * -1;
    armVal = (armVal * 1024);
    _talon.set(ControlMode.Position, armVal);
    return _talon.getMotorOutputPercent();
  }
  public void printEncoderVal(){
    System.out.println(_talon.getSensorCollection().getPulseWidthPosition());
  }
  



  @Override
  public void initDefaultCommand() {
    // Set the default command for a subsystem here.
    // setDefaultCommand(new MySpecialCommand());
  }
}
