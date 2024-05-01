package frsf.cidisi.faia.examples.situationcalculus.wumpus.actions;

import frsf.cidisi.faia.agent.situationcalculus.SituationCalculusAction;
import frsf.cidisi.faia.examples.situationcalculus.wumpus.WumpusEnvironmentState;
import frsf.cidisi.faia.state.AgentState;
import frsf.cidisi.faia.state.EnvironmentState;

public class TurnRight extends SituationCalculusAction {

    @Override
    public EnvironmentState execute(AgentState ast, EnvironmentState est) {
        WumpusEnvironmentState environmentState =
                (WumpusEnvironmentState) est;

        String agentOrientation = environmentState.getAgentOrientation();
        String newAgentOrientation = "";

        if (agentOrientation.equals("up"))
            newAgentOrientation = "right";
        else if (agentOrientation.equals("right"))
            newAgentOrientation = "down";
        else if (agentOrientation.equals("down"))
            newAgentOrientation = "left";
        else if (agentOrientation.equals("left"))
            newAgentOrientation = "up";

        environmentState.setAgentOrientation(newAgentOrientation);

        return environmentState;
    }

    @Override
    public String toString() {
        return "turnright";
    }
}
