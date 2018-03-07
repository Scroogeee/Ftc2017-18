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
}
