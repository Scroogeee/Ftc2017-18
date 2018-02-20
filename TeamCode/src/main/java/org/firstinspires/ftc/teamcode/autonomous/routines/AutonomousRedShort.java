package org.firstinspires.ftc.teamcode.autonomous.routines;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.hardware.CRServo;

import org.firstinspires.ftc.teamcode.DriverControlled.Constants;
import org.firstinspires.ftc.teamcode.autonomous.AutonomousCore;

/**
 * Created by FTC on 25.01.2018.
 */

@Autonomous(name = "autoRS", group = "test")
public class AutonomousRedShort extends AutonomousCore {

	/**
	 * Glyph Servo
	 */
	private CRServo glyph_servo;

	@Override
	public void runOpMode() throws InterruptedException {
		initialize();
		glyph_servo.setPower(1);
		waitForStart();

		//VOR,RECHTS,VOR

		//VOR
		drive.driveByPulses(3100, -1, 1, 1, -1);
		sleep(1000);
		//RECHTS
		drive.driveByPulses(1300, -1, -1, -1, -1);
		sleep(1000);
		//VOR
		drive.driveByPulses(1500, -1, 1, 1, -1);
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
