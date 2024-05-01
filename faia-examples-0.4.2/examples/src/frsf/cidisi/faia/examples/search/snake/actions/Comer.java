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

package frsf.cidisi.faia.examples.search.snake.actions;

import frsf.cidisi.faia.examples.search.snake.*;
import frsf.cidisi.faia.agent.search.SearchAction;
import frsf.cidisi.faia.agent.search.SearchBasedAgentState;
import frsf.cidisi.faia.state.AgentState;
import frsf.cidisi.faia.state.EnvironmentState;

public class Comer extends SearchAction {

    @Override
    public SearchBasedAgentState execute(SearchBasedAgentState s) {
        EstadoSnake snake = (EstadoSnake) s;
        snake.incrementarCosto(this.getCost());

        if (snake.getOrientacionDeLaCabeza() == EstadoSnake.NORTE &&
                snake.hayComida(EstadoSnake.NORTE)) {
            snake.comerAlNorte();

            return snake;
        } else if (snake.getOrientacionDeLaCabeza() == EstadoSnake.SUR &&
                snake.hayComida(EstadoSnake.SUR)) {
            snake.comerAlSur();

            return snake;
        } else if (snake.getOrientacionDeLaCabeza() == EstadoSnake.ESTE &&
                snake.hayComida(EstadoSnake.ESTE)) {
            snake.comerAlEste();

            return snake;
        } else if (snake.getOrientacionDeLaCabeza() == EstadoSnake.OESTE &&
                snake.hayComida(EstadoSnake.OESTE)) {
            snake.comerAlOeste();

            return snake;
        }

        return null;
    }

    @Override
    public EnvironmentState execute(AgentState ast, EnvironmentState est) {
        EstadoAmbiente estA = (EstadoAmbiente) est;
        EstadoSnake snake = (EstadoSnake) ast;

        if (snake.getOrientacionDeLaCabeza() == EstadoSnake.NORTE &&
                snake.hayComida(EstadoSnake.NORTE)) {
            snake.comerAlNorte();
            estA.comerAlNorte();
        } else if (snake.getOrientacionDeLaCabeza() == EstadoSnake.SUR &&
                snake.hayComida(EstadoSnake.SUR)) {
            snake.comerAlSur();
            estA.comerAlSur();
        } else if (snake.getOrientacionDeLaCabeza() == EstadoSnake.ESTE &&
                snake.hayComida(EstadoSnake.ESTE)) {
            snake.comerAlEste();
            estA.comerAlEste();
        } else if (snake.getOrientacionDeLaCabeza() == EstadoSnake.OESTE &&
                snake.hayComida(EstadoSnake.OESTE)) {
            snake.comerAlOeste();
            estA.comerAlOeste();
        }

        return estA;
    }

    @Override
    public Double getCost() {
        return new Double(5);
    }

    @Override
    public String toString() {
        return "Comer";
    }
}
