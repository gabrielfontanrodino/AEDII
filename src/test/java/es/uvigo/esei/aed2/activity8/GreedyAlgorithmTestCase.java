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

import static es.uvigo.esei.aed2.activity8.data.IsEqualToGraph.equalToGraph;
import static es.uvigo.esei.aed2.activity8.data.IsEqualToMap.equalToMap;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

import java.util.Set;

import org.junit.jupiter.api.Test;

import es.uvigo.esei.aed2.activity8.data.GreedyRepository;
import es.uvigo.esei.aed2.graph.AdjacencyList;
import es.uvigo.esei.aed2.graph.Graph;
import es.uvigo.esei.aed2.graph.Vertex;
import es.uvigo.esei.aed2.map.Map;

public class GreedyAlgorithmTestCase {

    private final GreedyRepository dataGreedy = new GreedyRepository(AdjacencyList::new, AdjacencyList::new);

    /**
     * Test of traveller method, of class GreedyAlgorithm.
     */
    @Test
    public void testTraveller() {
        Graph<String, Integer> expectedGraph = this.dataGreedy.getGraphTraveller();

        Graph<String, Integer> resultGraph = GreedyAlgorithm
            .traveller(this.dataGreedy.getGraphTravellerPrim(), this.dataGreedy.getVertexONE());

        assertThat(resultGraph, is(equalToGraph(expectedGraph)));
    }

    /**
     * Test of prim method, of class GreedyAlgorithm.
     */
    @Test
    public void testPrim() {
        Graph<String, Integer> expectedGraph = this.dataGreedy.getGraphPrim();

        Graph<String, Integer> resultGraph = GreedyAlgorithm
            .prim(this.dataGreedy.getGraphTravellerPrim(), this.dataGreedy.getVertexSEIX());

        assertThat(resultGraph, is(equalToGraph(expectedGraph)));
    }

    /**
     * Test of Dijkstra method, of class GreedyAlgorithm.
     */
    @Test
    public void testDijkstra() {
        Map<Vertex<String>, Integer> expectedGraph = this.dataGreedy.getMapDijkstra();

        Map<Vertex<String>, Integer> resultGraph = GreedyAlgorithm
            .dijkstra(this.dataGreedy.getGraphTravellerPrim(), this.dataGreedy.getVertexONE());

        assertThat(resultGraph, is(equalToMap(expectedGraph)));
    }

    /**
     * Test of colourMap method, of class GreedyAlgorithm.
     */
    @Test
    public void testColourMap() {
        Map<Vertex<String>, String> expectedGraph = this.dataGreedy.getMapColour();

        Map<Vertex<String>, String> resultGraph = GreedyAlgorithm
            .colorMap(this.dataGreedy.getGraphColour(), this.dataGreedy.getColours());

        assertThat(resultGraph, is(equalToMap(expectedGraph)));
    }

    /**
     * Test of giveChange method, of class GreedyAlgorithm.
     */
    @Test
    public void testGiveChange() {
        Map<Integer, Integer> expectedMap = this.dataGreedy.getMapChangeResult();

        Map<Integer, Integer> resultMap = GreedyAlgorithm.giveChange(1200, this.dataGreedy.getMapChange());

        assertThat(resultMap, is(equalToMap(expectedMap)));
    }

    /**
     * Test of burnCD method, of class GreedyAlgorithm.
     */
    @Test
    public void testBurnCD() {
        Set<String> expectedProgram = this.dataGreedy.getProgramResult();

        Set<String> resultProgram = GreedyAlgorithm.burnCD(21, this.dataGreedy.getMapCD());

        assertThat(resultProgram, is(equalTo(expectedProgram)));
    }

    /**
     * Test of fillRucksack method, of class GreedyAlgorithm.
     */
    @Test
    public void testFillRucksack() {
        Map<String, Integer> expectedMap = this.dataGreedy.getMapRucksackResult();

        Map<String, Integer> resultMap = GreedyAlgorithm
            .fillRucksack(55, this.dataGreedy.getMapRucksackAmounts(), this.dataGreedy.getMapRucksackVolumes());

        assertThat(resultMap, is(equalToMap(expectedMap)));
    }

    /**
     * Test of examSchedule method, of class GreedyAlgorithm.
     */
    @Test
    public void testExamSchedule() {
        Map<Vertex<String>, String> expectedMap = this.dataGreedy.getMapSchedule();

        Map<Vertex<String>, String> resultMap = GreedyAlgorithm
            .examSchedule(this.dataGreedy.getGraphScheduler(), this.dataGreedy.getDaysWeek());

        //Print results

        System.out.println("Expected Map:");
        for (Vertex<String> entry : expectedMap.getKeys()) {
            System.out.println("Vertex: " + entry.getValue() + ", Day: " + expectedMap.get(entry));
        }

        System.out.println("Result Map:");
        for (Vertex<String> entry : resultMap.getKeys()) {
            System.out.println("Vertex: " + entry.getValue() + ", Day: " + resultMap.get(entry));
        }

        assertThat(resultMap, is(equalToMap(expectedMap)));
    }

    /**
     * Test of planificadorActivityes method, of class GreedyAlgorithm.
     */
    @Test
    public void testPlannerActivityes() {
        Set<String> expectedActivities = this.dataGreedy.getActivitiesResult();

        Set<String> resultActivities = GreedyAlgorithm.plannerActivities(dataGreedy.getListActivities());

        assertThat(resultActivities, is(equalTo(expectedActivities)));
    }

}
