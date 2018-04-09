package org.firstinspires.ftc.teamcode.autonomous.routines;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.hardware.DcMotorSimple;

import org.firstinspires.ftc.teamcode.autonomous.VuMarkAutonomous;
import org.firstinspires.ftc.teamcode.autonomous.autoRobot.autoRobotModules.autoJewels.JewelColor;

import static org.firstinspires.ftc.teamcode.autonomous.autoRobot.autoDriving.W4StraightByColor.TURN_SPEED;

/**
 * Created by FTC on 25.01.2018.
 */

@Autonomous(name = "autoRL", group = "drive")
public class AutonomousRedLong extends VuMarkAutonomous {

	@Override
	protected void routine() {
		//Jewels
		kickJewel(JewelColor.BLUE);
		//VuMark
		if (drive.isRangeUsed()) {
			detectedVuMark = scanWithTurn();
		}

		//VOR,LINKS,VOR,RECHTS,VOR

		//VOR
		drive.driveByPulses(2500, -1, 1);
		sleep(300);
		//LINKS
		if (!drive.isGyroUsed()) {
			drive.driveByPulses(1400, 1, 1);
		} else {
			drive.gyroHold(TURN_SPEED, 90, 0.5);
			drive.gyroTurn(TURN_SPEED, 90);

		}
		sleep(300);
		//VOR
		if (!drive.isRangeUsed()) {
			drive.driveByPulses(1250, -1, 1);
		} else {
			drive.driveToColumnByRange(VuMarkToInt(detectedVuMark), DcMotorSimple.Direction.FORWARD);
		}
		sleep(300);
		//RECHTS
		if (!drive.isGyroUsed()) {
			drive.driveByPulses(1700, -1, -1);
		} else {
			drive.gyroHold(TURN_SPEED, 0, 0.5);
			drive.gyroTurn(TURN_SPEED, 0);
		}
		sleep(300);
		//VOR
		drive.driveByPulses(1000, -1, 1);
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
