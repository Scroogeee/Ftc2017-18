package org.firstinspires.ftc.teamcode.autonomous;

import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.ColorSensor;

import org.firstinspires.ftc.teamcode.DriverControlled.Constants;

/**
 * Created by FTC on 21.02.2018.
 */

public class AutoJewelControl {

	private ColorSensor jewel_sensor;

	private CRServo jewel_arm;

	private AutonomousCore coreRef;

	/**
	 * Initializes the jewel control class
	 */
	public void initialize(AutonomousCore p_mainRef) {
		coreRef = p_mainRef;
		jewel_arm = coreRef.hardwareMap.crservo.get(Constants.jewelArm_name);
		jewel_sensor = coreRef.hardwareMap.colorSensor.get(Constants.jewelSensor_name);
		jewel_arm.setPower(0);
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
