package frsf.cidisi.faia.examples.search.Suspicious;

import frsf.cidisi.faia.solver.search.IStepCostFunction;
import frsf.cidisi.faia.solver.search.NTree;

/**
 * This class can be used in any search strategy like Uniform Cost.
 */
public class CostFunction implements IStepCostFunction {

	@Override
	public double calculateCost(NTree node) {
		// TODO Auto-generated method stub
		
		return ((SusAgentState) node.getAgentState()).getCostCalculated();
	}
}
