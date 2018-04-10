package org.firstinspires.ftc.teamcode.autonomous.routines;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import org.firstinspires.ftc.teamcode.autonomous.VuMarkAutonomous;
import org.firstinspires.ftc.teamcode.autonomous.autoRobot.autoRobotModules.autoJewels.JewelColor;

import static org.firstinspires.ftc.teamcode.autonomous.autoRobot.autoDriving.W4StraightByColor.TURN_SPEED;

/**
 * Created by FTC on 25.01.2018.
 */

@Autonomous(name = "autoRS", group = "drive")
public class AutonomousRedShort extends VuMarkAutonomous {

	@Override
	protected void routine() {
		//Jewels
		kickJewel(JewelColor.BLUE);
		//VuMark
		if (drive.isRangeUsed()) {
			detectedVuMark = scanWithTurn();
		}
		//VOR,RECHTS,VOR

		//VOR
		if (!drive.isRangeUsed()) {
			drive.driveByPulses(3200, -1, 1);
		} else {
			drive.driveToColumnByRange(VuMarkToInt(detectedVuMark), DcMotorSimple.Direction.FORWARD);
		}
		sleep(300);
		//RECHTS
		if (!drive.isGyroUsed()) {
			drive.driveByPulses(1800, -1, -1);
		} else {
			drive.gyroTurn(TURN_SPEED, -90);
			drive.gyroHold(TURN_SPEED, -90, 1);
		}
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

}
