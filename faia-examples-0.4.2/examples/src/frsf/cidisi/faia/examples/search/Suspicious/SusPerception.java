package frsf.cidisi.faia.examples.search.Suspicious;

import java.util.HashMap;

import frsf.cidisi.faia.agent.Agent;
import frsf.cidisi.faia.agent.Perception;
import frsf.cidisi.faia.environment.Environment;

public class SusPerception extends Perception{
	/* TODO In the perception the agent needs to know:
	 * - current Room: crewmate qty on the room, sabotage task in the room
	 * - adjacent rooms: crewmate qty on the rooms, sabotage tasks in the rooms
	 * - Cada cierto tiempo aleatorio (de 3 a 5 ciclos),
	 * 	 	el agente utiliza el sensor extrasensorial (omnisciencia)
	 * 	 	con el cual percibe la ubicación de todos los tripulantes en la nave (mapa completo).
	 */
	
	
	//Initials
	private int agentPosition;
	private int agentEnergy;
	private int crewmateQuantity;
	private int sabotageTasksLeft;
	
	//
	private RoomState roomState;
	private HashMap<Integer, RoomState> roomStates;
	


	/**
     * This method is used to setup the perception.
     */
	@Override
	public void initPerception(Agent agent, Environment environment) {
		/* TODO: Check if the workflow goes thru here.
		 * In the initial perception the agent needs to get
		 * their position, initial energy, crewmate quantity
		 */ 
		SusAgent susAgent = (SusAgent) agent;
		SusEnvironment susEnvironment = (SusEnvironment) environment;
		SusEnvironmentState susEnvironmentState = susEnvironment.getEnvironmentState();
		
		this.setAgentPosition(susEnvironmentState.getAgentPosition());
		this.setAgentEnergy(susEnvironmentState.getAgentEnergy());
		this.setCrewmateQuantity(susEnvironmentState.getInitialCrewmateQuantity());
		
		
	}
	
	public SusPerception(Agent agent, Environment environment) {
		super(agent,environment);
	}

	//Generates a new perception
	public SusPerception() {
		// Set everything to a non-error value
		agentPosition = -1;
		agentEnergy = -1;
		crewmateQuantity = -1;
		//sabotageTasksLeft = -1;
		roomState = null;
		//System.out.println("The workflow goes thru waypoint P \n PERCEPTION:\n" + this.toString());
	}

	public int getAgentPosition() {
		return agentPosition;
	}


	public void setAgentPosition(int agentPosition) {
		this.agentPosition = agentPosition;
	}


	public int getAgentEnergy() {
		return agentEnergy;
	}


	public void setAgentEnergy(int agentEnergy) {
		this.agentEnergy = agentEnergy;
	}


	public int getCrewmateQuantity() {
		return crewmateQuantity;
	}


	public void setCrewmateQuantity(int crewmateQuantity) {
		this.crewmateQuantity = crewmateQuantity;
	}

	@Override
	public String toString() {
		return "SusPerception [agentPosition=" + agentPosition + ", agentEnergy=" + agentEnergy + ", crewmateQuantity="
				+ crewmateQuantity + "]";
	}
	
    public int getSabotageTasksLeft() {
		return sabotageTasksLeft;
	}

	public void setSabotageTasksLeft(int sabotageTasksLeft) {
		this.sabotageTasksLeft = sabotageTasksLeft;
	}

	public HashMap<Integer, RoomState> getRoomStates() {
		return roomStates;
	}

	public void setRoomStates(HashMap<Integer, RoomState> roomStates) {
		this.roomStates = roomStates;
	}

	public RoomState getRoomState() {
		return roomState;
	}

	public void setRoomState(RoomState roomState) {
		this.roomState = roomState;
	}
}
