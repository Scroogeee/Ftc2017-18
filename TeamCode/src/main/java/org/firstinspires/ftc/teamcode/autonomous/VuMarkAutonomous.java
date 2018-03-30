package org.firstinspires.ftc.teamcode.autonomous;

import org.firstinspires.ftc.robotcore.external.navigation.RelicRecoveryVuMark;
import org.firstinspires.ftc.teamcode.autonomous.autoRobot.autoRobotModules.VuMarkDetector;

/**
 * Created by FTC on 20.03.2018.
 */

public class VuMarkAutonomous extends AutonomousCore {

	protected VuMarkDetector vuMarkDetector = new VuMarkDetector();

	@Override
	protected void initialize() {
		super.initialize();
		vuMarkDetector.initialize(this);
	}

	/**
	 * Scans until a VuMark is visible
	 */
	protected RelicRecoveryVuMark scanVuMark() {
		RelicRecoveryVuMark vuMark = vuMarkDetector.scan();
		while (vuMark == RelicRecoveryVuMark.UNKNOWN) {
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

}
