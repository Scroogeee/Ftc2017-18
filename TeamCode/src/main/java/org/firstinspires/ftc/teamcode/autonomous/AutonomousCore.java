package org.firstinspires.ftc.teamcode.autonomous;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.CRServo;

import org.firstinspires.ftc.teamcode.autonomous.driving.W4StraightAuto;
import org.firstinspires.ftc.teamcode.autonomous.jewels.AutoJewelControl;
import org.firstinspires.ftc.teamcode.autonomous.jewels.JewelColor;
import org.firstinspires.ftc.teamcode.driverControlled.Constants;

/**
 * Created by FTC on 24.01.2018.
 */

public class AutonomousCore extends LinearOpMode {

	protected W4StraightAuto drive = new W4StraightAuto(this);
	protected CRServo glyph_servo;
	protected AutoRelicControl relicControl = new AutoRelicControl();
	protected AutoJewelControl jewelControl = new AutoJewelControl();
	protected JewelColor currentJewelColor = JewelColor.NONE;

	@Override
	public void runOpMode() throws InterruptedException {
		initialize();
		waitForStart();
	}

	protected void initialize() {
		drive.initialize();
		glyph_servo = hardwareMap.crservo.get(Constants.servoGlyph_name);
		relicControl.initialize(this);
		jewelControl.initialize(this);
	}
}
