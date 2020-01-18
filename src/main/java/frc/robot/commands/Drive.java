/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;
import frc.robot.RobotMap;

/**
 * An example command.  You can replace me with your own command.
 */
public class Drive extends Command {
  private double[] axiis = new double[3];
  private double angle = 0;
  private double difference = 0;
  private double gyro = 0;
  private double limiter = 0.2;
  private double sLimiter = 0.5;
  private double hypotonuse = 0;
  private String direction = "forward";
  public Drive() {
    // Use requires() here to declare subsystem dependencies
    requires(Robot.driveTrain);
  }

  // Called just before this Command runs the first time
  @Override
  protected void initialize() {

  }
  // Called repeatedly when this Command is scheduled to run
  @Override
  protected void execute() {
    getDifference();
    if(Math.abs(difference) > 20){//if there is significant difference
      if(difference < 0){//if difference is negative
        //turn towards negative
        //System.out.println("NEGATIVE");
        Robot.driveTrain.moveRight(-hypotonuse * limiter);
        Robot.driveTrain.moveLeft(-limiter * hypotonuse);
      }else{//if difference is positive
        //turn towards positive
       // System.out.println("POSITIVE");
       // System.out.println("POSITIVE");
        Robot.driveTrain.moveRight(hypotonuse * limiter);
        Robot.driveTrain.moveLeft(limiter * hypotonuse);
      }
    }else if(Math.abs(difference) > 5){//if there is a little difference
      if(difference < 0){//if difference is negative
        //turn towards negative
        //System.out.println("negative");
        Robot.driveTrain.moveRight(hypotonuse * limiter);
        Robot.driveTrain.moveLeft(-2 * limiter * hypotonuse);
      }else{//if difference is positive;
        //turn towards positive.
        //System.out.println("positive");
        Robot.driveTrain.moveRight(2 * hypotonuse * limiter);
        Robot.driveTrain.moveLeft(-limiter * hypotonuse);
       }
    }else{//if there is negligible difference
      //go straight.%
      //System.out.println("straight");
       Robot.driveTrain.moveRight(Math.abs(hypotonuse * sLimiter));
       Robot.driveTrain.moveLeft(-Math.abs(hypotonuse * sLimiter));
    }
  }

  // Make this return true when this Command no longer needs to run execute()
  @Override
  protected boolean isFinished() {
    return false;
  }

  // Called once after isFinished returns true
  @Override
  protected void end() {
    Robot.driveTrain.moveRight(0);
    Robot.driveTrain.moveLeft(0);
  }

  // Called when another command which requires one or more of the same
  // subsystems is scheduled to run
  @Override
  protected void interrupted() {
    this.end();
  }

  public void getDifference(){
    axiis = Robot.m_oi.getAxiis();
    gyro = Robot.gyro.getAxis();
    angle = 0;
    angle = Math.atan(axiis[1] / axiis[0]) * (180 / Math.PI);
    if(angle < 0){
      angle += 90;
    }else{
     angle -= 90;
    }
    //angle *= -1;
    // if(axiis[1] < 0){
    //   if(axiis[0] > 0){//quad 2
    //     angle = Math.abs(Math.atan(axiis[1] / axiis[0])) + 4.8;
    //   }else{//quad 3
    //     angle = Math.abs(Math.abs(Math.atan(axiis[1] / axiis[0]))- 1.6);
    //   }
    // }else{
    //   if(axiis[0] > 0){//quad 1
    //     angle = Math.abs(Math.abs(Math.atan(axiis[1] / axiis[0]))- 1.6) + 3.2;
    //   }else{//quad 4
    //     angle = Math.abs(Math.atan(axiis[1] / axiis[0])) + 1.6;
    //   }
    // }
    difference = angle - gyro;
    System.out.println("Angle: " + angle +  " Gyro: " + gyro + "Difference: " + difference);
    if(axiis[1] > 0 && direction.equals("forward")){
      Robot.gyro.setGyro(-180);//flip view
      direction = "backward";
    }else if(axiis[1] < 0 && direction.equals("backward")){
      Robot.gyro.setGyro(0);//flip view
      direction = "forward";
    }
    hypotonuse = Math.sqrt(Math.pow(axiis[0], 2) + Math.pow(axiis[1], 2));
    if (hypotonuse < 0.3){//deadZone
      hypotonuse = 0;
    }
    limiter = .3 * ((-axiis[3] + 2)/ 2);
    sLimiter = ((-axiis[3] + 2) / 2);
   // System.out.println(limiter);
  }
}
