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

public class GirarIzquierda extends SearchAction {

    @Override
    public SearchBasedAgentState execute(SearchBasedAgentState s) {
        EstadoSnake snake = (EstadoSnake) s;
        snake.incrementarCosto(this.getCost());

//		if (snake.getVecesQueSeGiro() > 4) {
//			snake.destrabaAlAgente();
//			snake.setVecesQueSeGiro(0);
//			
//			return null;
//		}

        if (snake.getOrientacionDeLaCabeza() == EstadoSnake.NORTE) {
            snake.incVecesQueSeGiro();

            snake.setOrientacionDeLaCabeza(EstadoSnake.OESTE);

            return snake;
        } else if (snake.getOrientacionDeLaCabeza() == EstadoSnake.OESTE) {
            snake.incVecesQueSeGiro();

            snake.setOrientacionDeLaCabeza(EstadoSnake.SUR);

            return snake;
        } else if (snake.getOrientacionDeLaCabeza() == EstadoSnake.SUR) {
            snake.incVecesQueSeGiro();

            snake.setOrientacionDeLaCabeza(EstadoSnake.ESTE);

            return snake;
        } else if (snake.getOrientacionDeLaCabeza() == EstadoSnake.ESTE) {
            snake.incVecesQueSeGiro();

            snake.setOrientacionDeLaCabeza(EstadoSnake.NORTE);

            return snake;
        }

        return null;
    }

    @Override
    public EnvironmentState execute(AgentState ast, EnvironmentState est) {
        EstadoAmbiente estA = (EstadoAmbiente) est;
        EstadoSnake snake = (EstadoSnake) ast;

        if (snake.getOrientacionDeLaCabeza() == EstadoSnake.NORTE) {
            snake.incVecesQueSeGiro();

            snake.setOrientacionDeLaCabeza(EstadoSnake.OESTE);
            estA.setOrientacionDeLaCabeza(EstadoSnake.OESTE);
        } else if (snake.getOrientacionDeLaCabeza() == EstadoSnake.OESTE) {
            snake.incVecesQueSeGiro();

            snake.setOrientacionDeLaCabeza(EstadoSnake.SUR);
            estA.setOrientacionDeLaCabeza(EstadoSnake.SUR);
        } else if (snake.getOrientacionDeLaCabeza() == EstadoSnake.SUR) {
            snake.incVecesQueSeGiro();

            snake.setOrientacionDeLaCabeza(EstadoSnake.ESTE);
            estA.setOrientacionDeLaCabeza(EstadoSnake.ESTE);
        } else if (snake.getOrientacionDeLaCabeza() == EstadoSnake.ESTE) {
            snake.incVecesQueSeGiro();

            snake.setOrientacionDeLaCabeza(EstadoSnake.NORTE);
            estA.setOrientacionDeLaCabeza(EstadoSnake.NORTE);
        }

        return estA;
    }

    @Override
    public Double getCost() {
        return new Double(10);
    }

    @Override
    public String toString() {
        return "Girar_Izquierda";
    }
}
