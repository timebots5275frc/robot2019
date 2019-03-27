/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.command.Subsystem;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import frc.robot.RobotMap;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.FeedbackDevice;

/**
 * Add your docs here.
 */
public class LinearSlide extends Subsystem {
  // Put methods for controlling this subsystem
  // here. Call these from Commands.
  public TalonSRX _talon = new TalonSRX(RobotMap.SLIDE_TALON);
  int COUNT_LIMIT = 512;
  int zeroedpos = 0;
  double command = 0;
  boolean zeroed = false;
  public void init(double f, double p, double i, double d){}
  // public void init(double F, double P, double I, double D){
  //   _talon.configSelectedFeedbackSensor(FeedbackDevice.CTRE_MagEncoder_Absolute, 0, 30);
  //   _talon.configNominalOutputForward(0, 30);
	// 	_talon.configNominalOutputReverse(0, 30);
	// 	_talon.configPeakOutputForward(1, 30);
  //   _talon.configPeakOutputReverse(-1, 30);
  //   _talon.configAllowableClosedloopError(0, 0, 30);
  //   _talon.config_kF(0, F, 30);
	// 	_talon.config_kP(0, P, 30);
	// 	_talon.config_kI(0, I, 30);
  //   _talon.config_kD(0, D, 30);
  //   zeroed = false;
  //   zeroedpos = _talon.getSensorCollection().getQuadraturePosition();
  //   command = _talon.getSelectedSensorPosition();
  //   setZero();
  // }
  // public void setZero(){
  //   zeroedpos = 0;
  //   command = 0;
  //   zeroed = true;
  //   _talon.setSelectedSensorPosition(zeroedpos, 0, 30);
  // }
  // public double loop(double position){
  //   _talon.set(ControlMode.Position, position);
  //   return _talon.getMotorOutputPercent();
  // }
  // public double incrementalLoop(double increment){
  //   command += increment;
  //   if (command < -(COUNT_LIMIT)){
  //     command = -(COUNT_LIMIT);
  //   }
  //   if (command > COUNT_LIMIT){
  //     command = COUNT_LIMIT;
  //   }
  //   return loop(command);
  // }

  @Override
  public void initDefaultCommand() {
    // Set the default command for a subsystem here.
    // setDefaultCommand(new MySpecialCommand());
  }
}
