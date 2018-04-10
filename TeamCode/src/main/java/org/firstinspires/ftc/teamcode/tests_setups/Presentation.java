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
	public void runOpMode() {
		initialize();
		dashboard.displayText(1, "Press Play");
		dashboard.refreshDisplay();
		waitForStart();
		dashboard.displayText(1, "speaking");
		dashboard.refreshDisplay();
		say(Constants.greetings);
		dashboard.displayText(1, "Stopped");
		dashboard.refreshDisplay();
		//textToSpeech.shutdown();
	}

	@Override
	protected void routine() {

	}
}
