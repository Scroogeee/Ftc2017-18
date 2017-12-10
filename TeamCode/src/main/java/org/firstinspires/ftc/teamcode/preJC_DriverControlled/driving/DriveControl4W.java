package org.firstinspires.ftc.teamcode.preJC_DriverControlled.driving;

/**
 * Created by FTC on 16.10.2017.
 * This class
 */
public class DriveControl4W {

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
	 * Declaration of the motor references
	 */
	private DcMotor front_left;
	private DcMotor front_right;
	private DcMotor rear_right;
	private DcMotor rear_left;

	/**
	 * Initializes the Drive Control class
	 */
	public void init(CoreUnit p_mainRef) {
		mainRef = p_mainRef;
		initMotors();
		currentDir = Direction.NORTH;
		updateDirection();
	}

	public void initMotors() {
		Drive_A = mainRef.hardwareMap.dcMotor.get(Constants.Drive_A);
		Drive_B = mainRef.hardwareMap.dcMotor.get(Constants.Drive_B);
		Drive_C = mainRef.hardwareMap.dcMotor.get(Constants.Drive_C);
		Drive_D = mainRef.hardwareMap.dcMotor.get(Constants.Drive_D);
	}

	/**
	 * This method updates the Drives definition for a new Direction.
	 */
	public void updateDirection() {
		switch (currentDir) {
			case Direction.NORTH:
				front_left = Drive_A;
				front_right = Drive_B;
				rear_right = Drive_C;
				rear_left = Drive_D;
				break;
			case Direction.EAST:
				front_left = Drive_D;
				front_right = Drive_A;
				rear_right = Drive_B;
				rear_left = Drive_C;
				break;
			case Direction.SOUTH:
				front_left = Drive_C;
				front_right = Drive_D;
				rear_right = Drive_A;
				rear_left = Drive_B;
				break;
			case Direction.WEST:
				front_left = Drive_B;
				front_right = Drive_C;
				rear_right = Drive_D;
				rear_left = Drive_A;
				break;
			default:
				System.out.println("An internal Error occurred");
		}
	}

	/**
	 * This method updates the desired power values
	 * for the drive engines.
	 */
	public void updateMotorData() {

	}

	/**
	 * This method computes the power values for the drive engines.
	 */
	private void calcDriveValues() {

	}

}
