package org.firstinspires.ftc.teamcode.autonomous;

import org.firstinspires.ftc.teamcode.driverControlled.Constants;
import org.firstinspires.ftc.teamcode.driverControlled.JewelControl;

/**
 * Created by FTC on 21.02.2018.
 */

public class AutoJewelControl extends JewelControl {

	private AutonomousCore coreRef;

	/**
	 * Initializes the jewel control class
	 */
	public void initialize(AutonomousCore p_mainRef) {
		this.coreRef = p_mainRef;
		jewel_arm = coreRef.hardwareMap.servo.get(Constants.jewelArm_name);
		jewel_sensor = coreRef.hardwareMap.colorSensor.get(Constants.jewelSensor_name);
		jewel_arm.setPosition(1);
	}

}
