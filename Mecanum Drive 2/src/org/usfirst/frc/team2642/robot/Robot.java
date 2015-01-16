package org.usfirst.frc.team2642.robot;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.RobotDrive;
import edu.wpi.first.wpilibj.SampleRobot;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.Gyro;
import edu.wpi.first.wpilibj.RobotDrive.MotorType;



public class Robot extends SampleRobot {
	
	private RobotDrive myRobot; // robot drive system
    private Gyro gyro;
    RobotDrive robotDrive;
    Joystick stick;
    
    final int frontLeftChannel	= 2;
    final int rearLeftChannel	= 3;
    final int frontRightChannel	= 1;
    final int rearRightChannel	= 0;
    final int joystickChannel	= 0;

    double Kp = 0.03;

    public Robot() {
        gyro = new Gyro(1);             // Gyro on Analog Channel 1
        myRobot = new RobotDrive(1,2);  // Drive train jaguars on PWM 1 and 2
        myRobot.setExpiration(0.1);
        robotDrive.setExpiration(0.1);
        robotDrive = new RobotDrive(frontLeftChannel, rearLeftChannel, frontRightChannel, rearRightChannel);
    	robotDrive.setInvertedMotor(MotorType.kFrontLeft, true);	// invert the left side motors
    	robotDrive.setInvertedMotor(MotorType.kRearLeft, true);		// you may need to change or remove this to match your robot

        stick = new Joystick(joystickChannel);
    }

    public void operatorControl() {
        robotDrive.setSafetyEnabled(true);
        while (isOperatorControl() && isEnabled()) {
            robotDrive.mecanumDrive_Cartesian(stick.getX(), stick.getY(), stick.getZ(), 0);
        }
        }
    
    public void Autonomous(){
    	gyro.reset();
    	while (isAutonomous()) {
    		double angle = gyro.getAngle(); // get current heading
    		myRobot.drive(-1.0, -angle*Kp); // drive towards heading 0
    		Timer.delay(0.004);
    	}
    	myRobot.drive(0.0, 0.0);
}
}
