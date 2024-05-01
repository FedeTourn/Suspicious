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

import frsf.cidisi.faia.examples.search.snake.actions.Avanzar;
import frsf.cidisi.faia.examples.search.snake.actions.Comer;
import frsf.cidisi.faia.examples.search.snake.actions.GirarDerecha;
import frsf.cidisi.faia.examples.search.snake.actions.GirarIzquierda;

import java.util.Vector;

import calculador.Calculador;

import frsf.cidisi.faia.agent.Action;
import frsf.cidisi.faia.agent.Perception;
import frsf.cidisi.faia.agent.search.Problem;
import frsf.cidisi.faia.agent.search.SearchAction;
import frsf.cidisi.faia.agent.search.SearchBasedAgent;
import frsf.cidisi.faia.solver.search.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class AgenteSnake extends SearchBasedAgent {

    private Calculador calculador;

    @Override
    public Action selectAction() {
        // Instanciación la estrategia de búsqueda primero en profundidad.-
//		DepthFirstSearch estrategiaBusqueda = new DepthFirstSearch();

        /**
         * Ejemplos de instanciación de otras estrategias de búsqueda.-
         * 
         * // Instanciación de la estrategia primero en amplitud.-
         * BreathFirstSearch estrategiaBusqueda = new BreathFirstSearch();
         * 
         * // Instanciación de la estrategia de costo uniforme.-
         * IStepCostFunction costo = new FuncionCosto();
         * UniformCostSearch estrategiaBusqueda = new UniformCostSearch(costo);
         *///		IStepCostFunction costo = new FuncionCosto();
//		UniformCostSearch estrategiaBusqueda = new UniformCostSearch(costo);
        DepthFirstSearch estrategiaBusqueda = new DepthFirstSearch();

        // Instancia un proceso de búsqueda indicando como parámetro la estrategia a utilizar.-
        Search busqueda = new Search(estrategiaBusqueda);

        // Indica que el árbol de búsqueda debe ser mostrado e formato XML.-
        busqueda.setVisibleTree(Search.XML_TREE);

        // Le indica al Solver el proceso de búsqueda que debe ejecutar.- 
        this.setSolver(busqueda);

        // Se ejecuta el proceso de selección de la acción más adecuada.-
        Action accionSeleccionada = null;
        try {
            accionSeleccionada = this.getSolver().solve(new Object[]{this.getProblem()});
        } catch (Exception ex) {
            Logger.getLogger(AgenteSnake.class.getName()).log(Level.SEVERE, null, ex);
        }

        if (accionSeleccionada.toString().equals("Avanzar")) {
            this.calculador.reportarAccion(Calculador.AVANZAR);
        } else if (accionSeleccionada.toString().equals("Comer")) {
            this.calculador.reportarAccion(Calculador.COMER);
        } else {
            this.calculador.reportarAccion(Calculador.GIRAR);        // Retorna la acción seleccionada.-
        }
        
        return accionSeleccionada;
    }

    public AgenteSnake(Calculador calculador) {
        // Instancia la meta del Snake.-
        MetaSnake meta = new MetaSnake();
        // Instancia el estado inicial del Snake.-
        EstadoSnake estado = new EstadoSnake();

        this.setAgentState(estado);

        // Se generan las instancias de los operadores del Snake.-
        Vector<SearchAction> operadores = new Vector<SearchAction>();
        operadores.addElement(new Comer());
        operadores.addElement(new Avanzar());
        operadores.addElement(new GirarIzquierda());
        operadores.addElement(new GirarDerecha());

        // Se inicializa y asigna el problema inicial que debe resolver el Snake.-
        EstadoSnake estSnake = (EstadoSnake) this.getAgentState();
        Problem problema = new Problem(meta, estSnake, operadores);
        this.setProblem(problema);

        this.calculador = calculador;
    }

	@Override
	public void see(Perception p) {
		this.getAgentState().updateState(p);
	}
}
