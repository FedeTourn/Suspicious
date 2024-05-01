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

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;

import frsf.cidisi.faia.agent.Perception;
import frsf.cidisi.faia.agent.search.SearchBasedAgentState;

public class RobotAgentState extends SearchBasedAgentState {

    public static final String A = "A";
    public static final String B = "B";
    public static final String C = "C";
    public static final String D = "D";
    public static final String E = "E";
    public static final String F = "F";
    public static final String G = "G";
    public static final String H = "H";
    public static final String I = "I";
    public static final String J = "J";
    public static final String K = "K";
    public static final String L = "L";
    public static final String M = "M";
    public static final String N = "N";
    public static final String O = "O";
    public static final String P = "P";
    public static final String Q = "Q";

    /**
     * Actual agent position
     */
    String position;

    /**
     * This map has a point of the world (A, B, C, ...) as key, and a collection
     * of successors of that point.
     */
    private HashMap<String, Collection<String>> knownMap;
    private ArrayList<String> visitedPositions;

    public RobotAgentState() {
        this.initState();
    }

    @Override
    public RobotAgentState clone() {
        RobotAgentState newState = new RobotAgentState();
        newState.setPosition(position);
        ArrayList<String> visitedPosition = (ArrayList<String>) visitedPositions.clone();
        newState.setVisitedPositions(visitedPosition);
        return newState;
    }

    @Override
    public void initState() {
        position = A;

        /**
         * In this matrix the first element of each row represents a position
         * in the map and the seccessors of that position.
         */
        String[][] positions = new String[][]{
            {A, C, G},
            {B, J, K, O},
            {C, D, G},
            {D, C, E},
            {E, F, H, I, D},
            {F, E, H, G, Q},
            {G, C, F, Q},
            {H, E, F, I, J},
            {I, E, H, J, L},
            {J, B, H, I, K},
            {K, J, N, L},
            {L, I, K, M},
            {M, L, N},
            {N, K, M},
            {O, B, P},
            {P, O, Q},
            {Q, B, F, G, P}
        };

        knownMap = new HashMap<String, Collection<String>>();
        for (int i = 0; i < positions.length; i++) {
            ArrayList<String> successors = new ArrayList<String>();
            for (int j = 1; j < positions[i].length; j++) {
                successors.add(positions[i][j]);
            }
            knownMap.put(positions[i][0], successors);

        }

        visitedPositions = new ArrayList<String>();

    }

    @Override
    public void updateState(Perception p) {
        visitedPositions.add(position);
    }

    @Override
    public String toString() {
        String str = "Posicion: " + position;

        return str;

    }

    @Override
    public boolean equals(Object obj) {

        if (!(obj instanceof RobotAgentState)) {
            return false;
        }
        return position.equals(((RobotAgentState) obj).getPosition());
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public Collection<String> getSuccessors() {
        return knownMap.get(position);
    }

    public ArrayList<String> getVisitedPositions() {
        return visitedPositions;
    }

    public void setVisitedPositions(ArrayList<String> visitedPositions) {
        this.visitedPositions = visitedPositions;
    }
}
