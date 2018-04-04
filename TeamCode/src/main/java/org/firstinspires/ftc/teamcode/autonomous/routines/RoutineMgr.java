package org.firstinspires.ftc.teamcode.autonomous.routines;

import org.firstinspires.ftc.teamcode.autonomous.AutonomousStrategy;
import org.firstinspires.ftc.teamcode.autonomous.VuMarkAutonomous;

public class RoutineMgr {
	private AutonomousBlueLong bl;
	private AutonomousBlueShort bs;
	private AutonomousRedLong rl;
	private AutonomousRedShort rs;
	private VuMarkAutonomous vuMarkAutonomous;

	public RoutineMgr(VuMarkAutonomous param_va) {
		vuMarkAutonomous = param_va;
		bl = new AutonomousBlueLong(vuMarkAutonomous);
		bs = new AutonomousBlueShort(vuMarkAutonomous);
		rl = new AutonomousRedLong(vuMarkAutonomous);
		rs = new AutonomousRedShort(vuMarkAutonomous);
	}

	public void selectRoutine(AutonomousStrategy strategy) {
		switch (strategy.getAlliance()) {
			case RED:
				switch (strategy.getStartingPos()) {
					case LONG:
						rl.routine(strategy);
						break;
					case SHORT:
						rs.routine(strategy);
						break;
				}
				break;
			case BLUE:
				switch (strategy.getStartingPos()) {
					case LONG:
						bl.routine(strategy);
						break;
					case SHORT:
						bs.routine(strategy);
						break;
				}
				break;
		}
	}
}
