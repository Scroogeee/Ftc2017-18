package org.firstinspires.ftc.teamcode.autonomous;

import android.graphics.Color;
import com.qualcomm.robotcore.hardware.ColorSensor;
import com.qualcomm.robotcore.hardware.HardwareMap;
import org.firstinspires.ftc.teamcode.util.Constants;


/**
 * Created by FTC on 22.03.2018.
 */

public class ModeDetector {

	private HardwareMap hardwareMap;

	private ColorSensor reader;

	private float[] hsvValues = new float[3];

	/**
	 * Initializes the <code>ModeDetector</code> class
	 */
	public void initialize(HardwareMap hwMap) {
		this.hardwareMap = hwMap;
		reader = hardwareMap.colorSensor.get(Constants.reader_name);
		reader.enableLed(true);
	}

	/**
	 * @return the current <code>HardwareConfiguration</code>
	 */
	public HardwareConfiguration getConfiguration() {
		Color.RGBToHSV(reader.red() * 8, reader.green() * 8, reader.blue() * 8, hsvValues);
		float hue = hsvValues[0];
		if (hue >= 0 && hue <= 20) {
			return HardwareConfiguration.RED;
		} else if (hue >= 35 && hue <= 50) {
			return HardwareConfiguration.YELLOW;
		} else if (hue >= 90 && hue <= 150) {
			return HardwareConfiguration.GREEN;
		} else if (hue >= 220 && hue <= 240) {
			return HardwareConfiguration.BLUE;
		} else {
			return HardwareConfiguration.NONE;
		}
	}

	/**
	 * @return the current Hue value read by the sensor (debugging)
	 */
	float getHue() {
		float hue = hsvValues[0];
		return hue;
	}

}
