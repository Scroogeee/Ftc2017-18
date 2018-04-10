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

	/**
	 * HardwareMap reference
	 */
	private HardwareMap hardwareMap;

	/**
	 * Current direction
	 */
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
	 * This method initializes the Motors
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
	}

	/**
	 * This method updates every tick
	 * CAUTION: Do only use if DriverCore is assigned
	 */
	public void update() {
		getDirectionFromGamepad();
		updateDirection();
		updateMotorData();
	}

	/**
	 * Gets and sets the desired direction
	 */
	private void getDirectionFromGamepad() {
		if (mainRef.gamepad1.y) {
			currentDir = NORTH;
		} else if (mainRef.gamepad1.b) {
			currentDir = EAST;
		} else if (mainRef.gamepad1.a) {
			currentDir = SOUTH;
		} else if (mainRef.gamepad1.x) {
			currentDir = WEST;
		}
		//Else Direction unchanged

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
		ArrayList<Double> list = new ArrayList<>();
		list.add(fl);
		list.add(fr);
		list.add(rr);
		list.add(rl);
		Double highestNumber = Methods.getHighestNumber(list);
		if (mainRef != null) {
			mainRef.telemetry.addLine("Highest: " + highestNumber.toString());
		}
		if (highestNumber >= 1) {
			fl = fl / highestNumber;
			fr = fr / highestNumber;
			rr = rr / highestNumber;
			rl = rl / highestNumber;
		}
		/**Clamp to ensure no errors occur*/
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
