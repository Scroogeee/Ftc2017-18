package org.firstinspires.ftc.teamcode.autonomous.routines;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

import org.firstinspires.ftc.teamcode.autonomous.AutonomousCore;
import org.firstinspires.ftc.teamcode.autonomous.JewelColor;

/**
 * Created by FTC on 23.02.2018.
 */

@Autonomous(name = "BlueJewel", group = "jewelAlternate")
public class JewelOnlyBlue extends AutonomousCore {
	@Override
	public void runOpMode() throws InterruptedException {
		initialize();
		waitForStart();
		kickJewel(JewelColor.RED);
	}

	@Override
	protected void initialize() {
		super.initialize();
	}
}
