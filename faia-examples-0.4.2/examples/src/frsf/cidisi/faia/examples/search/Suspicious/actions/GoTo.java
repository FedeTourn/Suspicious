package frsf.cidisi.faia.examples.search.Suspicious.actions;

import frsf.cidisi.faia.agent.search.SearchAction;
import frsf.cidisi.faia.agent.search.SearchBasedAgentState;
import frsf.cidisi.faia.examples.search.Suspicious.SusEnvironment;
import frsf.cidisi.faia.state.AgentState;
import frsf.cidisi.faia.state.EnvironmentState;

public class GoTo extends SearchAction {
	
	
	private Integer destinationRoomId;

	
	public GoTo (Integer destination) {
		this.destinationRoomId = destination;
	}

	@Override
	public SearchBasedAgentState execute(SearchBasedAgentState s) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Double getCost() {
		// TODO Auto-generated method stub
		return 1.0;
	}

	@Override
	public EnvironmentState execute(AgentState ast, EnvironmentState est) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return "Go To" + destinationRoomId + " - " + SusEnvironment.ROOM_NAMES.get(destinationRoomId);
	}

}
