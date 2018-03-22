package org.firstinspires.ftc.teamcode.autonomous;

import android.graphics.Color;

import com.qualcomm.robotcore.hardware.ColorSensor;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.teamcode.Constants;


/**
 * Created by FTC on 22.03.2018.
 */

public class ModeDetector {

	private HardwareMap hardwareMap;

	private ColorSensor reader;

	private float[] hsvValues = new float[3];

	public void initialize(HardwareMap hwMap) {
		this.hardwareMap = hwMap;
		reader = hardwareMap.colorSensor.get(Constants.reader_name);
		reader.enableLed(true);
	}

	public HardwareConfiguration getConfiguration() {
		Color.RGBToHSV(reader.red() * 8, reader.green() * 8, reader.blue() * 8, hsvValues);
		return HardwareConfiguration.NONE;
	}

	public float getHue() {
		float i = hsvValues[0];
		return i;
	}

}
