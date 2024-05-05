package frsf.cidisi.faia.examples.search.Suspicious.actions;

import frsf.cidisi.faia.agent.search.SearchAction;
import frsf.cidisi.faia.agent.search.SearchBasedAgentState;
import frsf.cidisi.faia.examples.search.Suspicious.RoomState;
import frsf.cidisi.faia.examples.search.Suspicious.SusAgentState;
import frsf.cidisi.faia.examples.search.Suspicious.SusEnvironmentState;
import frsf.cidisi.faia.state.AgentState;
import frsf.cidisi.faia.state.EnvironmentState;

public class Sabotage extends SearchAction {

    /**
     * This method updates a tree node state when the search process is running.
     * It does not updates the real world state.
     */
	@Override
	public SearchBasedAgentState execute(SearchBasedAgentState s) {
		SusAgentState susState = (SusAgentState) s;
		
		/* The 'Sabotage' action can be selected only if there room
		 * has sabotage tasks available and the agent has enough energy
		 * to do the action and stay alive. Otherwise return 'null'.*/
		int energy = susState.getAgentEnergy();
		int tasks = susState.getSabotageTasksLeft();
		int room = susState.getAgentPosition(); 
		
		//TODO: Find the way to get the tasks available in the room
		
		if(energy > 1) {
			/* If the action is Sabotage, then the actual room has no
			 * more sabotage tasks available and the .*/
			susState.setSabotageTasksLeft(tasks-1);
			susState.setAgentEnergy(energy-1);
			//TODO: set the task in the room as null
			
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
		SusAgentState sustState = ((SusAgentState) ast);
		
		
		int room = environmentState.getAgentPosition();
		
		/*
	     * TODO: Como obtengo el roomState? pq lo necesito para saber 
	     * si puedo sabotear
	     */
		
		return null;
	}

	@Override
	public Double getCost() {
		return 1.0;
	}
    /**
     * This method is not important for a search based agent, but is essensial
     * when creating a calculus based one.
     */
	@Override
	public String toString() {
		return "Sabotage";
	}

}
