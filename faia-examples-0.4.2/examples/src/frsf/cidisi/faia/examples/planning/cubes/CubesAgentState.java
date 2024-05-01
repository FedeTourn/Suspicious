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
package frsf.cidisi.faia.examples.planning.cubes;

import java.util.Hashtable;

import frsf.cidisi.faia.agent.Perception;
import frsf.cidisi.faia.exceptions.PrologConnectorException;
import frsf.cidisi.faia.agent.ActionFactory;
import frsf.cidisi.faia.agent.planning.PlanningBasedAgentState;

public class CubesAgentState extends PlanningBasedAgentState {

    public CubesAgentState() throws PrologConnectorException {
        super("cubes.pl");

        this.initState();
    }

    @Override
    public ActionFactory getActionFactory() {
        return CubesActionFactory.getInstance();
    }

    @Override
    public String getBestActionPredicate() {
        return "bestAction";
    }

    @Override
    public String getExecuteActionPredicate() {
        return "executeAction";
    }

    @Override
    public void updateState(Perception p) {
        
    }

    @Override
    public void initState() {
        this.addInitState("on_table(a)");
        this.addInitState("on(b,a)");
        this.addInitState("clear(b)");
        this.addInitState("holding(c)");
    }

    @Override
    public String toString() {
        StringBuffer sb = new StringBuffer();
        sb.append("\n");

        // Query for cubes on the table
        Hashtable[] result = this.query("on_table(X)");

        for (Hashtable res : result) {
            String cubesOnTable = res.get("X").toString();

            sb.append(cubesOnTable + " <- ");
            sb.append(on(cubesOnTable));

            sb.append("\n");
        }

        // Cube holded by the robot arm
        String holdingCube = "(none)";

        result = this.query("holding(X)");
        if (result.length > 0) {
            holdingCube = result[0].get("X").toString();
        }

        sb.append("Robot arm: " + holdingCube);

        return sb.toString();
    }

    private String on(String cube) {
        StringBuffer sb = new StringBuffer();

        // Query for the cube over 'cube'
        Hashtable[] result = this.query("on(X," + cube + ")");

        if (result.length > 0) {
            String cubeOn = result[0].get("X").toString();
            sb.append(cubeOn + " <- ");
            sb.append(on(cubeOn));
        } else {
            sb.append("");
        }

        return sb.toString();
    }
}
