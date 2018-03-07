package org.firstinspires.ftc.teamcode.driverControlled.driverDriving;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.Gamepad;

import org.firstinspires.ftc.teamcode.driverControlled.DriverCore;
import org.firstinspires.ftc.teamcode.robotModules.Constants;

import java.util.ArrayList;

import static org.firstinspires.ftc.teamcode.driverControlled.driverDriving.RobotDirection.EAST;
import static org.firstinspires.ftc.teamcode.driverControlled.driverDriving.RobotDirection.NORTH;
import static org.firstinspires.ftc.teamcode.driverControlled.driverDriving.RobotDirection.SOUTH;
import static org.firstinspires.ftc.teamcode.driverControlled.driverDriving.RobotDirection.WEST;

/**
 * Created by FTC on 08.01.2018.
 */

public class DriveStraight4W {

	/**
	 * Values for the driving (as from gamepad)
	 */
	private double frontvector = 0;
	private double sidevector = 0;
	private double turnrate = 0;

	/**
	 * Desired setpoints of the motors
	 */
	private double rearr, rearl, frontr, frontl;

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
		updateMotorData();
	}

	private void getDirectionFromGamepad() {
		if (mainRef.gamepad1.dpad_up) {
			currentDir = NORTH;
		} else if (mainRef.gamepad1.dpad_right) {
			currentDir = EAST;
		} else if (mainRef.gamepad1.dpad_down) {
			currentDir = SOUTH;
		} else if (mainRef.gamepad1.dpad_left) {
			currentDir = WEST;
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

		frontvector = gamepad1.right_stick_y;
		sidevector = gamepad1.right_stick_x;
		turnrate = gamepad1.left_stick_x;

		adjustByDirection(frontvector, sidevector, turnrate);

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

	private void adjustByDirection(double p_frontvector, double p_sidevector, double p_turnrate) {
		switch (currentDir) {
			case NORTH:
				frontvector = p_frontvector;
				sidevector = p_sidevector;
				break;
			case EAST:
				sidevector = -p_frontvector;
				frontvector = p_sidevector;
				break;
			case SOUTH:
				frontvector = -p_frontvector;
				sidevector = -p_sidevector;
				break;
			case WEST:
				sidevector = p_frontvector;
				frontvector = -p_sidevector;
				break;
			default:
				mainRef.telemetry.addLine("Intenal Error: Bad Direction");
				break;
		}
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
