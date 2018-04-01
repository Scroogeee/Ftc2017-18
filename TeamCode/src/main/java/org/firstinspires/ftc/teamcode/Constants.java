package org.firstinspires.ftc.teamcode;

import java.util.ArrayList;

/**
 * Created by FTC on 16.10.2017. <br>
 * Variables for the names of the components
 */
public class Constants {

	//motor names

	//drives for tank-drive
	public static final String rd_name = "right_drive";
	public static final String ld_name = "left_drive";
	//drives for omni-drive
	public static final String Drive_A = "Drive_A";
	public static final String Drive_B = "Drive_B";
	public static final String Drive_C = "Drive_C";
	public static final String Drive_D = "Drive_D";
	//Motor scales
	public static final double A_Scale = 1;
	public static final double B_Scale = 1;
	public static final double C_Scale = 1;
	public static final double D_Scale = 1;

	//drive mode scales
	public static final double FAST_SCALE = 1;
	public static final double SLOW_SCALE = 0.25;
	//Relic
	public static final String Vertical_Relic_Motor = "RelicHeight";
	public static final String Lateral_Relic_Motor = "RelicLateral";
	public static final String Servo_Relic = "servo_relic";
	public static final double RELIC_HEIGHT_SCALE = 1;
	public static final double RELIC_EXTEND_SCALE = 1;
	//Glyph
	public static final String servoGlyph_name = "servo_glyph";
	public static final String Glyphlift_name = "glyph_lift";
	//Jewel
	public static final String jewelArm_name = "jewel_servo";
	public static final String jewelSensor_name = "jewel_sensor";
	public static final int minimum_ConfidenceLevel = 8;
	//Presentation
	public static final String greetings = "Hello! I am Herbert," +
			" the robot of the FROG Team. I am  a rookie, 8 months old and I am very happy " +
			"to participate in the competition today!";

	public static final String gyro_name = "drive_gyro";
	public static final String reader_name = "reader";

	public static final int MAX_SCAN_TIME_SECONDS = 5;
	public static final double RANGE_THRESHHOLD = 2;

	public static double getHighestNumber(ArrayList<Double> list) {
		double Highest = 1;
		for (double x : list) {
			if (x > Highest) {
				Highest = x;
			}
		}
		return Highest;
	}

	public static int countWords(String s) {
		int wordCount = 0;

		boolean word = false;
		int endOfLine = s.length() - 1;

		for (int i = 0; i < s.length(); i++) {
			// if the char is a letter, word = true.
			if (Character.isLetter(s.charAt(i)) && i != endOfLine) {
				word = true;
				// if char isn't a letter and there have been letters before,
				// counter goes up.
			} else if (!Character.isLetter(s.charAt(i)) && word) {
				wordCount++;
				word = false;
				// last word of String; if it doesn't end with a non letter, it
				// wouldn't count without this.
			} else if (Character.isLetter(s.charAt(i)) && i == endOfLine) {
				wordCount++;
			}
		}
		return wordCount;
	}
}
