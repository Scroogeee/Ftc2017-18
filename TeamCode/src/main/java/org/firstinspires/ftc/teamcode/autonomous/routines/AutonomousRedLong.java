package org.firstinspires.ftc.teamcode.autonomous.routines;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

import org.firstinspires.ftc.teamcode.autonomous.VuMarkAutonomous;
import org.firstinspires.ftc.teamcode.autonomous.autoRobotModules.autoJewels.JewelColor;

/**
 * Created by FTC on 25.01.2018.
 */

@Autonomous(name = "autoRL", group = "drive")
public class AutonomousRedLong extends VuMarkAutonomous {

	@Override
	public void runOpMode() throws InterruptedException {
		initialize();
		waitForStart();

		//Relic ein Stück hochfahren
		relicControl.update(1, 0, 0);
		sleep(300);
		relicControl.update(0, 0, 0);

		//Jewels
		kickJewel(JewelColor.BLUE);

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
		//textToSpeech.shutdown();
	}

	protected void initialize() {
		super.initialize();
	}
}
