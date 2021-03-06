package org.firstinspires.ftc.teamcode.driverControlled;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.autonomous.autoRobot.autoRobotModules.DecorationControl;
import org.firstinspires.ftc.teamcode.autonomous.autoRobot.autoRobotModules.JewelControl;
import org.firstinspires.ftc.teamcode.driverControlled.driverRobot.driverDriving.DriveStraight4W;
import org.firstinspires.ftc.teamcode.driverControlled.driverRobot.driverRobotModules.DriverGlyphControl;
import org.firstinspires.ftc.teamcode.driverControlled.driverRobot.driverRobotModules.DriverRelicControl;

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
	private DecorationControl decorationControl = new DecorationControl();

	/**
	 * Initializes the Robot and its sub-components such as:
	 * <ul>
	 * <li>Drive Control</li>
	 * <li>Glyph Control</li>
	 * <li>Relic Control</li>
	 * <li>Jewel Control</li>
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
		decorationControl.initialize(this.hardwareMap);
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
		decorationControl.update(this.gamepad1);
		jewelControl.update(this.gamepad2);
	}


}
