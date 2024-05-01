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
import frsf.cidisi.faia.agent.situationcalculus.SituationCalculusPerception;
import frsf.cidisi.faia.exceptions.PrologConnectorException;
import frsf.cidisi.faia.agent.ActionFactory;
import frsf.cidisi.faia.agent.situationcalculus.KnowledgeBase;

import java.util.Hashtable;

/**
 * This class represents the Pacman agent state. In this example it is his
 * knowledge base.
 */
public class PacmanLogicAgentState extends KnowledgeBase {
    
    /**
     * Here we called the superclass constructor with the name of the file
     * which contains the Prolog sentences. Then we setup the agent state.
     * @throws PrologConnectorException
     */
    public PacmanLogicAgentState() throws PrologConnectorException {
        super("pacman_logic.pl");

        this.initState();
    }
    
    /**
     * This method receives a Perception object and updates the agent state.
     * It is called by the 'see(Perception)' method in the PacmanLogicAgent
     * class.
     * @param perception
     */
    @Override
    public void updateState(Perception perception) {
        this.tell((SituationCalculusPerception) perception);
    }
    
    /**
     * Prints a string representation of the knowledge base.
     * @return
     */
    @Override
    public String toString() {
        String str = "";

        str = str + " position=\"(" + this.getRow() + "," + "" + this.getColumn() + ")\"";
        str = str + " energy=\"" + this.getEnergy() + "\"\n";

        str = str + "world=\"[ \n";
        for (int row = 0; row < this.getWorldSize(); row++) {
            str = str + "[ ";
            for (int col = 0; col < this.getWorldSize(); col++) {

                if (this.getRow() == row && this.getColumn() == col) {
                    str = str + "P" + " ";
                } else {
                    str = str + this.convertCell(this.getPositionState(row, col));
                }
            }
            str = str + " ]\n";
        }
        str = str + " ]\"";

        return str;
    }

    /**
     * Aquí se indica cuál es el objeto CalculusActionFactory definido
     * por el usuario. Ver comentarios en la clase PacmanLogicAccionFactory.
     */
    /**
     * Here we return the ActionFactory defined by the user. In this case
     * we return a PacmanLogicActionFactory instance.
     * @return
     */
    @Override
    public ActionFactory getActionFactory() {
        return PacmanLogicAccionFactory.getInstance();
    }
    
    /**
     * This method must return the string used in the Prolog file to represent
     * the best action sentence. In this example it is used like this:
     *
     *      bestAction(noAction,S):-goalReached(S),!.
     *
     * The knowledge base needs to know how we named that sentence.
     */
    @Override
    public String getBestActionPredicate() {
        return "bestAction";
    }

    /**
     * Similar to 'getBestActionPredicate'.
     */
    @Override
    public String getExecutedActionPredicate() {
        return "executedAction";
    }

    /**
     * Similar to 'getBestActionPredicate'
     * @return
     */
    @Override
    public String getSituationPredicate() {
        return "actualSituation";
    }

    /**
     * Here we setup the initial state of the agent.
     */
    @Override
    public void initState() {
        //this.addKnowledge("actualSituation(0)");
        this.addKnowledge("position(1,2,0)");
        this.addKnowledge("energy(50,0)");
    }

    // These methods are internal:

    private int[] getPosition() {
        String positionQuery = "position(X,Y," + this.getSituation() + ")";

        Hashtable[] result = this.query(positionQuery);

        int x = Integer.parseInt(result[0].get("X").toString());
        int y = Integer.parseInt(result[0].get("Y").toString());

        return new int[]{x, y};
    }

    private int getRow() {
        return this.getPosition()[0];
    }

    private int getColumn() {
        return this.getPosition()[1];
    }

    private int getEnergy() {
        String energyQuery = "energy(E," + this.getSituation() + ")";

        Hashtable[] result = this.query(energyQuery);

        int energy = Integer.parseInt(result[0].get("E").toString());

        return energy;
    }
    
    private int getWorldSize() {
        return 4;
    }

    private String convertCell(int p) {
        String r = "";

        if (p == PacmanLogicPerception.FOOD_PERCEPTION) {
            r = "F";
        } else if (p == PacmanLogicPerception.ENEMY_PERCEPTION) {
            r = "E";
        } else if (p == PacmanLogicPerception.EMPTY_PERCEPTION) {
            r = " ";
        } else if (p == PacmanLogicPerception.UNKNOWN_PERCEPTION) {
            r = "*";
        }

        return r + " ";
    }

    private int getPositionState(int row, int col) {

        // Query the cell state to see if we know it
        String cellStateQuery = "knows(" + row + "," +
                col + "," + this.getSituation() + ")";

        if (!this.queryHasSolution(cellStateQuery)) {
            return PacmanLogicPerception.UNKNOWN_PERCEPTION;
        }

        // Query the cell state to see if there is an enemy there
        cellStateQuery = "enemy(" + row + "," +
                col + "," + this.getSituation() + ")";

        if (this.queryHasSolution(cellStateQuery)) {
            return PacmanLogicPerception.ENEMY_PERCEPTION;
        }

        // Query the cell state to see if there is food there
        cellStateQuery = "food(" + row + "," +
                col + "," + this.getSituation() + ")";

        if (this.queryHasSolution(cellStateQuery)) {
            return PacmanLogicPerception.FOOD_PERCEPTION;
        }

        // Query the cell state to see if it is empty
        cellStateQuery = "empty(" + row + "," +
                col + "," + this.getSituation() + ")";

        if (this.queryHasSolution(cellStateQuery)) {
            return PacmanLogicPerception.EMPTY_PERCEPTION;
        }

        return -100;
    }
}
