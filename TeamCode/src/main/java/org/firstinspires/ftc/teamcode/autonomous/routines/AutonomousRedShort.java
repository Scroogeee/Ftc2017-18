package org.firstinspires.ftc.teamcode.autonomous.routines;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import org.firstinspires.ftc.teamcode.autonomous.AutonomousStrategy;
import org.firstinspires.ftc.teamcode.autonomous.VuMarkAutonomous;
import org.firstinspires.ftc.teamcode.autonomous.autoRobot.autoDriving.W4StraightByColor;
import org.firstinspires.ftc.teamcode.autonomous.autoRobot.autoRobotModules.autoJewels.JewelColor;

import static org.firstinspires.ftc.teamcode.autonomous.autoRobot.autoDriving.W4StraightByColor.TURN_SPEED;

/**
 * Created by FTC on 25.01.2018.
 */

@Autonomous(name = "autoRS", group = "drive")
@Disabled
public class AutonomousRedShort {

	private VuMarkAutonomous vuMarkAutonomous;
	private W4StraightByColor drive = vuMarkAutonomous.getDrive();

	public AutonomousRedShort(VuMarkAutonomous param_vuMarkAutonomous) {
		this.vuMarkAutonomous = param_vuMarkAutonomous;
	}

	public void routine(AutonomousStrategy strategy) {
		if (strategy.doJewel()) {
			//Jewels
			vuMarkAutonomous.kickJewel(JewelColor.BLUE);
		}
		if (strategy.doCrypto()) {
			//VuMark
			if (drive.isRangeUsed()) {
				vuMarkAutonomous.detectedVuMark = vuMarkAutonomous.scanWithTurn();
			}

			//VOR,RECHTS,VOR

			//VOR
			if (!drive.isRangeUsed()) {
				drive.driveByPulses(3200, -1, 1);
			} else {
				drive.driveToColumnByRange(vuMarkAutonomous.VuMarkToInt(vuMarkAutonomous.getDetectedVuMark()),
						DcMotorSimple.Direction.FORWARD);
			}

			vuMarkAutonomous.sleep(300);

			//RECHTS
			if (!drive.isGyroUsed()) {
				drive.driveByPulses(1800, -1, -1);
			} else {
				drive.gyroTurn(TURN_SPEED, -90);
				drive.gyroHold(TURN_SPEED, -90, 1);
			}

			vuMarkAutonomous.sleep(300);
			//VOR
			drive.driveByPulses(1500, -1, 1);
			vuMarkAutonomous.sleep(300);
			//release glyph
			vuMarkAutonomous.glyph_servo.setPower(-1);
			vuMarkAutonomous.sleep(1200);
			//ZURÜCK
			drive.driveByPulses(300, 1, -1);
			vuMarkAutonomous.sleep(300);
			//VOR
			drive.driveByPulses(300, -1, 1);
			vuMarkAutonomous.sleep(300);
			//ZURÜCK
			drive.driveByPulses(300, 1, -1);
			//textToSpeech.shutdown();

		}

	}
}
