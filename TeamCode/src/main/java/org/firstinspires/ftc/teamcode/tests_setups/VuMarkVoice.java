package org.firstinspires.ftc.teamcode.tests_setups;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import org.firstinspires.ftc.robotcore.external.navigation.RelicRecoveryVuMark;
import org.firstinspires.ftc.teamcode.autonomous.VuMarkAutonomous;

/**
 * Created by FTC on 20.03.2018.
 */
@Disabled
@Autonomous(name = "VuMarkVoiceTest", group = "Test")
public class VuMarkVoice extends VuMarkAutonomous {
	@Override
	public void runOpMode() {
		initialize();
		waitForStart();
		RelicRecoveryVuMark vuMark = scanVuMark();
		say("I recognized the VuMark:" + vuMark.name());
		//textToSpeech.shutdown();
	}

}
