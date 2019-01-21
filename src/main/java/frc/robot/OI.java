/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import edu.wpi.first.wpilibj.Joystick;
import frc.robot.Robot;
import edu.wpi.first.wpilibj.buttons.JoystickButton;
import edu.wpi.first.wpilibj.buttons.Button;


import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.buttons.Button;
import edu.wpi.first.wpilibj.buttons.JoystickButton;
import frc.robot.commands.IntakePistonOut;
import frc.robot.commands.IntakePistonIn;



/**
 * This class is the glue that binds the controls on the physical operator
 * interface to the commands and   command groups that allow control of the robot.
 */
public class OI {

  public static Joystick driveJoystick = new Joystick(0);

  Button pistonOut = new JoystickButton(driveJoystick, 3);
  Button pistonRetract = new JoystickButton(driveJoystick, 4);
  
  pistonOut.whenpressed(new IntakePistonOut());
  pistonRetract.whenpressed(new IntakePistonIn());
  public static Button button1 = new JoystickButton(driveJoystick, 7);
  public static Button button2 = new JoystickButton(driveJoystick, 8);



  


  //// CREATING BUTTONS
  // One type of button is a joystick button which is any button on a
  //// joystick.
  // You create one by telling it which joystick it's on and which button
  // number it is.
  // Joystick stick = new Joystick(port);
  // Button button = new JoystickButton(stick, buttonNumber);

}
