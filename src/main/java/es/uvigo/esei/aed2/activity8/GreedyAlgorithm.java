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

import java.util.List;
import java.util.Set;

import es.uvigo.esei.aed2.graph.Graph;
import es.uvigo.esei.aed2.graph.Vertex;
import es.uvigo.esei.aed2.map.HashMap;
import es.uvigo.esei.aed2.map.Map;

public class GreedyAlgorithm {

    // Exercise 1
    public static <T> Graph<T, Integer> traveller(Graph<T, Integer> graph, Vertex<T> vertex) {

        return null;
    }

    // Exercise 2
    public static <T> Graph<T, Integer> prim(Graph<T, Integer> graph, Vertex<T> vertex) {

        return null;
    }

    // Exercise 3
    public static <T> Map<Vertex<T>, Integer> dijkstra(Graph<T, Integer> graph, Vertex<T> vertex) {

        return null;
    }

    // Exercise 4
    public static <T> Map<Vertex<T>, String> colorMap(Graph<T, Integer> graph, String[] colors) {
        Map<Vertex<T>, String> vertexColoursMap = new HashMap<>();

        // Si el test espera un orden específico, itera directamente
        for (Vertex<T> vertex : graph.getVertices()) {
            String color = defineColor(vertex, colors, graph, vertexColoursMap);
            if(color != null) {
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

    // Exercise 6
    public static Set<String> burnCD(int maximumCapacity, Map<String, Integer> espacePrograms) {

        return null;
    }

    // Exercise 7
    public static Map<String, Integer> fillRucksack(int maxVolume, Map<String, Integer> amounts, Map<String, Integer> volumes) {

        return null;
    }

    // Exercise 8
    public static Map<Vertex<String>, String> examSchedule(Graph<String, Integer> graph, String[] daysWeek) {

        return null;
    }

    // Exercise 9
    public static Set<String> plannerActivities(List<Activity8> listActivities) {

        return null;
    }

}
