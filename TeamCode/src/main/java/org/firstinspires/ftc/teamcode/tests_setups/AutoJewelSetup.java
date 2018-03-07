package org.firstinspires.ftc.teamcode.tests_setups;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

import org.firstinspires.ftc.teamcode.autonomous.AutonomousCore;
import org.firstinspires.ftc.teamcode.robotModules.JewelControl;

/**
 * Created by FTC on 21.02.2018.
 */
@Autonomous(name = "JewelTest", group = "Test")
public class AutoJewelSetup extends AutonomousCore {

	private JewelControl jewelcontrol = new JewelControl();

	@Override
	public void runOpMode() throws InterruptedException {
		initialize();
		waitForStart();
		while (opModeIsActive()) {
			if (jewelcontrol.getColor() != null) {
				telemetry.addLine(jewelcontrol.getColor().toString());
			}
			telemetry.update();
		}
	}

	public void initialize() {
		super.initialize();
		jewelcontrol.initialize(this.hardwareMap);
	}
}
