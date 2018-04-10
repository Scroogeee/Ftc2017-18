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

@Autonomous(name = "autoBL", group = "drive")
@Disabled
public class AutonomousBlueLong {

	private VuMarkAutonomous vuMarkAutonomous;
	private W4StraightByColor drive;

	public void initialize(VuMarkAutonomous param_vuMarkAutonomous) {
		this.vuMarkAutonomous = param_vuMarkAutonomous;
		drive = vuMarkAutonomous.getDrive();
	}

	public void routine(AutonomousStrategy strategy) {
		if (strategy.doJewel()) {
			//Jewels herunter kicken
			vuMarkAutonomous.kickJewel(JewelColor.RED);
		}
		if (strategy.doCrypto()) {
			if (drive.isRangeUsed()) {
				//VuMark
				vuMarkAutonomous.scanWithTurn();
			}

			//ZURÜCK,LINKS,VOR,LINKS,VOR


			//ZURÜCK
			drive.driveByPulses(2500, 1, -1);
			vuMarkAutonomous.sleep(300);

			//RECHTS
			if (!drive.isGyroUsed()) {
				drive.driveByPulses(1400, -1, -1);
			} else {
				drive.gyroTurn(TURN_SPEED, -90);
				drive.gyroHold(TURN_SPEED, -90, 1);
			}
			vuMarkAutonomous.sleep(300);

			//ZURÜCK
			if (!drive.isRangeUsed()) {
				drive.driveByPulses(1250, 1, -1);
			} else {
				drive.driveToColumnByRange(vuMarkAutonomous.VuMarkToInt(vuMarkAutonomous.getDetectedVuMark()),
						DcMotorSimple.Direction.REVERSE);
			}
			vuMarkAutonomous.sleep(300);

			//RECHTS
			if (!drive.isGyroUsed()) {
				drive.driveByPulses(1600, -1, -1);
			} else {
				drive.gyroTurn(TURN_SPEED, -180);
			}
			vuMarkAutonomous.sleep(300);

			//VOR
			drive.driveByPulses(1000, -1, 1);
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
