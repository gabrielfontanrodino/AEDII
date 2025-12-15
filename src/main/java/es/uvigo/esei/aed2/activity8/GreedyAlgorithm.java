package es.uvigo.esei.aed2.activity8;
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

import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import es.uvigo.esei.aed2.activity6.mapofmap.MapOfMap;
import es.uvigo.esei.aed2.graph.Edge;
import es.uvigo.esei.aed2.graph.Graph;
import es.uvigo.esei.aed2.graph.Vertex;
import es.uvigo.esei.aed2.map.HashMap;
import es.uvigo.esei.aed2.map.Map;

public class GreedyAlgorithm {

    /**
     * Obtiene el valor de la arista entre dos vértices en un grafo.
     *
     * @param graph  El grafo donde buscar la arista.
     * @param source Vértice de origen.
     * @param target Vértice de destino.
     * @return El valor de la arista si existe, o Integer.MAX_VALUE si
     */
    private static <T> Integer getEdgeValue(Graph<T, Integer> graph, Vertex<T> source, Vertex<T> target) {
        for (Edge<T, Integer> edge : graph.getEdges()) {
            if (edge.getSource().equals(source) && edge.getTarget().equals(target)) {
                return edge.getLabel();
            }
        }
        return Integer.MAX_VALUE;
    }

    // Exercise 1 - Problema del viajante (aproximación voraz)
    public static <T> Graph<T, Integer> traveller(Graph<T, Integer> graph, Vertex<T> vertex) {
        Graph<T, Integer> result = new MapOfMap<>();
        Set<Vertex<T>> visited = new HashSet<>();
        Vertex<T> current = vertex;

        // Añadir el vértice inicial al conjunto de visitados
        visited.add(current);

        // Mientras haya vértices no visitados
        while (visited.size() < graph.getVertices().size()) {
            Vertex<T> nearest = null;
            Integer minDistance = Integer.MAX_VALUE;

            // Buscar el vértice no visitado más cercano
            for (Vertex<T> adjacent : graph.getAdjacentsVertex(current)) {
                if (!visited.contains(adjacent)) {
                    // Obtener la distancia entre el vértice actual y el adyacente (el label de la arista)
                    Integer distance = getEdgeValue(graph, current, adjacent);
                    if (distance < minDistance) {
                        minDistance = distance;
                        nearest = adjacent;
                    }
                }
            }

            // Si se encontró un vértice cercano, añadir la arista al resultado y marcarlo como visitado
            if (nearest != null) {
                result.addVertex(current);
                result.addVertex(nearest);

                result.addEdge(current, nearest, minDistance);
                visited.add(nearest);
                current = nearest;
            } else {
                break;
            }
        }

        return result;
    }

    // Exercise 2 - Algoritmo de Prim (árbol de expansión mínima)
    public static <T> Graph<T, Integer> prim(Graph<T, Integer> graph, Vertex<T> vertex) {
        Graph<T, Integer> result = new MapOfMap<>();
        Set<Vertex<T>> visitedSet = new HashSet<>();

        visitedSet.add(vertex);

        while (visitedSet.size() < graph.getVertices().size()) {
            Vertex<T> from = null;
            Vertex<T> to = null;
            Integer minWeight = Integer.MAX_VALUE;

            // Buscar la arista de menor peso que conecte un vértice visitado con uno no visitado
            for (Vertex<T> visitedVertex : visitedSet) {
                for (Vertex<T> adjacent : graph.getAdjacentsVertex(visitedVertex)) {
                    if (!visitedSet.contains(adjacent)) {
                        Integer weight = getEdgeValue(graph, visitedVertex, adjacent);
                        if (weight < minWeight) {
                            minWeight = weight;
                            from = visitedVertex;
                            to = adjacent;
                        }
                    }
                }
            }

            if (to != null) {
                result.addVertex(from);
                result.addVertex(to);

                result.addEdge(from, to, minWeight);
                visitedSet.add(to);
            } else {
                break;
            }
        }

        return result;
    }

    // Exercise 3 - Algoritmo de Dijkstra (caminos más cortos)
    public static <T> Map<Vertex<T>, Integer> dijkstra(Graph<T, Integer> graph, Vertex<T> vertex) {
        Map<Vertex<T>, Integer> distances = new HashMap<>();
        Set<Vertex<T>> visited = new HashSet<>();

        // Inicializar distancias: 0 para el origen, infinito para el resto
        for (Vertex<T> v : graph.getVertices()) {
            distances.add(v, v.equals(vertex) ? 0 : Integer.MAX_VALUE);
        }

        while (visited.size() < graph.getVertices().size()) {
            // Seleccionar el vértice no visitado con menor distancia
            Vertex<T> current = null;
            Integer minDistance = Integer.MAX_VALUE;

            for (Vertex<T> v : graph.getVertices()) {
                if (!visited.contains(v) && distances.get(v) < minDistance) {
                    minDistance = distances.get(v);
                    current = v;
                }
            }

            if (current == null) break;

            visited.add(current);

            // Actualizar distancias de los vecinos
            for (Vertex<T> adjacent : graph.getAdjacentsVertex(current)) {
                if (!visited.contains(adjacent)) {
                    Integer newDistance = distances.get(current) + getEdgeValue(graph, current, adjacent);
                    if (newDistance < distances.get(adjacent)) {
                        distances.add(adjacent, newDistance);
                    }
                }
            }
        }

        return distances;
    }

    // Exercise 4
    public static <T> Map<Vertex<T>, String> colorMap(Graph<T, Integer> graph, String[] colors) {
        Map<Vertex<T>, String> vertexColoursMap = new HashMap<>();

        // Si el test espera un orden específico, itera directamente
        for (Vertex<T> vertex : graph.getVertices()) {
            String color = defineColor(vertex, colors, graph, vertexColoursMap);
            if (color != null) {
                vertexColoursMap.add(vertex, color);
            }
        }

        return vertexColoursMap;
    }


    private static <T> String defineColor(Vertex<T> vertex, String[] colors, Graph<T, Integer> graph, Map<Vertex<T>, String> vertexColours) {
        if (vertex == null || graph == null || colors == null) {
            return null;
        }

        for (String currentColor : colors) {
            boolean available = true;

            for (Vertex<T> adjVertex : graph.getAdjacentsVertex(vertex)) {
                String adjColour = vertexColours.get(adjVertex);

                if (adjColour != null && adjColour.equals(currentColor)) {
                    available = false;
                    break;
                }
            }

            if (available) {
                return currentColor;
            }
        }

        return colors[colors.length - 1];
    }

    // Exercise 5
    public static Map<Integer, Integer> giveChange(int amountToReturn, Map<Integer, Integer> changeAvailable) {
        Map<Integer, Integer> change = new HashMap<>();
        int returnedValue = 0;

        while (returnedValue < amountToReturn) {
            int remainingAmount = amountToReturn - returnedValue;

            Integer chosenBanknote = selectBanknote(remainingAmount, changeAvailable);

            if (chosenBanknote != null) {
                int neededBanknotes = remainingAmount / chosenBanknote;

                int availableBanknotes = changeAvailable.get(chosenBanknote);

                if (neededBanknotes > availableBanknotes) {
                    neededBanknotes = availableBanknotes;
                }

                change.add(chosenBanknote, neededBanknotes);

                returnedValue += chosenBanknote * neededBanknotes;

                changeAvailable.add(chosenBanknote, availableBanknotes - neededBanknotes);
            } else {
                // Si no hay cambio exacto posible
                return new HashMap<>();
            }
        }

        return change;
    }

    private static Integer selectBanknote(int amountToReturn, Map<Integer, Integer> changeAvailable) {
        Integer highestBanknote = null;

        Set<Integer> banknotes = changeAvailable.getKeys();

        for (Integer banknote : banknotes) {
            boolean isValid = (banknote <= amountToReturn) && (changeAvailable.get(banknote) > 0);

            if (isValid) {
                if (highestBanknote == null || banknote > highestBanknote) {
                    highestBanknote = banknote;
                }
            }
        }

        return highestBanknote;
    }

    // Exercise 6 - Grabar programas en CD
    public static Set<String> burnCD(int maximumCapacity, Map<String, Integer> espacePrograms) {
        Set<String> selectedPrograms = new HashSet<>();
        int usedCapacity = 0;

        // Repetir mientras haya espacio y programas disponibles
        while (usedCapacity < maximumCapacity) {
            String selectedProgram = null;
            int maxSize = 0;

            // Buscar el programa más grande que quepa
            for (String program : espacePrograms.getKeys()) {
                Integer size = espacePrograms.get(program);

                if (!selectedPrograms.contains(program) && usedCapacity + size <= maximumCapacity && size > maxSize) {
                    maxSize = size;
                    selectedProgram = program;
                }
            }

            if (selectedProgram != null) {
                selectedPrograms.add(selectedProgram);
                usedCapacity += maxSize;
            } else {
                break;
            }
        }

        return selectedPrograms;
    }

    // Exercise 7 - Llenar mochila
    public static Map<String, Integer> fillRucksack(int maxVolume, Map<String, Integer> amounts, Map<String, Integer> volumes) {
        Map<String, Integer> result = new HashMap<>();
        int usedVolume = 0;

        while (usedVolume < maxVolume) {
            String bestItem = null;
            double bestRatio = 0;

            // Buscar el objeto con mejor relación cantidad/volumen que quepa
            for (String item : amounts.getKeys()) {
                Integer amount = amounts.get(item);
                Integer volume = volumes.get(item);
                // Obtener la cantidad actual en la mochila
                Integer currentItemCount = result.get(item);

                if (currentItemCount == null) currentItemCount = 0;

                // Si aún quedan unidades de este objeto y cabe en la mochila
                if (currentItemCount < amount && usedVolume + volume <= maxVolume) {
                    double ratio = (double) amount / volume;
                    if (ratio > bestRatio) {
                        bestRatio = ratio;
                        bestItem = item;
                    }
                }
            }

            // Añadir el mejor objeto encontrado a la mochila
            if (bestItem != null) {
                Integer currentItemCount = result.get(bestItem);
                if (currentItemCount == null) currentItemCount = 0;
                result.add(bestItem, currentItemCount + 1);
                usedVolume += volumes.get(bestItem);
            } else {
                break;
            }
        }

        return result;
    }

    // Exercise 8 - Programar exámenes
    public static Map<Vertex<String>, String> examSchedule(Graph<String, Integer> graph, String[] daysWeek) {
        Map<Vertex<String>, String> schedule = new HashMap<>();

        // Para cada vértice (asignatura), asignar el primer día disponible
        for (Vertex<String> subject : graph.getVertices()) {
            String assignedDay = null;

            // Buscar el primer día que no tenga conflictos
            for (String day : daysWeek) {
                boolean available = true;

                // Verificar que ningún vecino tenga este día asignado
                for (Vertex<String> adjacent : graph.getAdjacentsVertex(subject)) {
                    String adjacentDay = schedule.get(adjacent);

                    if (adjacentDay != null && adjacentDay.equals(day)) {
                        available = false;
                        break;
                    }
                }

                if (available) {
                    assignedDay = day;
                    break;
                }
            }

            if (assignedDay != null) {
                schedule.add(subject, assignedDay);
            }
        }

        return schedule;
    }

    // Exercise 9 - Planificador de actividades
    public static Set<String> plannerActivities(List<Activity8> listActivities) {
        Set<String> selectedActivities = new HashSet<>();

        // Ordenar actividades por hora de finalización
        List<Activity8> sortedActivities = new java.util.ArrayList<>(listActivities);
        sortedActivities.sort(Comparator.comparingInt(Activity8::getEnd));

        int lastEndTime = -1;

        // Seleccionar actividades que no se solapen
        for (Activity8 activity : sortedActivities) {
            if (activity.getStart() >= lastEndTime) {
                selectedActivities.add(activity.getNomActivity());
                lastEndTime = activity.getEnd();
            }
        }

        return selectedActivities;
    }

}
