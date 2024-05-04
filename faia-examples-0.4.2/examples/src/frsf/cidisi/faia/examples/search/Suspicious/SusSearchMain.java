package frsf.cidisi.faia.examples.search.Suspicious;

import frsf.cidisi.faia.exceptions.PrologConnectorException;
import frsf.cidisi.faia.simulator.SearchBasedAgentSimulator;

public class SusSearchMain {
	public static void main(String[] args) throws PrologConnectorException {
        //Creates the agent instance
		SusAgent susAgent = new SusAgent();
        
		//Creates the agent environment instance
        SusEnvironment susEnvironment = new SusEnvironment();
        
        //SearchBasedAgentSimulator simulator =
         //       new SearchBasedAgentSimulator(susEnvironment, susAgent);
        
       // simulator.start();
    }
}
