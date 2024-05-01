package frsf.cidisi.faia.examples.matlab.simple_water_tank;

import frsf.cidisi.faia.agent.Action;
import frsf.cidisi.faia.examples.matlab.simple_water_tank.actions.ChangeVolumeFlow;
import frsf.cidisi.faia.solver.Solve;

public class ReactiveSolver extends Solve {

    @Override
    public void showSolution() {
        // TODO Auto-generated method stub
    }

    @Override
    public Action solve(Object[] params) throws Exception {
        SimpleWaterTankAgentState tankState =
                (SimpleWaterTankAgentState) params[0];

        // Calculate the error
        double error = 30 - tankState.getTankHeight();

        // Calculate new volume flow
        double newVolumeFlow = 2.00 * error + 59;

        Action action = new ChangeVolumeFlow(newVolumeFlow);

        return action;
    }
}
