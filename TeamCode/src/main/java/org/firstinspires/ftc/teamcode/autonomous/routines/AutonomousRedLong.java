package org.firstinspires.ftc.teamcode.autonomous.routines;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.hardware.DcMotorSimple;

import org.firstinspires.ftc.teamcode.autonomous.VuMarkAutonomous;
import org.firstinspires.ftc.teamcode.util.autoChoices.AllianceColor;

import static org.firstinspires.ftc.teamcode.autonomous.autoRobot.autoDriving.W4StraightByColor.TURN_SPEED;

/**
 * Created by FTC on 25.01.2018.
 */

@Autonomous(name = "autoRL", group = "drive")
public class AutonomousRedLong extends VuMarkAutonomous {

	@Override
	protected void routine() {
		alliance = AllianceColor.RED;
		opponent = AllianceColor.BLUE;
		//Jewels
		kickJewel(opponent);
		//reposition
		if (drive.isGyroUsed()) {
			drive.gyroTurn(TURN_SPEED, 0);
			drive.gyroHold(TURN_SPEED, 0, 1);
		}
		//VuMark
		if (drive.isRangeUsed()) {
			drive.driveByPulses(500, -1, 1);
			detectedVuMark = scanVuMark();
		}

		//VOR,LINKS,VOR,RECHTS,VOR

		//VOR
		drive.driveByPulses(2500, -1, 1);
		sleep(300);
		//LINKS
		if (!drive.isGyroUsed()) {
			drive.driveByPulses(1400, 1, 1);
		} else {
			drive.gyroHold(TURN_SPEED, 90, 1);
			drive.gyroTurn(TURN_SPEED, 90);

		}
		sleep(300);
		//VOR
		if (!drive.isRangeUsed()) {
			drive.driveByPulses(1250, -1, 1);
		} else {
			drive.driveToColumnByRange(VuMarkToInt(detectedVuMark, alliance),
					DcMotorSimple.Direction.FORWARD);
		}
		sleep(300);
		//RECHTS
		if (!drive.isGyroUsed()) {
			drive.driveByPulses(1700, -1, -1);
		} else {
			drive.gyroHold(TURN_SPEED, 0, 1);
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
