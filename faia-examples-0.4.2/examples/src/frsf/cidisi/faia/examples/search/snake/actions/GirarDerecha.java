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

public class GirarDerecha extends SearchAction {

    @Override
    public SearchBasedAgentState execute(SearchBasedAgentState s) {
        EstadoSnake snake = (EstadoSnake) s;
        snake.incrementarCosto(this.getCost());

//		if (snake.getVecesQueSeGiroDerecha() > 4) {
//			snake.destrabaAlAgente();
//			snake.setVecesQueSeGiroDerecha(0);
//			
//			return null;
//		}

        if (snake.getOrientacionDeLaCabeza() == EstadoSnake.NORTE) {
            snake.setOrientacionDeLaCabeza(EstadoSnake.ESTE);
            snake.incVecesQueSeGiroDerecha();

            return snake;
        } else if (snake.getOrientacionDeLaCabeza() == EstadoSnake.OESTE) {
            snake.setOrientacionDeLaCabeza(EstadoSnake.NORTE);
            snake.incVecesQueSeGiroDerecha();

            return snake;
        } else if (snake.getOrientacionDeLaCabeza() == EstadoSnake.SUR) {
            snake.setOrientacionDeLaCabeza(EstadoSnake.OESTE);
            snake.incVecesQueSeGiroDerecha();

            return snake;
        } else if (snake.getOrientacionDeLaCabeza() == EstadoSnake.ESTE) {
            snake.setOrientacionDeLaCabeza(EstadoSnake.SUR);
            snake.incVecesQueSeGiroDerecha();

            return snake;
        }

        return null;
    }

    @Override
    public EnvironmentState execute(AgentState ast, EnvironmentState est) {
        EstadoAmbiente estA = (EstadoAmbiente) est;
        EstadoSnake snake = (EstadoSnake) ast;

        if (snake.getOrientacionDeLaCabeza() == EstadoSnake.NORTE) {
            snake.setOrientacionDeLaCabeza(EstadoSnake.ESTE);
            estA.setOrientacionDeLaCabeza(EstadoSnake.ESTE);
            snake.incVecesQueSeGiroDerecha();

        } else if (snake.getOrientacionDeLaCabeza() == EstadoSnake.OESTE) {
            snake.setOrientacionDeLaCabeza(EstadoSnake.NORTE);
            estA.setOrientacionDeLaCabeza(EstadoSnake.NORTE);
            snake.incVecesQueSeGiroDerecha();

        } else if (snake.getOrientacionDeLaCabeza() == EstadoSnake.SUR) {
            snake.setOrientacionDeLaCabeza(EstadoSnake.OESTE);
            estA.setOrientacionDeLaCabeza(EstadoSnake.OESTE);
            snake.incVecesQueSeGiroDerecha();
        } else if (snake.getOrientacionDeLaCabeza() == EstadoSnake.ESTE) {
            snake.setOrientacionDeLaCabeza(EstadoSnake.SUR);
            estA.setOrientacionDeLaCabeza(EstadoSnake.SUR);
            snake.incVecesQueSeGiroDerecha();
        }

        return estA;
    }

    @Override
    public Double getCost() {
        return new Double(10);
    }

    @Override
    public String toString() {
        return "Girar_Derecha";
    }
}