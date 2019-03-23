/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import edu.wpi.first.wpilibj.Joystick;
import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.VictorSPX;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.commands.ExampleCommand;
import frc.robot.commands.TeleopDrive;
import frc.robot.subsystems.ExampleSubsystem;
import edu.wpi.first.wpilibj.DigitalInput;
import frc.robot.subsystems.CompressorControl;
import frc.robot.subsystems.DriveTrain;
import frc.robot.subsystems.Elevator;
import frc.robot.subsystems.Intake;
import frc.robot.OI;
import frc.robot.commands.IntakePistonIn;
import frc.robot.commands.IntakePistonOut;
import frc.robot.subsystems.Arm;
import frc.robot.commands.ArmZero;
import frc.robot.commands.CompressorOff;
import frc.robot.commands.CompressorOn;
import frc.robot.commands.ElevatorSeek;



/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the TimedRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the build.gradle file in the
 * project.
 */
public class Robot extends TimedRobot {
  public static ExampleSubsystem m_subsystem = new ExampleSubsystem();
  public static CompressorControl compressor = new CompressorControl();
  
  public static Intake intakePneumatics = new Intake();
  public static OI m_oi;

  Command m_autonomousCommand;
  IntakePistonIn pisIn = new IntakePistonIn();
  IntakePistonOut pisOut = new IntakePistonOut();

  SendableChooser<Command> m_chooser = new SendableChooser<>();
  ArmZero zeroCommand = new ArmZero();

  Elevator rearElevator = new Elevator(new DigitalInput(RobotMap.REAR_HIGH_LIMIT),
                                       new DigitalInput(RobotMap.REAR_LOW_LIMIT),
                                       new VictorSPX(RobotMap.REAR_ELEVATOR_VICTOR),
                                       false,
                                       new ElevatorSeek(new Elevator(), ElevatorPosition.HIGH, .3, 0.0),
                                       new ElevatorSeek(new Elevator(), ElevatorPosition.LOW, -.4, .5)
                                       );
  Elevator frontElevator = new Elevator(new DigitalInput(RobotMap.FRONT_HIGH_LIMIT),
                                       new DigitalInput(RobotMap.FRONT_LOW_LIMIT),
                                       new VictorSPX(RobotMap.FRONT_ELEVATOR_VICTOR),
                                       false,
                                       new ElevatorSeek(new Elevator(), ElevatorPosition.HIGH, .5),
                                       new ElevatorSeek(new Elevator(), ElevatorPosition.LOW, -1)
                                       );

  
  public TeleopDrive teleopDriveCommand = new TeleopDrive();

  //DO NOT COMMIT
  private VictorSPX pusher = new VictorSPX(RobotMap.PUSHER_VICTOR);

  public static Arm arm = new Arm();
  public Joystick j = new Joystick(0);





  /**
   * This function is run when the robot is first started up and should be
   * used for any initialization code.
   */
  @Override
  public void robotInit() {
    m_oi = new OI();
    m_chooser.setDefaultOption("Default Auto", new ExampleCommand());
    // chooser.addOption("My Auto", new MyAutoCommand());
    SmartDashboard.putData("Auto mode", m_chooser);
    arm.init(0, 5, 0, 0, new DigitalInput(RobotMap.ARM_STOP_SWITCH));
    System.out.println("robot init");
  }

  /**
   * This function is called every robot packet, no matter the mode. Use
   * this for items like diagnostics that you want ran during disabled,
   * autonomous, teleoperated and test.
   *
   * <p>This runs after the mode specific periodic functions, but before
   * LiveWindow and SmartDashboard integrated updating.
   */
  @Override
  public void robotPeriodic() {
  }

  /**
   * This function is called once each time the robot enters Disabled mode.
   * You can use it to reset any subsystem information you want to clear when
   * the robot is disabled.
   */
  @Override
  public void disabledInit() {
  }

  @Override
  public void disabledPeriodic() {
    Scheduler.getInstance().run();
  }

  /**
   * This autonomous (along with the chooser code above) shows how to select
   * between different autonomous modes using the dashboard. The sendable
   * chooser code works with the Java SmartDashboard. If you prefer the
   * LabVIEW Dashboard, remove all of the chooser code and uncomment the
   * getString code to get the auto name from the text box below the Gyro
   *
   * <p>You can add additional auto modes by adding additional commands to the
   * chooser code above (like the commented example) or additional comparisons
   * to the switch structure below with additional strings & commands.
   */
  @Override
  public void autonomousInit() {
    m_autonomousCommand = m_chooser.getSelected();
    arm.setZero();
    arm.resetZero();
    zeroCommand.start();
    System.out.println("autonomous init");

    /*
     * String autoSelected = SmartDashboard.getString("Auto Selector",
     * "Default"); switch(autoSelected) { case "My Auto": autonomousCommand
     * = new MyAutoCommand(); break; case "Default Auto": default:
     * autonomousCommand = new ExampleCommand(); break; }
     */

    // schedule the autonomous command (example)
    if (m_autonomousCommand != null) {
      m_autonomousCommand.start();
    }

  }

  /**
   * This function is called periodically during autonomous.
   */
  @Override
  public void autonomousPeriodic() {
    Scheduler.getInstance().run();
    if (zeroCommand.isCompleted()){
      arm.incrementalLoop(OI.driveJoystick.getRawAxis(1) * 10);
    }
  }

  @Override
  public void teleopInit() {
    // This makes sure that the autonomous stops running when
    // teleop starts running. If you want the autonomous to
    // continue until interrupted by another command, remove
    // this line or comment it out.
    if (m_autonomousCommand != null) {
      m_autonomousCommand.cancel();
    }
    teleopDriveCommand.start();

    
  }

  /**
   * This function is called periodically during operator control.
   */
  @Override
  public void teleopPeriodic() {
    Scheduler.getInstance().run();
    if (zeroCommand.isCompleted()){
      arm.incrementalLoop(OI.driveJoystick.getRawAxis(1) * 10);
    }

    OI.pistonDeploy.whenPressed(pisOut);
    OI.pistonRetract.whenPressed(pisIn);

    if (OI.xbController.getRawButton(1) && !rearElevator.retractCommand.isRunning()) {
      rearElevator.cancelAll();
      rearElevator.retractCommand.start();
    }if (OI.xbController.getRawButton(2) && !rearElevator.deployCommand.isRunning()) {
      rearElevator.cancelAll();
      rearElevator.deployCommand.start();
    }if (OI.xbController.getRawButton(3) && !frontElevator.retractCommand.isRunning()) {
      frontElevator.cancelAll();
      frontElevator.retractCommand.start();
    }if (OI.xbController.getRawButton(4) && !frontElevator.deployCommand.isRunning()) {
      frontElevator.cancelAll();
      frontElevator.deployCommand.start();
    }
    if (OI.xbController.getRawButton(5)){
      pusher.set(ControlMode.PercentOutput, (OI.xbController.getRawAxis(1) * .4));
      System.out.println((OI.xbController.getRawAxis(1) * .4));
    }
    else{
      pusher.set(ControlMode.PercentOutput, 0.0);
    }
    if (OI.xbController.getRawButton(6)){
      System.out.println("elevator cancel");
      frontElevator.cancelAll();
      rearElevator.cancelAll();
    }



  }

  /**
   * This function is called periodically during test mode.
   */
  @Override
  public void testPeriodic() {
    System.out.println(Integer.toString(arm.getPosition()) + " zpos: " + Integer.toString(arm.zeroedpos));
  }
}
