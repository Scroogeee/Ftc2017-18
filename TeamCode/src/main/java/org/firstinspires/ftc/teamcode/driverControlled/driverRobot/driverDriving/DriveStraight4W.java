package org.firstinspires.ftc.teamcode.driverControlled.driverRobot.driverDriving;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.Gamepad;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.teamcode.driverControlled.DriverCore;
import org.firstinspires.ftc.teamcode.util.Constants;
import org.firstinspires.ftc.teamcode.util.Methods;

import java.util.ArrayList;

import static org.firstinspires.ftc.teamcode.driverControlled.driverRobot.driverDriving.RobotDirection.EAST;
import static org.firstinspires.ftc.teamcode.driverControlled.driverRobot.driverDriving.RobotDirection.NORTH;
import static org.firstinspires.ftc.teamcode.driverControlled.driverRobot.driverDriving.RobotDirection.SOUTH;
import static org.firstinspires.ftc.teamcode.driverControlled.driverRobot.driverDriving.RobotDirection.WEST;
import static org.firstinspires.ftc.teamcode.util.Constants.A_Scale;
import static org.firstinspires.ftc.teamcode.util.Constants.B_Scale;
import static org.firstinspires.ftc.teamcode.util.Constants.C_Scale;
import static org.firstinspires.ftc.teamcode.util.Constants.D_Scale;
import static org.firstinspires.ftc.teamcode.util.Constants.FAST_SCALE;
import static org.firstinspires.ftc.teamcode.util.Constants.SLOW_SCALE;

/**
 * Created by FTC on 08.01.2018.
 */

public class DriveStraight4W {

	private boolean isSlow = false;

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

	private HardwareMap hardwareMap;

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
	 * CAUTION: At least one parameter has to be not null.
	 */
	public void init(HardwareMap hwMap, DriverCore p_mainRef) {
		mainRef = p_mainRef;
		if (hwMap != null) {
			hardwareMap = hwMap;
		} else if (mainRef != null) {
			hardwareMap = mainRef.hardwareMap;
		} else {
			throw new NullPointerException("DriverCore or HardwareMap not specified");
		}
		initMotors();
		currentDir = NORTH;
		update();
	}

	/**
	 * Initializes the motor references
	 */
	public void initMotors() {
		Drive_A = hardwareMap.dcMotor.get(Constants.Drive_A);
		Drive_B = hardwareMap.dcMotor.get(Constants.Drive_B);
		Drive_C = hardwareMap.dcMotor.get(Constants.Drive_C);
		Drive_D = hardwareMap.dcMotor.get(Constants.Drive_D);
		Drive_A.setDirection(DcMotorSimple.Direction.FORWARD);
		Drive_B.setDirection(DcMotorSimple.Direction.FORWARD);
		Drive_C.setDirection(DcMotorSimple.Direction.FORWARD);
		Drive_D.setDirection(DcMotorSimple.Direction.FORWARD);
		Drive_A.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
		Drive_B.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
		Drive_C.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
		Drive_D.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
		Drive_A.setPower(0);
		Drive_B.setPower(0);
		Drive_C.setPower(0);
		Drive_D.setPower(0);
	}

	/**
	 * This method updates every tick
	 * CAUTION: Do only use if DriverCore is assigned
	 */
	public void update() {
		getAndSetSlowMode();
		getDirectionFromGamepad();
		updateMotorData();
	}

	/**
	 * Gets and sets the slowmode on/off
	 */
	private void getAndSetSlowMode() {
		if (mainRef.gamepad1.x) {
			isSlow = true;
		} else if (mainRef.gamepad1.y) {
			isSlow = false;
		}
		//Else Slowmode status unchanged

	}

	/**
	 * Gets and sets the desired direction
	 */
	private void getDirectionFromGamepad() {
		if (mainRef.gamepad1.dpad_up) {
			currentDir = NORTH;
		} else if (mainRef.gamepad1.dpad_right) {
			currentDir = EAST;
		} else if (mainRef.gamepad1.dpad_down) {
			currentDir = SOUTH;
		} else if (mainRef.gamepad1.dpad_left) {
			currentDir = WEST;
		}
		//Else Direction unchanged

	}

	/**
	 * This method updates the desired power values
	 * for the drive engines.
	 */
	private void updateMotorData() {
		computeDriveValues();
		if (!isSlow) {
			Drive_A.setPower(frontl * A_Scale * FAST_SCALE);
			Drive_C.setPower(rearr * B_Scale * FAST_SCALE);
			Drive_B.setPower(frontr * C_Scale * FAST_SCALE);
			Drive_D.setPower(rearl * D_Scale * FAST_SCALE);
		} else {
			Drive_A.setPower(frontl * A_Scale * SLOW_SCALE);
			Drive_C.setPower(rearr * B_Scale * SLOW_SCALE);
			Drive_B.setPower(frontr * C_Scale * SLOW_SCALE);
			Drive_D.setPower(rearl * D_Scale * SLOW_SCALE);
		}
		if (mainRef != null) {
			//mainRef.telemetry.addLine(Double.toString(frontl));
			//mainRef.telemetry.addLine(Double.toString(frontr));
			//mainRef.telemetry.addLine(Double.toString(rearr));
			//mainRef.telemetry.addLine(Double.toString(rearl));
		}
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
				throw new IllegalArgumentException("Bad Direction");
		}
	}

	/**
	 * Scales down all values so that none exceeds 1 or -1
	 */
	private void scaleDownValues() {
		ArrayList<Double> list = new ArrayList<>();
		list.add(frontl);
		list.add(frontr);
		list.add(rearr);
		list.add(rearl);
		Double highestNumber = Methods.getHighestNumber(list);
		if (mainRef != null) {
			mainRef.telemetry.addLine("Highest: " + highestNumber.toString());
		}
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
