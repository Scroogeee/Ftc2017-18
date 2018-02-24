package org.firstinspires.ftc.teamcode.autonomous.routines;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

import org.firstinspires.ftc.teamcode.autonomous.AutonomousCore;

/**
 * Created by FTC on 23.02.2018.
 */

@Autonomous(name = "BlueJewel", group = "jewelAlternate")
public class JewelOnlyBlue extends AutonomousCore {
	@Override
	public void runOpMode() throws InterruptedException {
		initialize();
		jewelControl.updateArm(-1);
		sleep(1000);
		jewelControl.updateArm(0);
		currentJewelColor = jewelControl.getColor();
		telemetry.addLine(currentJewelColor.toString());
		sleep(1000);
		switch (currentJewelColor) {
			case RED:
				drive.driveByPulses(350, 1, 1, 1, 1);
				sleep(200);
				jewelControl.updateArm(1);
				sleep(1000);
				//jewelControl.updateArm(0);
				drive.driveByPulses(350, -1, -1, -1, -1);
				break;
			case BLUE:
				drive.driveByPulses(350, -1, -1, -1, -1);
				sleep(200);
				jewelControl.updateArm(1);
				sleep(1000);
				//jewelControl.updateArm(0);
				drive.driveByPulses(350, 1, 1, 1, 1);
				break;
			case NONE:
				jewelControl.updateArm(1);
				sleep(1000);
				//jewelControl.updateArm(0);
				break;
		}
	}

	@Override
	protected void initialize() {
		super.initialize();
	}

}