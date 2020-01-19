package frc.robot;

import edu.wpi.first.wpilibj.I2C;
import edu.wpi.first.wpilibj.shuffleboard.Shuffleboard;
import edu.wpi.first.wpilibj.smartdashboard.*;
import edu.wpi.first.wpilibj.util.Color;

import com.revrobotics.ColorSensorV3;

public class ColorSensor{

    private final I2C.Port i2cPort = I2C.Port.kOnboard;
    private final ColorSensorV3 m_colorSensor = new ColorSensorV3(i2cPort);


        Color detectedColor = m_colorSensor.getColor();


    public void colorDisplay(){
        double IR = m_colorSensor.getIR();
        SmartDashboard.getNumber("Color Sensor Values", IR);
        detectedColor = m_colorSensor.getColor();
        SmartDashboard.putNumber("Red", detectedColor.red);

        SmartDashboard.putNumber("Green", detectedColor.green);
    
        SmartDashboard.putNumber("Blue", detectedColor.blue);
    
        SmartDashboard.putNumber("IR", IR);

        int proximity = m_colorSensor.getProximity();

        SmartDashboard.putNumber("Proximity", proximity);

        if(detectedColor.red > .5){
            SmartDashboard.putString("Color", "red");
        }else if(detectedColor.red > .2 && detectedColor.red < .35){
            SmartDashboard.putString("Color", "blue");
        }else if(detectedColor.red < .2 && detectedColor.green > .5){
            SmartDashboard.putString("Color", "green");
        }else if(detectedColor.red < 5 && detectedColor.red > .35){
            SmartDashboard.putString("Color", "yellow");
        }else{
          System.out.println("none");
        }
    }
}



/*COLOR READINGS
Under No Light:

   RED
RED: 0.46
GREEN: 0.37
BLUE: 0.16
  YELLOW
RED: 0.31
GREEN: 0.55
BLUE: 0.13
  BLUE
RED:  0.13
GREEN: 0.42
BLUE: 0.44
  GREEN
RED: 0.18
GREEN: 0.55
BLUE: 0.26

Under Room Light:

   RED
RED: 0.61
GREEN: 0.31
BLUE: 0.07
  YELLOW
RED: 0.39
GREEN: 0.52
BLUE: 0.08
  BLUE
RED:  0.16
GREEN: 0.46
BLUE: 0.37
  GREEN
RED: 0.21
GREEN: 0.58
BLUE: 0.20

Plexi Under No Light:

   RED
RED: 0.38
GREEN: 0.40
BLUE: 0.20
  YELLOW
RED: 0.30
GREEN: 0.54
BLUE: 0.15
  BLUE
RED:  0.17
GREEN: 0.43
BLUE: 0.39
  GREEN
RED: 0.20
GREEN: 0.52
BLUE: 0.27

Plexi Under Room Light:

   RED
RED: 0.58
GREEN: 0.32
BLUE: 0.09
  YELLOW
RED: 0.38
GREEN: 0.51
BLUE: 0.096
  BLUE
RED:  0.18
GREEN: 0.46
BLUE: 0.35
  GREEN
RED: 0.22
GREEN: 0.56
BLUE: 0.20
*/