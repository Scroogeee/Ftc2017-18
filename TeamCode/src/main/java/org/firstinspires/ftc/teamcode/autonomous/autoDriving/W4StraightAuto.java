package org.firstinspires.ftc.teamcode.autonomous.autoDriving;

import com.qualcomm.robotcore.hardware.DcMotor;

import org.firstinspires.ftc.teamcode.autonomous.AutonomousCore;
import org.firstinspires.ftc.teamcode.autonomous.JewelColor;
import org.firstinspires.ftc.teamcode.robotModules.Constants;

/**
 * Created by FTC on 25.01.2018.
 */

public class W4StraightAuto {

	/**
	 * Robot motors
	 */
	public DcMotor A;
	public DcMotor B;
	public DcMotor C;
	public DcMotor D;
	/**
	 * Core reference
	 */
	private AutonomousCore autonomousCore;

	public W4StraightAuto(AutonomousCore param_ac) {
		this.autonomousCore = param_ac;
	}

	public void kickJewel(JewelColor toKick, JewelColor sensorColor) {

	}

	public void driveByPulses(int pulseNum, int afactor, int bfactor, int cfactor, int dfactor) {
		/**Reset Encoders*/
		A.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
		B.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
		C.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
		D.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
		/**Run to position*/
		A.setMode(DcMotor.RunMode.RUN_TO_POSITION);
		B.setMode(DcMotor.RunMode.RUN_TO_POSITION);
		C.setMode(DcMotor.RunMode.RUN_TO_POSITION);
		D.setMode(DcMotor.RunMode.RUN_TO_POSITION);
		/**Set the target position*/
		A.setTargetPosition(pulseNum * afactor);
		B.setTargetPosition(pulseNum * bfactor);
		C.setTargetPosition(pulseNum * cfactor);
		D.setTargetPosition(pulseNum * dfactor);
		/**Set the power*/
		A.setPower(0.9);
		B.setPower(0.9);
		C.setPower(0.9);
		D.setPower(0.9);
		while (autonomousCore.opModeIsActive()) {
			if (A.isBusy() && B.isBusy() && C.isBusy() && D.isBusy()) {
				/*autonomousCore.telemetry.addLine(String.valueOf(A.getCurrentPosition()));
				autonomousCore.telemetry.addLine(String.valueOf(B.getCurrentPosition()));
				autonomousCore.telemetry.addLine(String.valueOf(C.getCurrentPosition()));
				autonomousCore.telemetry.addLine(String.valueOf(D.getCurrentPosition()));
				autonomousCore.telemetry.update();*/
				//drive
			} else {
				break;
			}
		}
		A.setPower(0);
		B.setPower(0);
		C.setPower(0);
		D.setPower(0);
	}

	public void initialize() {
		A = autonomousCore.hardwareMap.dcMotor.get(Constants.Drive_A);
		B = autonomousCore.hardwareMap.dcMotor.get(Constants.Drive_B);
		C = autonomousCore.hardwareMap.dcMotor.get(Constants.Drive_C);
		D = autonomousCore.hardwareMap.dcMotor.get(Constants.Drive_D);
	}

}
