package org.firstinspires.ftc.teamcode.driverControlled.driverDriving;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.Gamepad;

import org.firstinspires.ftc.teamcode.Constants;
import org.firstinspires.ftc.teamcode.driverControlled.DriverCore;

import static org.firstinspires.ftc.teamcode.driverControlled.driverDriving.RobotDirection.EAST;
import static org.firstinspires.ftc.teamcode.driverControlled.driverDriving.RobotDirection.NORTH;
import static org.firstinspires.ftc.teamcode.driverControlled.driverDriving.RobotDirection.SOUTH;
import static org.firstinspires.ftc.teamcode.driverControlled.driverDriving.RobotDirection.WEST;

/**
 * Created by FTC on 16.10.2017.
 * This class is a basic implementation for 4-wheeled omni-drive
 */
public class DriveCross4W {

	/**
	 * Desired setpoints of the motors
	 */
	private double rr, rl, fr, fl;

	/**
	 * DriverCore reference
	 */
	private DriverCore mainRef;

	private RobotDirection currentDir;

	/**
	 * Declaration of the drives
	 */
	private DcMotor Drive_A;
	private DcMotor Drive_B;
	private DcMotor Drive_C;
	private DcMotor Drive_D;

	/**
	 * Declaration of the motor references
	 */
	private DcMotor front_left;
	private DcMotor front_right;
	private DcMotor rear_right;
	private DcMotor rear_left;

	/**
	 * Initializes the Drive Control class
	 */
	public void init(DriverCore p_mainRef) {
		mainRef = p_mainRef;
		initMotors();
		currentDir = NORTH;
		update();
	}

	public void initMotors() {
		Drive_A = mainRef.hardwareMap.dcMotor.get(Constants.Drive_A);
		Drive_B = mainRef.hardwareMap.dcMotor.get(Constants.Drive_B);
		Drive_C = mainRef.hardwareMap.dcMotor.get(Constants.Drive_C);
		Drive_D = mainRef.hardwareMap.dcMotor.get(Constants.Drive_D);
		Drive_A.setDirection(DcMotorSimple.Direction.FORWARD);
		Drive_B.setDirection(DcMotorSimple.Direction.FORWARD);
		Drive_C.setDirection(DcMotorSimple.Direction.FORWARD);
		Drive_D.setDirection(DcMotorSimple.Direction.FORWARD);
	}

	/**
	 * This method updates every tick
	 */
	public void update() {
		getDirectionFromGamepad();
		updateDirection();
		updateMotorData();
	}

	private void getDirectionFromGamepad() {
		if (mainRef.gamepad1.y) {
			currentDir = NORTH;
		} else if (mainRef.gamepad1.b) {
			currentDir = EAST;
		} else if (mainRef.gamepad1.a) {
			currentDir = SOUTH;
		} else if (mainRef.gamepad1.x) {
			currentDir = WEST;
		} else {
			//Direction unchanged
		}
	}

	/**
	 * This method updates the Drives definition for a new Direction.
	 */
	private void updateDirection() {
		switch (currentDir) {
			case NORTH:
				front_left = Drive_A;
				front_right = Drive_B;
				rear_right = Drive_C;
				rear_left = Drive_D;
				break;
			case EAST:
				front_left = Drive_D;
				front_right = Drive_A;
				rear_right = Drive_B;
				rear_left = Drive_C;
				break;
			case SOUTH:
				front_left = Drive_C;
				front_right = Drive_D;
				rear_right = Drive_A;
				rear_left = Drive_B;
				break;
			case WEST:
				front_left = Drive_B;
				front_right = Drive_C;
				rear_right = Drive_D;
				rear_left = Drive_A;
				break;
			default:
				System.out.println("An internal Error occurred: BAD DIRECTION");
		}
	}

	/**
	 * This method updates the desired power values
	 * for the drive engines.
	 */
	private void updateMotorData() {
		computeDriveValues();
		front_left.setPower(fl);
		rear_right.setPower(rr);
		front_right.setPower(fr);
		rear_left.setPower(rl);
		/*mainRef.telemetry.addLine(Double.toString(fl));
		mainRef.telemetry.addLine(Double.toString(fr));
		mainRef.telemetry.addLine(Double.toString(rr));
		mainRef.telemetry.addLine(Double.toString(rl));*/
		mainRef.telemetry.addLine(Drive_A.getDirection().toString());
	}

	/**
	 * This method computes the desired power values
	 * for the drive engines.
	 */
	private void computeDriveValues() {
		Gamepad gamepad1 = mainRef.gamepad1;

		/** Drive values for the four straight directions */
		fl = gamepad1.right_stick_x - gamepad1.right_stick_y;
		rl = -gamepad1.right_stick_x - gamepad1.right_stick_y;
		rr = -gamepad1.right_stick_x + gamepad1.right_stick_y;
		fr = gamepad1.right_stick_x + gamepad1.right_stick_y;

		/** Turning */
		fl -= gamepad1.left_stick_x;
		fr -= gamepad1.left_stick_x;
		rl -= gamepad1.left_stick_x;
		rr -= gamepad1.left_stick_x;

		scaleDownValues();

	}

	/**
	 * Scales down all values so that none exceeds 1 or -1
	 */
	private void scaleDownValues() {
		//TODO scale the values instead of clamping them
		fl = Math.min(fl, 1);
		rr = Math.min(rr, 1);
		fr = Math.min(fr, 1);
		rl = Math.min(rl, 1);
		fl = Math.max(fl, -1);
		rr = Math.max(rr, -1);
		fr = Math.max(fr, -1);
		rl = Math.max(rl, -1);
	}

}
