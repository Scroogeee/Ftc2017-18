package org.firstinspires.ftc.teamcode.autonomous.routines;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.hardware.DcMotorSimple;

import org.firstinspires.ftc.robotcore.external.navigation.RelicRecoveryVuMark;
import org.firstinspires.ftc.teamcode.autonomous.VuMarkAutonomous;
import org.firstinspires.ftc.teamcode.util.autoChoices.AllianceColor;

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
	}
}
