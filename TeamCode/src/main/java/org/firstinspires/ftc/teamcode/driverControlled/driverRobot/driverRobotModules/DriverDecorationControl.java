package org.firstinspires.ftc.teamcode.driverControlled.driverRobot.driverRobotModules;

import com.qualcomm.robotcore.hardware.CRServo;
import org.firstinspires.ftc.teamcode.driverControlled.DriverCore;
import org.firstinspires.ftc.teamcode.util.Constants;

/**
 * Created by FTC on 10.04.2018.
 */

public class DriverDecorationControl {
	private CRServo frog_servo;
	private DriverCore core_Ref;

	public void initialize(DriverCore driverCore) {
		core_Ref = driverCore;
		frog_servo = core_Ref.hardwareMap.crservo.get(Constants.servoFrog_name);
	}

	public void update() {
		if (core_Ref.gamepad1.right_bumper) {
			//on
			frog_servo.setPower(1);
		} else if (core_Ref.gamepad1.left_bumper) {
			//off
			frog_servo.setPower(0);
		}
	}
}
