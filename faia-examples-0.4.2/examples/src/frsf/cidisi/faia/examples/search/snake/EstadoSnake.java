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

import java.awt.Point;
import java.util.Iterator;
import java.util.Vector;

import frsf.cidisi.faia.agent.Perception;
import frsf.cidisi.faia.agent.search.SearchBasedAgentState;
import frsf.cidisi.faia.state.AgentState;

public class EstadoSnake extends SearchBasedAgentState {

    private Vector<Point> composicionDelAgente;
    /*
     * Orientación
     * 
     * 0 Norte
     * 1 Sur
     * 2 Este
     * 3 Oeste
     */
    private int orientacionDeLaCabeza;
    public static int NORTE = 0;
    public static int SUR = 1;
    public static int ESTE = 2;
    public static int OESTE = 3;
    /*
     * Mundo Conocido
     * 
     * 0 No hay nada
     * 1 Límite del mundo
     * 2 Comida
     * 3 Parte del Agente
     * -1 Desconocido
     */
    /*
     * El mundoConocido es un vector que tiene las filas que adentro tiene un vector que indica las columnas.
     */
    private Vector<Vector<Integer>> mundoConocido;
    private int longitud;
    private boolean estaVivo;
    private Vector<Point> celdasVisitadas;
    private double costo;
    private boolean encontreElLimiteAlNorte;
    private boolean encontreElLimiteAlSur;
    private boolean encontreElLimiteAlEste;
    private boolean encontreElLimiteAlOeste;
    private int vecesQueSeGiro;
    private int vecesQueSeGiroDerecha;
    public static int NO_HAY_NADA = 0;
    public static int LIMITE_DEL_MUNDO = 1;
    public static int COMIDA = 2;
    public static int PARTE_DEL_AGENTE = 3;
    public static int DESCONOCIDO = -1;

    public EstadoSnake() {
        this.initState();
    }

    public void updateState(Perception p) {
        // Actualizar las percepciones
        Point cabeza = this.composicionDelAgente.firstElement();

        // Al Norte de la cabeza
        if (((PercepcionSnake) p).getSensorNorte() == PercepcionSnake.LIMITE_DEL_MUNDO) {
            this.encontreElLimiteAlNorte = true;

            // Vemos si hay fila de desconocidos al norte
            boolean hayFilaDeDesconocidos = false;

            for (int col = 0; col < this.mundoConocido.size(); col++) {
                hayFilaDeDesconocidos = (this.mundoConocido.firstElement().get(col) == PercepcionSnake.DESCONOCIDO);

                if (hayFilaDeDesconocidos == false) {
                    break;
                }
            }

            if (this.mundoConocido.size() == 10) {
//				if (!hayFilaDeDesconocidos) {
//					return;
//				}
//				else {
                if (hayFilaDeDesconocidos) {
                    boolean columnaDeDesconocidosAlOeste = false;
                    for (int fil = 0; fil < this.mundoConocido.size(); fil++) {
                        columnaDeDesconocidosAlOeste = (this.mundoConocido.get(fil).firstElement() == PercepcionSnake.DESCONOCIDO);

                        if (!columnaDeDesconocidosAlOeste) {
                            break;
                        }
                    }

                    boolean columnaDeDesconocidosAlEste = false;
                    for (int fil = 0; fil < this.mundoConocido.size(); fil++) {
                        columnaDeDesconocidosAlEste = (this.mundoConocido.get(fil).lastElement() == PercepcionSnake.DESCONOCIDO);

                        if (!columnaDeDesconocidosAlEste) {
                            break;
                        }
                    }

                    this.mundoConocido.removeElementAt(0);
                    while (hayFilaDeDesconocidos) {

                        if (columnaDeDesconocidosAlOeste) {
                            for (int fil = 0; fil < this.mundoConocido.size(); fil++) {
                                this.mundoConocido.get(fil).removeElementAt(0);
                            //						Actualizo la posición del agente
                            }
                            Iterator<Point> it = this.composicionDelAgente.iterator();
                            while (it.hasNext()) {
                                Point par = it.next();
                                par.setLocation(par.getX(), par.getY() - 1);
                            }

                            // Actualizo la posición de las celdas visitadas
                            it = this.celdasVisitadas.iterator();
                            while (it.hasNext()) {
                                Point par = it.next();
                                par.setLocation(par.getX(), par.getY() - 1);
                            }

                            for (int fil = 0; fil < this.mundoConocido.size(); fil++) {
                                columnaDeDesconocidosAlOeste = (this.mundoConocido.get(fil).firstElement() == PercepcionSnake.DESCONOCIDO);

                                if (!columnaDeDesconocidosAlOeste) {
                                    break;
                                }
                            }
                        }
                        if (columnaDeDesconocidosAlEste) {
                            for (int fil = 0; fil < this.mundoConocido.size(); fil++) {
                                this.mundoConocido.get(fil).removeElementAt(this.mundoConocido.size() - 1);
                            }
                            for (int fil = 0; fil < this.mundoConocido.size(); fil++) {
                                columnaDeDesconocidosAlEste = (this.mundoConocido.get(fil).lastElement() == PercepcionSnake.DESCONOCIDO);

                                if (!columnaDeDesconocidosAlEste) {
                                    break;
                                }
                            }
                        }

                        Vector<Integer> filaDeDesconocidos = new Vector<Integer>();

                        for (int col = 0; col < this.mundoConocido.size(); col++) {
                            filaDeDesconocidos.add(new Integer(PercepcionSnake.DESCONOCIDO));
                        }
                        this.mundoConocido.removeElementAt(0);
                        this.mundoConocido.add(filaDeDesconocidos);

                        // Actualizo la posición del agente
                        Iterator<Point> it = this.composicionDelAgente.iterator();
                        while (it.hasNext()) {
                            Point par = it.next();
                            par.setLocation(par.getX() - 1, par.getY());
                        }

                        // Actualizo la posición de las celdas visitadas
                        it = this.celdasVisitadas.iterator();
                        while (it.hasNext()) {
                            Point par = it.next();
                            par.setLocation(par.getX() - 1, par.getY());
                        }

                        for (int col = 0; col < this.mundoConocido.size(); col++) {
                            hayFilaDeDesconocidos = (this.mundoConocido.firstElement().get(col) == PercepcionSnake.DESCONOCIDO);

                            if (hayFilaDeDesconocidos == false) {
                                break;
                            }
                        }
                    }
                }
            }

            if (!this.encontreElLimiteAlSur) {
                while (hayFilaDeDesconocidos) {
                    Vector<Integer> filaDeDesconocidos = new Vector<Integer>();

                    for (int col = 0; col < this.mundoConocido.size(); col++) {
                        filaDeDesconocidos.add(new Integer(PercepcionSnake.DESCONOCIDO));
                    }
                    this.mundoConocido.removeElementAt(0);
                    this.mundoConocido.add(filaDeDesconocidos);

                    // Actualizo la posición del agente
                    Iterator<Point> it = this.composicionDelAgente.iterator();
                    while (it.hasNext()) {
                        Point par = it.next();
                        par.setLocation(par.getX() - 1, par.getY());
                    }

                    // Actualizo la posición de las celdas visitadas
                    it = this.celdasVisitadas.iterator();
                    while (it.hasNext()) {
                        Point par = it.next();
                        par.setLocation(par.getX() - 1, par.getY());
                    }

                    for (int col = 0; col < this.mundoConocido.size(); col++) {
                        hayFilaDeDesconocidos = (this.mundoConocido.firstElement().get(col) == PercepcionSnake.DESCONOCIDO);

                        if (hayFilaDeDesconocidos == false) {
                            break;
                        }
                    }
                }
            } else if (hayFilaDeDesconocidos && encontreElLimiteAlSur) {
                boolean columnaDeDesconocidosAlOeste = false;
                for (int fil = 0; fil < this.mundoConocido.size(); fil++) {
                    columnaDeDesconocidosAlOeste = (this.mundoConocido.get(fil).firstElement() == PercepcionSnake.DESCONOCIDO);

                    if (!columnaDeDesconocidosAlOeste) {
                        break;
                    }
                }

                boolean columnaDeDesconocidosAlEste = false;
                for (int fil = 0; fil < this.mundoConocido.size(); fil++) {
                    columnaDeDesconocidosAlEste = (this.mundoConocido.get(fil).lastElement() == PercepcionSnake.DESCONOCIDO);

                    if (!columnaDeDesconocidosAlEste) {
                        break;
                    }
                }

                this.mundoConocido.removeElementAt(0);

                if (columnaDeDesconocidosAlOeste) {
                    for (int fil = 0; fil < this.mundoConocido.size(); fil++) {
                        this.mundoConocido.get(fil).removeElementAt(0);
//					Actualizo la posición del agente
                    }
                    Iterator<Point> it = this.composicionDelAgente.iterator();
                    while (it.hasNext()) {
                        Point par = it.next();
                        par.setLocation(par.getX(), par.getY() - 1);
                    }

                    // Actualizo la posición de las celdas visitadas
                    it = this.celdasVisitadas.iterator();
                    while (it.hasNext()) {
                        Point par = it.next();
                        par.setLocation(par.getX(), par.getY() - 1);
                    }
                } else if (columnaDeDesconocidosAlEste) {
                    for (int fil = 0; fil < this.mundoConocido.size(); fil++) {
                        this.mundoConocido.get(fil).removeElementAt(this.mundoConocido.size() - 1);//					Actualizo la posición del agente
                    }
                    Iterator<Point> it = this.composicionDelAgente.iterator();
                    while (it.hasNext()) {
                        Point par = it.next();
                        par.setLocation(par.getX() - 1, par.getY());
                    }

                    // Actualizo la posición de las celdas visitadas
                    it = this.celdasVisitadas.iterator();
                    while (it.hasNext()) {
                        Point par = it.next();
                        par.setLocation(par.getX() - 1, par.getY());
                    }
                } else {
//					Actualizo la posición del agente
                    Iterator<Point> it = this.composicionDelAgente.iterator();
                    while (it.hasNext()) {
                        Point par = it.next();
                        par.setLocation(par.getX() - 1, par.getY());
                    }

                    // Actualizo la posición de las celdas visitadas
                    it = this.celdasVisitadas.iterator();
                    while (it.hasNext()) {
                        Point par = it.next();
                        par.setLocation(par.getX() - 1, par.getY());
                    }
                }
            }
        } else {
            if ((int) cabeza.getX() == 0) {
                if (this.mundoConocido.size() == 10 &&
                        !this.encontreElLimiteAlSur &&
                        !this.encontreElLimiteAlNorte) {
                    /*
                     * Si estoy en el límite máximo del mundo, la idea es que seguro habrá algo desconocido en el extremo opuesto,
                     * por lo tanto, determino que esa fila desconocida, estará al norte ahora.
                     * 
                     */
                    Vector<Integer> filaNueva = new Vector<Integer>();

                    for (int fil = 0; fil < this.mundoConocido.size(); fil++) {
                        filaNueva.add(new Integer(PercepcionSnake.DESCONOCIDO));
                    }
                    this.mundoConocido.removeElementAt(this.mundoConocido.size() - 1);

                    this.mundoConocido.insertElementAt(filaNueva, 0);

//					Actualizo la posición del agente
                    Iterator<Point> it = this.composicionDelAgente.iterator();
                    while (it.hasNext()) {
                        Point par = it.next();
                        par.setLocation(par.getX() + 1, par.getY());
                    }

                    // Actualizo la posición de las celdas visitadas
                    it = this.celdasVisitadas.iterator();
                    while (it.hasNext()) {
                        Point par = it.next();
                        par.setLocation(par.getX() + 1, par.getY());
                    }
                } else {
                    // Agrando el mundo conocido
                    // Creo la fila nueva
                    Vector<Integer> filaNueva = new Vector<Integer>();
                    for (int fil = 0; fil < this.mundoConocido.size(); fil++) {
                        filaNueva.add(new Integer(PercepcionSnake.DESCONOCIDO));                    // Agrego la fila nueva a la matriz del mundo conocido al norte
                    }
                    this.mundoConocido.insertElementAt(filaNueva, 0);

                    // Por defecto, para que la matriz quede cuadrada, agregamos una columna al este.
                    // Si no se puede xq ya se encontró el límite, se agrega al oeste.
                    if (!this.encontreElLimiteAlEste) {
                        for (int fil = 0; fil < this.mundoConocido.size(); fil++) {
                            this.mundoConocido.get(fil).add(new Integer(EstadoSnake.DESCONOCIDO));                        // Actualizo la posición del agente
                        }
                        Iterator<Point> it = this.composicionDelAgente.iterator();
                        while (it.hasNext()) {
                            Point par = it.next();
                            par.setLocation(par.getX() + 1, par.getY());
                        }

                        // Actualizo la posición de las celdas visitadas
                        it = this.celdasVisitadas.iterator();
                        while (it.hasNext()) {
                            Point par = it.next();
                            par.setLocation(par.getX() + 1, par.getY());
                        }
                    } else {
                        for (int fil = 0; fil < this.mundoConocido.size(); fil++) {
                            this.mundoConocido.get(fil).insertElementAt(new Integer(EstadoSnake.DESCONOCIDO), 0);                        // Actualizo la posición del agente
                        }
                        Iterator<Point> it = this.composicionDelAgente.iterator();
                        while (it.hasNext()) {
                            Point par = it.next();
                            par.setLocation(par.getX() + 1, par.getY() + 1);
                        }

                        // Actualizo la posición de las celdas visitadas
                        it = this.celdasVisitadas.iterator();
                        while (it.hasNext()) {
                            Point par = it.next();
                            par.setLocation(par.getX() + 1, par.getY() + 1);
                        }
                    }
                }
            }
            cabeza = this.composicionDelAgente.firstElement();
            // Agrego la percepción
            this.mundoConocido.get((int) cabeza.getX() - 1).setElementAt(
                    new Integer(((PercepcionSnake) p).getSensorNorte()), (int) cabeza.getY());
        }
        // Al Sur de la cabeza
        if (((PercepcionSnake) p).getSensorSur() == PercepcionSnake.LIMITE_DEL_MUNDO) {
            this.encontreElLimiteAlSur = true;

            // Vemos si hay fila de desconocidos al sur
            boolean hayFilaDeDesconocidos = false;

            for (int col = 0; col < this.mundoConocido.size(); col++) {
                hayFilaDeDesconocidos = (this.mundoConocido.lastElement().get(col) == PercepcionSnake.DESCONOCIDO);

                if (hayFilaDeDesconocidos == false) {
                    break;
                }
            }

            if (this.mundoConocido.size() == 10) {
//				if (!hayFilaDeDesconocidos) {
//					return;
//				}
//				else {
                if (hayFilaDeDesconocidos) {
                    boolean columnaDeDesconocidosAlOeste = false;
                    for (int fil = 0; fil < this.mundoConocido.size(); fil++) {
                        columnaDeDesconocidosAlOeste = (this.mundoConocido.get(fil).firstElement() == PercepcionSnake.DESCONOCIDO);

                        if (!columnaDeDesconocidosAlOeste) {
                            break;
                        }
                    }

                    boolean columnaDeDesconocidosAlEste = false;
                    for (int fil = 0; fil < this.mundoConocido.size(); fil++) {
                        columnaDeDesconocidosAlEste = (this.mundoConocido.get(fil).lastElement() == PercepcionSnake.DESCONOCIDO);

                        if (!columnaDeDesconocidosAlEste) {
                            break;
                        }
                    }

                    this.mundoConocido.removeElementAt(this.mundoConocido.size() - 1);

                    while (hayFilaDeDesconocidos) {
                        if (columnaDeDesconocidosAlOeste && hayFilaDeDesconocidos) {
                            for (int fil = 0; fil < this.mundoConocido.size(); fil++) {
                                this.mundoConocido.get(fil).removeElementAt(0);
                            //						Actualizo la posición del agente
                            }
                            Iterator<Point> it = this.composicionDelAgente.iterator();
                            while (it.hasNext()) {
                                Point par = it.next();
                                par.setLocation(par.getX(), par.getY() - 1);
                            }

                            // Actualizo la posición de las celdas visitadas
                            it = this.celdasVisitadas.iterator();
                            while (it.hasNext()) {
                                Point par = it.next();
                                par.setLocation(par.getX(), par.getY() - 1);
                            }
                        }
                        if (columnaDeDesconocidosAlEste && hayFilaDeDesconocidos) {
                            for (int fil = 0; fil < this.mundoConocido.size(); fil++) {
                                this.mundoConocido.get(fil).removeElementAt(this.mundoConocido.size() - 1);
                            }
                            for (int fil = 0; fil < this.mundoConocido.size(); fil++) {
                                columnaDeDesconocidosAlEste = (this.mundoConocido.get(fil).lastElement() == PercepcionSnake.DESCONOCIDO);

                                if (!columnaDeDesconocidosAlEste) {
                                    break;
                                }
                            }
                        }


                        Vector<Integer> filaDeDesconocidos = new Vector<Integer>();

                        for (int col = 0; col < this.mundoConocido.size(); col++) {
                            filaDeDesconocidos.add(new Integer(PercepcionSnake.DESCONOCIDO));
                        }
                        this.mundoConocido.removeElementAt(this.mundoConocido.size() - 1);
                        this.mundoConocido.insertElementAt(filaDeDesconocidos, 0);

                        // Actualizo la posición del agente
                        Iterator<Point> it = this.composicionDelAgente.iterator();
                        while (it.hasNext()) {
                            Point par = it.next();
                            par.setLocation(par.getX() + 1, par.getY());
                        }

                        // Actualizo la posición de las celdas visitadas
                        it = this.celdasVisitadas.iterator();
                        while (it.hasNext()) {
                            Point par = it.next();
                            par.setLocation(par.getX() + 1, par.getY());
                        }

                        for (int col = 0; col < this.mundoConocido.size(); col++) {
                            hayFilaDeDesconocidos = (this.mundoConocido.lastElement().get(col) == PercepcionSnake.DESCONOCIDO);

                            if (hayFilaDeDesconocidos == false) {
                                break;
                            }
                        }
                    }
                }
            }

            if (!this.encontreElLimiteAlNorte) {
                while (hayFilaDeDesconocidos) {
                    Vector<Integer> filaDeDesconocidos = new Vector<Integer>();

                    for (int col = 0; col < this.mundoConocido.size(); col++) {
                        filaDeDesconocidos.add(new Integer(PercepcionSnake.DESCONOCIDO));
                    }
                    this.mundoConocido.removeElementAt(this.mundoConocido.size() - 1);
                    this.mundoConocido.insertElementAt(filaDeDesconocidos, 0);

                    // Actualizo la posición del agente
                    Iterator<Point> it = this.composicionDelAgente.iterator();
                    while (it.hasNext()) {
                        Point par = it.next();
                        par.setLocation(par.getX() + 1, par.getY());
                    }

                    // Actualizo la posición de las celdas visitadas
                    it = this.celdasVisitadas.iterator();
                    while (it.hasNext()) {
                        Point par = it.next();
                        par.setLocation(par.getX() + 1, par.getY());
                    }

                    for (int col = 0; col < this.mundoConocido.size(); col++) {
                        hayFilaDeDesconocidos = (this.mundoConocido.lastElement().get(col) == PercepcionSnake.DESCONOCIDO);

                        if (hayFilaDeDesconocidos == false) {
                            break;
                        }
                    }
                }
            } else if (hayFilaDeDesconocidos && encontreElLimiteAlNorte) {
                boolean columnaDeDesconocidosAlOeste = false;
                for (int fil = 0; fil < this.mundoConocido.size(); fil++) {
                    columnaDeDesconocidosAlOeste = (this.mundoConocido.get(fil).firstElement() == PercepcionSnake.DESCONOCIDO);

                    if (!columnaDeDesconocidosAlOeste) {
                        break;
                    }
                }

                boolean columnaDeDesconocidosAlEste = false;
                for (int fil = 0; fil < this.mundoConocido.size(); fil++) {
                    columnaDeDesconocidosAlEste = (this.mundoConocido.get(fil).lastElement() == PercepcionSnake.DESCONOCIDO);

                    if (!columnaDeDesconocidosAlEste) {
                        break;
                    }
                }

                this.mundoConocido.removeElementAt(this.mundoConocido.size() - 1);

                if (columnaDeDesconocidosAlOeste) {
                    for (int fil = 0; fil < this.mundoConocido.size(); fil++) {
                        this.mundoConocido.get(fil).removeElementAt(0);
//					Actualizo la posición del agente
                    }
                    Iterator<Point> it = this.composicionDelAgente.iterator();
                    while (it.hasNext()) {
                        Point par = it.next();
                        par.setLocation(par.getX(), par.getY() - 1);
                    }

                    // Actualizo la posición de las celdas visitadas
                    it = this.celdasVisitadas.iterator();
                    while (it.hasNext()) {
                        Point par = it.next();
                        par.setLocation(par.getX(), par.getY() - 1);
                    }
                } else if (columnaDeDesconocidosAlEste) {
                    for (int fil = 0; fil < this.mundoConocido.size(); fil++) {
                        this.mundoConocido.get(fil).removeElementAt(this.mundoConocido.size() - 1);
                    }
                }
            }
        } else {
            if ((int) cabeza.getX() == this.mundoConocido.size() - 1) {
                if (this.mundoConocido.size() == 10 &&
                        !this.encontreElLimiteAlNorte &&
                        !this.encontreElLimiteAlSur) {
                    /*
                     * Si estoy en el límite máximo del mundo, la idea es que seguro habrá algo desconocido en el extremo opuesto,
                     * por lo tanto, determino que esa fila desconocida, estará al sur ahora.
                     * 
                     */
                    Vector<Integer> filaNueva = new Vector<Integer>();

                    for (int fil = 0; fil < this.mundoConocido.size(); fil++) {
                        filaNueva.add(new Integer(PercepcionSnake.DESCONOCIDO));
                    }
                    this.mundoConocido.removeElementAt(0);

                    this.mundoConocido.add(filaNueva);

//					Actualizo la posición del agente
                    Iterator<Point> it = this.composicionDelAgente.iterator();
                    while (it.hasNext()) {
                        Point par = it.next();
                        par.setLocation(par.getX() - 1, par.getY());
                    }

                    // Actualizo la posición de las celdas visitadas
                    it = this.celdasVisitadas.iterator();
                    while (it.hasNext()) {
                        Point par = it.next();
                        par.setLocation(par.getX() - 1, par.getY());
                    }
                } else {
                    // Agrando el mundo conocido
                    // Creo la fila nueva
                    Vector<Integer> filaNueva = new Vector<Integer>();
                    for (int fil = 0; fil < this.mundoConocido.size(); fil++) {
                        filaNueva.add(new Integer(PercepcionSnake.DESCONOCIDO));                    // Agrego la fila nueva a la matriz del mundo conocido al sur
                    }
                    this.mundoConocido.add(filaNueva);

                    // Por defecto, para que la matriz quede cuadrada, agregamos una columna al este.
                    // Si no se puede xq ya se encontró el límite, se agrega al oeste.
                    if (!this.encontreElLimiteAlEste) {
                        for (int fil = 0; fil < this.mundoConocido.size(); fil++) {
                            this.mundoConocido.get(fil).add(new Integer(EstadoSnake.DESCONOCIDO));                        // No tengo que actualizar la posición del agente ni las celdas visitadas.
                        }
                    } else {
                        for (int fil = 0; fil < this.mundoConocido.size(); fil++) {
                            this.mundoConocido.get(fil).insertElementAt(new Integer(EstadoSnake.DESCONOCIDO), 0);                        // Actualizo la posición del agente
                        }
                        Iterator<Point> it = this.composicionDelAgente.iterator();
                        while (it.hasNext()) {
                            Point par = it.next();
                            par.setLocation(par.getX(), par.getY() + 1);
                        }

                        // Actualizo la posición de las celdas visitadas
                        it = this.celdasVisitadas.iterator();
                        while (it.hasNext()) {
                            Point par = it.next();
                            par.setLocation(par.getX(), par.getY() + 1);
                        }
                    }
                }
            }
            cabeza = this.composicionDelAgente.firstElement();
            this.mundoConocido.get((int) cabeza.getX() + 1).setElementAt(
                    new Integer(((PercepcionSnake) p).getSensorSur()), (int) cabeza.getY());
        }
        // Al Este de la cabeza
        if (((PercepcionSnake) p).getSensorEste() == PercepcionSnake.LIMITE_DEL_MUNDO) {
            this.encontreElLimiteAlEste = true;

            // Vemos si hay columna de desconocidos al este
            boolean hayColumnaDeDesconocidos = false;

            for (int fil = 0; fil < this.mundoConocido.size(); fil++) {
                hayColumnaDeDesconocidos = (this.mundoConocido.get(fil).lastElement() == PercepcionSnake.DESCONOCIDO);

                if (hayColumnaDeDesconocidos == false) {
                    break;
                }
            }

            if (this.mundoConocido.size() == 10) {
//				if (!hayColumnaDeDesconocidos) 
//					return;
//				else {
                if (hayColumnaDeDesconocidos) {
                    boolean filaDeDesconocidosAlNorte = false;
                    for (int col = 0; col < this.mundoConocido.size(); col++) {
                        filaDeDesconocidosAlNorte = (this.mundoConocido.firstElement().get(col) == PercepcionSnake.DESCONOCIDO);

                        if (!filaDeDesconocidosAlNorte) {
                            break;
                        }
                    }

                    boolean filaDeDesconocidosAlSur = false;
                    for (int col = 0; col < this.mundoConocido.size(); col++) {
                        filaDeDesconocidosAlSur = (this.mundoConocido.lastElement().get(col) == PercepcionSnake.DESCONOCIDO);

                        if (!filaDeDesconocidosAlSur) {
                            break;
                        }
                    }

                    for (int fil = 0; fil < this.mundoConocido.size(); fil++) {
                        this.mundoConocido.get(fil).removeElementAt(this.mundoConocido.size() - 1);
                    }
                    while (hayColumnaDeDesconocidos) {
                        if (filaDeDesconocidosAlNorte && hayColumnaDeDesconocidos) {
                            this.mundoConocido.removeElementAt(0);
                            //						Actualizo la posición del agente
                            Iterator<Point> it = this.composicionDelAgente.iterator();
                            while (it.hasNext()) {
                                Point par = it.next();
                                par.setLocation(par.getX() - 1, par.getY());
                            }

                            // Actualizo la posición de las celdas visitadas
                            it = this.celdasVisitadas.iterator();
                            while (it.hasNext()) {
                                Point par = it.next();
                                par.setLocation(par.getX() - 1, par.getY());
                            }

                            for (int col = 0; col < this.mundoConocido.size(); col++) {
                                filaDeDesconocidosAlNorte = (this.mundoConocido.firstElement().get(col) == PercepcionSnake.DESCONOCIDO);

                                if (!filaDeDesconocidosAlNorte) {
                                    break;
                                }
                            }
                        }
                        if (filaDeDesconocidosAlSur && hayColumnaDeDesconocidos) {
                            this.mundoConocido.removeElementAt(this.mundoConocido.size() - 1);

                            for (int col = 0; col < this.mundoConocido.size(); col++) {
                                filaDeDesconocidosAlSur = (this.mundoConocido.lastElement().get(col) == PercepcionSnake.DESCONOCIDO);

                                if (!filaDeDesconocidosAlSur) {
                                    break;
                                }
                            }
                        }


                        for (int fil = 0; fil < this.mundoConocido.size(); fil++) {
                            this.mundoConocido.get(fil).removeElementAt(this.mundoConocido.size() - 1);
                            this.mundoConocido.get(fil).insertElementAt(new Integer(PercepcionSnake.DESCONOCIDO), 0);
                        }

                        // Actualizo la posición del agente
                        Iterator<Point> it = this.composicionDelAgente.iterator();
                        while (it.hasNext()) {
                            Point par = it.next();
                            par.setLocation(par.getX(), par.getY() + 1);
                        }

                        // Actualizo la posición de las celdas visitadas
                        it = this.celdasVisitadas.iterator();
                        while (it.hasNext()) {
                            Point par = it.next();
                            par.setLocation(par.getX(), par.getY() + 1);
                        }

                        for (int fil = 0; fil < this.mundoConocido.size(); fil++) {
                            hayColumnaDeDesconocidos = (this.mundoConocido.get(fil).lastElement() == PercepcionSnake.DESCONOCIDO);

                            if (hayColumnaDeDesconocidos == false) {
                                break;
                            }
                        }
                    }
                }
            }

            if (!this.encontreElLimiteAlOeste) {
                while (hayColumnaDeDesconocidos) {
                    for (int fil = 0; fil < this.mundoConocido.size(); fil++) {
                        this.mundoConocido.get(fil).removeElementAt(this.mundoConocido.size() - 1);
                        this.mundoConocido.get(fil).insertElementAt(new Integer(PercepcionSnake.DESCONOCIDO), 0);
                    }

                    // Actualizo la posición del agente
                    Iterator<Point> it = this.composicionDelAgente.iterator();
                    while (it.hasNext()) {
                        Point par = it.next();
                        par.setLocation(par.getX(), par.getY() + 1);
                    }

                    // Actualizo la posición de las celdas visitadas
                    it = this.celdasVisitadas.iterator();
                    while (it.hasNext()) {
                        Point par = it.next();
                        par.setLocation(par.getX(), par.getY() + 1);
                    }

                    for (int fil = 0; fil < this.mundoConocido.size(); fil++) {
                        hayColumnaDeDesconocidos = (this.mundoConocido.get(fil).lastElement() == PercepcionSnake.DESCONOCIDO);

                        if (hayColumnaDeDesconocidos == false) {
                            break;
                        }
                    }
                }
            } else if (hayColumnaDeDesconocidos && encontreElLimiteAlOeste) {
                boolean filaDeDesconocidosAlNorte = false;
                for (int col = 0; col < this.mundoConocido.size(); col++) {
                    filaDeDesconocidosAlNorte = (this.mundoConocido.firstElement().get(col) == PercepcionSnake.DESCONOCIDO);

                    if (!filaDeDesconocidosAlNorte) {
                        break;
                    }
                }

                boolean filaDeDesconocidosAlSur = false;
                for (int col = 0; col < this.mundoConocido.size(); col++) {
                    filaDeDesconocidosAlSur = (this.mundoConocido.lastElement().get(col) == PercepcionSnake.DESCONOCIDO);

                    if (!filaDeDesconocidosAlSur) {
                        break;
                    }
                }

                for (int fil = 0; fil < this.mundoConocido.size(); fil++) {
                    this.mundoConocido.get(fil).removeElementAt(this.mundoConocido.size() - 1);
                }
                if (filaDeDesconocidosAlNorte) {
                    this.mundoConocido.removeElementAt(0);
//					Actualizo la posición del agente
                    Iterator<Point> it = this.composicionDelAgente.iterator();
                    while (it.hasNext()) {
                        Point par = it.next();
                        par.setLocation(par.getX() - 1, par.getY());
                    }

                    // Actualizo la posición de las celdas visitadas
                    it = this.celdasVisitadas.iterator();
                    while (it.hasNext()) {
                        Point par = it.next();
                        par.setLocation(par.getX() - 1, par.getY());
                    }
                } else if (filaDeDesconocidosAlSur) {
                    this.mundoConocido.removeElementAt(this.mundoConocido.size() - 1);
                }

            }
        } else {
            if ((int) cabeza.getY() == this.mundoConocido.size() - 1) {
                if (this.mundoConocido.size() == 10 &&
                        !this.encontreElLimiteAlOeste &&
                        !this.encontreElLimiteAlEste) {
                    /*
                     * Si estoy en el límite máximo del mundo, la idea es que seguro habrá algo desconocido en el extremo opuesto,
                     * por lo tanto, determino que esa columna desconocida, estará al este ahora.
                     * 
                     */

                    for (int fil = 0; fil < this.mundoConocido.size(); fil++) {
                        this.mundoConocido.get(fil).removeElementAt(0);
                        this.mundoConocido.get(fil).add(new Integer(PercepcionSnake.DESCONOCIDO));
                    }

//					Actualizo la posición del agente
                    Iterator<Point> it = this.composicionDelAgente.iterator();
                    while (it.hasNext()) {
                        Point par = it.next();
                        par.setLocation(par.getX(), par.getY() - 1);
                    }

                    // Actualizo la posición de las celdas visitadas
                    it = this.celdasVisitadas.iterator();
                    while (it.hasNext()) {
                        Point par = it.next();
                        par.setLocation(par.getX(), par.getY() - 1);
                    }
                } else {
                    // Agrando el mundo conocido
                    // Creo la fila nueva
                    Vector<Integer> filaNueva = new Vector<Integer>();
                    for (int fil = 0; fil < this.mundoConocido.size(); fil++) {
                        filaNueva.add(new Integer(PercepcionSnake.DESCONOCIDO));                    // Por defecto, para que la matriz quede cuadrada, agregamos una fila al sur.
                    // Si no se puede xq ya se encontró el límite, se agrega al norte.
                    // Agrego la fila nueva a la matriz del mundo conocido al sur
                    }
                    if (!this.encontreElLimiteAlSur) {
                        this.mundoConocido.add(filaNueva);

                        // Agregamos las columnas
                        for (int fil = 0; fil < this.mundoConocido.size(); fil++) {
                            this.mundoConocido.get(fil).add(new Integer(EstadoSnake.DESCONOCIDO));
                        }
                    } else {
                        this.mundoConocido.insertElementAt(filaNueva, 0);

                        // Agregamos las columnas
                        for (int fil = 0; fil < this.mundoConocido.size(); fil++) {
                            this.mundoConocido.get(fil).add(new Integer(EstadoSnake.DESCONOCIDO));                        // Actualizo la posición del agente
                        }
                        Iterator<Point> it = this.composicionDelAgente.iterator();
                        while (it.hasNext()) {
                            Point par = it.next();
                            par.setLocation(par.getX() + 1, par.getY());
                        }

                        // Actualizo la posición de las celdas visitadas
                        it = this.celdasVisitadas.iterator();
                        while (it.hasNext()) {
                            Point par = it.next();
                            par.setLocation(par.getX() + 1, par.getY());
                        }
                    }
                }
            }

            cabeza = this.composicionDelAgente.firstElement();
            this.mundoConocido.get((int) cabeza.getX()).setElementAt(
                    new Integer(((PercepcionSnake) p).getSensorEste()), (int) cabeza.getY() + 1);
        }
        // Al Oeste de la cabeza
        if (((PercepcionSnake) p).getSensorOeste() == PercepcionSnake.LIMITE_DEL_MUNDO) {
            this.encontreElLimiteAlOeste = true;

            // Vemos si hay columna de desconocidos al oeste
            boolean hayColumnaDeDesconocidos = false;

            for (int fil = 0; fil < this.mundoConocido.size(); fil++) {
                hayColumnaDeDesconocidos = (this.mundoConocido.get(fil).firstElement() == PercepcionSnake.DESCONOCIDO);

                if (hayColumnaDeDesconocidos == false) {
                    break;
                }
            }

            if (this.mundoConocido.size() == 10) {
//				if (!hayColumnaDeDesconocidos)
//					return;
//				else {
                if (hayColumnaDeDesconocidos) {
                    boolean filaDeDesconocidosAlNorte = false;
                    for (int col = 0; col < this.mundoConocido.size(); col++) {
                        filaDeDesconocidosAlNorte = (this.mundoConocido.firstElement().get(col) == PercepcionSnake.DESCONOCIDO);

                        if (!filaDeDesconocidosAlNorte) {
                            break;
                        }
                    }

                    boolean filaDeDesconocidosAlSur = false;
                    for (int col = 0; col < this.mundoConocido.size(); col++) {
                        filaDeDesconocidosAlSur = (this.mundoConocido.lastElement().get(col) == PercepcionSnake.DESCONOCIDO);

                        if (!filaDeDesconocidosAlSur) {
                            break;
                        }
                    }

                    for (int fil = 0; fil < this.mundoConocido.size(); fil++) {
                        this.mundoConocido.get(fil).removeElementAt(0);
                    }
                    while (hayColumnaDeDesconocidos) {
                        if (filaDeDesconocidosAlNorte && hayColumnaDeDesconocidos) {
                            this.mundoConocido.removeElementAt(0);
                            //						Actualizo la posición del agente
                            Iterator<Point> it = this.composicionDelAgente.iterator();
                            while (it.hasNext()) {
                                Point par = it.next();
                                par.setLocation(par.getX() - 1, par.getY());
                            }

                            // Actualizo la posición de las celdas visitadas
                            it = this.celdasVisitadas.iterator();
                            while (it.hasNext()) {
                                Point par = it.next();
                                par.setLocation(par.getX() - 1, par.getY());
                            }

                            for (int col = 0; col < this.mundoConocido.size(); col++) {
                                filaDeDesconocidosAlNorte = (this.mundoConocido.firstElement().get(col) == PercepcionSnake.DESCONOCIDO);

                                if (!filaDeDesconocidosAlNorte) {
                                    break;
                                }
                            }

                        }
                        if (filaDeDesconocidosAlSur && hayColumnaDeDesconocidos) {
                            this.mundoConocido.removeElementAt(this.mundoConocido.size() - 1);

                            for (int col = 0; col < this.mundoConocido.size(); col++) {
                                filaDeDesconocidosAlSur = (this.mundoConocido.lastElement().get(col) == PercepcionSnake.DESCONOCIDO);

                                if (!filaDeDesconocidosAlSur) {
                                    break;
                                }
                            }
                        }


                        for (int fil = 0; fil < this.mundoConocido.size(); fil++) {
                            this.mundoConocido.get(fil).removeElementAt(0);
                            this.mundoConocido.get(fil).add(new Integer(PercepcionSnake.DESCONOCIDO));
                        }

                        // Actualizo la posición del agente
                        Iterator<Point> it = this.composicionDelAgente.iterator();
                        while (it.hasNext()) {
                            Point par = it.next();
                            par.setLocation(par.getX(), par.getY() - 1);
                        }

                        // Actualizo la posición de las celdas visitadas
                        it = this.celdasVisitadas.iterator();
                        while (it.hasNext()) {
                            Point par = it.next();
                            par.setLocation(par.getX(), par.getY() - 1);
                        }

                        for (int fil = 0; fil < this.mundoConocido.size(); fil++) {
                            hayColumnaDeDesconocidos = (this.mundoConocido.get(fil).firstElement() == PercepcionSnake.DESCONOCIDO);

                            if (hayColumnaDeDesconocidos == false) {
                                break;
                            }
                        }
                    }

//					// Actualizo la posición del agente
//					Iterator<Point> it =  this.composicionDelAgente.iterator();
//					while (it.hasNext()) {
//						Point par = it.next();
//						par.setLocation(par.getX(), par.getY() - 1);
//					}
//
//					// Actualizo la posición de las celdas visitadas
//					it =  this.celdasVisitadas.iterator();
//					while (it.hasNext()) {
//						Point par = it.next();
//						par.setLocation(par.getX(), par.getY() - 1);
//					}

                }
            }

            if (!this.encontreElLimiteAlEste) {
                while (hayColumnaDeDesconocidos) {
                    for (int fil = 0; fil < this.mundoConocido.size(); fil++) {
                        this.mundoConocido.get(fil).removeElementAt(0);
                        this.mundoConocido.get(fil).add(new Integer(PercepcionSnake.DESCONOCIDO));
                    }

                    // Actualizo la posición del agente
                    Iterator<Point> it = this.composicionDelAgente.iterator();
                    while (it.hasNext()) {
                        Point par = it.next();
                        par.setLocation(par.getX(), par.getY() - 1);
                    }

                    // Actualizo la posición de las celdas visitadas
                    it = this.celdasVisitadas.iterator();
                    while (it.hasNext()) {
                        Point par = it.next();
                        par.setLocation(par.getX(), par.getY() - 1);
                    }

                    for (int fil = 0; fil < this.mundoConocido.size(); fil++) {
                        hayColumnaDeDesconocidos = (this.mundoConocido.get(fil).firstElement() == PercepcionSnake.DESCONOCIDO);

                        if (hayColumnaDeDesconocidos == false) {
                            break;
                        }
                    }
                }
            } else if (hayColumnaDeDesconocidos && encontreElLimiteAlEste) {
                boolean filaDeDesconocidosAlNorte = false;
                for (int col = 0; col < this.mundoConocido.size(); col++) {
                    filaDeDesconocidosAlNorte = (this.mundoConocido.firstElement().get(col) == PercepcionSnake.DESCONOCIDO);

                    if (!filaDeDesconocidosAlNorte) {
                        break;
                    }
                }

                boolean filaDeDesconocidosAlSur = false;
                for (int col = 0; col < this.mundoConocido.size(); col++) {
                    filaDeDesconocidosAlSur = (this.mundoConocido.lastElement().get(col) == PercepcionSnake.DESCONOCIDO);

                    if (!filaDeDesconocidosAlSur) {
                        break;
                    }
                }

                for (int fil = 0; fil < this.mundoConocido.size(); fil++) {
                    this.mundoConocido.get(fil).removeElementAt(0);
                }
                if (filaDeDesconocidosAlNorte) {
                    this.mundoConocido.removeElementAt(0);
//					Actualizo la posición del agente
                    Iterator<Point> it = this.composicionDelAgente.iterator();
                    while (it.hasNext()) {
                        Point par = it.next();
                        par.setLocation(par.getX() - 1, par.getY() - 1);
                    }

                    // Actualizo la posición de las celdas visitadas
                    it = this.celdasVisitadas.iterator();
                    while (it.hasNext()) {
                        Point par = it.next();
                        par.setLocation(par.getX() - 1, par.getY() - 1);
                    }
                } else if (filaDeDesconocidosAlSur) {
                    this.mundoConocido.removeElementAt(this.mundoConocido.size() - 1);

                    // Actualizo la posición del agente
                    Iterator<Point> it = this.composicionDelAgente.iterator();
                    while (it.hasNext()) {
                        Point par = it.next();
                        par.setLocation(par.getX(), par.getY() - 1);
                    }

                    // Actualizo la posición de las celdas visitadas
                    it = this.celdasVisitadas.iterator();
                    while (it.hasNext()) {
                        Point par = it.next();
                        par.setLocation(par.getX(), par.getY() - 1);
                    }
                } else {
//					Actualizo la posición del agente
                    Iterator<Point> it = this.composicionDelAgente.iterator();
                    while (it.hasNext()) {
                        Point par = it.next();
                        par.setLocation(par.getX(), par.getY() - 1);
                    }

                    // Actualizo la posición de las celdas visitadas
                    it = this.celdasVisitadas.iterator();
                    while (it.hasNext()) {
                        Point par = it.next();
                        par.setLocation(par.getX(), par.getY() - 1);
                    }
                }
            }
        } else {
            if ((int) cabeza.getY() == 0) {
                if (this.mundoConocido.size() == 10 &&
                        !this.encontreElLimiteAlEste &&
                        !this.encontreElLimiteAlOeste) {
                    /*
                     * Si estoy en el límite máximo del mundo, la idea es que seguro habrá algo desconocido en el extremo opuesto,
                     * por lo tanto, determino que esa columna desconocida, estará al oeste ahora.
                     * 
                     */

                    for (int fil = 0; fil < this.mundoConocido.size(); fil++) {
                        this.mundoConocido.get(fil).removeElementAt(this.mundoConocido.size() - 1);
                        this.mundoConocido.get(fil).insertElementAt(new Integer(PercepcionSnake.DESCONOCIDO), 0);
                    }

//					Actualizo la posición del agente
                    Iterator<Point> it = this.composicionDelAgente.iterator();
                    while (it.hasNext()) {
                        Point par = it.next();
                        par.setLocation(par.getX(), par.getY() + 1);
                    }

                    // Actualizo la posición de las celdas visitadas
                    it = this.celdasVisitadas.iterator();
                    while (it.hasNext()) {
                        Point par = it.next();
                        par.setLocation(par.getX(), par.getY() + 1);
                    }
                } else {
                    // Agrando el mundo conocido

                    // Creo la fila nueva
                    Vector<Integer> filaNueva = new Vector<Integer>();
                    for (int fil = 0; fil < this.mundoConocido.size(); fil++) {
                        filaNueva.add(new Integer(PercepcionSnake.DESCONOCIDO));                    // Por defecto, para que la matriz quede cuadrada, agregamos una fila al sur.
                    // Si no se puede xq ya se encontró el límite, se agrega al norte.
                    // Agrego la fila nueva a la matriz del mundo conocido al sur
                    }
                    if (!this.encontreElLimiteAlSur) {
                        this.mundoConocido.add(filaNueva);

                        // Agregamos las columnas
                        for (int fil = 0; fil < this.mundoConocido.size(); fil++) {
                            this.mundoConocido.get(fil).insertElementAt(new Integer(EstadoSnake.DESCONOCIDO), 0);                        // Actualizo la posición del agente
                        }
                        Iterator<Point> it = this.composicionDelAgente.iterator();
                        while (it.hasNext()) {
                            Point par = it.next();
                            par.setLocation(par.getX(), par.getY() + 1);
                        }

                        // Actualizo la posición de las celdas visitadas
                        it = this.celdasVisitadas.iterator();
                        while (it.hasNext()) {
                            Point par = it.next();
                            par.setLocation(par.getX(), par.getY() + 1);
                        }
                    } else {
                        this.mundoConocido.insertElementAt(filaNueva, 0);

                        // Agregamos las columnas
                        for (int fil = 0; fil < this.mundoConocido.size(); fil++) {
                            this.mundoConocido.get(fil).insertElementAt(new Integer(EstadoSnake.DESCONOCIDO), 0);                        // Actualizo la posición del agente
                        }
                        Iterator<Point> it = this.composicionDelAgente.iterator();
                        while (it.hasNext()) {
                            Point par = it.next();
                            par.setLocation(par.getX() + 1, par.getY() + 1);
                        }

                        // Actualizo la posición de las celdas visitadas
                        it = this.celdasVisitadas.iterator();
                        while (it.hasNext()) {
                            Point par = it.next();
                            par.setLocation(par.getX() + 1, par.getY() + 1);
                        }
                    }
                }
            }
            cabeza = this.composicionDelAgente.firstElement();
            this.mundoConocido.get((int) cabeza.getX()).setElementAt(
                    new Integer(((PercepcionSnake) p).getSensorOeste()), (int) cabeza.getY() - 1);
        }
    }

    @Override
    public SearchBasedAgentState clone() {
        EstadoSnake clon = new EstadoSnake();

        try {
            clon.setLongitud(this.longitud);
        } catch (Exception e) {
        }

        clon.setOrientacionDeLaCabeza(this.orientacionDeLaCabeza);

        Vector<Point> composicionDelAgenteClon = new Vector<Point>();
        for (int par = 0; par < this.composicionDelAgente.size(); par++) {
            composicionDelAgenteClon.add(new Point(
                    this.composicionDelAgente.get(par)));
        }

        clon.setComposicionDelAgente(composicionDelAgenteClon);

        Vector<Point> celdasVisitadasClon = new Vector<Point>();
        for (int par = 0; par < this.celdasVisitadas.size(); par++) {
            celdasVisitadasClon.add(new Point(
                    this.celdasVisitadas.get(par)));
        }

        clon.setCeldasVisitadas(celdasVisitadasClon);

        Vector<Vector<Integer>> mundoConocidoClon = new Vector<Vector<Integer>>();
        for (int fil = 0; fil < this.mundoConocido.size(); fil++) {
            mundoConocidoClon.add(new Vector<Integer>());
            for (int col = 0; col < this.mundoConocido.get(fil).size(); col++) {
                mundoConocidoClon.get(fil).add(new Integer(
                        this.mundoConocido.get(fil).get(col)));
            }
        }

        clon.setMundoConocido(mundoConocidoClon);

        clon.setEstaVivo(this.estaVivo);

        clon.setEncontreElLimiteAlEste(encontreElLimiteAlEste);
        clon.setEncontreElLimiteAlNorte(encontreElLimiteAlNorte);
        clon.setEncontreElLimiteAlOeste(encontreElLimiteAlOeste);
        clon.setEncontreElLimiteAlSur(encontreElLimiteAlSur);

        clon.setVecesQueSeGiro(this.vecesQueSeGiro);
        clon.setVecesQueSeGiroDerecha(this.vecesQueSeGiroDerecha);

        return clon;
    }

    @Override
    public boolean equals(Object obj) {
        EstadoSnake snake = (EstadoSnake) obj;

        // Comparamos si esta vivo
        if (this.estaVivo != snake.getVivo()) {
            return false;        // Comparamos los limites encontrados
        }
        if (this.encontreElLimiteAlEste != snake.isEncontreElLimiteAlEste()) {
            return false;
        }
        if (this.encontreElLimiteAlOeste != snake.isEncontreElLimiteAlOeste()) {
            return false;
        }
        if (this.encontreElLimiteAlNorte != snake.isEncontreElLimiteAlNorte()) {
            return false;
        }
        if (this.encontreElLimiteAlSur != snake.isEncontreElLimiteAlSur()) {
            return false;        // Comparamos la orientacion
        }
        if (this.orientacionDeLaCabeza != snake.getOrientacionDeLaCabeza()) {
            return false;        // Comparamos la longitud
        }
        if (this.longitud != snake.getLongitud()) {
            return false;        // Comparamos la composición del agente
        }
        for (int i = 0; i < this.composicionDelAgente.size(); i++) {
            if (this.composicionDelAgente.get(i) != snake.composicionDelAgente.get(i)) {
                return false;            // Comparamos las celdas visitadas
            }
        }
        if (this.celdasVisitadas.size() != snake.getCeldasVisitadas().size()) {
            return false;
        }
        for (int i = 0; i < this.celdasVisitadas.size(); i++) {
            if (this.celdasVisitadas.get(i) != snake.getCeldasVisitadas().get(i)) {
                return false;            // Comparamos las mundos conocidos
            }
        }
        if (this.mundoConocido.size() != snake.getMundoConocido().size()) {
            return false;
        }
        for (int fil = 0; fil < this.mundoConocido.size(); fil++) {
            if (this.mundoConocido.get(fil).size() != snake.getMundoConocido().get(fil).size()) {
                return false;
            }
            for (int col = 0; col < this.mundoConocido.size(); col++) {
                if (this.mundoConocido.get(fil).get(col) != snake.getMundoConocido().get(fil).get(col)) {
                    return false;
                }
            }
        }

        return true;
    }

    @Override
    public void initState() {
        this.mundoConocido = new Vector<Vector<Integer>>();
        // Inicialmente asumimos que el mundo es de 3x3 y desconocido menos la posición central, en donde está el agente.

        for (int fil = 0; fil < 3; fil++) {
            this.mundoConocido.add(new Vector<Integer>());
            for (int col = 0; col < 3; col++) {
                this.mundoConocido.get(fil).add(new Integer(PercepcionSnake.DESCONOCIDO));
            }
        }

        this.mundoConocido.get(1).setElementAt(new Integer(PercepcionSnake.PARTE_DEL_AGENTE), 1);

        // Por defecto el agente arranca mirando al Este.
        this.orientacionDeLaCabeza = EstadoSnake.ESTE;

        this.composicionDelAgente = new Vector<Point>();
        this.composicionDelAgente.add(new Point(1, 1));

        this.celdasVisitadas = new Vector<Point>();

        this.longitud = 1;

        this.estaVivo = true;

        this.encontreElLimiteAlNorte = false;
        this.encontreElLimiteAlSur = false;
        this.encontreElLimiteAlEste = false;
        this.encontreElLimiteAlOeste = false;

        this.vecesQueSeGiro = 0;
        this.vecesQueSeGiroDerecha = 0;
    }

    @Override
    public String toString() {
        String str = "";

        str = str + " composicion=\"{";
        for (int i = 0; i < this.composicionDelAgente.size(); i++) {
            str = str + "(" + (int) this.composicionDelAgente.get(i).getX() + "," + (int) this.composicionDelAgente.get(i).getY() + ")";
        }
        str = str + "}\"\n";
        str = str + " longitud=\"" + this.longitud + "\"\n";
        str = str + " orientacion_cabeza=\"" + this.orientacionComoString() + "\"\n";
        str = str + " esta_vivo=\"" + this.estaVivo + "\"\n";

        str = str + "mundo_conocido=\"[ \n";
        for (int fil = 0; fil < this.mundoConocido.size(); fil++) {
            str = str + "[ ";
            for (int col = 0; col < this.mundoConocido.get(fil).size(); col++) {
                if (this.mundoConocido.get(fil).get(col) == -1) {
                    str = str + "* ";
                } else {
                    str = str + this.mundoConocido.get(fil).get(col) + " ";
                }
            }
            str = str + " ]\n";
        }
        str = str + " ]\"";

        return str;
    }

    private String orientacionComoString() {
        String str = "";

        switch (this.orientacionDeLaCabeza) {
            case 0:
                str = "Norte";
                break;
            case 1:
                str = "Sur";
                break;
            case 2:
                str = "Este";
                break;
            case 3:
                str = "Oeste";
                break;
            default:
                break;
        }

        return str;
    }

    public boolean getVivo() {
        return this.estaVivo;
    }

    public int getLongitud() {
        return this.longitud;
    }

    public void setLongitud(int longitud) throws Exception {
        if (longitud != this.composicionDelAgente.size()) {
            throw new Exception("La longitud no concuerda con el tama;o de la vibora");
        }
        this.longitud = longitud;
    }

    public boolean noHayMasComida() {
        for (int fil = 0; fil < this.mundoConocido.size(); fil++) {
            for (int col = 0; col < this.mundoConocido.get(fil).size(); col++) {
                if (this.mundoConocido.get(fil).get(col) == EstadoSnake.COMIDA) {
                    return false;
                }
            }
        }
        return true;
    }

    public boolean todoConocido() {
        for (int fil = 0; fil < this.mundoConocido.size(); fil++) {
            for (int col = 0; col < this.mundoConocido.get(fil).size(); col++) {
                if (this.mundoConocido.get(fil).get(col) == EstadoSnake.DESCONOCIDO) {
                    return false;
                }
            }
        }
        return true;
    }

    public int getOrientacionDeLaCabeza() {
        return orientacionDeLaCabeza;
    }

    public void setOrientacionDeLaCabeza(int orientacionDeLaCabeza) {
        this.orientacionDeLaCabeza = orientacionDeLaCabeza;
    }

    public Point getPosicionCabeza() {
        return this.composicionDelAgente.firstElement();
    }

    private void setComposicionDelAgente(Vector<Point> composicionDelAgente) {
        this.composicionDelAgente = composicionDelAgente;
    }

    public void setMundoConocido(Vector<Vector<Integer>> mundoConocido) {
        this.mundoConocido = mundoConocido;
    }

    public Vector<Vector<Integer>> getMundoConocido() {
        return this.mundoConocido;
    }

    public void moverAlNorte() {
        this.composicionDelAgente.insertElementAt(new Point(
                (int) this.composicionDelAgente.firstElement().getX() - 1,
                (int) this.composicionDelAgente.firstElement().getY()), 0);

        Point puntoABorrar = this.composicionDelAgente.remove(this.composicionDelAgente.size() - 1);

        this.mundoConocido.get((int) this.composicionDelAgente.firstElement().getX()).setElementAt(
                EstadoSnake.PARTE_DEL_AGENTE, (int) this.composicionDelAgente.firstElement().getY());
        this.mundoConocido.get((int) puntoABorrar.getX()).setElementAt(
                EstadoSnake.NO_HAY_NADA, (int) puntoABorrar.getY());

        this.celdasVisitadas.add(new Point((int) puntoABorrar.getX(), (int) puntoABorrar.getY()));
    }

    public void moverAlSur() {
        this.composicionDelAgente.insertElementAt(new Point(
                (int) this.composicionDelAgente.firstElement().getX() + 1,
                (int) this.composicionDelAgente.firstElement().getY()), 0);

        Point puntoABorrar = this.composicionDelAgente.remove(this.composicionDelAgente.size() - 1);

        this.mundoConocido.get((int) this.composicionDelAgente.firstElement().getX()).setElementAt(
                EstadoSnake.PARTE_DEL_AGENTE, (int) this.composicionDelAgente.firstElement().getY());
        this.mundoConocido.get((int) puntoABorrar.getX()).setElementAt(
                EstadoSnake.NO_HAY_NADA, (int) puntoABorrar.getY());

        this.celdasVisitadas.add(new Point((int) puntoABorrar.getX(), (int) puntoABorrar.getY()));
    }

    public void moverAlEste() {
        this.composicionDelAgente.insertElementAt(new Point(
                (int) this.composicionDelAgente.firstElement().getX(),
                (int) this.composicionDelAgente.firstElement().getY() + 1), 0);

        Point puntoABorrar = this.composicionDelAgente.remove(this.composicionDelAgente.size() - 1);
        this.mundoConocido.get((int) this.composicionDelAgente.firstElement().getX()).setElementAt(
                EstadoSnake.PARTE_DEL_AGENTE, (int) this.composicionDelAgente.firstElement().getY());
        this.mundoConocido.get((int) puntoABorrar.getX()).setElementAt(
                EstadoSnake.NO_HAY_NADA, (int) puntoABorrar.getY());

        this.celdasVisitadas.add(new Point((int) puntoABorrar.getX(), (int) puntoABorrar.getY()));
    }

    public void moverAlOeste() {
        this.composicionDelAgente.insertElementAt(new Point(
                (int) this.composicionDelAgente.firstElement().getX(),
                (int) this.composicionDelAgente.firstElement().getY() - 1), 0);

        Point puntoABorrar = this.composicionDelAgente.remove(this.composicionDelAgente.size() - 1);

        this.mundoConocido.get((int) this.composicionDelAgente.firstElement().getX()).setElementAt(
                EstadoSnake.PARTE_DEL_AGENTE, (int) this.composicionDelAgente.firstElement().getY());
        this.mundoConocido.get((int) puntoABorrar.getX()).setElementAt(
                EstadoSnake.NO_HAY_NADA, (int) puntoABorrar.getY());

        this.celdasVisitadas.add(new Point((int) puntoABorrar.getX(), (int) puntoABorrar.getY()));
    }

    public void inventaLoQueTiene() {
        // Ahora pongo todo lo desconocido como si fuera que no hay nada ahi.
        for (int fil = 0; fil < this.mundoConocido.size(); fil++) {
            for (int col = 0; col < this.mundoConocido.get(fil).size(); col++) {
                if (this.mundoConocido.get(fil).get(col) == EstadoSnake.DESCONOCIDO) {
                    this.mundoConocido.get(fil).setElementAt(EstadoSnake.NO_HAY_NADA, col);
                }
            }
        }
    }

    public void comerAlNorte() {
        this.composicionDelAgente.insertElementAt(new Point(
                (int) this.composicionDelAgente.firstElement().getX() - 1,
                (int) this.composicionDelAgente.firstElement().getY()), 0);

        this.mundoConocido.get((int) this.composicionDelAgente.firstElement().getX()).setElementAt(
                EstadoSnake.PARTE_DEL_AGENTE, (int) this.composicionDelAgente.firstElement().getY());

        this.longitud = this.composicionDelAgente.size();
    }

    public void comerAlSur() {
        this.composicionDelAgente.insertElementAt(new Point(
                (int) this.composicionDelAgente.firstElement().getX() + 1,
                (int) this.composicionDelAgente.firstElement().getY()), 0);

        this.mundoConocido.get((int) this.composicionDelAgente.firstElement().getX()).setElementAt(
                EstadoSnake.PARTE_DEL_AGENTE, (int) this.composicionDelAgente.firstElement().getY());

        this.longitud = this.composicionDelAgente.size();
    }

    public void comerAlEste() {
        this.composicionDelAgente.insertElementAt(new Point(
                (int) this.composicionDelAgente.firstElement().getX(),
                (int) this.composicionDelAgente.firstElement().getY() + 1), 0);

        this.mundoConocido.get((int) this.composicionDelAgente.firstElement().getX()).setElementAt(
                EstadoSnake.PARTE_DEL_AGENTE, (int) this.composicionDelAgente.firstElement().getY());

        this.longitud = this.composicionDelAgente.size();
    }

    public void comerAlOeste() {
        this.composicionDelAgente.insertElementAt(new Point(
                (int) this.composicionDelAgente.firstElement().getX(),
                (int) this.composicionDelAgente.firstElement().getY() - 1), 0);

        this.mundoConocido.get((int) this.composicionDelAgente.firstElement().getX()).setElementAt(
                EstadoSnake.PARTE_DEL_AGENTE, (int) this.composicionDelAgente.firstElement().getY());

        this.longitud = this.composicionDelAgente.size();
    }

    public boolean hayObstaculo(int donde) {
        Point posicionCabeza = this.getPosicionCabeza();
        boolean hayObstaculo = false;

        switch (donde) {
            case 0:	// Norte
                if (posicionCabeza.getX() == 0) {
                    hayObstaculo = true;
                } else if (this.mundoConocido.get((int) posicionCabeza.getX() - 1).
                        get((int) posicionCabeza.getY()) == EstadoSnake.PARTE_DEL_AGENTE) {
                    hayObstaculo = true;
                }
                break;
            case 1:	// Sur
                if (posicionCabeza.getX() == this.mundoConocido.size() - 1) {
                    hayObstaculo = true;
                } else if (this.mundoConocido.get((int) posicionCabeza.getX() + 1).
                        get((int) posicionCabeza.getY()) == EstadoSnake.PARTE_DEL_AGENTE) {
                    hayObstaculo = true;
                }
                break;
            case 2:	// Este
                if (posicionCabeza.getY() == this.mundoConocido.size() - 1) {
                    hayObstaculo = true;
                } else if (this.mundoConocido.get((int) posicionCabeza.getX()).
                        get((int) posicionCabeza.getY() + 1) == EstadoSnake.PARTE_DEL_AGENTE) {
                    hayObstaculo = true;
                }
                break;
            case 3:	// Oeste

                if (posicionCabeza.getY() == 0) {
                    hayObstaculo = true;
                } else if (this.mundoConocido.get((int) posicionCabeza.getX()).
                        get((int) posicionCabeza.getY() - 1) == EstadoSnake.PARTE_DEL_AGENTE) {
                    hayObstaculo = true;
                }
                break;
            default:
                break;
        }

        return hayObstaculo;
    }

    public boolean hayComida(int donde) {
        Point posicionCabeza = this.getPosicionCabeza();
        boolean hayComida = false;

        switch (donde) {
            case 0:	// Norte
                if (posicionCabeza.getX() == 0) {
                    hayComida = false;
                } else if (this.mundoConocido.get((int) posicionCabeza.getX() - 1).
                        get((int) posicionCabeza.getY()) == EstadoSnake.COMIDA) {
                    hayComida = true;
                }
                break;
            case 1:	// Sur
                if (posicionCabeza.getX() == this.mundoConocido.size() - 1) {
                    hayComida = false;
                } else if (this.mundoConocido.get((int) posicionCabeza.getX() + 1).
                        get((int) posicionCabeza.getY()) == EstadoSnake.COMIDA) {
                    hayComida = true;
                }
                break;
            case 2:	// Este
                if (posicionCabeza.getY() == this.mundoConocido.size() - 1) {
                    hayComida = false;
                } else if (this.mundoConocido.get((int) posicionCabeza.getX()).
                        get((int) posicionCabeza.getY() + 1) == EstadoSnake.COMIDA) {
                    hayComida = true;
                }
                break;
            case 3:	// Oeste
                if (posicionCabeza.getY() == 0) {
                    hayComida = false;
                } else if (this.mundoConocido.get((int) posicionCabeza.getX()).
                        get((int) posicionCabeza.getY() - 1) == EstadoSnake.COMIDA) {
                    hayComida = true;
                }
                break;
            default:
                break;
        }

        return hayComida;
    }

    public boolean isEstaVivo() {
        return estaVivo;
    }

    public void setEstaVivo(boolean estaVivo) {
        this.estaVivo = estaVivo;
    }

    public double getCosto() {
        return this.costo;
    }

    public void incrementarCosto(double costo) {
        this.costo += costo;
    }

    public Vector<Point> getCeldasVisitadas() {
        return celdasVisitadas;
    }

    public boolean pasoNVecesPorEstaCelda(Point celda) {
        int cantidadDeVeces = 0;

        for (int i = 0; i < this.celdasVisitadas.size(); i++) {
            if (this.celdasVisitadas.get(i).getX() == celda.getX() &&
                    this.celdasVisitadas.get(i).getY() == celda.getY()) {
                cantidadDeVeces++;
            }
        }

        if (cantidadDeVeces > 1) {
            return true;
        }
        return false;
    }

    public void agregarCeldaALasVisitadas(Point celda) {
        if (!this.pasoNVecesPorEstaCelda(celda)) {
            this.celdasVisitadas.add(celda);
        }
    }

    public void setCeldasVisitadas(Vector<Point> celdasVisitadas) {
        this.celdasVisitadas = celdasVisitadas;
    }

    public boolean isEncontreElLimiteAlEste() {
        return encontreElLimiteAlEste;
    }

    public void setEncontreElLimiteAlEste(boolean encontreElLimiteAlEste) {
        this.encontreElLimiteAlEste = encontreElLimiteAlEste;
    }

    public boolean isEncontreElLimiteAlNorte() {
        return encontreElLimiteAlNorte;
    }

    public void setEncontreElLimiteAlNorte(boolean encontreElLimiteAlNorte) {
        this.encontreElLimiteAlNorte = encontreElLimiteAlNorte;
    }

    public boolean isEncontreElLimiteAlOeste() {
        return encontreElLimiteAlOeste;
    }

    public void setEncontreElLimiteAlOeste(boolean encontreElLimiteAlOeste) {
        this.encontreElLimiteAlOeste = encontreElLimiteAlOeste;
    }

    public boolean isEncontreElLimiteAlSur() {
        return encontreElLimiteAlSur;
    }

    public void setEncontreElLimiteAlSur(boolean encontreElLimiteAlSur) {
        this.encontreElLimiteAlSur = encontreElLimiteAlSur;
    }

    public void destrabaAlAgente() {
        this.celdasVisitadas.clear();
    }

    public int getVecesQueSeGiro() {
        return this.vecesQueSeGiro;
    }

    public void setVecesQueSeGiro(int vecesQueSeGiro) {
        this.vecesQueSeGiro = vecesQueSeGiro;
    }

    public void incVecesQueSeGiro() {
        this.vecesQueSeGiro++;
    }

    public int getVecesQueSeGiroDerecha() {
        return this.vecesQueSeGiroDerecha;
    }

    public void setVecesQueSeGiroDerecha(int vecesQueSeGiroDerecha) {
        this.vecesQueSeGiroDerecha = vecesQueSeGiroDerecha;
    }

    public void incVecesQueSeGiroDerecha() {
        this.vecesQueSeGiroDerecha++;
    }
}
