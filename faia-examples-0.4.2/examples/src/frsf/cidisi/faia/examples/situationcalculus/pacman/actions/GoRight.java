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
package frsf.cidisi.faia.examples.situationcalculus.pacman.actions;

import frsf.cidisi.faia.agent.situationcalculus.SituationCalculusAction;
import frsf.cidisi.faia.examples.situationcalculus.pacman.PacmanLogicEnvironmentState;
import frsf.cidisi.faia.state.AgentState;
import frsf.cidisi.faia.state.EnvironmentState;

public class GoRight extends SituationCalculusAction {

    @Override
    public EnvironmentState execute(AgentState ast, EnvironmentState est) {
        PacmanLogicEnvironmentState environmentState =
                (PacmanLogicEnvironmentState) est;

        // Get the actual agent position
        int row = environmentState.getAgentPosition()[0];
        int col = environmentState.getAgentPosition()[1];

        // Modify the position according to the taken action
        if (col == 3)
            col = 0;
        else
            col++;

        environmentState.setAgentPosition(new int[] {row, col});

        return est;
    }

    @Override
    public String toString() {
        return "goright";
    }
}
