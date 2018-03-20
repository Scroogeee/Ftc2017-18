package org.firstinspires.ftc.teamcode.tests_setups;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

import org.firstinspires.ftc.robotcore.external.navigation.RelicRecoveryVuMark;
import org.firstinspires.ftc.teamcode.autonomous.autoRobotModules.VuMarkAutonomous;

/**
 * Created by FTC on 20.03.2018.
 */
@Autonomous(name = "VuMarkRecognitionTest", group = "Test")
public class VuMarkAuto extends VuMarkAutonomous {
	@Override
	public void runOpMode() throws InterruptedException {
		initialize();
		waitForStart();
		RelicRecoveryVuMark vuMark = scanVuMark();
		say("I recognized the VuMark:" + vuMark.name());
		//textToSpeech.shutdown();
	}

}
