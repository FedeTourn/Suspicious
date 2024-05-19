package frsf.cidisi.faia.examples.search.Suspicious;

import frsf.cidisi.faia.solver.search.IEstimatedCostFunction;
import frsf.cidisi.faia.solver.search.NTree;

/**
 * This class allows to define a function to be used by any
 * informed search strategy, like A Star or Greedy.
 */
public class Heuristic implements IEstimatedCostFunction {

    /**
     * It returns the estimated cost to reach the goal from a NTree node.
     */
    @Override
    public double getEstimatedCost(NTree node) {
        SusAgentState susState = (SusAgentState) node.getAgentState();

        return (susState.getCrewmatesLeft() + susState.getSabotageTasksLeft());
    }
}
