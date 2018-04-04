package org.firstinspires.ftc.teamcode.autonomous;

import org.firstinspires.ftc.teamcode.util.autoChoices.Alliance;
import org.firstinspires.ftc.teamcode.util.autoChoices.BalancingStone;

public class AutonomousStrategy {

	private Alliance alliance = Alliance.RED;
	private BalancingStone startingPos = BalancingStone.RED_SHORT;
	private boolean doJewel = true;
	private boolean doCrypto = true;

	public AutonomousStrategy(Alliance p_alliance, BalancingStone p_startingPos, boolean p_doJewel, boolean p_doCrypto) {
		if (p_alliance != null) {
			alliance = p_alliance;
		}
		if (p_startingPos != null) {
			startingPos = p_startingPos;
		}
		doJewel = p_doJewel;
		doCrypto = p_doCrypto;
	}

	public static Alliance getAllianceFromBalancingStone(BalancingStone currentChoice) {
		switch (currentChoice) {
			case RED_LONG:
				return Alliance.RED;
			case RED_SHORT:
				return Alliance.RED;
			case BLUE_LONG:
				return Alliance.BLUE;
			case BLUE_SHORT:
				return Alliance.BLUE;
		}
		return null;
	}

	public Alliance getAlliance() {
		return alliance;
	}

	public BalancingStone getStartingPos() {
		return startingPos;
	}

	public boolean doJewel() {
		return doJewel;
	}

	public boolean doCrypto() {
		return doCrypto;
	}

}
