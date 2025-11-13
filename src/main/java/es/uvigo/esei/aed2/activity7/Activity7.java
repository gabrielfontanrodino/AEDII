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

import es.uvigo.esei.aed2.graph.Edge;
import es.uvigo.esei.aed2.graph.Graph;
import es.uvigo.esei.aed2.graph.Vertex;

import java.util.*;

public class Activity7 {

    //exercise 1

    /**
     * Obtiene el conjunto de predecesores de un vértice dado en un grafo.
     * Un vértice es considerado predecesor si existe una arista con origen
     * en dicho vértice y destino en el vértice dado.
     *
     * @param <T>    el tipo de los datos almacenados en los vértices del grafo
     * @param <E>    el tipo de los datos almacenados en las aristas del grafo
     * @param graph  el grafo del cual se obtendrán los predecesores
     * @param vertex el vértice para el cual se buscan los predecesores
     * @return un conjunto que contiene los vértices predecesores del vértice dado
     */
    public static <T, E> Set<Vertex<T>> getPredecessors(Graph<T, E> graph, Vertex<T> vertex) {
        if (graph == null || vertex == null) {
            return new HashSet<>();
        }

        Set<Vertex<T>> predecessors = new HashSet<>();

        Set<Edge<T, E>> edges = graph.getEdges();

        for (Edge<T, E> edge : edges) {
            if (edge.getTarget().equals(vertex)) predecessors.add(edge.getSource());
        }

        return predecessors;
    }

    //exercise 2

    /**
     * Comprueba si un vértice dado es un sumidero en el grafo.
     * Un vértice es considerado sumidero si no tiene vértices adyacentes
     * y todos los demás vértices del grafo son predecesores de este.
     *
     * @param <T>    el tipo de los datos almacenados en los vértices del grafo
     * @param <E>    el tipo de los datos almacenados en las aristas del grafo
     * @param graph  el grafo en el que se realizará la comprobación
     * @param vertex el vértice que se comprobará si es un sumidero
     * @return true si el vértice es un sumidero, false en caso contrario
     */
    public static <T, E> boolean isDrain(Graph<T, E> graph, Vertex<T> vertex) {
        if (graph == null || vertex == null) return false;
        if (!graph.getAdjacentsVertex(vertex).isEmpty()) return false;

        Set<Vertex<T>> predecessors = getPredecessors(graph, vertex);

        return predecessors.size() == (graph.numberOfVertices() - 1);
    }

    //exercise 3

    /**
     * Comprueba si un grafo es regular.
     * Un grafo es considerado regular si todos sus vértices tienen el mismo grado.
     *
     * @param <T>   el tipo de los datos almacenados en los vértices del grafo
     * @param <E>   el tipo de los datos almacenados en las aristas del grafo
     * @param graph el grafo que se comprobará si es regular
     * @return true si el grafo es regular, false en caso contrario
     */
    public static <T, E> boolean isRegular(Graph<T, E> graph) {
        if (graph == null) return false;
        if (graph.isEmpty()) return true;

        Iterator<Vertex<T>> vertexIterator = graph.getVertices().iterator();

        int amount = graph.getAdjacentsVertex(vertexIterator.next()).size();

        while (vertexIterator.hasNext()) {
            if (graph.getAdjacentsVertex(vertexIterator.next()).size() != amount) return false;
        }

        return true;
    }

    //exercise 4

    /**
     * Comprueba si un grafo es conexo desde un vértice específico.
     * Un grafo es conexo desde un vértice si es posible alcanzar todos los vértices
     * del grafo partiendo de dicho vértice.
     *
     * @param <T>    el tipo de los datos almacenados en los vértices del grafo
     * @param <E>    el tipo de los datos almacenados en las aristas del grafo
     * @param graph  el grafo que se comprobará
     * @param vertex el vértice desde el cual se verificará la conexidad
     * @return true si el grafo es conexo desde el vértice dado, false en caso contrario
     */
    public static <T, E> boolean isConnectedFromVertex(Graph<T, E> graph, Vertex<T> vertex) {
        if (graph == null || vertex == null) return false;
        if (graph.isEmpty()) return false;
        if (!graph.getVertices().contains(vertex)) return false;

        //BFD
        Set<Vertex<T>> visited = new HashSet<>();
        Queue<Vertex<T>> queue = new ArrayDeque<>();

        visited.add(vertex);
        queue.add(vertex);

        while (!queue.isEmpty()) {
            Vertex<T> current = queue.poll();
            for (Vertex<T> neighbor : graph.getAdjacentsVertex(current)) {
                if (!visited.contains(neighbor)) {
                    visited.add(neighbor);
                    queue.add(neighbor);
                }
            }
        }

        return visited.size() == graph.numberOfVertices();
    }

    //exercise 5

    /**
     * Comprueba si existe un camino entre dos vértices en un grafo.
     * Un camino existe si hay al menos una arista que conecta el vértice
     * de origen con el vértice de destino.
     *
     * @param <T>    el tipo de los datos almacenados en los vértices del grafo
     * @param <E>    el tipo de los datos almacenados en las aristas del grafo
     * @param graph  el grafo en el que se realizará la comprobación
     * @param source el vértice de origen
     * @param target el vértice de destino
     * @return true si existe un camino entre los vértices, false en caso contrario
     */
    public static <T, E> boolean thereIsPathBetweenVertices(Graph<T, E> graph, Vertex<T> source, Vertex<T> target) {
        if (graph == null || target == null) return false;

        Set<Edge<T, E>> edges = graph.getEdges();

        for (Edge<T, E> edge : edges) {
            if (edge.getSource().equals(source) && edge.getTarget().equals(target)) {
                return true;
            }
        }

        return false;
    }

    //exercise 6

    /**
     * Comprueba si una lista de vértices forma un ciclo en un grafo.
     * Un ciclo es una secuencia de vértices donde el primer y último vértice son iguales,
     * y cada par consecutivo de vértices en la lista está conectado por una arista en el grafo.
     * Además, ningún vértice intermedio se repite en el ciclo.
     *
     * @param <T>   el tipo de los datos almacenados en los vértices del grafo
     * @param <E>   el tipo de los datos almacenados en las aristas del grafo
     * @param graph el grafo en el que se comprobará el ciclo
     * @param path  la lista de vértices que representa el camino a verificar
     * @return true si la lista de vértices forma un ciclo, false en caso contrario
     */
    public static <T, E> boolean isACycle(Graph<T, E> graph, List<Vertex<T>> path) {
        if (graph == null || path == null || path.isEmpty()) {
            return false;
        }

        if (!path.getFirst().equals(path.getLast())) return false;

        Set<Vertex<T>> visited = new HashSet<>();

        for (int i = 0; i < path.size() - 1; i++) {
            Vertex<T> current = path.get(i);
            Vertex<T> next = path.get(i + 1);

            if (visited.contains(current)) return false;
            if (i < path.size() - 2) {
                visited.add(current);
            }

            if (!thereIsPathBetweenVertices(graph, current, next)) {
                return false;
            }
        }

        return true;
    }

    private static <T, E> void dfs(Graph<T, E> graph, Vertex<T> vertex, Set<Vertex<T>> visited) {
        System.out.println(vertex);
        visited.add(vertex);
        for (Vertex<T> adj : graph.getAdjacentsVertex(vertex)) {
            if (!visited.contains(adj)) dfs(graph, adj, visited);
        }
    }

    //exercise 7
    public static <T, E> int numberOfConnectedComponents(Graph<T, E> graph) {
        int connectedComponents = 0;

        Set<Vertex<T>> visitedVertices = new HashSet<>();

        for(Vertex<T> vertex: graph.getVertices()) {
            if(visitedVertices.contains(vertex)) continue;
            dfs(graph, vertex,visitedVertices);
            connectedComponents++;
        }


        return connectedComponents;
    }
}
