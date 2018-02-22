package org.firstinspires.ftc.teamcode.autonomous;

import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

/**
 * Created by FTC on 21.02.2018.
 */
@Autonomous(name = "JewelTest", group = "test")
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
