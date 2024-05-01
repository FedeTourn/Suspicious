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

import frsf.cidisi.faia.state.EnvironmentState;

/**
 * This class is identical to PacmanEnvironmentState class in the Pacman (Search)
 * example, because the change in the agent type has no efects on the
 * Environment. It is advisable to read comments there.
 */
public class PacmanLogicEnvironmentState extends EnvironmentState {

    private int[][] world;
    private int[] agentPosition;
    private int agentEnergy;

    public PacmanLogicEnvironmentState(int[][] w) {
        world = w;
    }

    public PacmanLogicEnvironmentState() {
        world = new int[4][4];
        this.initState();
    }

    @Override
    public void initState() {
        
        this.setAgentPosition(new int[] {1, 2});
        this.setAgentEnergy(50);

        for (int row = 0; row < world.length; row++) {
            for (int col = 0; col < world.length; col++) {
                world[row][col] = PacmanLogicPerception.EMPTY_PERCEPTION;
            }
        }

        world[0][0] = PacmanLogicPerception.FOOD_PERCEPTION;
        world[0][2] = PacmanLogicPerception.FOOD_PERCEPTION;
        world[3][1] = PacmanLogicPerception.ENEMY_PERCEPTION;
        world[2][1] = PacmanLogicPerception.FOOD_PERCEPTION;
        world[0][3] = PacmanLogicPerception.ENEMY_PERCEPTION;
        world[1][2] = PacmanLogicPerception.FOOD_PERCEPTION;
    }

    @Override
    public String toString() {
        String str = "";

        str = str + "[ \n";
        for (int row = 0; row < world.length; row++) {
            str = str + "[ ";
            for (int col = 0; col < world.length; col++) {
                str = str + this.convertCell(world[row][col]) + " ";
            }
            str = str + " ]\n";
        }
        str = str + " ]";

        return str;
    }

    // These methods are internal:

    private String convertCell(int p) {
        String r = "";

        if (p == PacmanLogicPerception.FOOD_PERCEPTION) {
            r = "F";
        } else if (p == PacmanLogicPerception.ENEMY_PERCEPTION) {
            r = "E";
        } else if (p == PacmanLogicPerception.EMPTY_PERCEPTION) {
            r = " ";
        }

        return r;
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

    public void setAgentEnergy(int agentEnerty) {
        this.agentEnergy = agentEnerty;
    }

    public int[][] getWorld() {
        return world;
    }

    public void setWorld(int[][] world) {
        this.world = world;
    }

    public void setWorld(int row, int col, int value) {
        this.world[row][col] = value;
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
