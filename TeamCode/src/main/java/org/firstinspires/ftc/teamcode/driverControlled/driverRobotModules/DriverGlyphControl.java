package org.firstinspires.ftc.teamcode.driverControlled.driverRobotModules;

import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.teamcode.Constants;
import org.firstinspires.ftc.teamcode.driverControlled.DriverCore;

/**
 * Created by FTC on 17.10.2017.
 */
public class DriverGlyphControl {

	/**
	 * OpMode reference
	 */
	private DriverCore mainRef;

	private HardwareMap hardwareMap;

	/**
	 * Tightloose
	 */
	private CRServo tightloose;
	private double tightloose_power = 0;

	/**
	 * Glyphlift
	 */
	private DcMotor glyphlift;

	public void init(HardwareMap hwMap, DriverCore p_mainRef) {
		//initialize references
		mainRef = p_mainRef;
		if (hwMap != null) {
			hardwareMap = hwMap;
		} else if (mainRef != null) {
			hardwareMap = mainRef.hardwareMap;
		} else {
			throw new NullPointerException("DriverCore or HardwareMap not specified");
		}

		//initialize tightloose
		tightloose = hardwareMap.crservo.get(Constants.servoGlyph_name);
		//Standstill at FORWARD and 0.05
		tightloose.setDirection(DcMotorSimple.Direction.FORWARD);
		tightloose.setPower(0.05);
		//Glyphlift
		glyphlift = hardwareMap.dcMotor.get(Constants.Glyphlift_name);
		glyphlift.setDirection(DcMotorSimple.Direction.REVERSE);
		glyphlift.setPower(0);

	}

	public void updateGlyphArm() {
		updateHeight();
		updateTLPos();
	}

	private void updateHeight() {
		glyphlift.setPower(-mainRef.gamepad2.right_stick_y);
	}

	/**
	 * Updates position of the CRServo
	 */
	private void updateTLPos() {
		if (mainRef.gamepad2.left_bumper) {
			tightloose.setDirection(DcMotorSimple.Direction.FORWARD);
			tightloose_power = 1;
		} else if (mainRef.gamepad2.right_bumper) {
			tightloose.setDirection(DcMotorSimple.Direction.REVERSE);
			tightloose_power = 1;
		} else {
			tightloose.setDirection(DcMotorSimple.Direction.FORWARD);
			tightloose_power = 0.05;
		}
		/** Just for security */
		tightloose_power = Math.min(tightloose_power, 1);
		tightloose_power = Math.max(tightloose_power, 0);
		/** Set the power */
		tightloose.setPower(tightloose_power);

		if (mainRef != null) {
			//mainRef.telemetry.addLine(Double.toString(tightloose_power));
			//mainRef.telemetry.update();
		}
	}


}
