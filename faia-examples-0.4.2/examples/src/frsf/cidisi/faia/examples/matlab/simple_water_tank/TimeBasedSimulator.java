package frsf.cidisi.faia.examples.matlab.simple_water_tank;

import java.util.Arrays;
import java.util.Vector;

import frsf.cidisi.faia.agent.Action;
import frsf.cidisi.faia.agent.Agent;
import frsf.cidisi.faia.agent.NoAction;
import frsf.cidisi.faia.environment.MatlabEnvironment;
import frsf.cidisi.faia.simulator.ReactiveBasedAgentSimulator;
import frsf.cidisi.faia.state.MatlabEnvironmentState;

public class TimeBasedSimulator extends ReactiveBasedAgentSimulator {

    public TimeBasedSimulator(MatlabEnvironment environment, Vector<Agent> agent) {
        super(environment, agent);
    }

    public TimeBasedSimulator(MatlabEnvironment environment, Agent agent) {
        this(environment, new Vector<Agent>(Arrays.asList(agent)));
    }

    @Override
    public MatlabEnvironment getEnvironment() {
        return (MatlabEnvironment) this.environment;
    }

    @Override
    public boolean agentSucceeded(Action actionReturned) {
        MatlabEnvironmentState environmentState =
                this.getEnvironment().getEnvironmentState();

        if (actionReturned instanceof NoAction) {
            return true;
        }

        if (environmentState.getEndTime() >= 100) {
            return true;
        }

        return false;
    }
}
