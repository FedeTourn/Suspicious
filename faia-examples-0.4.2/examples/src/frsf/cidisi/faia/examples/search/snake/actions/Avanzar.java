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

import java.awt.Point;

import frsf.cidisi.faia.agent.search.SearchAction;
import frsf.cidisi.faia.agent.search.SearchBasedAgentState;
import frsf.cidisi.faia.state.AgentState;
import frsf.cidisi.faia.state.EnvironmentState;

public class Avanzar extends SearchAction {

    @Override
    public SearchBasedAgentState execute(SearchBasedAgentState s) {
        EstadoSnake snake = (EstadoSnake) s;
        snake.incrementarCosto(this.getCost());

        if (snake.getOrientacionDeLaCabeza() == EstadoSnake.NORTE &&
                !snake.hayObstaculo(EstadoSnake.NORTE) &&
                !snake.hayComida(EstadoSnake.NORTE) &&
                !snake.pasoNVecesPorEstaCelda(new Point(
                (int) snake.getPosicionCabeza().getX() - 1,
                (int) snake.getPosicionCabeza().getY()))) {
            snake.moverAlNorte();

            return snake;
        } else if (snake.getOrientacionDeLaCabeza() == EstadoSnake.SUR &&
                !snake.hayObstaculo(EstadoSnake.SUR) &&
                !snake.hayComida(EstadoSnake.SUR) &&
                !snake.pasoNVecesPorEstaCelda(new Point(
                (int) snake.getPosicionCabeza().getX() + 1,
                (int) snake.getPosicionCabeza().getY()))) {
            snake.moverAlSur();

            return snake;
        } else if (snake.getOrientacionDeLaCabeza() == EstadoSnake.ESTE &&
                !snake.hayObstaculo(EstadoSnake.ESTE) &&
                !snake.hayComida(EstadoSnake.ESTE) &&
                !snake.pasoNVecesPorEstaCelda(new Point(
                (int) snake.getPosicionCabeza().getX(),
                (int) snake.getPosicionCabeza().getY() + 1))) {
            snake.moverAlEste();

            return snake;
        } else if (snake.getOrientacionDeLaCabeza() == EstadoSnake.OESTE &&
                !snake.hayObstaculo(EstadoSnake.OESTE) &&
                !snake.hayComida(EstadoSnake.OESTE) &&
                !snake.pasoNVecesPorEstaCelda(new Point(
                (int) snake.getPosicionCabeza().getX(),
                (int) snake.getPosicionCabeza().getY() - 1))) {
            snake.moverAlOeste();

            return snake;
        }

        return null;
    }

    @Override
    public EnvironmentState execute(AgentState ast, EnvironmentState est) {
        EstadoAmbiente estA = (EstadoAmbiente) est;
        EstadoSnake snake = (EstadoSnake) ast;

        if (snake.getOrientacionDeLaCabeza() == EstadoSnake.NORTE &&
                !snake.hayObstaculo(EstadoSnake.NORTE) &&
                !snake.hayComida(EstadoSnake.NORTE) &&
                !snake.pasoNVecesPorEstaCelda(new Point(
                (int) snake.getPosicionCabeza().getX() - 1,
                (int) snake.getPosicionCabeza().getY()))) {
            snake.moverAlNorte();
            estA.moverAlNorte();
        } else if (snake.getOrientacionDeLaCabeza() == EstadoSnake.SUR &&
                !snake.hayObstaculo(EstadoSnake.SUR) &&
                !snake.hayComida(EstadoSnake.SUR) &&
                !snake.pasoNVecesPorEstaCelda(new Point(
                (int) snake.getPosicionCabeza().getX() + 1,
                (int) snake.getPosicionCabeza().getY()))) {
            snake.moverAlSur();
            estA.moverAlSur();
        } else if (snake.getOrientacionDeLaCabeza() == EstadoSnake.ESTE &&
                !snake.hayObstaculo(EstadoSnake.ESTE) &&
                !snake.hayComida(EstadoSnake.ESTE) &&
                !snake.pasoNVecesPorEstaCelda(new Point(
                (int) snake.getPosicionCabeza().getX(),
                (int) snake.getPosicionCabeza().getY() + 1))) {
            snake.moverAlEste();
            estA.moverAlEste();
        } else if (snake.getOrientacionDeLaCabeza() == EstadoSnake.OESTE &&
                !snake.hayObstaculo(EstadoSnake.OESTE) &&
                !snake.hayComida(EstadoSnake.OESTE) &&
                !snake.pasoNVecesPorEstaCelda(new Point(
                (int) snake.getPosicionCabeza().getX(),
                (int) snake.getPosicionCabeza().getY() - 1))) {
            snake.moverAlOeste();
            estA.moverAlOeste();
        }

        return estA;
    }

    @Override
    public Double getCost() {
        return new Double(7);
    }

    @Override
    public String toString() {
        return "Avanzar";
    }
}
