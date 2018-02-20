package org.firstinspires.ftc.teamcode.DriverControlled.driving;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;

import org.firstinspires.ftc.teamcode.DriverControlled.Constants;

/**
 * Created by FTC on 11.12.2017.
 */
@TeleOp(name = "OmniSetupTest")
public class OmniTest extends OpMode {

	private DcMotor a;
	private DcMotor b;
	private DcMotor c;
	private DcMotor d;


	@Override
	public void init() {
		a = hardwareMap.dcMotor.get(Constants.Drive_A);
		b = hardwareMap.dcMotor.get(Constants.Drive_B);
		c = hardwareMap.dcMotor.get(Constants.Drive_C);
		d = hardwareMap.dcMotor.get(Constants.Drive_D);
	}

	@Override
	public void loop() {
		if (gamepad1.a){
			a.setPower(1);
		}else if (gamepad1.b){
			b.setPower(1);
		} else if (gamepad1.y) {
			c.setPower(1);
		} else if (gamepad1.x) {
			d.setPower(1);
		}else {
			a.setPower(0);
			b.setPower(0);
			c.setPower(0);
			d.setPower(0);
		}
	}
}
