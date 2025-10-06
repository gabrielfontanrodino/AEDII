package es.uvigo.esei.aed2.activity7;

/*-
 * #%L
 * AEDII - Activities
 * %%
 * Copyright (C) 2025 Rosalía Laza Fidalgo, María Reyes Pavón Rial,
 * Florentino Fernández Riverola, María Novo Lourés, and Miguel Reboiro Jato
 * %%
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 * #L%
 */

import es.uvigo.esei.aed2.graph.Graph;
import es.uvigo.esei.aed2.graph.Vertex;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Activity7 {

    //exercise 1
    public static <T, E> Set<Vertex<T>> getPredecessors(Graph<T, E> graph, Vertex<T> vertex) {
        // TODO: Implementa la obtención de predecesores de un vértice en el grafo
        return new HashSet<>();
    }

    //exercise 2
    public static <T, E> boolean isDrain(Graph<T, E> graph, Vertex<T> vertex) {
        // TODO: Implementa la comprobación de si un vértice es sumidero
        return false;
    }

    //exercise 3
    public static <T, E> boolean isRegular(Graph<T, E> graph) {
        // TODO: Implementa la comprobación de si el grafo es regular
        return false;
    }

    //exercise 4
    public static <T, E> boolean isConnectedFromVertex(Graph<T, E> graph, Vertex<T> vertex) {
        // TODO: Implementa la comprobación de si el grafo es conexo desde un vértice
        return false;
    }

    //exercise 5
    public static <T, E> boolean thereIsPathBetweenVertices(Graph<T, E> graph, Vertex<T> source, Vertex<T> target) {
        // TODO: Implementa la comprobación de si existe un camino entre dos vértices
        return false;
    }

    //exercise 6
    public static <T, E> boolean isACycle(Graph<T, E> graph, List<Vertex<T>> path) {
        // TODO: Implementa la comprobación de si una lista de vértices forma un ciclo en el grafo
        return false;
    }

    //exercise 7
    public static <T, E> int numberOfConnectedComponents(Graph<T, E> graph) {
        // TODO: Implementa el cálculo del número de componentes conexas en el grafo
        return 0;
    }
}
