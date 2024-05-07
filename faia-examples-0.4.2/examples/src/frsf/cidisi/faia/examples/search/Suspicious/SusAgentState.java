package frsf.cidisi.faia.examples.search.Suspicious;

import java.util.HashMap;
import java.util.HashSet;

import frsf.cidisi.faia.agent.Perception;
import frsf.cidisi.faia.agent.search.SearchBasedAgentState;

public class SusAgentState  extends SearchBasedAgentState {

	//Estado del impostor:
	private HashMap<Integer, RoomState> roomStates;
	private HashMap<Integer, HashSet<Integer>> adjacencyMap;
	private Integer agentPosition, agentEnergy, crewmatesLeft, sabotageTasksLeft;

	
	// Creates the new state
	// MAPA
	public SusAgentState() {
		this.initState();
	}
	
    /**
     * This method clones the state of the agent. It's used in the search
     * process, when creating the search tree.
     */
	@Override
	public SearchBasedAgentState clone() {
		//TODO: Control, maybe there is unnecessary sets
		SusAgentState newState = new SusAgentState();
		newState.setAgentEnergy(agentEnergy);
		newState.setAgentPosition(agentPosition);
		newState.setCrewmateQuantity(crewmatesLeft);
		newState.setRoomStates(roomStates);
		newState.setSabotageTasksLeft(sabotageTasksLeft);
		
		
		return newState;
	}
	
    /**
     * This method is used to update the sus State when a Perception is
     * received by the Simulator.
     */
	@Override
	public void updateState(Perception p) {
		/* The perception has to contain the state of the room that the agent entered
		 * (list of crewmates, adjacency list, sabotage tasks available) * 
		 */
		SusPerception susPerception = (SusPerception) p;
		
		//This is for the first perception (to set the agent state)
		agentEnergy = susPerception.getAgentEnergy();
		agentPosition = susPerception.getAgentPosition();
		crewmatesLeft = susPerception.getCrewmateQuantity();
		
		// For the next perceptions the agent only needs the current roomState
		// TODO and its adjacent roomStates
		RoomState currentRoom = susPerception.getRoomState();
		
		this.updateRoomStates(currentRoom);
		
	}
	
    /**
     * This method sets the initial state of the agent.
     */
	@Override
	public void initState() {
		// Initialize empty rooms
		roomStates = new HashMap<Integer, RoomState>();
		for (Integer key : SusEnvironment.ROOM_NAMES.keySet()) {
			roomStates.put(key, new RoomState(key));
		}
		
		// Sets the adjacency map
		setAdjacencyMap(SusEnvironment.ADJACENCY_MAP);
		
		//Set sabotage tasks
		//1 - Reactor
		roomStates.get(1).setHasSabotageTask(true);
		//4 - Electrical
		roomStates.get(4).setHasSabotageTask(true);
		//10 - Weapons
		roomStates.get(10).setHasSabotageTask(true);
		
		sabotageTasksLeft = 3;
		
		//System.out.println("The workflow goes thru waypoint 1 \n" + this.toString());

	}
	
	@Override
	public String toString() {
		return "SusAgentState \n\t[roomStates=\n\t" + roomStates + ",\n\t agentPosition=" + agentPosition + ",\n\t agentEnergy="
				+ agentEnergy + ",\n\t crewmatesLeft=" + crewmatesLeft + ",\n\t sabotageTasksLeft=" + sabotageTasksLeft + "]";
	}
	
	@Override
	public boolean equals(Object obj) {
		// TODO Auto-generated method stub
		return false;
	}
	/**
	 * This function updates the current room, so we only actualize only a part of the 'picture'
	 */
	public void updateRoomStates(RoomState rs) {
		
		this.roomStates.replace(rs.getId(), rs);
		
	}
	
	public HashMap<Integer, RoomState> getRoomStates() {
		return roomStates;
	}

	public void setRoomStates(HashMap<Integer, RoomState> roomStates) {
		this.roomStates = roomStates;
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
		return crewmatesLeft;
	}

	public void setCrewmateQuantity(Integer crewmateQuantity) {
		this.crewmatesLeft = crewmateQuantity;
	}

	public Integer getSabotageTasksLeft() {
		return sabotageTasksLeft;
	}

	public void setSabotageTasksLeft(Integer sabotageTasksLeft) {
		this.sabotageTasksLeft = sabotageTasksLeft;
	}

	public HashMap<Integer, HashSet<Integer>> getAdjacencyMap() {
		return adjacencyMap;
	}

	public void setAdjacencyMap(HashMap<Integer, HashSet<Integer>> adjacencyMap) {
		this.adjacencyMap = adjacencyMap;
	}

	public Integer getCrewmatesLeft() {
		return crewmatesLeft;
	}

	public void setCrewmatesLeft(Integer crewmatesLeft) {
		this.crewmatesLeft = crewmatesLeft;
	}

}
