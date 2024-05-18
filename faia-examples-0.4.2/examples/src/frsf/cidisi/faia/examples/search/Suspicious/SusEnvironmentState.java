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
	private Integer initialCrewmateQuantity, initialAgentEnergy;
	private Integer agentPosition, agentEnergy, nextGlobalPerception, currentCrewmateQuantity;	

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
		
		// Set crewmates in rooms
		initialCrewmateQuantity = 4;
		currentCrewmateQuantity = initialCrewmateQuantity;
	
		
		for (int i = 0; i < initialCrewmateQuantity; i++) { 
			Integer initialPosition = NumberGeneratorHelper.generateRoomId();
		  	
			RoomState initialRoom = roomStates.get(initialPosition); 
		  	
		  	Integer	initialNextJump = NumberGeneratorHelper.generateCrewmateNextJump(); 
		  	
		  	Crewmate newCM = new Crewmate(i, initialNextJump, initialRoom); 
		  	
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
		setAgentPosition(NumberGeneratorHelper.generateRoomId());
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
//		for (RoomState room : roomStates.values()) {
//			result += room.toString();
//		}
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
	
	public HashMap<Integer, RoomState> getRoomStates() {
		return roomStates;
	}

	public Integer getNextGlobalPerception() {
		return nextGlobalPerception;
	}

	public Integer getCurrentCrewmateQuantity() {
		return currentCrewmateQuantity;
	}

	public void setCurrentCrewmateQuantity(Integer currentCrewmateQuantity) {
		this.currentCrewmateQuantity = currentCrewmateQuantity;
	}

	public void setNextGlobalPerception(Integer nextGlobalPerception) {
		this.nextGlobalPerception = nextGlobalPerception;
	}
	
	public void setRoomStates(HashMap<Integer, RoomState> roomStates) {
		this.roomStates = roomStates;
	}

	public Integer getSabotageTasksLeft() {
		return (int) roomStates.keySet().stream().filter(key -> roomStates.get(key).getHasSabotageTask()).count();
	}

}

