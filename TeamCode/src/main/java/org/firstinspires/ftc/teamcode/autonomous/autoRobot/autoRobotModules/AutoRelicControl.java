package org.firstinspires.ftc.teamcode.autonomous.autoRobot.autoRobotModules;

import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;
import org.firstinspires.ftc.teamcode.Constants;

public class AutoRelicControl {

	/**
	 * HardwareMap reference
	 */
	private HardwareMap hardwareMap;

	/**
	 * Relic height and Grab arm
	 */

	private DcMotor height_motor;
	private DcMotor extending_motor;

	/**
	 * CRServo for the Relic grip
	 */

	private CRServo relic_grip;

	/**
	 * Initializes the relic control class
	 */
	public void initialize(HardwareMap hwMap) {
		hardwareMap = hwMap;
		height_motor = hardwareMap.dcMotor.get(Constants.Vertical_Relic_Motor);
		extending_motor = hardwareMap.dcMotor.get(Constants.Lateral_Relic_Motor);
		relic_grip = hardwareMap.crservo.get(Constants.Servo_Relic);
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
	public void update(double height, double extend, double grip) {
		updateHeight(height);
		updateExtend(extend);
		updateRelicGrip(grip);
	}

	/**
	 * Updates the rotaion of the CRServo for the relic grip
	 */
	private void updateRelicGrip(double grip) {
		Math.min(grip, 1);
		Math.max(grip, -1);
		relic_grip.setPower(grip);
	}

	/**
	 * Updates the height of the relic claw arm
	 */
	private void updateHeight(double height) {
		Math.min(height, 1);
		Math.max(height, -1);
		height_motor.setPower(height * Constants.RELIC_HEIGHT_SCALE);
		//mainRef.telemetry.addLine("Relic height: " + Double.toString(height_motor.getPower()));
	}

	/**
	 * Updates the extend of the relic claw arm
	 */
	private void updateExtend(double extend) {
		Math.min(extend, 1);
		Math.max(extend, -1);
		extending_motor.setPower(extend * Constants.RELIC_EXTEND_SCALE);
	}

}