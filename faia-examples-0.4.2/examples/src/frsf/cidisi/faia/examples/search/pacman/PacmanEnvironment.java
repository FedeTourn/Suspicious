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

import frsf.cidisi.faia.agent.Action;
import frsf.cidisi.faia.agent.Agent;
import frsf.cidisi.faia.agent.Perception;
import frsf.cidisi.faia.environment.Environment;

public class PacmanEnvironment extends Environment {

    public PacmanEnvironment() {
        // Create the environment state
        this.environmentState = new PacmanEnvironmentState();
    }

    @Override
    public PacmanEnvironmentState getEnvironmentState() {
        return (PacmanEnvironmentState) super.getEnvironmentState();
    }

    /**
     * This method is called by the simulator. Given the Agent, it creates
     * a new perception reading, for example, the agent position.
     * @param agent
     * @return A perception that will be given to the agent by the simulator.
     */
    @Override
    public Perception getPercept() {
        // Create a new perception to return
        PacmanPerception perception = new PacmanPerception();
        
        // Get the actual position of the agent to be able to create the
        // perception
        int row = this.getEnvironmentState().getAgentPosition()[0];
        int col = this.getEnvironmentState().getAgentPosition()[1];

        // Set the perception sensors
        perception.setTopSensor(this.getTopCell(row, col));
        perception.setLeftSensor(this.getLeftCell(row, col));
        perception.setRightSensor(this.getRightCell(row, col));
        perception.setBottomSensor(this.getBottomCell(row, col));

        // Return the perception
        return perception;
    }

    @Override
    public String toString() {
        return environmentState.toString();
    }

    @Override
    public boolean agentFailed(Action actionReturned) {

        PacmanEnvironmentState pacmanEnvironmentState =
                this.getEnvironmentState();

        int agentEnergy = pacmanEnvironmentState.getAgentEnergy();

        // FIXME: The pacman agent always has the same energy
        // If the agent has no energy, he failed
        if (agentEnergy <= 0)
            return true;

        return false;
    }

    // The following methods are Pacman-specific:
    
    public int getTopCell(int row, int col) {
        return ((PacmanEnvironmentState) this.environmentState)
                .getTopCell(row, col);
    }

    public int getLeftCell(int row, int col) {
        return ((PacmanEnvironmentState) this.environmentState)
                .getLeftCell(row, col);
    }

    public int getRightCell(int row, int col) {
        return ((PacmanEnvironmentState) this.environmentState)
                .getRightCell(row, col);
    }

    public int getBottomCell(int row, int col) {
        return ((PacmanEnvironmentState) this.environmentState)
                .getBottomCell(row, col);
    }
}
