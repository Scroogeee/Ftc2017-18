package org.firstinspires.ftc.teamcode.tests_setups;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

import org.firstinspires.ftc.teamcode.autonomous.AutonomousCore;
import org.firstinspires.ftc.teamcode.util.Constants;

/**
 * Created by FTC on 19.03.2018.
 */
@Autonomous(name = "Presentation", group = "Test")
public class Presentation extends AutonomousCore {

	@Override
	protected void routine() {
		telemetry.addLine("speaking");
		telemetry.update();
		say(Constants.greetings);
		telemetry.addLine("Stopped");
		telemetry.update();
		textToSpeech.shutdown();
	}
}
