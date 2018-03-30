package org.firstinspires.ftc.teamcode.autonomous.routines.byPulses;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import org.firstinspires.ftc.teamcode.autonomous.VuMarkAutonomous;
import org.firstinspires.ftc.teamcode.autonomous.autoRobot.autoRobotModules.autoJewels.JewelColor;

/**
 * Created by FTC on 25.01.2018.
 */

@Autonomous(name = "autoBS", group = "drive")
public class AutonomousBlueShort extends VuMarkAutonomous {

	@Override
	public void runOpMode() {
		initialize();
		waitForStart();

		upRelic();

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