package org.firstinspires.ftc.teamcode.autonomous.tests;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

import org.firstinspires.ftc.teamcode.autonomous.AutonomousCore;
import org.firstinspires.ftc.teamcode.autonomous.jewels.AutoJewelControl;

/**
 * Created by FTC on 21.02.2018.
 */
@Autonomous(name = "JewelTest", group = "Test")
public class AutoJewelSetup extends AutonomousCore {

	private AutoJewelControl jewelcontrol = new AutoJewelControl();

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
		jewelcontrol.initialize(this);
	}
}
