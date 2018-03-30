package org.firstinspires.ftc.teamcode.autonomous.routines;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import org.firstinspires.ftc.teamcode.autonomous.AutonomousCore;
import org.firstinspires.ftc.teamcode.autonomous.autoRobot.autoRobotModules.autoJewels.JewelColor;

/**
 * Created by FTC on 23.02.2018.
 */

@Autonomous(name = "RedJewel", group = "jewelAlternate")
public class JewelOnlyRed extends AutonomousCore {
	@Override
	public void runOpMode() {
		initialize();
		waitForStart();
		upRelic();
		kickJewel(JewelColor.BLUE);
		//textToSpeech.shutdown();
	}

	@Override
	protected void initialize() {
		super.initialize();
	}
}
