package frsf.cidisi.faia.examples.search.Suspicious;

import java.util.HashMap;

import frsf.cidisi.faia.agent.Perception;
import frsf.cidisi.faia.agent.search.SearchBasedAgentState;

public class SusAgentState  extends SearchBasedAgentState {
	public HashMap<Integer, RoomState> getRoomStates() {
		return roomStates;
	}

	public void setRoomStates(HashMap<Integer, RoomState> roomStates) {
		this.roomStates = roomStates;
	}

	//Estado del impostor:
	private HashMap<Integer, RoomState> roomStates;
	private Integer agentPosition, agentEnergy, crewmateQuantity, sabotageTasksLeft;

	
	// Creates the new state
	// MAPA
	public SusAgentState() {
		// Initialize empty rooms
		roomStates = new HashMap<Integer, RoomState>();
		for (Integer key : SusEnvironment.ROOM_NAMES.keySet()) {
			roomStates.put(key, new RoomState(key));
		}
		
		//Set sabotage tasks
		//1 - Reactor
		roomStates.get(1).setHasSabotageTask(true);
		//4 - Electrical
		roomStates.get(4).setHasSabotageTask(true);
		//10 - Weapons
		roomStates.get(10).setHasSabotageTask(true);
		
		
	}

	public Integer getAgentPosition() {
		return agentPosition;
	}

	public void setAgentPosition(Integer agentPosition) {
		this.agentPosition = agentPosition;
	}

	public Integer getAgentEnergy() {
		return agentEnergy;
	}

	public void setAgentEnergy(Integer agentEnergy) {
		this.agentEnergy = agentEnergy;
	}

	public Integer getCrewmateQuantity() {
		return crewmateQuantity;
	}

	public void setCrewmateQuantity(Integer crewmateQuantity) {
		this.crewmateQuantity = crewmateQuantity;
	}

	public Integer getSabotageTasksLeft() {
		return sabotageTasksLeft;
	}

	public void setSabotageTasksLeft(Integer sabotageTasksLeft) {
		this.sabotageTasksLeft = sabotageTasksLeft;
	}

	@Override
	public boolean equals(Object obj) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public SearchBasedAgentState clone() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void updateState(Perception p) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void initState() {
		// TODO Auto-generated method stub
		
	}

}
