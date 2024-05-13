package frsf.cidisi.faia.examples.search.Suspicious;

import frsf.cidisi.faia.agent.search.GoalTest;
import frsf.cidisi.faia.state.AgentState;

public class SusGoal extends GoalTest{

	@Override
	public boolean isGoalState(AgentState agentState) {
		
		SusAgentState impostorState = (SusAgentState) agentState;
		
		if(impostorState.getCrewmatesLeft() == 0 && impostorState.getSabotageTasksLeft() == 0) {
			return true;
		}
		
		return false;
	}

}
