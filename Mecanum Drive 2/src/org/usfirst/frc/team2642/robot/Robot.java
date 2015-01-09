package org.usfirst.frc.team2642.robot;


import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.RobotDrive;
import edu.wpi.first.wpilibj.RobotDrive.MotorType;
import edu.wpi.first.wpilibj.SampleRobot;
import edu.wpi.first.wpilibj.Timer;

/**
 * This is a demo program showing how to use Mecanum control with the RobotDrive class.
 */
public class Robot extends SampleRobot {
	
    RobotDrive robotDrive;
    Joystick stick;
    Relay relay1;
    Gyro gyro;
    static const float Kp = 0.03

    // Channels for the wheels
    final int frontLeftChannel	= 2;
    final int rearLeftChannel	= 3;
    final int frontRightChannel	= 1;
    final int rearRightChannel	= 0;
    boolean button1 = stick.getRawButton(1);
    boolean button2 = stick.getRawButton(2);
    boolean button3 = stick.getRawButton(3);
   
    
    
    // The channel on the driver station that the joystick is connected to
    final int joystickChannel	= 0;

    public Robot() {
    	robotDrive.setExpiration(0.1);
        robotDrive = new RobotDrive(frontLeftChannel, rearLeftChannel, frontRightChannel, rearRightChannel);
    	robotDrive.setInvertedMotor(MotorType.kFrontLeft, true);	// invert the left side motors
    	robotDrive.setInvertedMotor(MotorType.kRearLeft, true);		// you may need to change or remove this to match your robot

        stick = new Joystick(joystickChannel);
    }
        
    public:
    	GyroSample() :
    		myRobot(1,2), // initialize the sensors in the initialization list
    		gyro(1)
    		{
    			gyro.Reset();
    			while (IsAutonomous())
    			{
    				float angle = gryo.GetAngle() ; //get heading
    				myRobot.Drive(-1.0, -angle * Kp); //turn to correct heading
    				Wait(0.004);
    			}
    			myRobot.Drive(0.0, 0.0); //stop robot
    		}
};
    /**
     * Runs the motors with Mecanum drive.
     */
    public void operatorControl() {
        robotDrive.setSafetyEnabled(true);
        while (isOperatorControl() && isEnabled()) {
        	
        	// Use the joystick X axis for lateral movement, Y axis for forward movement, and Z axis for rotation.
        	// This sample does not use field-oriented drive, so the gyro input is set to zero.
            
            	
            	if (button1){
            		robotDrive.mecanumDrive_Cartesian(stick.getX()/2, stick.getY()/2, stick.getZ()/2, 0);
            	}else{
            		robotDrive.mecanumDrive_Cartesian(stick.getX(), stick.getY(), stick.getZ(), 0);
            		
            	}
            	
            	if (button2){
            		relay1.set(Relay.Value.kForward);
            	}else if(button3){
            		relay1.set(Relay.Value.kReverse);
            	}else{(Relay.Value.kOff);
            		}
            		
            	}
            Timer.delay(0.005);	// wait 5ms to avoid hogging CPU cycles
        }
    }
    
}
