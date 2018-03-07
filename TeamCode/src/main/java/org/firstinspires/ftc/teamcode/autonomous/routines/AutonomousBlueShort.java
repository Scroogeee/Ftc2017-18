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
		jewelControl.updateArm(0.27);
		sleep(1000);
		currentJewelColor = jewelControl.getColor();
		sleep(1000);
		switch (currentJewelColor) {
			case RED:
				drive.driveByPulses(350, 1, 1, 1, 1);
				sleep(200);
				jewelControl.updateArm(1);
				sleep(1000);
				drive.driveByPulses(350, -1, -1, -1, -1);
				break;
			case BLUE:
				drive.driveByPulses(350, -1, -1, -1, -1);
				sleep(200);
				jewelControl.updateArm(1);
				sleep(1000);
				drive.driveByPulses(350, 1, 1, 1, 1);
				break;
			case NONE:
				jewelControl.updateArm(1);
				sleep(1000);
				break;
		}

		sleep(1000);
		jewelControl.updateArm(1);
		telemetry.addLine(currentJewelColor.toString());
		//ZURÜCK,RECHTS,VOR

		//ZURÜCK
		drive.driveByPulses(3000, 1, -1, -1, 1);
		sleep(300);
		//RECHTS
		drive.driveByPulses(1700, -1, -1, -1, -1);
		sleep(300);
		//VOR
		drive.driveByPulses(1500, -1, 1, 1, -1);
		sleep(300);
		glyph_servo.setPower(-1);
		sleep(1200);
		//ZURÜCK
		drive.driveByPulses(300, 1, -1, -1, 1);
		sleep(300);
		//VOR
		drive.driveByPulses(300, -1, 1, 1, -1);
		sleep(300);
		//ZURÜCK
		drive.driveByPulses(300, 1, -1, -1, 1);
	}

	protected void initialize() {
		super.initialize();
	}
}
