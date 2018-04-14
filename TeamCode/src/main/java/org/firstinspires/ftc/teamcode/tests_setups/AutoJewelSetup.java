package org.firstinspires.ftc.teamcode.tests_setups;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;

import org.firstinspires.ftc.teamcode.autonomous.AutonomousCore;
import org.firstinspires.ftc.teamcode.autonomous.autoRobot.autoRobotModules.JewelControl;

/**
 * Created by FTC on 21.02.2018.
 */
@Disabled
@Autonomous(name = "JewelTest", group = "Test")
public class AutoJewelSetup extends AutonomousCore {

	private final JewelControl jewelcontrol = new JewelControl();

	@Override
	public void runOpMode() {
		initialize();
		waitForStart();
		while (opModeIsActive()) {
			if (jewelcontrol.getColor() != null) {
				telemetry.addLine(jewelcontrol.getColor().toString());
			}
			telemetry.update();
		}
		textToSpeech.shutdown();
	}

	@Override
	protected void routine() {

	}

	/**
	 * initializes the JewelSetup class
	 */
	public void initialize() {
		super.initialize();
		jewelcontrol.initialize(this.hardwareMap);
	}
}
