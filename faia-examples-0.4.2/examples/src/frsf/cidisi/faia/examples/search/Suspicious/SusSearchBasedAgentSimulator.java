package frsf.cidisi.faia.examples.search.Suspicious;

import frsf.cidisi.faia.agent.Action;
import frsf.cidisi.faia.agent.Agent;
import frsf.cidisi.faia.agent.GoalBasedAgent;
import frsf.cidisi.faia.agent.Perception;
import frsf.cidisi.faia.environment.Environment;
import frsf.cidisi.faia.examples.search.Suspicious.actions.GoTo;
import frsf.cidisi.faia.simulator.SearchBasedAgentSimulator;
import frsf.cidisi.faia.simulator.events.EventType;
import frsf.cidisi.faia.simulator.events.SimulatorEventNotifier;

public class SusSearchBasedAgentSimulator extends SearchBasedAgentSimulator {

	public SusSearchBasedAgentSimulator(Environment environment, Agent agent) {
		super(environment, agent);
		// TODO Auto-generated constructor stub
	}
	
	@Override
    public void start() {

        System.out.println("----------------------------------------------------");
        System.out.println("--- " + this.getSimulatorName() + " ---");
        System.out.println("----------------------------------------------------");
        System.out.println();

        Perception perception;
        Action action;
        SusAgent agent;
        SusEnvironment environment;

        agent = (SusAgent) this.getAgents().firstElement();
        environment = (SusEnvironment) this.getEnvironment();

        /*
         * Simulation starts. The environment sends perceptions to the agent, and
         * it returns actions. The loop condition evaluation is placed at the end.
         * This works even when the agent starts with a goal state (see agentSucceeded
         * method in the SearchBasedAgentSimulator).
         */
        
        do {

            System.out.println("------------------------------------");

            System.out.println("Sending perception to agent...");
            perception = this.getPercept();
            agent.see(perception);
            System.out.println("Perception: " + perception);

            System.out.println("Agent State: " + agent.getAgentState());
            System.out.println("Environment: " + environment);

            System.out.println("Asking the agent for an action...");
            action = agent.selectAction();

            if (action == null) {
                break;
            }

            System.out.println("Action returned: " + action);
            System.out.println();

            this.updateActionsPriorities(agent, action);
            
            this.actionReturned(agent, action);
            
            environment.updateCrewmates();

        } while (!this.agentSucceeded(action) && !this.agentFailed(action));

        // Check what happened, if agent has reached the goal or not.
        if (this.agentSucceeded(action)) {
            System.out.println("Agent has reached the goal!");
        } else {
            System.out.println("ERROR: The simulation has finished, but the agent has not reached his goal.");
        }

        // Leave a blank line
        System.out.println();

        // FIXME: This call can be moved to the Simulator class
        this.environment.close();

        // Launch simulationFinished event
        SimulatorEventNotifier.runEventHandlers(EventType.SimulationFinished, null);
        
    }

	private void updateActionsPriorities(SusAgent agent, Action action) {
		 agent.updateActionsPriorities(action);
	}

}
