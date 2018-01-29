package org.firstinspires.ftc.teamcode.preJC_DriverControlled.driving;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.Gamepad;

import org.firstinspires.ftc.teamcode.preJC_DriverControlled.Constants;
import org.firstinspires.ftc.teamcode.preJC_DriverControlled.CoreUnit;

import static org.firstinspires.ftc.teamcode.preJC_DriverControlled.driving.RobotDirection.NORTH;
import static org.firstinspires.ftc.teamcode.preJC_DriverControlled.driving.RobotDirection.SOUTH;
/**
 * Created by FTC on 08.01.2018.
 */

public class DriveStraight4W {

	/**
	 * Desired setpoints of the motors
	 */
	private double rearr, rearl, frontr, frontl;

	/**
	 * CoreUnit reference
	 */
	private CoreUnit mainRef;

	private RobotDirection currentDir;

	/**
	 * Declaration of the drives
	 */
	private DcMotor Drive_A;
	private DcMotor Drive_B;
	private DcMotor Drive_C;
	private DcMotor Drive_D;

	/**
	 * Initializes the Drive Control class
	 */
	public void init(CoreUnit p_mainRef) {
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
		//TODO update direction
		updateMotorData();
	}

	private void getDirectionFromGamepad() {
		if (mainRef.gamepad1.y) {
			currentDir = NORTH;
		} else if (mainRef.gamepad1.a) {
			currentDir = SOUTH;
	 	} else {
			//Direction unchanged
		}
	}

	/**
	 * This method updates the desired power values
	 * for the drive engines.
	 */
	private void updateMotorData() {
		computeDriveValues();
		Drive_A.setPower(frontl);
		Drive_C.setPower(rearr);
		Drive_B.setPower(frontr);
		Drive_D.setPower(rearl);
		mainRef.telemetry.addLine(Double.toString(frontl));
		mainRef.telemetry.addLine(Double.toString(frontr));
		mainRef.telemetry.addLine(Double.toString(rearr));
		mainRef.telemetry.addLine(Double.toString(rearl));
	}

	/**
	 * This method computes the desired power values
	 * for the drive engines.
	 */
	private void computeDriveValues() {
		Gamepad gamepad1 = mainRef.gamepad1;

		/** Drive values for the four straight directions */
<<<<<<< HEAD
		frontl = gamepad1.right_stick_y;
		rearl = gamepad1.right_stick_y;
		frontr = -gamepad1.right_stick_y;
		rearr = -gamepad1.right_stick_y;

		/** Drive values for cross driving */
		frontl += gamepad1.right_stick_x;
		frontr += gamepad1.right_stick_x;
		rearr -= gamepad1.right_stick_x;
		rearl -= gamepad1.right_stick_x;

		/** Turning */
		frontl -= gamepad1.left_stick_x;
		frontr -= gamepad1.left_stick_x;
		rearr -= gamepad1.left_stick_x;
		rearl -= gamepad1.left_stick_x;

		scaleDownValues();

	}

	/**
	 * Scales down all values so that none exceeds 1 or -1
	 */
	private void scaleDownValues() {

		//TODO scale the values instead of clamping them

		frontl = Math.min(frontl, 1);
		rearr = Math.min(rearr, 1);
		frontr = Math.min(frontr, 1);
		rearl = Math.min(rearl, 1);
		frontl = Math.max(frontl, -1);
		rearr = Math.max(rearr, -1);
		frontr = Math.max(frontr, -1);
		rearl = Math.max(rearl, -1);
	}


}
