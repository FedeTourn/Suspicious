package frsf.cidisi.faia.examples.search.Suspicious;

import frsf.cidisi.faia.agent.Action;
import frsf.cidisi.faia.agent.Perception;
import frsf.cidisi.faia.environment.Environment;
import frsf.cidisi.faia.state.EnvironmentState;

public class SusEnvironment extends Environment{
	
	

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
		// TODO Auto-generated method stub
		return super.agentFailed(actionReturned);
	}


	/**
     * This method is called by the simulator. Given the Agent, it creates
     * a new perception reading, for example, the agent position.
     * @param agent
     * @return A perception that will be given to the agent by the simulator.
     */
	@Override
	public Perception getPercept() {
		// TODO Auto-generated method stub
		return null;
	}

}
