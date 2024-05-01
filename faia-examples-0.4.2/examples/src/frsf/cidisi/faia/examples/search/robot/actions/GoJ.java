/*
 * Copyright 2007-2009 Georgina Stegmayer, Milagros Gutiérrez, Jorge Roa
 * y Milton Pividori.
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
package frsf.cidisi.faia.examples.search.robot.actions;

import frsf.cidisi.faia.examples.search.robot.RobotAgentState;
import java.util.ArrayList;

import frsf.cidisi.faia.agent.search.SearchAction;
import frsf.cidisi.faia.agent.search.SearchBasedAgentState;
import frsf.cidisi.faia.state.AgentState;
import frsf.cidisi.faia.state.EnvironmentState;

public class GoJ extends SearchAction {

    /**
     * This method updates a tree node state when the search process is running.
     * It does not updates the real world state.
     */
    @Override
    public SearchBasedAgentState execute(SearchBasedAgentState s) {
        RobotAgentState agentState = (RobotAgentState) s;

        if (agentState.getVisitedPositions().contains(RobotAgentState.J)) {
            return null;
        }
        ArrayList<String> successors = new ArrayList<String>(agentState.getSuccessors());
        if (successors != null) {
            int index = successors.indexOf(RobotAgentState.J);
            if (index >= 0) {
                agentState.setPosition(RobotAgentState.J);
                return agentState;
            }

        }

        return null;
    }

    /**
     * This method updates the agent state and the real world state.
     */
    @Override
    public EnvironmentState execute(AgentState ast, EnvironmentState est) {
        this.execute((SearchBasedAgentState) ast);

        return null;
    }

    @Override
    public Double getCost() {
        return new Double(0);
    }

    @Override
    public String toString() {
        return "GoJ";
    }
}
