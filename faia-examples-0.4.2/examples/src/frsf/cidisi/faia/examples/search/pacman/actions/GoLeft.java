/*
 * Copyright 2007-2009 Georgina Stegmayer, Milagros Gutiérrez, Jorge Roa,
 * Luis Ignacio Larrateguy y Milton Pividori.
 * 
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 * 
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 * 
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package frsf.cidisi.faia.examples.search.pacman.actions;

import frsf.cidisi.faia.examples.search.pacman.*;
import frsf.cidisi.faia.agent.search.SearchAction;
import frsf.cidisi.faia.agent.search.SearchBasedAgentState;
import frsf.cidisi.faia.state.AgentState;
import frsf.cidisi.faia.state.EnvironmentState;

public class GoLeft extends SearchAction {

    /**
     * See comments in the Eat class.
     */
    @Override
    public SearchBasedAgentState execute(SearchBasedAgentState s) {

        PacmanAgentState pacmanState = (PacmanAgentState) s;

        pacmanState.increaseVisitedCellsCount();

        int row = pacmanState.getRowPosition();
        int col = pacmanState.getColumnPosition();

        // Check the limits of the world
        if (col == 0) {
            col = 3;
        } else {
            col = col - 1;
        }

        pacmanState.setColumnPosition(col);

        /* The agent can only go left when the cell is not empty */
        if (pacmanState.getWorldPosition(row, col) !=
                PacmanPerception.EMPTY_PERCEPTION) {

            pacmanState.setWorldPosition(row, col,
                    PacmanPerception.EMPTY_PERCEPTION);

            return pacmanState;
        }

        return null;
    }

    /**
     * See comments in the Eat class.
     */
    @Override
    public EnvironmentState execute(AgentState ast, EnvironmentState est) {

        PacmanEnvironmentState environmentState = (PacmanEnvironmentState) est;
        PacmanAgentState pacmanState = ((PacmanAgentState) ast);

        pacmanState.increaseVisitedCellsCount();

        int row = environmentState.getAgentPosition()[0];
        int col = environmentState.getAgentPosition()[1];

        // Check the limits of the world
        if (col == 0) {
            col = 3;
        } else {
            col = col - 1;
        }

        pacmanState.setColumnPosition(col);

        environmentState.setAgentPosition(new int[] {row, col});
        
        return environmentState;
    }

    /**
     * See comments in the Eat class.
     */
    @Override
    public Double getCost() {
        return new Double(0);
    }

    /**
     * See comments in the Eat class.
     */
    @Override
    public String toString() {
        return "GoLeft";
    }
}
