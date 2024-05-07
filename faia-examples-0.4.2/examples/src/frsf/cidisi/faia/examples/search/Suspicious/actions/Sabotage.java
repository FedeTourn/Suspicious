package frsf.cidisi.faia.examples.search.Suspicious.actions;

import java.util.HashMap;

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
		int actRoom = susState.getAgentPosition();
		HashMap<Integer, RoomState> rooms = susState.getRoomStates();
		
		RoomState room = rooms.get(actRoom);

		//System.out.println(actRoom + " - Sabotage Task in room: " + room.getHasSabotageTask());
				
		if((energy > 1) && (room.getHasSabotageTask())) {
			/* If the action is Sabotage, then the actual room has no
			 * more sabotage tasks available and the agent knows it,
			 * also the agent looses one energy point.*/

			room.setHasSabotageTask(false);
			rooms.replace(actRoom, room);
			
			//System.out.println(actRoom + " - Sabotage Task in room: " + room.getHasSabotageTask());
			susState.setRoomStates(rooms);
			susState.setSabotageTasksLeft(tasks-1);
			susState.setAgentEnergy(energy-1);
			
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
		
		int tasks = susState.getSabotageTasksLeft();
		int energy = environmentState.getAgentEnergy();
		int actRoom = environmentState.getAgentPosition();
		HashMap<Integer, RoomState> rooms = environmentState.getRoomStates();
		RoomState room = rooms.get(actRoom);
		
		if((energy > 1) && (room.getHasSabotageTask())) {
			// Update the real world
			room.setHasSabotageTask(false);
			rooms.replace(actRoom, room);
			
			environmentState.setRoomStates(rooms);
			environmentState.setAgentEnergy(energy-1);
			
			// Update the sus State
			susState.setRoomStates(rooms);
			susState.setSabotageTasksLeft(tasks-1);
			susState.setAgentEnergy(energy-1);
			
			return environmentState;
		}
		
		return null;
	}

	@Override
	public Double getCost() {
		return 1.0;
	}
    /**
     * This method is not important for a search based agent, but is essential
     * when creating a calculus based one.
     */
	@Override
	public String toString() {
		return "Sabotage";
	}

}
