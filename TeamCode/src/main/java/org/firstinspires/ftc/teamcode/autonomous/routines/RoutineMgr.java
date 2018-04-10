package org.firstinspires.ftc.teamcode.autonomous.routines;

import org.firstinspires.ftc.teamcode.autonomous.AutonomousStrategy;
import org.firstinspires.ftc.teamcode.autonomous.VuMarkAutonomous;

public class RoutineMgr {
	private AutonomousBlueLong bl;
	private AutonomousBlueShort bs;
	private AutonomousRedLong rl;
	private AutonomousRedShort rs;
	private VuMarkAutonomous vuMarkAutonomous;

	public void initialize(VuMarkAutonomous param_va) {
		vuMarkAutonomous = param_va;
		bl = new AutonomousBlueLong();
		bs = new AutonomousBlueShort();
		rl = new AutonomousRedLong();
		rs = new AutonomousRedShort();
		bl.initialize(param_va);
		bs.initialize(param_va);
		rl.initialize(param_va);
		rs.initialize(param_va);
	}

	public void selectRoutine(AutonomousStrategy strategy) {
		switch (strategy.getStartingPos()) {
			case RED_LONG:
				rl.routine(strategy);
				break;
			case RED_SHORT:
				rs.routine(strategy);
				break;
			case BLUE_LONG:
				bl.routine(strategy);
				break;
			case BLUE_SHORT:
				bs.routine(strategy);
				break;
		}
	}
}
