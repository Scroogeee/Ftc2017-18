package org.firstinspires.ftc.teamcode.autonomous;

import com.qualcomm.robotcore.util.ElapsedTime;
import org.firstinspires.ftc.robotcore.external.navigation.RelicRecoveryVuMark;
import org.firstinspires.ftc.teamcode.autonomous.autoRobot.autoRobotModules.VuMarkDetector;

import static org.firstinspires.ftc.teamcode.Constants.MAX_SCAN_TIME_SECONDS;
import static org.firstinspires.ftc.teamcode.autonomous.autoRobot.autoDriving.W4StraightByColor.TURN_SPEED;

/**
 * Created by FTC on 20.03.2018.
 */

public class VuMarkAutonomous extends AutonomousCore {

	protected RelicRecoveryVuMark detectedVuMark = RelicRecoveryVuMark.UNKNOWN;
	private ElapsedTime elapsedTime = new ElapsedTime();
	private VuMarkDetector vuMarkDetector = new VuMarkDetector();

	@Override
	public void runOpMode() {
		initialize();
		waitForStart();
		resetTimer();
		upRelic();
		routine();
	}

	@Override
	protected void initialize() {
		super.initialize();
		vuMarkDetector.initialize(this);
	}

	/**
	 * Resets the timer for the scan
	 */
	protected void resetTimer() {
		elapsedTime.reset();
	}

	/**
	 * Scans until either a VuMark is visible, the OpMode is stopped or the Timer reaches 10 seconds
	 */
	protected RelicRecoveryVuMark scanVuMark() {
		RelicRecoveryVuMark vuMark = vuMarkDetector.scan();
		resetTimer();
		while (vuMark == RelicRecoveryVuMark.UNKNOWN && !isStopRequested() &&
				elapsedTime.seconds() < MAX_SCAN_TIME_SECONDS) {
			vuMark = vuMarkDetector.scan();
		}
		return vuMark;
	}

	/**
	 * Scans for a VuMark once
	 */
	protected RelicRecoveryVuMark scanGeneral() {
		RelicRecoveryVuMark vuMark = vuMarkDetector.scan();
		return vuMark;
	}

	/**
	 * Scan with turning (As in competition)
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

}
