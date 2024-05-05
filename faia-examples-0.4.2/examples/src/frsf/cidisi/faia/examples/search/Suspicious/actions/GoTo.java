package frsf.cidisi.faia.examples.search.Suspicious.actions;

import frsf.cidisi.faia.agent.search.SearchAction;
import frsf.cidisi.faia.agent.search.SearchBasedAgentState;
import frsf.cidisi.faia.examples.search.Suspicious.SusAgentState;
import frsf.cidisi.faia.examples.search.Suspicious.SusEnvironment;
import frsf.cidisi.faia.state.AgentState;
import frsf.cidisi.faia.state.EnvironmentState;

public class GoTo extends SearchAction {
	
	
	private Integer destinationRoomId;

	
	public GoTo (Integer destination) {
		this.destinationRoomId = destination;
	}

    /**
     * This method updates a tree node state when the search process is running.
     * It does not updates the real world state.
     */
	@Override
	public SearchBasedAgentState execute(SearchBasedAgentState s) {
		SusAgentState susState = (SusAgentState) s;
		
		/* The 'GoTo' action can be selected to move to another 
		 * Otherwise return null.*/
		
		
		return null;
	}

    /**
     * This method updates the agent state and the real world state.
     */
	@Override
	public EnvironmentState execute(AgentState ast, EnvironmentState est) {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	public Double getCost() {
		// TODO Auto-generated method stub
		return 1.0;
	}

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return "Go To" + destinationRoomId + " - " + SusEnvironment.ROOM_NAMES.get(destinationRoomId);
	}

}
