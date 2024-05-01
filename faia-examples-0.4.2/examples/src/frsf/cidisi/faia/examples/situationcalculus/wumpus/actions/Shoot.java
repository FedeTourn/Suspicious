package frsf.cidisi.faia.examples.situationcalculus.wumpus.actions;

import frsf.cidisi.faia.agent.situationcalculus.SituationCalculusAction;
import frsf.cidisi.faia.state.AgentState;
import frsf.cidisi.faia.state.EnvironmentState;

public class Shoot extends SituationCalculusAction {

    @Override
    public EnvironmentState execute(AgentState ast, EnvironmentState est) {
        // TODO
        return est;
    }

    @Override
    public String toString() {
        return "shoot";
    }
}
