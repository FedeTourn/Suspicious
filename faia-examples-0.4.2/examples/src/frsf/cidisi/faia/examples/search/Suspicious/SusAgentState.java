package frsf.cidisi.faia.examples.search.Suspicious;

import frsf.cidisi.faia.agent.Perception;
import frsf.cidisi.faia.agent.search.SearchBasedAgentState;

public class SusAgentState  extends SearchBasedAgentState {
	//Estado del impostor:
	//[Mapa, Energía Actual, Cantidad de tripulantes en la nave, Cantidad de servicios por sabotear]
	
	/* Estado del pacman
	 * private int[][] world; 
	 * private int[] position; 
	 * private int[] initialPosition;
	 * private int energy; 
	 * private int visitedCells;
	 */

	@Override
	public boolean equals(Object obj) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public SearchBasedAgentState clone() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void updateState(Perception p) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void initState() {
		// TODO Auto-generated method stub
		
	}

}
