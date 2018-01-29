package org.firstinspires.ftc.teamcode.DriverControlled;

import java.util.ArrayList;

/**
 * Created by FTC on 16.10.2017. <br>
 * Variables for the names of the components
 */
public class Constants {


	/**
	 * motor names
	 */
	//drives for tank-drive
	public static final String rd_name = "right_drive";
	public static final String ld_name = "left_drive";
	//drives for omni-drive
	public static final String Drive_A = "Drive_A";
	public static final String Drive_B = "Drive_B";
	public static final String Drive_C = "Drive_C";
	public static final String Drive_D = "Drive_D";
	//Relic
	public static final String Vertical_Relic_Motor = "RelicHeight";
	public static final String Lateral_Relic_Motor = "RelicLateral";
	//Glyph
	public static final String servoGlyph_name = "servo_glyph";
	public static final String Glyphlift_name = "glyph_lift";
	//scales
	public static final double FAST_SCALE = 1;
	public static final double SLOW_SCALE = 0.25;
	public static final double RELIC_EXTEND_SCALE = 1;

	public static double getHighestNumber(ArrayList<Double> list) {
		double Highest = 1;
		for (double x : list) {
			if (x > Highest) {
				Highest = x;
			}
		}
		return Highest;
	}

}