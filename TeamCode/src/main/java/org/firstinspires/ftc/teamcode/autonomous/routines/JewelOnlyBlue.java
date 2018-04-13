package org.firstinspires.ftc.teamcode.autonomous.routines;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

import org.firstinspires.ftc.teamcode.autonomous.AutonomousCore;
import org.firstinspires.ftc.teamcode.util.autoChoices.AllianceColor;

/**
 * Created by FTC on 23.02.2018.
 */

@Autonomous(name = "LeaveBlueJewel", group = "jewelAlternate")
public class JewelOnlyBlue extends AutonomousCore {

	@Override
	protected void routine() {
		alliance = AllianceColor.BLUE;
		opponent = AllianceColor.RED;
		kickJewel(opponent);
	}

}
