package org.firstinspires.ftc.teamcode.autonomous.autoRobot.autoRobotModules.autoJewels;

import com.qualcomm.robotcore.hardware.ColorSensor;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

import static org.firstinspires.ftc.teamcode.util.Constants.jewelArm_name;
import static org.firstinspires.ftc.teamcode.util.Constants.jewelSensor_name;
import static org.firstinspires.ftc.teamcode.util.Constants.minimum_ConfidenceLevel;

/**
 * Created by FTC on 21.02.2018.
 * Controls the Jewel robot module
 */

public class JewelControl {

	protected ColorSensor jewel_sensor;

	protected Servo jewel_arm;

	private HardwareMap hardwareMap;

	/**
	 * Initializes the jewel control class
	 */
	public void initialize(HardwareMap hwMap) {
		hardwareMap = hwMap;
		jewel_arm = hardwareMap.servo.get(jewelArm_name);
		jewel_sensor = hardwareMap.colorSensor.get(jewelSensor_name);
		jewel_arm.setDirection(Servo.Direction.FORWARD);
		jewel_arm.setPosition(1);
		jewel_sensor.enableLed(true);
	}

	/**
	 * Updates the Servo position to the given values
	 */
	public void updateArm(double position) {
		position = Math.min(position, 1);
		position = Math.max(position, 0);
		jewel_arm.setPosition(position);
	}

	/**
	 * Returns the current jewel color which is visible
	 */
	public JewelColor getColor() {
		if (jewel_sensor.blue() > minimum_ConfidenceLevel) {
			return JewelColor.BLUE;
		} else if (jewel_sensor.red() > minimum_ConfidenceLevel) {
			return JewelColor.RED;
		}
		return JewelColor.NONE;
	}

}
