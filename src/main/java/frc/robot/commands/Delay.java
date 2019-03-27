/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.Timer;

public class Delay extends Command {
  int delay;
  Timer time = new Timer();
  /**
   * Constructor
   * @param _delay the time to delay in milliseconds
   */
  public Delay(int _delay) {
    delay = _delay;
  }

  // Called just before this Command runs the first time
  @Override
  protected void initialize() {
    time.start();

  }

  // Make this return true when this Command no longer needs to run execute()
  @Override
  protected boolean isFinished() {
    if (time.hasPeriodPassed(delay * 1000)) return true;
    else return false;
  }

  // Called once after isFinished returns true
  @Override
  protected void end() {
    time.stop();
  }

}
