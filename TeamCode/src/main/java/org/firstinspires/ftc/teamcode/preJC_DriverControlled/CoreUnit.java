package org.firstinspires.ftc.teamcode.preJC_DriverControlled;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import org.firstinspires.ftc.teamcode.preJC_DriverControlled.driving.*;

/**
 * Created by FTC on 25.09.2017.
 */
@TeleOp(name = "FROG-DriverControlled")
public class CoreUnit extends OpMode {

	DriveControl2W driveControl = new DriveControl2W();
	GlyphControl glyphControl = new GlyphControl();

	/**
	 * Initializes the Robot and its sub-components such as:
	 * <ul>
	 * <li>Drive Controls</li>
	 * </ul>
	 */
	@Override
	public void init() {
		driveControl.init(this);
		glyphControl.init(this);
	}

	/**
	 * This is the main loop which executes every tick.
	 */
	@Override
	public void loop() {
		driveControl.updateMotorData();
		glyphControl.updateGlyphArm();
	}


}
