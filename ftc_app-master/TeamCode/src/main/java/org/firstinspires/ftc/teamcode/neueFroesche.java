package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.DcMotor;

/**
 * Created by FTC on 22.09.2017.
 */
public class neueFroesche extends OpMode {

    DcMotor fahrenRechts;
    DcMotor fahrenLinks;

    public void init (){
        fahrenRechts = hardwareMap.dcMotor.get("fahren_rechts");
        fahrenLinks = hardwareMap.dcMotor.get("fahren_links");
    }

    public void loop (){
        fahrenRechts.setPower(gamepad1.right_stick_y);
        fahrenLinks.setPower(gamepad1.left_stick_y);
    }
}
