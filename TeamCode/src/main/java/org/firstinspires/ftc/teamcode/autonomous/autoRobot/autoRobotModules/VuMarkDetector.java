package org.firstinspires.ftc.teamcode.autonomous.autoRobot.autoRobotModules;

import com.qualcomm.robotcore.hardware.HardwareMap;
import org.firstinspires.ftc.robotcore.external.ClassFactory;
import org.firstinspires.ftc.robotcore.external.navigation.*;
import org.firstinspires.ftc.teamcode.autonomous.AutonomousCore;

/**
 * Created by FTC on 20.03.2018.
 */

public class VuMarkDetector {

	/**
	 * {@link #vuforia} is the variable we will use to store our instance of the Vuforia
	 * localization engine.
	 */
	VuforiaLocalizer vuforia;
	VuforiaTrackables relicTrackables;
	VuforiaTrackable relicTemplate;
	private HardwareMap hwMap;
	private AutonomousCore autonomousCore;

	public void initialize(AutonomousCore p_autonomousCore) {
		autonomousCore = p_autonomousCore;
		hwMap = autonomousCore.hardwareMap;

		/*
		 * To start up Vuforia, tell it the view that we wish to use for camera monitor (on the RC phone);
		 * If no camera monitor is desired, use the parameterless constructor instead (commented out below).
		 */
		int cameraMonitorViewId = hwMap.appContext.getResources().
				getIdentifier("cameraMonitorViewId", "id", hwMap.appContext.getPackageName());
		VuforiaLocalizer.Parameters parameters = new VuforiaLocalizer.Parameters(cameraMonitorViewId);

		// OR...  Do Not Activate the Camera Monitor View, to save power
		// VuforiaLocalizer.Parameters parameters = new VuforiaLocalizer.Parameters();

		/*
		 * IMPORTANT: You need to obtain your own license key to use Vuforia. The string below with which
		 * 'parameters.vuforiaLicenseKey' is initialized is for illustration only, and will not function.
		 * A Vuforia 'Development' license key, can be obtained free of charge from the Vuforia developer
		 * web site at https://developer.vuforia.com/license-manager.
		 *
		 * Vuforia license keys are always 380 characters long, and look as if they contain mostly
		 * random data. As an example, here is a example of a fragment of a valid key:
		 *      ... yIgIzTqZ4mWjk9wd3cZO9T1axEqzuhxoGlfOOI2dRzKS4T0hQ8kT ...
		 * Once you've obtained a license key, copy the string from the Vuforia web site
		 * and paste it in to your code onthe next line, between the double quotes.
		 */
		parameters.vuforiaLicenseKey = "AVCgnev/////AAAAGf7IKNhdQUcHpgHfiW0F0vgq22eZn7vIEsbBU4hn913gvPfChbo" +
				"ZW1P1UvSco1ML3LtyAJwhzDxPdnc1Q+6v7P0fePe3yARyzcy6dm4pH038lj48SAzW2+I+9tXwQ3R0wpGeiF2DRPAFed" +
				"HFWLMGscCMZdrEhacBYDgfNDgOEUvnr96wMIFT8ds+3/Lxeg9S47ATqjV2G7+yBzB80rs1oDxm5Jt469AR6SAZ6Z/1M" +
				"HALq9NFB9jEXjw5mC7YVInIF/Xhr3rBLof+NgEh3RMnthx4IzBaQvxkJ+1Gwxm3JRwSkCosMtO12Cy+PY5SCTeE+3tDnY" +
				"9DUgWJ/8GT3NNiBL3dEAk6vJKZ5LYMFSfFyFSd";

		/*
		 * We also indicate which camera on the RC that we wish to use.
		 * Here we chose the back (HiRes) camera (for greater range), but
		 * for a competition robot, the front camera might be more convenient.
		 */
		parameters.cameraDirection = VuforiaLocalizer.CameraDirection.FRONT;
		this.vuforia = ClassFactory.createVuforiaLocalizer(parameters);

		/**
		 * Load the data set containing the VuMarks for Relic Recovery. There's only one trackable
		 * in this data set: all three of the VuMarks in the game were created from this one template,
		 * but differ in their instance id information.
		 * @see VuMarkInstanceId
		 */
		relicTrackables = this.vuforia.loadTrackablesFromAsset("RelicVuMark");
		relicTemplate = relicTrackables.get(0);
		relicTemplate.setName("relicVuMarkTemplate"); // can help in debugging; otherwise not necessary

	}


	/**
	 * Scans for vuMarks but returns null if none visible
	 */
	public RelicRecoveryVuMark scan() {
		relicTrackables.activate();
		/**
		 * See if any of the instances of {@link relicTemplate} are currently visible.
		 * {@link RelicRecoveryVuMark} is an enum which can have the following values:
		 * UNKNOWN, LEFT, CENTER, and RIGHT. When a VuMark is visible, something other than
		 * UNKNOWN will be returned by {@link RelicRecoveryVuMark#from(VuforiaTrackable)}.
		 */
		RelicRecoveryVuMark vuMark = RelicRecoveryVuMark.from(relicTemplate);
		relicTrackables.deactivate();
		return vuMark;

	}

}
