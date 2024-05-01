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

import java.util.ArrayList;
import java.util.Hashtable;

import frsf.cidisi.faia.state.EnvironmentState;

public class CubesEnvironmentState extends EnvironmentState {

    private Hashtable<String, String> on;
    private ArrayList<String> onTable;

    public CubesEnvironmentState() {
        this.initState();
    }

    @Override
    public void initState() {
        this.on = new Hashtable<String, String>();
        this.onTable = new ArrayList<String>();

        // B on A:
        // B
        // A
        this.onTable("a");
        this.on("b", "a");
    }

    @Override
    public String toString() {
        StringBuffer sb = new StringBuffer();
        sb.append("\n");

        for (String cubeOnTable : this.onTable) {
            sb.append(cubeOnTable + " <- ");
            sb.append(getCubesOn(cubeOnTable));

            sb.append("\n");
        }

        return sb.toString();
    }

    /**
     * Given a cube, it returns all the cubes over it.
     * @param cubo
     * @return
     */
    private String getCubesOn(String cube) {
        StringBuffer sb = new StringBuffer();

        if (this.on.containsKey(cube)) {
            String cubeOn = this.on.get(cube);

            sb.append(cubeOn + " <- ");
            sb.append(getCubesOn(cubeOn));
        } else {
            sb.append("");
        }

        return sb.toString();
    }

    public void onTable(String cube) {
        this.onTable.add(cube);
    }

    public void takeFromTable(String cube) {
        this.onTable.remove(cube);
    }

    public void on(String cube1, String cube2) {
        this.on.put(cube2, cube1);
    }

    public void dropFrom(String cube1, String cube2) {
        this.on.remove(cube2);
    }
}
