package org.firstinspires.ftc.teamcode.util;

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
	public static final String jewelArm_name = "servo_jewel";
	public static final String jewelSensor_name = "sensor_jewel";
	public static final int minimum_ConfidenceLevel = 6;
	public static final double jewelArm_up = 1;
	public static final double jewelArm_down = 0;
	//Presentation
	public static final String greetings = "Hello! I am Herbert," +
			" the robot of the FROG Team. I am  a rookie, 8 months old and I am very happy " +
			"to participate in the competition today!";

	public static final String gyro_name = "sensor_gyro";
	public static final String reader_name = "reader";
	public static final String servoFrog_name = "servo_frog";
	public static final String range_sensor_name = "sensor_range";

	//maximum scan time for vuforia
	public static final int MAX_SCAN_TIME_SECONDS = 5;
	//lower is more sensitive
	public static final double RANGE_THRESHOLD_CM = 7;


}
