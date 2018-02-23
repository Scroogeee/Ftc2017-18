package org.firstinspires.ftc.teamcode.driverControlled.driving;

import com.qualcomm.robotcore.hardware.DcMotor;

import org.firstinspires.ftc.teamcode.driverControlled.Constants;
import org.firstinspires.ftc.teamcode.driverControlled.CoreUnit;

import java.util.concurrent.TimeUnit;


/**
 * Created by FTC on 16.10.2017.
 * This class
 */
public class DriveControl2W {

    /**
     * Declaration of the robot components
     */
    private DcMotor right_drive;
    private DcMotor left_drive;
    /**
     * Used for slow driving mode
     */
    private boolean isSlow = false;

    /**
     * CoreUnit reference
     */
    private CoreUnit mainRef;

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
    public void init(CoreUnit p_mainRef) {
	    mainRef = p_mainRef;
        left_drive = mainRef.hardwareMap.dcMotor.get(Constants.ld_name);
        right_drive = mainRef.hardwareMap.dcMotor.get(Constants.rd_name);
    }

    public void update(){
        updateMotorData();
    }

    /**
     * This method updates the desired power values
     * for the drive engines.
     */
    public void updateMotorData() {
        if (mainRef.gamepad1.a) {
            isSlow = !isSlow;
            try {
                TimeUnit.SECONDS.sleep((long) 0.5);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        calcDriveValues();
        left_drive.setPower(l_value);
        right_drive.setPower(r_value);
    }

    /**
     * This method computes the power values for the drive engines.
     */
    private void calcDriveValues() {
        double scaleFactor = 0;
        if (isSlow) {
            scaleFactor = Constants.SLOW_SCALE;
        } else {
            scaleFactor = Constants.FAST_SCALE;
        }

        /**right stick: full control*/
        l_value = (double) -mainRef.gamepad1.right_stick_x * scaleFactor;
        r_value = (double) -mainRef.gamepad1.right_stick_x * scaleFactor;

        l_value -= (double) mainRef.gamepad1.right_stick_y * scaleFactor;
        r_value += (double) mainRef.gamepad1.right_stick_y * scaleFactor;

        l_value -= (double) mainRef.gamepad1.left_stick_y * scaleFactor;
        r_value += (double) mainRef.gamepad1.left_stick_y * scaleFactor;

        /**make sure the value is not more than 1*/
        l_value = Math.min(l_value, 1);
        r_value = Math.min(r_value, 1);

        /**...and not less than -1*/
        l_value = Math.max(l_value, -1);
        r_value = Math.max(r_value, -1);

        //TODO remove Debugging
        //mainRef.telemetry.addData("left:", l_value);
        //mainRef.telemetry.addData("right:", r_value);
        //mainRef.telemetry.addData("Slow:", isSlow);
        //mainRef.telemetry.update();

    }

}
