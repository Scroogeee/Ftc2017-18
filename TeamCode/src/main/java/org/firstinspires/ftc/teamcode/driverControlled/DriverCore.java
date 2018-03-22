package org.firstinspires.ftc.teamcode.driverControlled;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.autonomous.autoRobotModules.autoJewels.JewelControl;
import org.firstinspires.ftc.teamcode.driverControlled.driverDriving.DriveStraight4W;
import org.firstinspires.ftc.teamcode.driverControlled.driverRobotModules.DriverGlyphControl;
import org.firstinspires.ftc.teamcode.driverControlled.driverRobotModules.DriverRelicControl;

/**
 * Created by FTC on 25.09.2017.
 */
@TeleOp(name = "FROG-DriverControlled", group = "drive")
public class DriverCore extends OpMode {

	//DriveCross4W drive4 = new DriveCross4W();
	//DriveControl2W driveControl = new DriveControl2W();
	private DriveStraight4W driveStraight4W = new DriveStraight4W();
	private DriverGlyphControl driverGlyphControl = new DriverGlyphControl();
	private DriverRelicControl driverRelicControl = new DriverRelicControl();
	private JewelControl jewelControl = new JewelControl();

	/**
	 * Initializes the Robot and its sub-components such as:
	 * <ul>
	 * <li>Drive Controls</li>
	 * </ul>
	 */
	@Override
	public void init() {
		//driveControl.initialize(this);
		//drive4.initialize(this);
		driveStraight4W.init(null, this);
		driverGlyphControl.init(null, this);
		driverRelicControl.init(null, this);
		jewelControl.initialize(this.hardwareMap);
	}

	/**
	 * This is the main loop which executes every tick.
	 */
	@Override
	public void loop() {
		//drive4.update();
		//driveControl.update();
		driveStraight4W.update();
		driverGlyphControl.updateGlyphArm();
		driverRelicControl.update();
	}


}
