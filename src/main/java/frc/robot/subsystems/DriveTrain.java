/*----------------------------------------------------------------------------*/
/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import com.ctre.phoenix.motorcontrol.can.VictorSPX;

import frc.robot.RobotMap;
import frc.robot.commands.Drive;
import edu.wpi.first.wpilibj.command.Subsystem;

public class DriveTrain extends Subsystem {

 
  VictorSPX leftMotor1 = new VictorSPX(RobotMap.LEFT_MOTOR1);
  VictorSPX leftMotor2 = new VictorSPX(RobotMap.LEFT_MOTOR2);
  VictorSPX rightMotor1 = new VictorSPX(RobotMap.RIGHT_MOTOR1);
  VictorSPX rightMotor2 = new VictorSPX(RobotMap.RIGHT_MOTOR2);

  public DriveTrain() {
    
  }
  public void moveRight(final double speed) {
    rightMotor1.set(ControlMode.PercentOutput, speed);
    rightMotor2.set(ControlMode.PercentOutput, speed);
  }

  public void moveLeft(final double speed) {
    leftMotor1.set(ControlMode.PercentOutput, speed);
    leftMotor2.set(ControlMode.PercentOutput, speed);
  }

  @Override
  protected void initDefaultCommand() {
    // Set the default command for a subsystem here.
    setDefaultCommand(new Drive());
  }
}
