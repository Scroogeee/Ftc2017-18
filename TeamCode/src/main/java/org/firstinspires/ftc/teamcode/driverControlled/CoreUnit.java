package org.firstinspires.ftc.teamcode.driverControlled;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.driverControlled.driving.DriveStraight4W;
import org.firstinspires.ftc.teamcode.driverControlled.robotModules.GlyphControl;
import org.firstinspires.ftc.teamcode.driverControlled.robotModules.JewelControl;
import org.firstinspires.ftc.teamcode.driverControlled.robotModules.RelicControl;

/**
 * Created by FTC on 25.09.2017.
 */
@TeleOp(name = "FROG-DriverControlled", group = "drive")
public class CoreUnit extends OpMode {

	//DriveCross4W drive4 = new DriveCross4W();
	//DriveControl2W driveControl = new DriveControl2W();
	DriveStraight4W driveStraight4W = new DriveStraight4W();
	GlyphControl glyphControl = new GlyphControl();
	RelicControl relicControl = new RelicControl();
	JewelControl jewelControl = new JewelControl();

	/**
	 * Initializes the Robot and its sub-components such as:
	 * <ul>
	 * <li>Drive Controls</li>
	 * </ul>
	 */
	@Override
	public void init() {
		//driveControl.init(this);
		//drive4.init(this);
		driveStraight4W.init(this);
		glyphControl.init(this);
		relicControl.init(this);
		jewelControl.initialize(this);
		jewelControl.updateArm(1);
	}

	/**
	 * This is the main loop which executes every tick.
	 */
	@Override
	public void loop() {
		//drive4.update();
		//driveControl.update();
		driveStraight4W.update();
		glyphControl.updateGlyphArm();
		relicControl.update();
	}


}
