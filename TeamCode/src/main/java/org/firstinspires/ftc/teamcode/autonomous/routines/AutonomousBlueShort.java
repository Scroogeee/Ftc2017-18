package org.firstinspires.ftc.teamcode.autonomous.routines;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

import org.firstinspires.ftc.teamcode.autonomous.VuMarkAutonomous;
import org.firstinspires.ftc.teamcode.autonomous.autoRobotModules.autoJewels.JewelColor;

/**
 * Created by FTC on 25.01.2018.
 */

@Autonomous(name = "autoBS", group = "drive")
public class AutonomousBlueShort extends VuMarkAutonomous {

	@Override
	public void runOpMode() throws InterruptedException {
		initialize();
		waitForStart();

		//Relic ein Stück hochfahren
		relicControl.update(1, 0, 0);
		sleep(300);
		relicControl.update(0, 0, 0);

		//Jewels herunter kicken
		kickJewel(JewelColor.RED);

		//ZURÜCK,RECHTS,VOR

		//ZURÜCK
		drive.driveByPulses(3000, 1, -1);
		sleep(300);
		//RECHTS
		drive.driveByPulses(1700, -1, -1);
		sleep(300);
		//VOR
		drive.driveByPulses(1500, -1, 1);
		sleep(300);
		glyph_servo.setPower(-1);
		sleep(1200);
		//ZURÜCK
		drive.driveByPulses(300, 1, -1);
		sleep(300);
		//VOR
		drive.driveByPulses(300, -1, 1);
		sleep(300);
		//ZURÜCK
		drive.driveByPulses(300, 1, -1);
		//textToSpeech.shutdown();
	}

	protected void initialize() {
		super.initialize();
	}
}
