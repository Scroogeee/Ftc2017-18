package org.firstinspires.ftc.teamcode.DriverControlled;

import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.DcMotor;

public class RelicControl {

	/**
	 * Main reference
	 */
	CoreUnit mainRef;

	/**
	 * Relic height and Grab arm
	 */

	DcMotor height_motor;
	DcMotor extending_motor;

	/**
	 * CRServo for the Relic grip
	 */

	CRServo relic_grip;

	/**
	 * Initializes the relic control class
	 */
	public void init(CoreUnit p_mainRef) {
		mainRef = p_mainRef;
		height_motor = mainRef.hardwareMap.dcMotor.get(Constants.Vertical_Relic_Motor);
		extending_motor = mainRef.hardwareMap.dcMotor.get(Constants.Lateral_Relic_Motor);
		relic_grip = mainRef.hardwareMap.crservo.get(Constants.Servo_Relic);
		initValues();
	}

	/**
	 * Sets the movement of all components to 0
	 */
	private void initValues() {
		height_motor.setPower(0);
		extending_motor.setPower(0);
		relic_grip.setPower(0);
	}

	/**
	 * Updates the motor values for the relic arm
	 */
	public void update() {
		updateHeight();
		updateExtend();
		updateRelicGrip();
	}

	/**
	 * Updates the rotaion of the CRServo for the relic grip
	 */
	private void updateRelicGrip() {

	}

	/**
	 * Updates the height of the relic claw arm
	 */
	private void updateHeight() {
		height_motor.setPower(-mainRef.gamepad2.left_stick_y * Constants.RELIC_HEIGHT_SCALE);
	}

	/**
	 * Updates the extend of the relic claw arm
	 */
	private void updateExtend() {
		extending_motor.setPower(mainRef.gamepad2.left_stick_x * Constants.RELIC_EXTEND_SCALE);
	}

}