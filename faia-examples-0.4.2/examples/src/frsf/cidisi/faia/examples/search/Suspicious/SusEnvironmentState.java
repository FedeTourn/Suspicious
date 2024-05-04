package frsf.cidisi.faia.examples.search.Suspicious;

import java.util.HashMap;
import java.util.Random;

import frsf.cidisi.faia.state.EnvironmentState;

/**
 * This class represents the real world state.
 */
public class SusEnvironmentState extends EnvironmentState {
	//Estado del ambiente??
	//[Mapa, Energía Actual, Cantidad de tripulantes en la nave]
	
	private HashMap<Integer, RoomState> roomStates;
	private HashMap<Integer, Crewmate> crewmates;
	private Integer initialCrewmateQuantity, initialAgentEnergy;
	private Integer agentPosition, agentEnergy, nextGlobalPerception;	
	
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
		
		// Set crewmates in rooms
		initialCrewmateQuantity = 8;
		crewmates = new HashMap<Integer, Crewmate>();
		for (int i = 0; i < initialCrewmateQuantity; i++) {
			Integer initialPosition = NumberGeneratorHelper.generateRoomId(SusEnvironment.ROOM_NAMES.size());
			RoomState initialRoom = roomStates.get(initialPosition);
			Integer initialNextJump = NumberGeneratorHelper.generateCrewmateNextJump();
			Crewmate newCM = new Crewmate(i, initialNextJump, initialRoom);
			crewmates.put(i, newCM);
			initialRoom.getCrewmates().add(newCM);
		}
		
		//Set sabotage tasks
		//1 - Reactor
		roomStates.get(1).setHasSabotageTask(true);
		//4 - Electrical
		roomStates.get(4).setHasSabotageTask(true);
		//10 - Weapons
		roomStates.get(10).setHasSabotageTask(true);
		
		// Set sus in a room with their initial values
		agentPosition = NumberGeneratorHelper.generateRoomId(SusEnvironment.ROOM_NAMES.size());
		initialAgentEnergy = NumberGeneratorHelper.generateInitialAgentEnergy();
		agentEnergy = initialAgentEnergy;
		nextGlobalPerception = 0;
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
		return result;
	}

}
