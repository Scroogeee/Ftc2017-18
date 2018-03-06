package org.firstinspires.ftc.teamcode.driverControlled.robotModules;

import com.qualcomm.robotcore.hardware.ColorSensor;
import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.teamcode.autonomous.jewels.JewelColor;
import org.firstinspires.ftc.teamcode.driverControlled.Constants;
import org.firstinspires.ftc.teamcode.driverControlled.CoreUnit;

/**
 * Created by FTC on 21.02.2018.
 */

public class JewelControl {

	protected ColorSensor jewel_sensor;

	protected Servo jewel_arm;

	private CoreUnit coreRef;

	/**
	 * Initializes the jewel control class
	 */
	public void initialize(CoreUnit p_mainRef) {
		coreRef = p_mainRef;
		jewel_arm = coreRef.hardwareMap.servo.get(Constants.jewelArm_name);
		jewel_sensor = coreRef.hardwareMap.colorSensor.get(Constants.jewelSensor_name);
		jewel_arm.setDirection(Servo.Direction.FORWARD);
		jewel_arm.setPosition(0);
	}

	/**
	 * Updates the Servo position to the given values
	 */
	public void updateArm(double position) {
		Math.min(position, 1);
		Math.max(position, 0);
		jewel_arm.setPosition(position);
	}

	public JewelColor getColor() {
		if (jewel_sensor.blue() > Constants.minimum_ConfidenceLevel) {
			return JewelColor.BLUE;
		} else if (jewel_sensor.red() > Constants.minimum_ConfidenceLevel) {
			return JewelColor.RED;
		}
		return JewelColor.NONE;
	}

}
