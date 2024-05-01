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

public class Eat extends SearchAction {

    /**
     * This method updates a tree node state when the search process is running.
     * It does not updates the real world state.
     */
    @Override
    public SearchBasedAgentState execute(SearchBasedAgentState s) {
        PacmanAgentState pacmanState = (PacmanAgentState) s;

        int row = pacmanState.getRowPosition();
        int col = pacmanState.getColumnPosition();

        /* The 'Eat' action can be selected only if there is food in the current
         * position. Otherwise return 'null'. */
        if (pacmanState.getWorld()[row][col] == PacmanPerception.FOOD_PERCEPTION) {
            // If the action is Eat, then the actual position has no more food.
            pacmanState.setWorldPosition(row, col, PacmanPerception.EMPTY_PERCEPTION);
            return pacmanState;
        }
        
        return null;
    }

    /**
     * This method updates the agent state and the real world state.
     */
    @Override
    public EnvironmentState execute(AgentState ast, EnvironmentState est) {
        PacmanEnvironmentState environmentState = (PacmanEnvironmentState) est;
        PacmanAgentState pacmanState = ((PacmanAgentState) ast);

        int row = environmentState.getAgentPosition()[0];
        int col = environmentState.getAgentPosition()[1];

        if (environmentState.getWorld()[row][col] == PacmanPerception.FOOD_PERCEPTION) {
            // Update the real world
            environmentState.setWorld(row, col, PacmanPerception.EMPTY_PERCEPTION);

            // Update the pacman state
            pacmanState.setWorldPosition(row, col, PacmanPerception.EMPTY_PERCEPTION);
            
            return environmentState;
        }

        return null;
    }

    /**
     * This method returns the action cost.
     */
    @Override
    public Double getCost() {
        return new Double(0);
    }

    /**
     * This method is not important for a search based agent, but is essensial
     * when creating a calculus based one.
     */
    @Override
    public String toString() {
        return "Eat";
    }
}
