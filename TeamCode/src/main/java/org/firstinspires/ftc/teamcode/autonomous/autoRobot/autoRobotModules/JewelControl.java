package org.firstinspires.ftc.teamcode.autonomous.autoRobot.autoRobotModules;

import com.qualcomm.robotcore.hardware.ColorSensor;
import com.qualcomm.robotcore.hardware.Gamepad;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.teamcode.util.enums.AllianceColor;

import static org.firstinspires.ftc.teamcode.util.Constants.jewelArm_down;
import static org.firstinspires.ftc.teamcode.util.Constants.jewelArm_name;
import static org.firstinspires.ftc.teamcode.util.Constants.jewelArm_up;
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
	 *
	 * @param hwMap the <code>HardwareMap</code> to use
	 */
	public void initialize(HardwareMap hwMap) {
		hardwareMap = hwMap;
		jewel_arm = hardwareMap.servo.get(jewelArm_name);
		jewel_sensor = hardwareMap.colorSensor.get(jewelSensor_name);
		jewel_arm.setDirection(Servo.Direction.FORWARD);
		jewel_arm.setPosition(jewelArm_up);
		jewel_sensor.enableLed(true);
	}

	/**
	 * Updates the Servo position to the given values
	 *
	 * @param position the new position of the jewelArm
	 */
	public void updateArm(double position) {
		position = Math.min(position, 1);
		position = Math.max(position, 0);
		jewel_arm.setPosition(position);
	}

	/**
	 * Returns the current jewel color which is visible
	 *
	 * @return the current jewel color which is visible <br>
	 * (<code>null</code> if none detected)
	 */
	public AllianceColor getColor() {
		if (jewel_sensor.blue() > minimum_ConfidenceLevel) {
			return AllianceColor.BLUE;
		} else if (jewel_sensor.red() > minimum_ConfidenceLevel) {
			return AllianceColor.RED;
		}
		return AllianceColor.NONE;
	}

	public void update(Gamepad gamepad) {
		if (gamepad.dpad_up) {
			updateArm(jewelArm_up);
		} else if (gamepad.dpad_down) {
			updateArm(jewelArm_down);
		}
	}
}
