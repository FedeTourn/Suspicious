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

import frsf.cidisi.faia.state.EnvironmentState;

public class RobotEnvironmentState extends EnvironmentState {

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
     * This map has a point of the world (A, B, C, ...) as key, and a collection
     * of successors of that point.
     */
    private HashMap<String, Collection<String>> map;

    public static final String[][] POSITIONS = new String[][]{
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

    RobotEnvironmentState() {
        map = new HashMap<String, Collection<String>>();
    }

    @Override
    public Object clone() {
        return map.clone();
    }

    @Override
    public void initState() {
        /**
         * In this matrix the first element of each row represents a position
         * in the map and the seccessors of that position.
         */
        map = new HashMap<String, Collection<String>>();
        
        for (int i = 0; i < POSITIONS.length; i++) {
            ArrayList<String> successors = new ArrayList<String>();
            for (int j = 1; j < POSITIONS[i].length; j++) {
                successors.add(POSITIONS[i][j]);
            }
            map.put(POSITIONS[i][0], successors);

        }
    }

    @Override
    public String toString() {
        String str = "";

        str = str + "[ \n";
        for (String point : map.keySet()) {
            str = str + "[ " + point + " --> ";
            Collection<String> successors = map.get(point);
            if (successors != null) {
                for (String successor : successors) {
                    str = str + successor + " ";
                }
            }
            str = str + " ]\n";
        }
        str = str + " ]";

        return str;
    }

    @Override
    public boolean equals(Object obj) {
        // Returns always true. This method is not used.
        return true;
    }
}
