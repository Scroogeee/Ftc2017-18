package org.firstinspires.ftc.teamcode.autonomous;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

import org.firstinspires.ftc.teamcode.Constants;

/**
 * Created by FTC on 19.03.2018.
 */
@Autonomous(name = "Presentation", group = "Test")
public class Presentation extends AutonomousCore {
	@Override
	public void runOpMode() throws InterruptedException {
		initialize();
		telemetry.addLine("Press Play");
		telemetry.update();
		waitForStart();
		speak(Constants.greetings);
		speak("Quack; " + "Quack");
	}
}
