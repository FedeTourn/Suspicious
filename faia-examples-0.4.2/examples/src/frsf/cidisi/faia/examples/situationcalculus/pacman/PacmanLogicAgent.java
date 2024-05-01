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
package frsf.cidisi.faia.examples.situationcalculus.pacman;

import frsf.cidisi.faia.agent.Perception;
import frsf.cidisi.faia.agent.Action;
import frsf.cidisi.faia.agent.situationcalculus.SituationCalculusBasedAgent;
import frsf.cidisi.faia.exceptions.PrologConnectorException;
import frsf.cidisi.faia.solver.situationcalculus.SituationCalculus;
import java.util.logging.Level;
import java.util.logging.Logger;

public class PacmanLogicAgent extends SituationCalculusBasedAgent {

    public PacmanLogicAgent() throws PrologConnectorException {
        // Create the agent state (knowledge base) of the agent
        PacmanLogicAgentState agentState = new PacmanLogicAgentState();
        this.setAgentState(agentState);
    }

    @Override
    public Action selectAction() {

        /* In the Pacman (search) example, the Solver is a Search object.
         * this example is a SituationCalculus one.
         */
        SituationCalculus calculusSolver = new SituationCalculus();
        this.setSolver(calculusSolver);

        // Ask the solver for the best action
        Action selectedAction = null;
        try {
            selectedAction =
                    this.getSolver().solve(new Object[]{this.getAgentState()});
        } catch (Exception ex) {
            Logger.getLogger(PacmanLogicAgent.class.getName()).log(Level.SEVERE, null, ex);
        }

        // Return the selected action
        return selectedAction;
    }
    
    /**
     * This method is called by the simulator to tell the agent that the
     * selected action is applied to the real world. It updates the knowledge
     * base of the agent.
     */
    @Override
    public void tell(Action action) {
        PacmanLogicAgentState kb = (PacmanLogicAgentState) this.getAgentState();
        kb.tell(action);
    }
    
    /**
     * This method is called by the simulator to give the agent a perception.
     * It updates his state (i.e. his knowledge base).
     * Internally it the method 'tell(Perception)' is called.
     */
    @Override
    public void see(Perception p) {
        this.getAgentState().updateState(p);
    }
}
