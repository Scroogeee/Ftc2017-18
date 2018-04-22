package org.firstinspires.ftc.teamcode.autonomous;

import org.firstinspires.ftc.robotcore.external.navigation.RelicRecoveryVuMark;
import org.firstinspires.ftc.teamcode.autonomous.autoRobot.autoRobotModules.VuMarkDetector;
import org.firstinspires.ftc.teamcode.util.enums.AllianceColor;

import static org.firstinspires.ftc.robotcore.external.navigation.RelicRecoveryVuMark.LEFT;
import static org.firstinspires.ftc.robotcore.external.navigation.RelicRecoveryVuMark.RIGHT;
import static org.firstinspires.ftc.teamcode.autonomous.autoRobot.autoDriving.W4StraightByColor.TURN_SPEED;
import static org.firstinspires.ftc.teamcode.util.Constants.MAX_SCAN_TIME_SECONDS;
import static org.firstinspires.ftc.teamcode.util.enums.AllianceColor.BLUE;
import static org.firstinspires.ftc.teamcode.util.enums.AllianceColor.RED;

/**
 * Created by FTC on 20.03.2018.
 */

public class VuMarkAutonomous extends AutonomousCore {

	protected RelicRecoveryVuMark detectedVuMark = RelicRecoveryVuMark.UNKNOWN;

	private VuMarkDetector vuMarkDetector = new VuMarkDetector();

	@Override
	protected void routine() {
		//empty routine
	}

	@Override
	protected void initialize() {
		super.initialize();
		vuMarkDetector.initialize(this);
	}

	/**
	 * Scans until either a VuMark is visible,
	 * the OpMode is stopped or the Timer reaches 10 seconds
	 * @return the scanned VuMark or null
	 */
	protected RelicRecoveryVuMark scanVuMark() {
		RelicRecoveryVuMark vuMark = scanOnce();
		elapsedTime.reset();
		while (vuMark == RelicRecoveryVuMark.UNKNOWN && !isStopRequested() &&
				elapsedTime.seconds() < MAX_SCAN_TIME_SECONDS) {
			vuMark = scanOnce();
		}
		return vuMark;
	}

	/**
	 * Scans for a VuMark once
	 * @return the recognized VuMark (if any)
	 */
	protected RelicRecoveryVuMark scanOnce() {
		RelicRecoveryVuMark vuMark = vuMarkDetector.scan();
		return vuMark;
	}

	/**
	 * Scan with turning
	 * @return the recognized VuMark
	 */
	protected RelicRecoveryVuMark scanWithTurn() {
		if (drive.isGyroUsed()) {
			drive.gyroTurn(TURN_SPEED, 30);
		} else {
			drive.driveByPulses(350, 1, 1);
		}
		RelicRecoveryVuMark vuMark = scanVuMark();
		if (drive.isGyroUsed()) {
			drive.gyroTurn(TURN_SPEED, 0);
		} else {
			drive.driveByPulses(350, -1, -1);
		}
		return vuMark;
	}

	protected int VuMarkToInt(RelicRecoveryVuMark v, AllianceColor allianceColor) {
		//TODO remove debugging
		telemetry.addData("VuMark: ", detectedVuMark.toString());
		telemetry.update();
		//default value is center
		int vuMarkNumber = 2;
		if ((v == LEFT && allianceColor == RED) || (v == RIGHT && allianceColor == BLUE)) {
			vuMarkNumber = 3;
		} else if ((v == LEFT && allianceColor == BLUE) || (v == RIGHT && allianceColor == RED)) {
			vuMarkNumber = 1;
		}
		return vuMarkNumber;
	}

}
