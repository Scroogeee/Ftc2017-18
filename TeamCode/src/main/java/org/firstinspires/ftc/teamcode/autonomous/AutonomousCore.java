package org.firstinspires.ftc.teamcode.autonomous;

import android.speech.tts.TextToSpeech;
import com.qualcomm.hardware.modernrobotics.ModernRoboticsI2cRangeSensor;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.CRServo;
import org.firstinspires.ftc.teamcode.Constants;
import org.firstinspires.ftc.teamcode.autonomous.autoRobot.autoDriving.W4StraightByColor;
import org.firstinspires.ftc.teamcode.autonomous.autoRobot.autoRobotModules.AutoRelicControl;
import org.firstinspires.ftc.teamcode.autonomous.autoRobot.autoRobotModules.autoJewels.JewelColor;
import org.firstinspires.ftc.teamcode.autonomous.autoRobot.autoRobotModules.autoJewels.JewelControl;

import java.util.Locale;

import static org.firstinspires.ftc.teamcode.autonomous.autoRobot.autoDriving.W4StraightByColor.TURN_SPEED;

/**
 * Created by FTC on 24.01.2018.
 */

public class AutonomousCore extends LinearOpMode {

	protected final W4StraightByColor drive = new W4StraightByColor(this);
	protected CRServo glyph_servo;
	protected final AutoRelicControl relicControl = new AutoRelicControl();
	protected final JewelControl jewelControl = new JewelControl();
	protected JewelColor currentJewelColor = JewelColor.NONE;
	protected TextToSpeech textToSpeech;
	protected final ModeDetector modeDetector = new ModeDetector();
	protected HardwareConfiguration hardwareConfiguration = HardwareConfiguration.NONE;
	protected ModernRoboticsI2cRangeSensor rangeSensor;

	/**
	 * @return the current <code>HardwareConfiguration</code>
	 */
	public HardwareConfiguration getHardwareConfiguration() {
		return hardwareConfiguration;
	}

	@Override
	public void runOpMode() {
		initialize();
		waitForStart();
		upRelic();
		routine();
	}

	/**
	 * The routine to perform
	 * Should be overridden
	 */
	protected void routine() {

	}

	/**
	 * Initializes the <code>AutonomousCore</code> class
	 */
	protected void initialize() {
		//HardwareConfig
		modeDetector.initialize(this.hardwareMap);
		sleep(1000);
		hardwareConfiguration = modeDetector.getConfiguration();
		telemetry.addData("HardwareConfig: ", hardwareConfiguration.toString());
		telemetry.addData("Hue: ", modeDetector.getHue());
		telemetry.update();
		//Drive
		drive.initialize();
		//Range Sensor
		if (hardwareConfiguration == HardwareConfiguration.GREEN //everything
				|| hardwareConfiguration == HardwareConfiguration.BLUE) //only range
		{
			rangeSensor = hardwareMap.get(ModernRoboticsI2cRangeSensor.class, "sensor_range");
		}
		//Glyph Servo
		glyph_servo = hardwareMap.crservo.get(Constants.servoGlyph_name);
		glyph_servo.setPower(1);
		//Relic
		relicControl.initialize(this.hardwareMap);
		//Jewel
		jewelControl.initialize(this.hardwareMap);
		//TextToSpeech
		initTTS();
	}

	/**
	 * Initializes the TextToSpeech software
	 */
	private void initTTS() {
		textToSpeech = new TextToSpeech(hardwareMap.appContext, null, "com.google.android.tts");
		textToSpeech.setLanguage(Locale.US);
		textToSpeech.setSpeechRate((float) 1.25);
		textToSpeech.setPitch(1);
	}

	/**
	 * Kicks the jewel of the given Color
	 */
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
				if (drive.isGyroUsed()) {
					drive.gyroTurn(TURN_SPEED, 30);
				} else {
					drive.driveByPulses(350, 1, 1);
				}
				sleep(200);
				jewelControl.updateArm(1);
				sleep(1000);
				if (drive.isGyroUsed()) {
					drive.gyroTurn(TURN_SPEED, 0);
				} else {
					drive.driveByPulses(350, -1, -1);
				}
			} else {
				if (drive.isGyroUsed()) {
					drive.gyroTurn(TURN_SPEED, -30);
				} else {
					drive.driveByPulses(350, -1, -1);
				}
				sleep(200);
				jewelControl.updateArm(1);
				sleep(1000);
				if (drive.isGyroUsed()) {
					drive.gyroTurn(TURN_SPEED, 0);
				} else {
					drive.driveByPulses(350, 1, 1);
				}
			}
		}
		sleep(1000);
		jewelControl.updateArm(1);
	}

	/**
	 * Says the given String via the TextToSpeech software
	 */
	protected void say(String s) {
		textToSpeech.speak(s, TextToSpeech.QUEUE_FLUSH, null);
		sleep(1000);
		while (textToSpeech.isSpeaking()) {
			sleep(1);
		}
	}

	/**
	 * Pushes the relic grip up a little, so it doesn't influence the driving
	 */
	protected void upRelic() {
		//Relic ein Stück hochfahren
		relicControl.update(1, 0, 0);
		sleep(300);
		relicControl.update(0, 0, 0);

	}

}
