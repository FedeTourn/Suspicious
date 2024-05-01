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

import frsf.cidisi.faia.agent.Agent;
import frsf.cidisi.faia.agent.situationcalculus.SituationCalculusPerception;
import frsf.cidisi.faia.environment.Environment;

public class PacmanLogicPerception extends SituationCalculusPerception {

    public static final int UNKNOWN_PERCEPTION = -1;
    public static final int EMPTY_PERCEPTION = 0;
    public static final int ENEMY_PERCEPTION = 1;
    public static final int FOOD_PERCEPTION = 2;
    
    private int leftSensor;
    private int topSensor;
    private int rightSensor;
    private int bottomSensor;
    private int energy;
    private int row;
    private int column;

    public PacmanLogicPerception() {
        energy = 50;
    }

    public PacmanLogicPerception(Agent agent, Environment environment) {
        super(agent, environment);
    }

    @Override
    public void initPerception(Agent agent, Environment environment) {
        
    }

    @Override
    public String toString() {
        StringBuffer result = new StringBuffer("perception([");

        // Adjacent cells
        result.append(this.convertCellState(this.leftSensor));
        result.append(",");
        result.append(this.convertCellState(this.rightSensor));
        result.append(",");
        result.append(this.convertCellState(this.topSensor));
        result.append(",");
        result.append(this.convertCellState(this.bottomSensor));
        result.append("],");

        // Agent position
        result.append(this.getRow());
        result.append(",");
        result.append(this.getColumn());
        result.append(",");

        // Agent energy
        result.append(this.getEnergy());

        result.append(")");

        return result.toString();
    }

    public int getLeftSensor() {
        return leftSensor;
    }

    public void setLeftSensor(int leftSensor) {
        this.leftSensor = leftSensor;
    }

    public int getTopSensor() {
        return topSensor;
    }

    public void setTopSensor(int topSensor) {
        this.topSensor = topSensor;
    }

    public int getRightSensor() {
        return rightSensor;
    }

    public void setRightSensor(int rightSensor) {
        this.rightSensor = rightSensor;
    }

    public int getBottomSensor() {
        return bottomSensor;
    }

    public void setBottomSensor(int bottomSensor) {
        this.bottomSensor = bottomSensor;
    }

    public int getEnergy() {
        return energy;
    }

    public void setEnergy(int energy) {
        this.energy = energy;
    }

    private String convertCellState(int cellState) {
        String result = null;

        switch (cellState) {
            case PacmanLogicPerception.FOOD_PERCEPTION:
                result = "food";
                break;
            case PacmanLogicPerception.ENEMY_PERCEPTION:
                result = "enemy";
                break;
            case PacmanLogicPerception.EMPTY_PERCEPTION:
                result = "empty";
                break;
        }

        return result;
    }

    public int getRow() {
        return row;
    }

    public void setRow(int row) {
        this.row = row;
    }

    public int getColumn() {
        return column;
    }

    public void setColumn(int column) {
        this.column = column;
    }
}
