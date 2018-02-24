package org.firstinspires.ftc.teamcode.driverControlled.driving;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.Gamepad;

import org.firstinspires.ftc.teamcode.driverControlled.Constants;
import org.firstinspires.ftc.teamcode.driverControlled.CoreUnit;

import java.util.ArrayList;

import static org.firstinspires.ftc.teamcode.driverControlled.driving.RobotDirection.NORTH;
import static org.firstinspires.ftc.teamcode.driverControlled.driving.RobotDirection.SOUTH;

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
		Drive_A.setPower(frontl * Constants.A_Scale);
		Drive_C.setPower(rearr * Constants.B_Scale);
		Drive_B.setPower(frontr * Constants.C_Scale);
		Drive_D.setPower(rearl * Constants.D_Scale);
		//TODO remove debugging
		//mainRef.telemetry.addLine(Double.toString(frontl));
		//mainRef.telemetry.addLine(Double.toString(frontr));
		//mainRef.telemetry.addLine(Double.toString(rearr));
		//mainRef.telemetry.addLine(Double.toString(rearl));

	}

	/**
	 * This method computes the desired power values
	 * for the drive engines.
	 */
	private void computeDriveValues() {
		Gamepad gamepad1 = mainRef.gamepad1;

		double frontvector = gamepad1.right_stick_y;
		double sidevector = gamepad1.right_stick_x;
		double turnrate = gamepad1.left_stick_x;

		if (currentDir == SOUTH) {
			frontvector *= -1;
			sidevector *= -1;
		}

		/** Drive values for the forward/backwards*/
		frontl = frontvector;
		rearl = frontvector;
		frontr = -frontvector;
		rearr = -frontvector;

		/** Drive values for cross driving */
		frontl += sidevector;
		frontr += sidevector;
		rearr -= sidevector;
		rearl -= sidevector;

		/** Turning */
		frontl -= turnrate;
		frontr -= turnrate;
		rearr -= turnrate;
		rearl -= turnrate;

		scaleDownValues();

	}

	/**
	 * Scales down all values so that none exceeds 1 or -1
	 */
	private void scaleDownValues() {
		ArrayList<Double> list = new ArrayList<Double>();
		list.add(frontl);
		list.add(frontr);
		list.add(rearr);
		list.add(rearl);
		Double highestNumber = Constants.getHighestNumber(list);
		//TODO remove debugging
		mainRef.telemetry.addLine("Highest: " + highestNumber.toString());
		if (highestNumber >= 1) {
			frontl = frontl / highestNumber;
			frontr = frontr / highestNumber;
			rearr = rearr / highestNumber;
			rearl = rearl / highestNumber;
		}
		/**Clamp to ensure no errors occur*/
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