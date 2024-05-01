package frsf.cidisi.faia.examples.matlab.simple_water_tank;

import java.util.logging.Level;
import java.util.logging.Logger;

import frsf.cidisi.faia.agent.Action;
import frsf.cidisi.faia.agent.Perception;
import frsf.cidisi.faia.agent.reactive.ReactiveBasedAgent;

public class SimpleWaterTankAgent extends ReactiveBasedAgent {

    public SimpleWaterTankAgent() {
        this.setAgentState(new SimpleWaterTankAgentState());
    }

    @Override
    public void see(Perception p) {
        this.getAgentState().updateState(p);
    }

    @Override
    public SimpleWaterTankAgentState getAgentState() {
        return (SimpleWaterTankAgentState) super.getAgentState();
    }

    @Override
    public Action selectAction() {
        ReactiveSolver tankSolver = new ReactiveSolver();

        this.setSolver(tankSolver);

        // It gets the best action from the solver
        Action selectedAction = null;
        try {
            selectedAction =
                    this.getSolver().solve(new Object[]{this.getAgentState()});
        } catch (Exception ex) {
            Logger.getLogger(SimpleWaterTankAgent.class.getName()).log(Level.SEVERE, null, ex);
        }

        return selectedAction;
    }
}
