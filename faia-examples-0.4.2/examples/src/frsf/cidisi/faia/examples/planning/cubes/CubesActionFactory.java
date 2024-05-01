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

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import frsf.cidisi.faia.agent.Action;
import frsf.cidisi.faia.examples.planning.cubes.actions.PutOn;
import frsf.cidisi.faia.examples.planning.cubes.actions.PutOnTable;
import frsf.cidisi.faia.examples.planning.cubes.actions.DropFrom;
import frsf.cidisi.faia.examples.planning.cubes.actions.TakeFromTable;
import frsf.cidisi.faia.agent.ActionFactory;

public class CubesActionFactory extends ActionFactory {

    private static CubesActionFactory instance;
    private Pattern regExpAction;

    private CubesActionFactory() {
        this.regExpAction = Pattern.compile("[A-Za-z]+\\(([A-Za-z, ]+)\\)");
    }

    public static CubesActionFactory getInstance() {
        if (instance == null) {
            instance = new CubesActionFactory();
        }

        return instance;
    }

    /**
     * Returns the string representation of the end action.
     * @return
     */
    @Override
    protected String endActionString() {
        return "end";
    }

    /**
     * This method creates the corresponding action objecto from an string
     * representation of it.
     * @param actionString
     * @return
     */
    @Override
    protected Action stringToAction(String actionString) {
        String[] param = this.getParameters(actionString);

        if (actionString.startsWith("putOnTable")) {
            return new PutOnTable(param);
        } else if (actionString.startsWith("dropFrom")) {
            return new DropFrom(param);
        } else if (actionString.startsWith("putOn")) {
            return new PutOn(param);
        } else if (actionString.startsWith("takeFromTable")) {
            return new TakeFromTable(param);
        }
        
        return null;
    }

    private String[] getParameters(String actionString) {
        Matcher m = this.regExpAction.matcher(actionString);
        m.find();
        String parm = m.group(1);

        String[] parameters = parm.split(",");

        for (int i = 0; i < parameters.length; i++) {
            parameters[i] = parameters[i].trim();
        }

        return parameters;
    }
}
