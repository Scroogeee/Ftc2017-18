package org.firstinspires.ftc.teamcode.autonomous;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.CRServo;

import org.firstinspires.ftc.teamcode.autonomous.autoDriving.W4StraightAuto;
import org.firstinspires.ftc.teamcode.autonomous.autoRobotModules.AutoRelicControl;
import org.firstinspires.ftc.teamcode.robotModules.Constants;
import org.firstinspires.ftc.teamcode.robotModules.JewelControl;

/**
 * Created by FTC on 24.01.2018.
 */

public class AutonomousCore extends LinearOpMode {

	protected W4StraightAuto drive = new W4StraightAuto(this);
	protected CRServo glyph_servo;
	protected AutoRelicControl relicControl = new AutoRelicControl();
	protected JewelControl jewelControl = new JewelControl();
	protected JewelColor currentJewelColor = JewelColor.NONE;

	@Override
	public void runOpMode() throws InterruptedException {
		initialize();
		waitForStart();
	}

	protected void initialize() {
		drive.initialize();
		glyph_servo = hardwareMap.crservo.get(Constants.servoGlyph_name);
		relicControl.initialize(this.hardwareMap);
		jewelControl.initialize(this.hardwareMap);
	}

	protected void kickJewel(JewelColor toKick) {
		//Jewels herunter kicken
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
}
