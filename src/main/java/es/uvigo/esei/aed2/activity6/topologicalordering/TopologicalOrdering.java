package es.uvigo.esei.aed2.activity6.topologicalordering;

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

import es.uvigo.esei.aed1.tads.queue.LinkedQueue;
import es.uvigo.esei.aed1.tads.queue.Queue;
import es.uvigo.esei.aed2.activity6.HashMap.HashMap;
import es.uvigo.esei.aed2.graph.Graph;
import es.uvigo.esei.aed2.graph.Vertex;
import es.uvigo.esei.aed2.map.Map;

import java.util.*;

public class TopologicalOrdering {
    /**
     * Calcula el orden topológico de un grafo dirigido acíclico (DAG).
     *
     * @param graph grafo dirigido acíclico.
     * @return lista de vértices en orden topológico.
     */
    public static <T, E> List<Vertex<T>> getTopologicalOrder(Graph<T, E> graph) {
        // Resultado: lista que contendrá el orden topológico
        List<Vertex<T>> order = new ArrayList<>();
        // Si el grafo es nulo devolvemos lista vacía
        if (graph == null) return order;

        // Mapa de grados de entrada (in degree) para cada vértice
        Map<Vertex<T>, Integer> indeg = new HashMap<>();
        // Inicializamos todos los indeg a 0
        for (Vertex<T> vertex : graph.getVertices()) {
            indeg.add(vertex, 0);
        }

        // Calculamos el grado de entrada real sumando 1 por cada arista entrante
        for (Vertex<T> vertex : graph.getVertices()) {
            for (Vertex<T> successor : graph.getAdjacentsVertex(vertex)) {
                //Actualizamos el indeg del sucesor
                indeg.add(successor, indeg.get(successor) + 1);
            }
        }

        // Cola para procesar vértices con indeg == 0 (algoritmo de Kahn)
        Queue<Vertex<T>> queue = new LinkedQueue<>();
        // Encolamos inicialmente todos los vértices sin predecesores
        for (Vertex<T> vertex : graph.getVertices()) {
            if (indeg.get(vertex) == 0) queue.add(vertex);
        }

        // Procesamos la cola extrayendo los vértices sin predecesores,
        // los añadimos al orden y reducimos el indeg de sus sucesores.
        while (!queue.isEmpty()) {
            Vertex<T> tVertex = queue.remove();
            order.add(tVertex);
            for (Vertex<T> adjacentVertex : graph.getAdjacentsVertex(tVertex)) {
                int d = indeg.get(adjacentVertex) - 1;      // decremento del indeg
                indeg.add(adjacentVertex, d);               // actualizamos el mapa
                if (d == 0) queue.add(adjacentVertex);      // si llega a cero, metemos en la cola
            }
        }

        // Devolvemos la lista con el orden topológico
        return order;
    }
}
