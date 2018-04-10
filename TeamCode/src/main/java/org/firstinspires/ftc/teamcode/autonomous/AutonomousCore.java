package org.firstinspires.ftc.teamcode.autonomous;

import android.speech.tts.TextToSpeech;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.CRServo;

import org.firstinspires.ftc.teamcode.autonomous.autoRobot.autoDriving.W4StraightByColor;
import org.firstinspires.ftc.teamcode.autonomous.autoRobot.autoRobotModules.AutoRelicControl;
import org.firstinspires.ftc.teamcode.autonomous.autoRobot.autoRobotModules.autoJewels.JewelColor;
import org.firstinspires.ftc.teamcode.autonomous.autoRobot.autoRobotModules.autoJewels.JewelControl;
import org.firstinspires.ftc.teamcode.util.Constants;
import org.firstinspires.ftc.teamcode.util.HalDashboard;
import org.firstinspires.ftc.teamcode.util.autoChoices.BalancingStone;
import org.firstinspires.ftc.teamcode.util.autoChoices.FtcChoiceMenu;
import org.firstinspires.ftc.teamcode.util.autoChoices.FtcMenu;

import java.util.Locale;

import static org.firstinspires.ftc.teamcode.autonomous.autoRobot.autoDriving.W4StraightByColor.TURN_SPEED;

/**
 * Created by FTC on 24.01.2018.
 */

public abstract class AutonomousCore extends LinearOpMode implements FtcMenu.MenuButtons {

	protected final W4StraightByColor drive = new W4StraightByColor(this);
	protected final AutoRelicControl relicControl = new AutoRelicControl();
	protected final JewelControl jewelControl = new JewelControl();
	protected final ModeDetector modeDetector = new ModeDetector();
	public CRServo glyph_servo;
	public HalDashboard dashboard;
	protected TextToSpeech textToSpeech;
	protected HardwareConfiguration hardwareConfiguration = HardwareConfiguration.NONE;
	protected JewelColor currentJewelColor = JewelColor.NONE;
	protected AutonomousStrategy strategy;

	public W4StraightByColor getDrive() {
		return drive;
	}

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
		dashboard.clearDisplay();
		if (opModeIsActive()) {
			upRelic();
		}
		if (opModeIsActive()) {
			routine();
		}
	}

	protected abstract void routine();

	/**
	 * Initializes the <code>AutonomousCore</code> class
	 */
	protected void initialize() {
		//HardwareConfig
		modeDetector.initialize(this.hardwareMap);
		sleep(1000);
		hardwareConfiguration = modeDetector.getConfiguration();
		//TODO remove debugging
		/*telemetry.addData("HardwareConfig: ", hardwareConfiguration.toString());
		telemetry.addData("Hue: ", modeDetector.getHue());
		telemetry.update();*/
		//Drive
		drive.initialize();
		//Glyph Servo
		glyph_servo = hardwareMap.crservo.get(Constants.servoGlyph_name);
		glyph_servo.setPower(1);
		//Relic
		relicControl.initialize(this.hardwareMap);
		//Jewel
		jewelControl.initialize(this.hardwareMap);
		//TextToSpeech
		initTTS();
		//Init dashboard
		dashboard = HalDashboard.createInstance(this.telemetry);
		dashboard.clearDisplay();
		doMenus();
	}

	protected void doMenus() {
		FtcChoiceMenu<BalancingStone> startPositionMenu =
				new FtcChoiceMenu<>("Start Position:", null, this);
		FtcChoiceMenu<Boolean> jewelMenu =
				new FtcChoiceMenu<>("Do Jewel?", startPositionMenu, this);
		FtcChoiceMenu<Boolean> cryptoMenu =
				new FtcChoiceMenu<>("Do Crypto?", jewelMenu, this);

		startPositionMenu.addChoice("RedShort", BalancingStone.RED_SHORT, true, jewelMenu);
		startPositionMenu.addChoice("RedLong", BalancingStone.RED_LONG, false, jewelMenu);
		startPositionMenu.addChoice("BlueShort", BalancingStone.BLUE_SHORT, false, jewelMenu);
		startPositionMenu.addChoice("BlueLong", BalancingStone.BLUE_LONG, false, jewelMenu);

		jewelMenu.addChoice("Yes", true, true, cryptoMenu);
		jewelMenu.addChoice("No", false, false, cryptoMenu);

		cryptoMenu.addChoice("Yes", true, true);
		cryptoMenu.addChoice("No", false, false);

		FtcMenu.walkMenuTree(startPositionMenu, this);

		strategy = new AutonomousStrategy(AutonomousStrategy.getAllianceFromBalancingStone(startPositionMenu.getCurrentChoiceObject()),
				startPositionMenu.getCurrentChoiceObject(), jewelMenu.getCurrentChoiceObject(), cryptoMenu.getCurrentChoiceObject());

		dashboard.displayPrintf(1, "Alliance=%s", strategy.getAlliance().toString());
		dashboard.displayPrintf(2, "StartPos=%s", strategy.getStartingPos().toString());
		dashboard.displayPrintf(3, "DoJewel=%s,DoCrypto=%s",
				Boolean.toString(strategy.doJewel()), Boolean.toString(strategy.doCrypto()));

	}


	@Override
	public boolean isMenuUpButton() {
		return gamepad1.dpad_up;
	}   //isMenuUpButton

	@Override
	public boolean isMenuDownButton() {
		return gamepad1.dpad_down;
	}   //isMenuDownButton

	@Override
	public boolean isMenuEnterButton() {
		return gamepad1.a;
	}   //isMenuEnterButton

	@Override
	public boolean isMenuBackButton() {
		return gamepad1.dpad_left;
	}   //isMenuBackButton


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
	public void kickJewel(JewelColor toKick) {
		// Jewels herunter kicken
		jewelControl.updateArm(0.325);
		sleep(1000);
		currentJewelColor = jewelControl.getColor();
		/*
		telemetry.addData("CurrentColor:", currentJewelColor.toString());
		telemetry.update();*/
		sleep(1000);
		if (currentJewelColor != null && currentJewelColor != JewelColor.NONE) {
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
