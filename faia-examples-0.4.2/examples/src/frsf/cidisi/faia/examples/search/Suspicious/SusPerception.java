package frsf.cidisi.faia.examples.search.Suspicious;

import java.util.HashMap;
import java.util.HashSet;

import frsf.cidisi.faia.agent.Agent;
import frsf.cidisi.faia.agent.Perception;
import frsf.cidisi.faia.environment.Environment;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

public class SusPerception extends Perception{
	/* TODO In the perception the agent needs to know:
	 * - current Room: crewmate qty on the room, sabotage task in the room
	 * - adjacent rooms: crewmate qty on the rooms, sabotage tasks in the rooms
	 * - Cada cierto tiempo aleatorio (de 3 a 5 ciclos),
	 * 	 	el agente utiliza el sensor extrasensorial (omnisciencia)
	 * 	 	con el cual percibe la ubicación de todos los tripulantes en la nave (mapa completo).
	 */
	
	//Initials
	private boolean isGlobal;
	private int agentPosition;
	private int agentEnergy;
	private int crewmateQuantity;
	private int sabotageTasksLeft;
	private HashSet<Integer> sabotageTasksPositions;
	private HashMap<Integer, Integer> aliveCrewmatesPositions;
	
	/**
     * This method is used to setup the perception.
     */
	@Override
	public void initPerception(Agent agent, Environment environment) {
		/* TODO: Check if the workflow goes thru here.
		 * In the initial perception the agent needs to get
		 * their position, initial energy, crewmate quantity
		 */ 
		throw new NotImplementedException();	
	}
	
	public SusPerception(Agent agent, Environment environment) {
		super(agent,environment);
	}

	//Generates a new perception
	public SusPerception(boolean isGlobal) {
		this.isGlobal = isGlobal;
	}

	public int getAgentPosition() {
		return agentPosition;
	}


	public void setAgentPosition(int agentPosition) {
		this.agentPosition = agentPosition;
	}

	public boolean getIsGlobal() {
		return isGlobal;
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
		return "SusPerception [agentPosition=" + agentPosition + ", isGlobal=" + isGlobal + ", agentEnergy=" + agentEnergy + ", crewmateQuantity="
				+ crewmateQuantity + "]";
	}
	
    public int getSabotageTasksLeft() {
		return sabotageTasksLeft;
	}

	public void setSabotageTasksLeft(int sabotageTasksLeft) {
		this.sabotageTasksLeft = sabotageTasksLeft;
	}

	public HashSet<Integer> getSabotageTasksPositions() {
		return sabotageTasksPositions;
	}

	public void setSabotageTasksPositions(HashSet<Integer> sabotageTasksPositions) {
		this.sabotageTasksPositions = sabotageTasksPositions;
	}

	public HashMap<Integer, Integer> getAliveCrewmatesPositions() {
		return aliveCrewmatesPositions;
	}

	public void setAliveCrewmatesPositions(HashMap<Integer, Integer> aliveCrewmatesPositons) {
		this.aliveCrewmatesPositions = aliveCrewmatesPositons;
	}
}
