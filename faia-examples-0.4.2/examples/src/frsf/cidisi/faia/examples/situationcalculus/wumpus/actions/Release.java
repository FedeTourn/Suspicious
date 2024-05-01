package frsf.cidisi.faia.examples.situationcalculus.wumpus.actions;

import frsf.cidisi.faia.agent.situationcalculus.SituationCalculusAction;
import frsf.cidisi.faia.examples.situationcalculus.wumpus.WumpusEnvironmentState;
import frsf.cidisi.faia.examples.situationcalculus.wumpus.WumpusEnvironmentState.CellState;
import frsf.cidisi.faia.state.AgentState;
import frsf.cidisi.faia.state.EnvironmentState;
import java.util.Vector;

public class Release extends SituationCalculusAction {

    @Override
    public EnvironmentState execute(AgentState ast, EnvironmentState est) {
        WumpusEnvironmentState environmentState =
                (WumpusEnvironmentState) est;

        int row = environmentState.getAgentPosition()[0];
        int col = environmentState.getAgentPosition()[1];

        if (environmentState.getAgentHoldingGold()) {
            environmentState.setAgentHoldingGold(false);
            environmentState.addCellState(row, col, CellState.GOLD);
        }

        return environmentState;
    }

    @Override
    public String toString() {
        return "release";
    }
}
