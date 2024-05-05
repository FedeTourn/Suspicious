package frsf.cidisi.faia.examples.search.Suspicious.actions;

import frsf.cidisi.faia.agent.search.SearchAction;
import frsf.cidisi.faia.agent.search.SearchBasedAgentState;
import frsf.cidisi.faia.examples.search.Suspicious.SusAgentState;
import frsf.cidisi.faia.examples.search.Suspicious.SusEnvironmentState;
import frsf.cidisi.faia.examples.search.pacman.PacmanAgentState;
import frsf.cidisi.faia.state.AgentState;
import frsf.cidisi.faia.state.EnvironmentState;

public class Kill extends SearchAction {

    /**
     * This method updates a tree node state when the search process is running.
     * It does not updates the real world state.
     */
	@Override
	public SearchBasedAgentState execute(SearchBasedAgentState s) {
		SusAgentState susState = (SusAgentState) s;
		
		/* The 'Kill' action can be selected only if there is a crewmate
		 * in the current room and if the energy is more than 1.
		 * Otherwise return null.*/
		int energy = susState.getAgentEnergy();
		int room = susState.getAgentPosition();
		int crewQty = susState.getCrewmateQuantity();
		
		if(energy > 1) {
            // There is no more crewmate if we kill it
			susState.setCrewmateQuantity(crewQty-1);
			//
			return susState;
		}
		return null;
	}
	
    /**
     * This method updates the agent state and the real world state.
     */
	@Override
	public EnvironmentState execute(AgentState ast, EnvironmentState est) {
		
		SusEnvironmentState environmentState = (SusEnvironmentState) est;
        SusAgentState susState = ((SusAgentState) ast);
        
        int energy = environmentState.getAgentEnergy();
        
        if(energy > 1) {
        	//TODO: Update the real world state
        	
        	//TODO: Update the agent state
        	
        	
        	return environmentState;
        }

		
		return null;
	}

	@Override
	public Double getCost() {
		return 1.0;
	}
	
	@Override
	public String toString() {
		return "Kill";
	}

}
