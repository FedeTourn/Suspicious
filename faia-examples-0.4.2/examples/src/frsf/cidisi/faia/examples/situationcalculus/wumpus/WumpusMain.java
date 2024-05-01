package frsf.cidisi.faia.examples.situationcalculus.wumpus;

import frsf.cidisi.faia.exceptions.PrologConnectorException;
import frsf.cidisi.faia.simulator.SituationCalculusBasedAgentSimulator;

public class WumpusMain {

    /**
     * @param args
     * @throws PrologConnectorException
     */
    public static void main(String[] args) throws PrologConnectorException {
        
        WumpusAgent agent = new WumpusAgent();
        WumpusEnvironment wumpusWorld = new WumpusEnvironment();

        SituationCalculusBasedAgentSimulator simulator =
                new SituationCalculusBasedAgentSimulator(wumpusWorld, agent);

        simulator.start();
    }
}
