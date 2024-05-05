package frsf.cidisi.faia.examples.search.Suspicious.actions;

import java.util.HashMap;

import frsf.cidisi.faia.agent.search.SearchAction;
import frsf.cidisi.faia.agent.search.SearchBasedAgentState;
import frsf.cidisi.faia.examples.search.Suspicious.Crewmate;
import frsf.cidisi.faia.examples.search.Suspicious.RoomState;
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
		int actRoom = susState.getAgentPosition();
		int crewQty = susState.getCrewmateQuantity();
		HashMap<Integer, RoomState> rooms = susState.getRoomStates();
		RoomState room = rooms.get(actRoom);
		
		
		//if((energy > 1) && (room.getCrewmates().size() > 0)) {
		if((energy > 1) && !(room.getCrewmates().isEmpty())) {
            /* There is one crewmate less if we kill it (in the room and also in the agent counter)
             * also the agent looses one energy point.*/
			System.out.println("Crewmate List in room " + actRoom + "before the kill: " + room.getCrewmates());
			
			//TODO no se si va a funcionar
			Crewmate crewmate = room.getCrewmates().remove(0);

			System.out.println("Crewmate List in room " + actRoom + "after the kill: " + room.getCrewmates());
			
			rooms.replace(actRoom, room);
			susState.setRoomStates(rooms);
			susState.setCrewmateQuantity(crewQty-1);
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
        
        int energy = environmentState.getAgentEnergy();
        int actRoom = environmentState.getAgentPosition();
        
		int crewQty = susState.getCrewmateQuantity();
		HashMap<Integer, RoomState> rooms = environmentState.getRoomStates();
		RoomState room = rooms.get(actRoom); 
        
		//if((energy > 1) && (room.getCrewmates().size() > 0)) {
		if((energy > 1) && !(room.getCrewmates().isEmpty())) {
			//Update the real world
			System.out.println("Crewmate List in room " + actRoom + "before the kill: " + room.getCrewmates());
			
			//TODO no se si va a funcionar
			Crewmate crewmate = room.getCrewmates().remove(0);

			System.out.println("Crewmate List in room " + actRoom + "after the kill: " + room.getCrewmates());
			
			rooms.replace(actRoom, room);
			environmentState.setRoomStates(rooms);
			environmentState.setAgentEnergy(energy-1);
			
			//Update the sus State
			susState.setCrewmateQuantity(crewQty-1);
        	
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
