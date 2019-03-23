/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

/**
 * The RobotMap is a mapping from the ports sensors and actuators are wired into
 * to a variable name. This provides flexibility changing wiring, makes checking
 * the wiring easier and significantly reduces the number of magic numbers
 * floating around.
 */
public class RobotMap {
  public static int RIGHT_VICTOR = 0;
  public static int LEFT_VICTOR = 1;
  public static int RIGHT_TALON = 0;
  public static int LEFT_TALON = 1;

  public static int FRONT_ELEVATOR_VICTOR = 2;
  public static int REAR_ELEVATOR_VICTOR = 3;  

  public static int REAR_HIGH_LIMIT = 3;
  public static int REAR_LOW_LIMIT = 2;
  public static int FRONT_HIGH_LIMIT = 1;
  public static int FRONT_LOW_LIMIT = 0;  

  public static int PUSHER_VICTOR = 5;

  public static int ARM_TALON = 3;
  public static int SLIDE_TALON = 2;

  public static int ARM_STOP_SWITCH = 4;

  public static final int Intake_dSolenoid1_Deploy = 1;
  public static final int Intake_dSolenoid1_Retract = 2;
  

}
