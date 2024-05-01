/*
 * Copyright 2007-2009 Georgina Stegmayer, Milagros Gutiérrez, Jorge Roa,
 * Darío Cravero, Gabriela Calgaro, Rafael Salis y Milton Pividori.
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

package frsf.cidisi.faia.examples.search.snake;

import calculador.Calculador;
import frsf.cidisi.faia.agent.Action;
import frsf.cidisi.faia.agent.Agent;
import frsf.cidisi.faia.agent.Perception;
import frsf.cidisi.faia.environment.Environment;

public class AmbienteSnake extends Environment {

    private Calculador calculador;

    public AmbienteSnake(Calculador calculador) {
        this.calculador = calculador;
        this.environmentState = new EstadoAmbiente(this.calculador);
    }

    @Override
    public Perception getPercept() {
        //		 El ambiente crea una percepción que va a ser recibida por el Snake.- 
        PercepcionSnake p = new PercepcionSnake();

        // Es necesario realizar un "cast" para acceder a los métodos del agente Snake.- 
        //AgenteSnake snake = (AgenteCalculus)agent;

        // Asigna las percepciones en los sensores.-
        p.setSensorNorte(this.getNorte());
        p.setSensorOeste(this.getOeste());
        p.setSensorEste(this.getEste());
        p.setSensorSur(this.getSur());

        // Retorna la nueva percepción creada.-
        return p;
    }

    public int getNorte() {
        return ((EstadoAmbiente) this.environmentState).getNorte();
    }

    public int getOeste() {
        return ((EstadoAmbiente) this.environmentState).getOeste();
    }

    public int getEste() {
        return ((EstadoAmbiente) this.environmentState).getEste();
    }

    public int getSur() {
        return ((EstadoAmbiente) this.environmentState).getSur();
    }

    public String toString() {
        return environmentState.toString();
    }
}
