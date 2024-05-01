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
package frsf.cidisi.faia.examples.search.robot;

import frsf.cidisi.faia.examples.search.robot.actions.*;
import java.util.Vector;

import frsf.cidisi.faia.agent.search.Problem;
import frsf.cidisi.faia.agent.search.SearchAction;
import frsf.cidisi.faia.agent.search.SearchBasedAgent;
import frsf.cidisi.faia.agent.Action;
import frsf.cidisi.faia.agent.Perception;
import frsf.cidisi.faia.solver.search.BreathFirstSearch;
import frsf.cidisi.faia.solver.search.DepthFirstSearch;
import frsf.cidisi.faia.solver.search.Search;
import java.util.logging.Level;
import java.util.logging.Logger;

public class RobotAgent extends SearchBasedAgent {

    public RobotAgent() {
        // Robot agent goal
        RobotGoal goal = new RobotGoal();

        // Robot agent state
        RobotAgentState agentState = new RobotAgentState();
        this.setAgentState(agentState);

        // Robot agent actions
        Vector<SearchAction> actions = new Vector<SearchAction>();
        actions.addElement(new GoA());
        actions.addElement(new GoB());
        actions.addElement(new GoC());
        actions.addElement(new GoD());
        actions.addElement(new GoE());
        actions.addElement(new GoF());
        actions.addElement(new GoG());
        actions.addElement(new GoH());
        actions.addElement(new GoI());
        actions.addElement(new GoJ());
        actions.addElement(new GoK());
        actions.addElement(new GoL());
        actions.addElement(new GoM());
        actions.addElement(new GoN());
        actions.addElement(new GoO());
        actions.addElement(new GoP());
        actions.addElement(new GoQ());

        // Robot agent problem
        Problem problem = new Problem(goal, agentState, actions);
        this.setProblem(problem);
    }

    @Override
    public Action selectAction() {
        // Breath first strategy
        BreathFirstSearch searchStrategy = new BreathFirstSearch();
//        DepthFirstSearch searchStrategy = new DepthFirstSearch();

        Search searchSolver = new Search(searchStrategy);

        // Set the search tree to be written in an XML file
        searchSolver.setVisibleTree(Search.GRAPHVIZ_TREE);

        // Set the search solver
        this.setSolver(searchSolver);

        // Run the actions selection process
        Action selectedAction = null;
        try {
            selectedAction = this.getSolver().solve(new Object[]{this.getProblem()});
        } catch (Exception ex) {
            Logger.getLogger(RobotAgent.class.getName()).log(Level.SEVERE, null, ex);
        }

        // Return the selected action
        return selectedAction;
    }

    @Override
    public void see(Perception perception) {
        this.getAgentState().updateState(perception);
    }
}
