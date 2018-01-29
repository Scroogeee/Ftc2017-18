package org.firstinspires.ftc.teamcode.autonomous.instructions;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.hardware.CRServo;

import org.firstinspires.ftc.teamcode.DriverControlled.Constants;
import org.firstinspires.ftc.teamcode.autonomous.AutonomousCore;

/**
 * Created by FTC on 25.01.2018.
 */

@Autonomous(name = "autoBL", group = "test")
public class AutonomousBlueLong extends AutonomousCore {

	/**
	 * Glyph Servo
	 */
	CRServo glyph_servo;

	@Override
	public void runOpMode() throws InterruptedException {
		initialize();
		glyph_servo.setPower(1);
		waitForStart();

		//VOR,RECHTS,VOR,LINKS,VOR


		//VOR
		drive.driveByPulses(2500, -1, 1, 1, -1);
		sleep(1000);
		//RECHTS
		drive.driveByPulses(1500, -1, -1, -1, -1);
		sleep(1000);
		//VOR
		drive.driveByPulses(1600, -1, 1, 1, -1);
		sleep(1000);
		//LINKS
		drive.driveByPulses(1500, 1, 1, 1, 1);
		sleep(1000);
		//VOR
		drive.driveByPulses(1000, -1, 1, 1, -1);
		sleep(1000);
		glyph_servo.setPower(-1);
		sleep(1000);
		//ZURÜCK
		drive.driveByPulses(300, 1, -1, -1, 1);
	}

	protected void initialize() {
		super.initialize();
		glyph_servo = hardwareMap.crservo.get(Constants.servoGlyph_name);
	}
}
