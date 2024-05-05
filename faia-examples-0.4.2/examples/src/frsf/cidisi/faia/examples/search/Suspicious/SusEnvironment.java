package frsf.cidisi.faia.examples.search.Suspicious;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.stream.Collectors;

import frsf.cidisi.faia.agent.Action;
import frsf.cidisi.faia.agent.Perception;
import frsf.cidisi.faia.environment.Environment;
import frsf.cidisi.faia.state.EnvironmentState;

public class SusEnvironment extends Environment{
	
	public static HashMap<Integer, String> ROOM_NAMES; 
	public static HashMap<Integer, HashSet<Integer>> ADJACENCY_MAP;
	
	
	static {
		String[] names = {"Upper Engine",
				"Reactor",
				"Security",
				"Lower Engine",
				"Electrical",
				"Cafeteria",
				"Medbay",
				"Admin",
				"Storage",
				"Communications",
				"Weapons",
				"O2",
				"Navigation",
				"Shields"};
		
		ROOM_NAMES = new HashMap<Integer, String>();
		for(int i = 0; i < names.length; i++) {
			ROOM_NAMES.put(i, names[i]);
		}
		
		ADJACENCY_MAP = new HashMap<Integer, HashSet<Integer>>();
		ADJACENCY_MAP.put(0, new HashSet<Integer>(Arrays.asList(1,2,3,5,6)));
		ADJACENCY_MAP.put(1, new HashSet<Integer>(Arrays.asList(0,2,3)));	
		ADJACENCY_MAP.put(2, new HashSet<Integer>(Arrays.asList(0,1,3)));	
		ADJACENCY_MAP.put(3, new HashSet<Integer>(Arrays.asList(0,1,2,4,8)));	
		ADJACENCY_MAP.put(4, new HashSet<Integer>(Arrays.asList(3,8)));	
		ADJACENCY_MAP.put(5, new HashSet<Integer>(Arrays.asList(0,6,7,8,10)));	
		ADJACENCY_MAP.put(6, new HashSet<Integer>(Arrays.asList(0,5)));	
		ADJACENCY_MAP.put(7, new HashSet<Integer>(Arrays.asList(5,8)));	
		ADJACENCY_MAP.put(8, new HashSet<Integer>(Arrays.asList(3,4,5,7,9,13)));	
		ADJACENCY_MAP.put(9, new HashSet<Integer>(Arrays.asList(8,13)));	
		ADJACENCY_MAP.put(10, new HashSet<Integer>(Arrays.asList(5,11,12,13)));	
		ADJACENCY_MAP.put(11, new HashSet<Integer>(Arrays.asList(10,12,13)));	
		ADJACENCY_MAP.put(12, new HashSet<Integer>(Arrays.asList(10,11,13)));	
		ADJACENCY_MAP.put(13, new HashSet<Integer>(Arrays.asList(8,9,10,11,12)));	
		
		//COMMENT IF NOT NEEDED
		System.out.println("Initializing Adjacency Map");
		for (Integer key : ADJACENCY_MAP.keySet()) {
			
			List<String> adjRoomNames = ADJACENCY_MAP.get(key).stream().map(adjRoom -> ROOM_NAMES.get(adjRoom)).collect(Collectors.toList());
		
			System.out.println(key + " - " + ROOM_NAMES.get(key) + " | " + String.join(", ", adjRoomNames));
		}
		System.out.println();
	}
	
	public SusEnvironment() {
		//Create the environment state
		this.environmentState = new SusEnvironmentState();
	}
	
	

	@Override
	public SusEnvironmentState getEnvironmentState() {
		return (SusEnvironmentState) super.getEnvironmentState();
	}



	@Override
	public boolean agentFailed(Action actionReturned) {
		SusEnvironmentState susEnvironmentState = this.getEnvironmentState();
		int agentEnergy = susEnvironmentState.getAgentEnergy();
		
		if (agentEnergy <=0)
			return true;
		
		return false;
	}


	/**
     * This method is called by the simulator. Given the Agent, it creates
     * a new perception reading the agent position.
     * @param agent
     * @return A perception that will be given to the agent by the simulator.
     */
	@Override
	public Perception getPercept() {
		// Create a perception to return
		SusPerception perception = new SusPerception();
		
		/* TODO Get the current position of the agent to be able to create
		 * the perception, in the perception the agent needs to know:
		 * - current Room: crewmate qty on the room, sabotage task in the room
		 * - adjacent rooms: crewmate qty on the rooms, sabotage tasks in the rooms
		 * - Cada cierto tiempo aleatorio (de 3 a 5 ciclos),
		 * 	 	el agente utiliza el sensor extrasensorial (omnisciencia)
		 * 	 	con el cual percibe la ubicación de todos los tripulantes en la nave (mapa completo).
		 */
		SusEnvironmentState environmentState = this.getEnvironmentState(); 
		
		int actRoom = environmentState.getAgentPosition();
		HashMap<Integer, RoomState> rooms = environmentState.getRoomStates();
		
		// Current Room
		RoomState room = rooms.get(actRoom); 
		ArrayList<Crewmate> crew = room.getCrewmates();
		Boolean taskAvailable = room.getHasSabotageTask();
		
		// Adjacent Rooms
		 HashSet<Integer> adjacentRooms = ADJACENCY_MAP.get(actRoom);		
		
		
		
		// Set the perception atributes
		perception.setAgentEnergy(this.getEnvironmentState().getAgentEnergy());
		perception.setAgentPosition(actRoom);
		perception.setCrewmateQuantity(this.getEnvironmentState().getInitialCrewmateQuantity());
		
		return perception;
	}

}
