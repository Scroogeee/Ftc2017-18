package org.firstinspires.ftc.teamcode.preJC_DriverControlled;

import com.qualcomm.robotcore.hardware.DcMotor;

import static org.firstinspires.ftc.teamcode.preJC_DriverControlled.Constants.RELIC_EXTEND_SCALE;

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
	 * Initializes the relic control class
	 */
	public void init(CoreUnit p_mainRef) {
		mainRef = p_mainRef;
		height_motor = mainRef.hardwareMap.dcMotor.get(Constants.Vertical_Relic_Motor);
		extending_motor = mainRef.hardwareMap.dcMotor.get(Constants.Lateral_Relic_Motor);

	}

	/**
	 * Updates the motor values for the relic arm
	 */
	public void update() {
		updateHeight();
		updateExtend();
	}

	private void updateHeight() {
		height_motor.setPower(-mainRef.gamepad2.left_stick_y);
	}

	private void updateExtend() {
		extending_motor.setPower(mainRef.gamepad2.left_stick_x * RELIC_EXTEND_SCALE);
	}

}