package org.firstinspires.ftc.teamcode.autonomous.autoDriving;

import com.qualcomm.hardware.modernrobotics.ModernRoboticsI2cGyro;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;

import org.firstinspires.ftc.teamcode.Constants;
import org.firstinspires.ftc.teamcode.autonomous.AutonomousCore;
import org.firstinspires.ftc.teamcode.autonomous.HardwareConfiguration;

/**
 * Created by FTC on 25.01.2018.
 */

public class W4StraightGyro extends W4StraightAuto {

	static final double COUNTS_PER_MOTOR_REV = 1440;    // eg: TETRIX Motor Encoder
	static final double DRIVE_GEAR_REDUCTION = 1.0;     // This is < 1.0 if geared UP
	static final double WHEEL_DIAMETER_INCHES = 4.0;     // For figuring circumference
	static final double COUNTS_PER_INCH = (COUNTS_PER_MOTOR_REV * DRIVE_GEAR_REDUCTION) /
			(WHEEL_DIAMETER_INCHES * 3.1415926535);
	// These constants define the desired driving/control characteristics
	// The can/should be tweaked to suite the specific robot drive train.
	static final double DRIVE_SPEED = 0.7;     // Nominal speed for better accuracy.
	static final double TURN_SPEED = 0.5;     // Nominal half speed for better accuracy.
	static final double HEADING_THRESHOLD = 1;      // As tight as we can make it with an integer gyro
	static final double P_TURN_COEFF = 0.5;     // Larger is more responsive, but also less stable
	static final double P_DRIVE_COEFF = 0.15;     // Larger is more responsive, but also less stable

	ModernRoboticsI2cGyro gyro = null;

	public W4StraightGyro(AutonomousCore param_ac) {
		super(param_ac);
	}

	@Override
	public void initialize() {
		super.initialize();
		if (autonomousCore.getHardwareConfiguration() == HardwareConfiguration.GREEN ||
				autonomousCore.getHardwareConfiguration() == HardwareConfiguration.YELLOW) {
			A.setDirection(DcMotorSimple.Direction.REVERSE);
			B.setDirection(DcMotorSimple.Direction.FORWARD);
			C.setDirection(DcMotorSimple.Direction.FORWARD);
			D.setDirection(DcMotorSimple.Direction.REVERSE);

			gyro = (ModernRoboticsI2cGyro) autonomousCore.hardwareMap.gyroSensor.get(Constants.gyro_name);

			// Ensure the robot it stationary, then reset the encoders and calibrate the gyro.
			A.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
			B.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
			C.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
			D.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
			// Send telemetry message to alert driver that we are calibrating;
			autonomousCore.telemetry.addData(">", "Calibrating Gyro");    //
			autonomousCore.telemetry.update();

			gyro.calibrate();

			// make sure the gyro is calibrated before continuing
			while (!autonomousCore.isStopRequested() && gyro.isCalibrating()) {
				autonomousCore.sleep(50);
				autonomousCore.idle();
			}

			autonomousCore.telemetry.addData(">", "Robot Ready.");    //
			autonomousCore.telemetry.update();

			A.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
			B.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
			C.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
			D.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

			gyro.resetZAxisIntegrator();
		}
	}


}