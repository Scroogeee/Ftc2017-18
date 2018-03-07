package org.firstinspires.ftc.teamcode.autonomous.routines;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

import org.firstinspires.ftc.teamcode.autonomous.AutonomousCore;

/**
 * Created by FTC on 25.01.2018.
 */

@Autonomous(name = "autoRL", group = "drive")
public class AutonomousRedLong extends AutonomousCore {

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
		telemetry.addLine(currentJewelColor.toString());
		sleep(1000);
		switch (currentJewelColor) {
			case RED:
				drive.driveByPulses(350, -1, -1, -1, -1);
				sleep(200);
				jewelControl.updateArm(1);
				sleep(1000);
				drive.driveByPulses(350, 1, 1, 1, 1);
				break;
			case BLUE:
				drive.driveByPulses(350, 1, 1, 1, 1);
				sleep(200);
				jewelControl.updateArm(1);
				sleep(1000);
				drive.driveByPulses(350, -1, -1, -1, -1);
				break;
			case NONE:
				jewelControl.updateArm(1);
				sleep(1000);
				break;
		}
		sleep(1000);
		jewelControl.updateArm(1);
		telemetry.addLine(currentJewelColor.toString());
		//VOR,LINKS,VOR,RECHTS,VOR

		//VOR
		drive.driveByPulses(2500, -1, 1, 1, -1);
		sleep(300);
		//LINKS
		drive.driveByPulses(1400, 1, 1, 1, 1);
		sleep(300);
		//VOR
		drive.driveByPulses(1250, -1, 1, 1, -1);
		sleep(300);
		//RECHTS
		drive.driveByPulses(1700, -1, -1, -1, -1);
		sleep(300);
		//VOR
		drive.driveByPulses(1000, -1, 1, 1, -1);
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
