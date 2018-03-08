package org.firstinspires.ftc.teamcode.autonomous.routines;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

import org.firstinspires.ftc.teamcode.autonomous.AutonomousCore;
import org.firstinspires.ftc.teamcode.autonomous.JewelColor;

/**
 * Created by FTC on 25.01.2018.
 */

@Autonomous(name = "autoRS", group = "drive")
public class AutonomousRedShort extends AutonomousCore {


	@Override
	public void runOpMode() throws InterruptedException {
		initialize();
		glyph_servo.setPower(1);
		waitForStart();

		//Relic ein Stück hochfahren
		relicControl.update(1, 0, 0);
		sleep(300);
		relicControl.update(0, 0, 0);

		//Jewels
		kickJewel(JewelColor.BLUE);

		//VOR,RECHTS,VOR

		//VOR
		drive.driveByPulses(3200, -1, 1, 1, -1);
		sleep(300);
		//RECHTS
		drive.driveByPulses(1900, -1, -1, -1, -1);
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
