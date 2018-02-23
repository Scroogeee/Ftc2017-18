package org.firstinspires.ftc.teamcode.autonomous.routines;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

import org.firstinspires.ftc.teamcode.autonomous.AutonomousCore;

/**
 * Created by FTC on 25.01.2018.
 */

@Autonomous(name = "autoBS", group = "drive")
public class AutonomousBlueShort extends AutonomousCore {

	@Override
	public void runOpMode() throws InterruptedException {
		initialize();
		glyph_servo.setPower(1);
		waitForStart();

		//Relic ein Stück hochfahren
		relicControl.update(1, 0, 0);
		sleep(300);
		relicControl.update(0, 0, 0);

		//Jewels herunter kicken
		jewelControl.updateArm(1);
		sleep(1000);
		jewelControl.updateArm(0);
		currentJewelColor = jewelControl.getColor();
		switch (currentJewelColor) {
			case RED:
				drive.driveByPulses(300, -1, 1, 1, -1);
				sleep(200);
				drive.driveByPulses(300, 1, -1, -1, 1);
				break;
			case BLUE:
				drive.driveByPulses(300, 1, -1, -1, 1);
				sleep(200);
				drive.driveByPulses(300, -1, 1, 1, -1);
				break;
			case NONE:

				break;
		}
		jewelControl.updateArm(-1);
		sleep(1000);
		jewelControl.updateArm(0);

		//VOR,LINKS,VOR

		//VOR
		drive.driveByPulses(3300, -1, 1, 1, -1);
		sleep(1000);
		//LINKS
		drive.driveByPulses(1500, 1, 1, 1, 1);
		sleep(1000);
		//VOR
		drive.driveByPulses(1300, -1, 1, 1, -1);
		sleep(1000);
		glyph_servo.setPower(-1);
		sleep(1000);
		//ZURÜCK
		drive.driveByPulses(300, 1, -1, -1, 1);
	}

	protected void initialize() {
		super.initialize();
	}
}
