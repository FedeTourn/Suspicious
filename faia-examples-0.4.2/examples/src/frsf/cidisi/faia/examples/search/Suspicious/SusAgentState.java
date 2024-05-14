package frsf.cidisi.faia.examples.search.Suspicious;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

import frsf.cidisi.faia.agent.Perception;
import frsf.cidisi.faia.agent.search.SearchBasedAgentState;
import frsf.cidisi.faia.examples.search.pacman.PacmanAgentState;

public class SusAgentState  extends SearchBasedAgentState {

	//Estado del impostor:
	private HashSet<Integer> sabotageTasksPositions;
	//(CrewmateId, RoomId)
	private HashMap<Integer, Integer> aliveCrewmatesPositions;
	
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
		
		SusAgentState newState = new SusAgentState();
		newState.setAgentEnergy(agentEnergy);
		newState.setAgentPosition(agentPosition);
		newState.setCrewmateQuantity(crewmatesLeft);
		newState.setSabotageTasksLeft(sabotageTasksLeft);

		HashMap<Integer, Integer> copyCrewmatesPositions = new HashMap<Integer, Integer>();
		
		for(Integer key : aliveCrewmatesPositions.keySet()) {
			copyCrewmatesPositions.put(key, aliveCrewmatesPositions.get(key));
		}
		
		newState.setAliveCrewmatesPositions(copyCrewmatesPositions);
		
		HashSet<Integer> copySabotageTasksPositions = new HashSet<Integer>();
		
		for(Integer value : sabotageTasksPositions) {
			copySabotageTasksPositions.add(value);
		}
		
		newState.setSabotageTasksPositions(copySabotageTasksPositions);
		
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
		sabotageTasksLeft = susPerception.getSabotageTasksLeft();
		
		//Update Percepted Crewmates
		HashMap<Integer, Integer> perceptedCrewmatesPositions = susPerception.getAliveCrewmatesPositions();
		
		for(Integer cwId : perceptedCrewmatesPositions.keySet()) {
			aliveCrewmatesPositions.put(cwId ,perceptedCrewmatesPositions.get(cwId));
		}	
		
		if (susPerception.getIsGlobal()) {
			sabotageTasksPositions = susPerception.getSabotageTasksPositions();
		}
	}
	
    /**
     * This method sets the initial state of the agent.
     */
	@Override
	public void initState() {
		// Initialize empty rooms
		aliveCrewmatesPositions = new HashMap<Integer, Integer>();
		sabotageTasksPositions = new HashSet<Integer>();

	}
	
	@Override
	public String toString() {
		return "SusAgentState \n\t[aliveCrewmatesPositions=\n\t" + aliveCrewmatesPositions + ",\n\t sabotageTasksPositions=" + sabotageTasksPositions + ",\n\t agentPosition=" + agentPosition + ",\n\t agentEnergy="
				+ agentEnergy + ",\n\t crewmatesLeft=" + crewmatesLeft + ",\n\t sabotageTasksLeft=" + sabotageTasksLeft + "]";
	}
	
	@Override
	public boolean equals(Object obj) {
		
		if (!(obj instanceof SusAgentState))
            return false;

        SusAgentState otherState = (SusAgentState) obj;
    	
    	if(this.agentPosition != otherState.agentPosition) return false;
    	
    	//if(this.agentEnergy != otherState.agentEnergy) return false;
    	
    	if(this.crewmatesLeft != otherState.crewmatesLeft) return false;
    	
    	if (this.sabotageTasksLeft != otherState.sabotageTasksLeft) return false;
    	
    	if (this.sabotageTasksPositions.size() != otherState.sabotageTasksPositions.size()) return false;
    	
    	for (Integer value : this.sabotageTasksPositions) {
    		if (!otherState.sabotageTasksPositions.contains(value)) return false;
    	}
    	
    	if (this.aliveCrewmatesPositions.size() != otherState.aliveCrewmatesPositions.size()) return false;
    	
    	for (Integer key : this.aliveCrewmatesPositions.keySet()) {
    		
    		if (!otherState.aliveCrewmatesPositions.containsKey(key)) return false;
    		
    		if (this.aliveCrewmatesPositions.get(key) != otherState.aliveCrewmatesPositions.get(key)) return false;
    	}
        
        return true;
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

	public void setAliveCrewmatesPositions(HashMap<Integer, Integer> crewmatesPositions) {
		this.aliveCrewmatesPositions = crewmatesPositions;
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

	public Integer getCrewmatesLeft() {
		return crewmatesLeft;
	}

	public void setCrewmatesLeft(Integer crewmatesLeft) {
		this.crewmatesLeft = crewmatesLeft;
	}

}
