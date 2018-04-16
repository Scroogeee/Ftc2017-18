package org.firstinspires.ftc.teamcode.autonomous.routines;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.hardware.DcMotorSimple;

import org.firstinspires.ftc.robotcore.external.navigation.RelicRecoveryVuMark;
import org.firstinspires.ftc.teamcode.autonomous.VuMarkAutonomous;
import org.firstinspires.ftc.teamcode.autonomous.autoRobot.autoDriving.W4StraightByColor;
import org.firstinspires.ftc.teamcode.util.enums.AllianceColor;

/**
 * Created by FTC on 13.04.2018.
 */
@Autonomous(name = "driveToCrypto", group = "test")
public class DriveToCrypto extends VuMarkAutonomous {
	@Override
	protected void routine() {
		if (drive.isRangeUsed()) {
			drive.driveToColumnByRange(VuMarkToInt(RelicRecoveryVuMark.CENTER, AllianceColor.RED),
					DcMotorSimple.Direction.FORWARD);
		}
		if (drive.isGyroUsed()) {
			drive.gyroTurn(W4StraightByColor.TURN_SPEED, -90);
			drive.gyroHold(W4StraightByColor.TURN_SPEED, -90, 0.5);
		}
	}
}
