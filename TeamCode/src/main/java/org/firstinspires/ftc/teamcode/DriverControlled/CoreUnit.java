package org.firstinspires.ftc.teamcode.DriverControlled;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.DriverControlled.driving.DriveStraight4W;

/**
 * Created by FTC on 25.09.2017.
 */
@TeleOp(name = "FROG-DriverControlled")
public class CoreUnit extends OpMode {

	//DriveCross4W drive4 = new DriveCross4W();
	//DriveControl2W driveControl = new DriveControl2W();
	DriveStraight4W driveStraight4W = new DriveStraight4W();
	GlyphControl glyphControl = new GlyphControl();
	//TODO reactivate
	//RelicControl relicControl = new RelicControl();

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
		//TODO  reactivate
		//relicControl.init(this);
		glyphControl.init(this);	}

	/**
	 * This is the main loop which executes every tick.
	 */
	@Override
	public void loop() {
		//drive4.update();
		//driveControl.update();
		driveStraight4W.update();
		glyphControl.update();
		//TODO reactivate
		//relicControl.update();
	}


}
