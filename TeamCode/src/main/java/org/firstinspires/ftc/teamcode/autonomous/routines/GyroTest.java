package org.firstinspires.ftc.teamcode.autonomous.routines;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import org.firstinspires.ftc.teamcode.autonomous.VuMarkAutonomous;

import static org.firstinspires.ftc.teamcode.autonomous.autoRobot.autoDriving.W4StraightByColor.TURN_SPEED;

/**
 * Created by FTC on 09.04.2018.
 */
@Autonomous(name = "GyroRoutine", group = "test")
public class GyroTest extends VuMarkAutonomous {
	@Override
	protected void routine() {
		drive.driveByPulses(0, 0, 0);
		if (drive.isGyroUsed()) {
			drive.gyroTurn(TURN_SPEED, -90);
			drive.gyroHold(TURN_SPEED, -90, 1);
		}
	}
}
