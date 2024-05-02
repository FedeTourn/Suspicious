package frsf.cidisi.faia.examples.search.Suspicious;

import frsf.cidisi.faia.agent.Agent;
import frsf.cidisi.faia.agent.Perception;
import frsf.cidisi.faia.environment.Environment;

public class SusPerception extends Perception{

	
    /**
     * This method is used to setup the perception.
     */
	@Override
	public void initPerception(Agent agent, Environment environment) {
		// TODO Auto-generated method stub
		SusAgent susAgent = (SusAgent) agent;
		SusEnvironment susEnvironment = (SusEnvironment) environment;
		SusEnvironmentState susEnvironmentState = susEnvironment.getEnvironmentState();
	}

}
