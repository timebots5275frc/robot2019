/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.buttons.JoystickButton;
import edu.wpi.first.wpilibj.buttons.Button;

/**
 * This class is the glue that binds the controls on the physical operator
 * interface to the commands and   command groups that allow control of the robot.
 */
public class OI {

  public static Joystick driveJoystick = new Joystick(0);

  public static Button pistonDeploy = new JoystickButton(driveJoystick, 3);
  public static Button pistonRetract = new JoystickButton(driveJoystick, 4);
  
  public static Button button1 = new JoystickButton(driveJoystick, 7);
  public static Button button2 = new JoystickButton(driveJoystick, 8);


}



