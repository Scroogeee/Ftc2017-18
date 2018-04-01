package org.firstinspires.ftc.teamcode.autonomous.routines;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import org.firstinspires.ftc.teamcode.autonomous.VuMarkAutonomous;
import org.firstinspires.ftc.teamcode.autonomous.autoRobot.autoRobotModules.autoJewels.JewelColor;

/**
 * Created by FTC on 25.01.2018.
 */

@Autonomous(name = "autoBL", group = "drive")
public class AutonomousBlueLong extends VuMarkAutonomous {

	@Override
	protected void routine() {
		//Jewels herunter kicken
		kickJewel(JewelColor.RED);
		//VuMark
		detectedVuMark = scanWithTurn();

		//ZURÜCK,LINKS,VOR,LINKS,VOR

		//ZURÜCK
		drive.driveByPulses(2500, 1, -1);
		sleep(300);
		//RECHTS
		drive.driveByPulses(1400, -1, -1);
		sleep(300);
		//ZURÜCK
		if (!drive.isRangeUsed()) {
			drive.driveByPulses(1250, 1, -1);
		} else {
			drive.driveToColumnByRange(VuMarkToInt(detectedVuMark), DcMotorSimple.Direction.REVERSE);
		}
		sleep(300);
		//RECHTS
		drive.driveByPulses(1600, -1, -1);
		sleep(300);
		//VOR
		drive.driveByPulses(800, -1, 1);
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

}
