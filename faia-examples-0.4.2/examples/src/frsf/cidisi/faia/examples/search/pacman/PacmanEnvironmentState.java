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

import frsf.cidisi.faia.state.EnvironmentState;

/**
 * This class represents the real world state.
 */
public class PacmanEnvironmentState extends EnvironmentState {

    private int[][] world;
    private int[] agentPosition;
    private int agentEnergy;

    public PacmanEnvironmentState(int[][] m) {
        world = m;
    }

    public PacmanEnvironmentState() {
        world = new int[4][4];
        this.initState();
    }

    /**
     * This method is used to setup the initial real world.
     */
    @Override
    public void initState() {

        // Sets all cells as empty
        for (int row = 0; row < world.length; row++) {
            for (int col = 0; col < world.length; col++) {
                world[row][col] = PacmanPerception.EMPTY_PERCEPTION;
            }
        }

        /* Sets some cells with foods and enemies. */
        world[0][0] = PacmanPerception.FOOD_PERCEPTION;
        world[0][2] = PacmanPerception.FOOD_PERCEPTION;
        world[3][1] = PacmanPerception.ENEMY_PERCEPTION;
        world[2][1] = PacmanPerception.FOOD_PERCEPTION;
        world[0][3] = PacmanPerception.ENEMY_PERCEPTION;
        world[1][2] = PacmanPerception.FOOD_PERCEPTION;

        this.setAgentPosition(new int[]{1, 1});
        this.setAgentEnergy(50);
    }

    /**
     * String representation of the real world state.
     */
    @Override
    public String toString() {
        String str = "";

        str = str + "[ \n";
        for (int row = 0; row < world.length; row++) {
            str = str + "[ ";
            for (int col = 0; col < world.length; col++) {
                str = str + world[row][col] + " ";
            }
            str = str + " ]\n";
        }
        str = str + " ]";

        return str;
    }

    // The following methods are Pacman-specific:

    public int[][] getWorld() {
        return world;
    }

    public void setWorld(int[][] world) {
        this.world = world;
    }

    public void setWorld(int row, int col, int value) {
        this.world[row][col] = value;
    }

    public int[] getAgentPosition() {
        return agentPosition;
    }

    public void setAgentPosition(int[] agentPosition) {
        this.agentPosition = agentPosition;
    }

    public int getAgentEnergy() {
        return agentEnergy;
    }

    public void setAgentEnergy(int agentEnergy) {
        this.agentEnergy = agentEnergy;
    }

    public int getTopCell(int row, int col) {
        if (row == 0) {
            return world[3][col];
        }
        return world[row - 1][col];
    }

    public int getLeftCell(int row, int col) {
        if (col == 0) {
            return world[row][3];
        }
        return world[row][col - 1];
    }

    public int getRightCell(int row, int col) {
        if (col == 3) {
            return world[row][0];
        }
        return world[row][col + 1];
    }

    public int getBottomCell(int row, int col) {
        if (row == 3) {
            return world[0][col];
        }
        return world[row + 1][col];
    }
}
