package frsf.cidisi.faia.examples.search.Suspicious;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Random;

import frsf.cidisi.faia.state.EnvironmentState;

/**
 * This class represents the real world state.
 */
public class SusEnvironmentState extends EnvironmentState {

	private HashMap<Integer, RoomState> roomStates;
	private HashMap<Integer, HashSet<Integer>> adjacencyMap;
	private HashMap<Integer, Crewmate> crewmates;
	private Integer initialCrewmateQuantity, initialAgentEnergy;
	private Integer agentPosition, agentEnergy, nextGlobalPerception;	
	
	public SusEnvironmentState() {
		this.initState();
	}
	
    /**
     * This method is used to setup the initial real world.
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
		
		// Set crewmates in rooms
		initialCrewmateQuantity = 8;
		crewmates = new HashMap<Integer, Crewmate>();
		
		for (int i = 0; i < initialCrewmateQuantity; i++) {
			Integer initialPosition = 0;
			RoomState initialRoom = roomStates.get(initialPosition);
			Integer initialNextJump = 0;
			Crewmate newCM = new Crewmate(i, initialNextJump, initialRoom);
			crewmates.put(i, newCM);
			initialRoom.getCrewmates().add(newCM);
		}
		
		/*
		 * for (int i = 0; i < initialCrewmateQuantity; i++) { Integer initialPosition =
		 * NumberGeneratorHelper.generateRoomId(SusEnvironment.ROOM_NAMES.size());
		 * RoomState initialRoom = roomStates.get(initialPosition); Integer
		 * initialNextJump = NumberGeneratorHelper.generateCrewmateNextJump(); Crewmate
		 * newCM = new Crewmate(i, initialNextJump, initialRoom); crewmates.put(i,
		 * newCM); initialRoom.getCrewmates().add(newCM); }
		 */
		
		//Set sabotage tasks
		//1 - Reactor
		roomStates.get(1).setHasSabotageTask(true);
		//4 - Electrical
		roomStates.get(4).setHasSabotageTask(true);
		//10 - Weapons
		roomStates.get(10).setHasSabotageTask(true);
		
		// Set sus in a room with their initial values
		//setAgentPosition(NumberGeneratorHelper.generateRoomId(SusEnvironment.ROOM_NAMES.size()));
		setAgentPosition(1);
		initialAgentEnergy = NumberGeneratorHelper.generateInitialAgentEnergy();
		setAgentEnergy(initialAgentEnergy);
		nextGlobalPerception = 0;
		
		//System.out.println("The workflow goes thru waypoint 2 " + this.toString());
	}

    /**
     * This method is used to transcrypt the environment into prompt-friendly format.
     */
	@Override
	public String toString() {
		String result = "World State: \n";
		result += "Next Global Agent Perception: " + nextGlobalPerception + "\n";
		for (RoomState room : roomStates.values()) {
			result += room.toString() + "\n";
		}
		result += "Agent position: " + agentPosition + "\n";
		return result;
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
	public Integer getInitialCrewmateQuantity() {
		return initialCrewmateQuantity;
	}

	public void setInitialCrewmateQuantity(Integer initialCrewmateQuantity) {
		this.initialCrewmateQuantity = initialCrewmateQuantity;
	}
	
	public HashMap<Integer, RoomState> getRoomStates() {
		return roomStates;
	}

	public void setRoomStates(HashMap<Integer, RoomState> roomStates) {
		this.roomStates = roomStates;
	}

	public HashMap<Integer, HashSet<Integer>> getAdjacencyMap() {
		return adjacencyMap;
	}

	public void setAdjacencyMap(HashMap<Integer, HashSet<Integer>> adjacencyMap) {
		this.adjacencyMap = adjacencyMap;
	}
}

