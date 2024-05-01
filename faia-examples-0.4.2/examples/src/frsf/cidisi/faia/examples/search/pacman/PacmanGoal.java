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
package frsf.cidisi.faia.examples.search.pacman;

import frsf.cidisi.faia.agent.search.GoalTest;
import frsf.cidisi.faia.state.AgentState;

/**
 * This class defines the 'isGoalState' method. It has the responsability of,
 * given the agent state, verify if it is a goal state. It is used by the
 * search process of the agent and by the simulator, to know when to stop.
 */
public class PacmanGoal extends GoalTest {

    @Override
    public boolean isGoalState(AgentState agentState) {
        if (((PacmanAgentState) agentState).isNoMoreFood() &&
                ((PacmanAgentState) agentState).isAllWorldKnown()) {
            return true;
        }
        return false;
    }
}
