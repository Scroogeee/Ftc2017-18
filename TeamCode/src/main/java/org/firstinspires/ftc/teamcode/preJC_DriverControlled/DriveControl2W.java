package org.firstinspires.ftc.teamcode.preJC_DriverControlled;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.DcMotor;


/**
 * Created by FTC on 16.10.2017.
 * This class
 */
public class DriveControl2W {

    /**
     * Declaration of the robot components
     */
    DcMotor right_drive;
    DcMotor left_drive;

    /**
     * OpMode reference
     */
    private OpMode mainRef;

    /**
     * drive values
     */
    private double r_value = 0;
    private double l_value = 0;

    /**
     * Initializes the Drive Control class with:
     * <ul>
     * <li>right and left motor names</li>
     * </ul>
     */
    public void init(Test2Wheel t2w) {
        left_drive = t2w.hardwareMap.dcMotor.get(Constants.ld_name);
        right_drive = t2w.hardwareMap.dcMotor.get(Constants.rd_name);
        mainRef = t2w;
    }

    /**
     * This method updates the desired power values
     * for the drive engines.
     */
    public void updateMotorData() {
        calcDriveValues();
        left_drive.setPower(l_value);
        right_drive.setPower(r_value);
    }

    /**
     * This method computes the power values for the drive engines.
     */
    private void calcDriveValues() {
        l_value = (double) -mainRef.gamepad1.right_stick_x;
        r_value = (double) -mainRef.gamepad1.right_stick_x;
        l_value -= (double) mainRef.gamepad1.right_stick_y;
        r_value += (double) mainRef.gamepad1.right_stick_y;

    }

}
