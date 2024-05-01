package frsf.cidisi.faia.examples.situationcalculus.wumpus.actions;

import frsf.cidisi.faia.agent.situationcalculus.SituationCalculusAction;
import frsf.cidisi.faia.examples.situationcalculus.wumpus.WumpusEnvironmentState;
import frsf.cidisi.faia.state.AgentState;
import frsf.cidisi.faia.state.EnvironmentState;

public class Forward extends SituationCalculusAction {

    @Override
    public EnvironmentState execute(AgentState ast, EnvironmentState est) {

        WumpusEnvironmentState environmentState =
                (WumpusEnvironmentState) est;

        String agentOrientation = environmentState.getAgentOrientation();
        int row = environmentState.getAgentPosition()[0];
        int col = environmentState.getAgentPosition()[1];

        int newRow = row, newCol = col;

        if (agentOrientation.equals("up"))
            newRow = row - 1;
        else if (agentOrientation.equals("down"))
            newRow = row + 1;
        else if (agentOrientation.equals("right"))
            newCol = col + 1;
        else if (agentOrientation.equals("left"))
            newCol = col - 1;

        environmentState.setAgentPosition(new int[] {newRow, newCol});

        return environmentState;
    }

    @Override
    public String toString() {
        return "forward";
    }
}
