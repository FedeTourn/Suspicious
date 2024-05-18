package frsf.cidisi.faia.examples.search.Suspicious.actions;

import frsf.cidisi.faia.agent.search.SearchAction;
import frsf.cidisi.faia.agent.search.SearchBasedAgentState;
import frsf.cidisi.faia.examples.search.Suspicious.SusAgentState;
import frsf.cidisi.faia.examples.search.Suspicious.SusEnvironment;
import frsf.cidisi.faia.examples.search.Suspicious.SusEnvironmentState;
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
		
		/* The 'GoTo' action can be selected to move to another room if the agent has enough
		 * energy to do the action and stay alive.
		 * Otherwise return null.*/
		int currentRoom = susState.getAgentPosition();
		int energy = susState.getAgentEnergy();
		
		if(SusEnvironment.ADJACENCY_MAP.get(currentRoom).contains(destinationRoomId)) {
			
			//System.out.println("Moving to Room: "+ destinationRoomId);
			susState.setCostCalculated(susState.getCostCalculated() + this.getCost());
			susState.setAgentPosition(destinationRoomId);
			susState.setAgentEnergy(energy - 1);
			
			return susState;
		}
		
		return null;
	}

    /**
     * This method updates the agent state and the real world state.
     */
	@Override
	public EnvironmentState execute(AgentState ast, EnvironmentState est) {

		SusEnvironmentState envState = (SusEnvironmentState) est;
		SusAgentState susState = (SusAgentState) ast;
		
		int currentRoom = envState.getAgentPosition();
		int energy = envState.getAgentEnergy();
		
		if(SusEnvironment.ADJACENCY_MAP.get(currentRoom).contains(destinationRoomId)) {
			
			envState.setAgentPosition(destinationRoomId);
			envState.setAgentEnergy(energy - 1);
			
			susState.setAgentPosition(destinationRoomId);
			susState.setAgentEnergy(susState.getAgentEnergy() - 1);
			susState.setCostCalculated(susState.getCostCalculated() + this.getCost());
			
			return est;
		}		
		
		return null;
	}
	
	@Override
	public Double getCost() {
		return 100.0;
	}

	@Override
	public String toString() {
		return "Go To " + destinationRoomId + " - " + SusEnvironment.ROOM_NAMES.get(destinationRoomId);
	}

}
