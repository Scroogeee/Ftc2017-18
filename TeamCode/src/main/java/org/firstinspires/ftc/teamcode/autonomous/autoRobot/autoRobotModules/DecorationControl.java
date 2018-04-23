package org.firstinspires.ftc.teamcode.autonomous.autoRobot.autoRobotModules;

import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.Gamepad;
import com.qualcomm.robotcore.hardware.HardwareMap;
import org.firstinspires.ftc.teamcode.util.Constants;

/**
 * Created by FTC on 10.04.2018.
 */

public class DecorationControl {
	private CRServo frog_servo;

	public void initialize(HardwareMap hwMap) {
		frog_servo = hwMap.crservo.get(Constants.servoFrog_name);
		updateServo(0);
	}

	public void update(Gamepad gamepad1) {
		if (gamepad1.right_bumper) {
			//on
			updateServo(1);
		} else if (gamepad1.left_bumper) {
			//off
			updateServo(0);
		}
	}

	public void updateServo(double power) {
		power = Math.min(power, 1);
		power = Math.max(power, -1);
		frog_servo.setPower(power);
	}
}
