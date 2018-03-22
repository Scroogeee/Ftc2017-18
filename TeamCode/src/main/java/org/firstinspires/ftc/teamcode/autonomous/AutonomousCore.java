package org.firstinspires.ftc.teamcode.autonomous;

import android.speech.tts.TextToSpeech;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.CRServo;

import org.firstinspires.ftc.teamcode.Constants;
import org.firstinspires.ftc.teamcode.autonomous.autoDriving.W4StraightAuto;
import org.firstinspires.ftc.teamcode.autonomous.autoRobotModules.AutoRelicControl;
import org.firstinspires.ftc.teamcode.autonomous.autoRobotModules.autoJewels.JewelColor;
import org.firstinspires.ftc.teamcode.autonomous.autoRobotModules.autoJewels.JewelControl;

import java.util.Locale;

/**
 * Created by FTC on 24.01.2018.
 */

public class AutonomousCore extends LinearOpMode {

	protected W4StraightAuto drive = new W4StraightAuto(this);
	protected CRServo glyph_servo;
	protected AutoRelicControl relicControl = new AutoRelicControl();
	protected JewelControl jewelControl = new JewelControl();
	protected JewelColor currentJewelColor = JewelColor.NONE;
	protected TextToSpeech textToSpeech;
	protected ModeDetector modeDetector = new ModeDetector();
	protected HardwareConfiguration hardwareConfiguration = HardwareConfiguration.NONE;

	@Override
	public void runOpMode() throws InterruptedException {
		initialize();
		waitForStart();
	}

	protected void initialize() {
		drive.initialize();
		glyph_servo = hardwareMap.crservo.get(Constants.servoGlyph_name);
		glyph_servo.setPower(1);
		relicControl.initialize(this.hardwareMap);
		jewelControl.initialize(this.hardwareMap);
		initTTS();
		modeDetector.initialize(this.hardwareMap);
		sleep(1000);
		hardwareConfiguration = modeDetector.getConfiguration();
		telemetry.addData("HardwareConfig: ", hardwareConfiguration.toString());
		telemetry.addData("Hue:", modeDetector.getHue());
		telemetry.update();
	}

	private void initTTS() {
		textToSpeech = new TextToSpeech(hardwareMap.appContext, null, "com.google.android.tts");
		textToSpeech.setLanguage(Locale.US);
		textToSpeech.setSpeechRate((float) 1.25);
		textToSpeech.setPitch(1);
	}

	protected void kickJewel(JewelColor toKick) {
		// Jewels herunter kicken
		jewelControl.updateArm(0.27);
		sleep(1000);
		currentJewelColor = jewelControl.getColor();
		telemetry.update();
		telemetry.addData("CurrentColor:", currentJewelColor.toString());
		sleep(1000);
		if (currentJewelColor != null) {
			if (currentJewelColor.equals(toKick)) {
				drive.driveByPulses(350, 1, 1, 1, 1);
				sleep(200);
				jewelControl.updateArm(1);
				sleep(1000);
				drive.driveByPulses(350, -1, -1, -1, -1);
			} else {
				drive.driveByPulses(350, -1, -1, -1, -1);
				sleep(200);
				jewelControl.updateArm(1);
				sleep(1000);
				drive.driveByPulses(350, 1, 1, 1, 1);
			}
		}
		sleep(1000);
		jewelControl.updateArm(1);
	}

	protected void say(String s) {
		textToSpeech.speak(s, TextToSpeech.QUEUE_FLUSH, null);
		sleep(1000);
		while (textToSpeech.isSpeaking()) {
			sleep(1);
		}
	}

}
