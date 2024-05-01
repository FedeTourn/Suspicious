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

import frsf.cidisi.faia.agent.Action;
import frsf.cidisi.faia.agent.ActionFactory;
import frsf.cidisi.faia.examples.situationcalculus.pacman.actions.*;

/**
 * When the Solver (Calculus in this case) ask the agent for the best action,
 * it receives from the knowledge base a string representation of it. However
 * the 'solve' method must return an Action object.
 * This class is used to turn an string representation of the action to an
 * Action object.
 */
public class PacmanLogicAccionFactory extends ActionFactory {

    private static PacmanLogicAccionFactory instance;

    private PacmanLogicAccionFactory() {
    }

    public static PacmanLogicAccionFactory getInstance() {
        if (instance == null) {
            instance = new PacmanLogicAccionFactory();
        }
        return instance;
    }

    @Override
    public Action stringToAction(String stringAction) {
        if (stringAction.equals("godown")) {
            return new GoDown();
        } else if (stringAction.equals("goup")) {
            return new GoUp();
        } else if (stringAction.equals("goright")) {
            return new GoRight();
        } else if (stringAction.equals("goleft")) {
            return new GoLeft();
        } else if (stringAction.equals("eat")) {
            return new Eat();
        } else if (stringAction.equals("fight")) {
            return new Fight();
        }

        return null;
    }
    
    @Override
    public String endActionString() {
        return "noAction";
    }
}
