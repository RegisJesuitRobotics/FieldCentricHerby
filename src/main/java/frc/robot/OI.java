/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.buttons.JoystickButton;
import frc.robot.commands.allStop;
import frc.robot.commands.autoDrive;

/**
 * This class is the glue that binds the controls on the physical operator
 * interface to the commands and command groups that allow control of the robot.
 */
public class OI {

 // private final Sensors gyro = new Sensors();
  private double gyro = 0;
  private  final Joystick joystick = new Joystick(RobotMap.JOYSTICK);
  private  final JoystickButton button0 = new JoystickButton(joystick, RobotMap.JOYSTICK_BUTTON0);
  private  final JoystickButton button1 = new JoystickButton(joystick, RobotMap.JOYSTICK_BUTTON1);

  private double xAxis= joystick.getRawAxis(RobotMap.XAXIS);
  private double yAxis= joystick.getRawAxis(RobotMap.YAXIS);
  private double zAxis= joystick.getRawAxis(RobotMap.ZAXIS);
  private double sAxis = joystick.getRawAxis(RobotMap.SAXIS);
  private double[] axiis = new double[4];
  //// CREATING BUTTONS
  // One type of button is a joystick button which is any button on a
  //// joystick.
  // You create one by telling it which joystick it's on and which button
  // number it is.
  // Joystick stick = new Joystick(port);
  // Button button = new JoystickButton(stick, buttonNumber);

  // There are a few additional built in buttons you can use. Additionally,
  // by subclassing Button you can create custom triggers and bind those to
  // commands the same as any other Button.

  //// TRIGGERING COMMANDS WITH BUTTONS
  // Once you have a button, it's trivial to bind it to a button in one of
  // three ways:

  // Start the command when the button is pressed and let it run the command
  // until it is finished as determined by it's isFinished method.
  // button.whenPressed(new ExampleCommand());

  // Run the command while the button is being held down and interrupt it once
  // the button is released.
  // button.whileHeld(new ExampleCommand());

  // Start the command when the button is released and let it run the command
  // until it is finished as determined by it's isFinished method.
  // button.whenReleased(new ExampleCommand());
  public OI(){
    button0.whileHeld(new allStop());
    button1.whileHeld(new autoDrive(0.5, -0.5)); 
  }
    public double[] getAxiis(){
    xAxis= joystick.getRawAxis(RobotMap.XAXIS);
    yAxis= joystick.getRawAxis(RobotMap.YAXIS);
    zAxis= joystick.getRawAxis(RobotMap.ZAXIS);
    sAxis = joystick.getRawAxis(RobotMap.SAXIS);
    axiis[0] = xAxis;
    axiis[1] = yAxis;
    axiis[2] = zAxis;
    axiis[3] = sAxis;
    return axiis;
  }
  public double getGyro(){
    //return gyro.getAxis('y');
    return gyro;
  }
}
