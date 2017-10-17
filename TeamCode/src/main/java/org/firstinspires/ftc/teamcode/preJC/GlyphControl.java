package org.firstinspires.ftc.teamcode.preJC;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.Servo;

/**
 * Created by FTC on 17.10.2017.
 */
public class GlyphControl {

    /**
     * OpMode reference
     */
    private OpMode mainRef;


    /**
     * Tightloose
     */
    private Servo tightloose;
    private double tightloose_pos = 0;


    public void init(Test2Wheel t2w) {
        tightloose = t2w.hardwareMap.servo.get(Constants.servoGlyph_name);
        tightloose.setPosition(tightloose_pos);
        mainRef = t2w;
    }

    public void updateGlyphArm() {
        updateTLPos();

    }

    private void updateTLPos() {
        tightloose_pos += mainRef.gamepad2.right_trigger;
        tightloose_pos -= mainRef.gamepad2.left_trigger;
        tightloose_pos = Math.min(tightloose_pos, 1);
        tightloose_pos = Math.max(tightloose_pos, 0);
        tightloose.setPosition(tightloose_pos);
    }

}
