package frsf.cidisi.faia.examples.search.Suspicious;

import frsf.cidisi.faia.agent.Agent;
import frsf.cidisi.faia.agent.Perception;
import frsf.cidisi.faia.environment.Environment;

public class SusPerception extends Perception{
	
	
	private int agentPosition;
	private int agentEnergy;
	private int crewmateQuantity;

	
    /**
     * This method is used to setup the perception.
     */
	@Override
	public void initPerception(Agent agent, Environment environment) {
		/* TODO in the initial perception the agent needs to get
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
		// TODO Auto-generated constructor stub
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
}
