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

import es.uvigo.esei.aed2.activity7.data.GraphRepository;
import es.uvigo.esei.aed2.graph.AdjacencyList;
import es.uvigo.esei.aed2.graph.Graph;
import es.uvigo.esei.aed2.graph.Vertex;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Set;

import static es.uvigo.esei.aed2.activity7.Activity7.*;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

public class Activity7TestCase {

    private final GraphRepository graphs = new GraphRepository(
        AdjacencyList::new,
        AdjacencyList::new
    );

    // Test of method getPredecessors
    @Test
    public void testGetPredecessorsEmptyGraph() {
        final Graph<String, Integer> graph = this.graphs.getEmptyGraph();

        Set<Vertex<String>> setOfPredecessors = getPredecessors(graph, this.graphs.getVertexCO());

        assertThat(setOfPredecessors.isEmpty(), is(true));
    }

    @Test
    public void testGetPredecessorsGraph() {
        final Graph<String, Integer> graph = this.graphs.getGraphCities();
        Set<Vertex<String>> predecessorsExpected = this.graphs.getVerticesPredecessorsOU();

        Set<Vertex<String>> setOfPredecessors = getPredecessors(graph, this.graphs.getVertexOU());

        assertThat(setOfPredecessors.equals(predecessorsExpected), is(true));
    }

    //Test of method isDrain
    @Test
    public void testIsDrainEmptyGraph() {
        final Graph<String, Integer> graph = this.graphs.getEmptyGraph();

        assertThat(isDrain(graph, this.graphs.getVertexCO()), is(false));
    }

    @Test
    public void testNoIsDrainGraph() {
        final Graph<String, Integer> graph = this.graphs.getGraphCities();

        assertThat(isDrain(graph, this.graphs.getVertexCO()), is(false));
    }

    @Test
    public void testIsDrainGraph() {
        final Graph<String, Integer> graph = this.graphs.getGraphCitiesDrain();

        assertThat(isDrain(graph, this.graphs.getVertexOU()), is(true));
    }

    // Test of method isRegular
    @Test
    public void testIsRegularEmptyGraph() {
        final Graph<String, Integer> graph = this.graphs.getEmptyGraph();

        assertThat(isRegular(graph), is(true));
    }

    @Test
    public void testIsNoRegularCitiesGraph() {
        final Graph<String, Integer> graph = this.graphs.getGraphCities();

        assertThat(isRegular(graph), is(false));
    }

    @Test
    public void testIsRegularCitiesGraph() {
        final Graph<String, Integer> graph = this.graphs.getGraphCitiesRegular();

        assertThat(isRegular(graph), is(true));
    }

    /**
     * Test of isConnectedFromVertex method, of class Actividad7.
     */
    @Test
    public void testIsConnectedFromVertexEmptyGraph() {
        final Graph<String, Integer> graph = this.graphs.getEmptyGraph();

        boolean connected = isConnectedFromVertex(graph, this.graphs.getVertexCO());

        assertThat(connected, is(false));
    }

    @Test
    public void testIsConnectedFromVertexGraphCities() {
        final Graph<String, Integer> graph = this.graphs.getGraphCities();

        boolean connected = isConnectedFromVertex(graph, this.graphs.getVertexCO());

        assertThat(connected, is(true));
    }

    @Test
    public void testIsNoConnectedFromVertexGraphTopological2() {
        final Graph<String, Integer> graph = this.graphs.getGraphTopologicalOrder2();

        boolean connected = isConnectedFromVertex(graph, this.graphs.getVertexB());

        assertThat(connected, is(false));
    }

    @Test
    public void testIsConnectedFromVertexGraphTopological2() {
        final Graph<String, Integer> graph = this.graphs.getGraphTopologicalOrder2();

        boolean connected = isConnectedFromVertex(graph, this.graphs.getVertexC());

        assertThat(connected, is(true));
    }

    /**
     * Test of thereIsPathBetweenVertices method, of class Actividad7.
     */
    @Test
    public void testThereIsPathBetweenVerticesGraphEmpty() {
        final Graph<String, Integer> graph = this.graphs.getEmptyGraph();

        boolean thereIsPath = thereIsPathBetweenVertices(graph, this.graphs.getVertexCO(), this.graphs.getVertexOU());

        assertThat(thereIsPath, is(false));
    }

    @Test
    public void testThereIsPathBetweenVerticesGraphCities() {
        final Graph<String, Integer> graph = this.graphs.getGraphCities();

        boolean thereIsPath = thereIsPathBetweenVertices(graph, this.graphs.getVertexCO(), this.graphs.getVertexOU());

        assertThat(thereIsPath, is(true));
    }

    @Test
    public void testThereIsPathBetweenVerticesGraphCities2() {
        final Graph<String, Integer> graph = this.graphs.getGraphCities();

        boolean thereIsPath = thereIsPathBetweenVertices(graph, this.graphs.getVertexOU(), this.graphs.getVertexCO());

        assertThat(thereIsPath, is(true));
    }

    @Test
    public void testThereIsPathBetweenVerticesGraphTopological2() {
        final Graph<String, Integer> graph = this.graphs.getGraphTopologicalOrder2();

        boolean thereIsPath = thereIsPathBetweenVertices(graph, this.graphs.getVertexC(), this.graphs.getVertexD());

        assertThat(thereIsPath, is(true));
    }

    @Test
    public void testThereIsNoPathBetweenVerticesGraphTopological2() {
        final Graph<String, Integer> graph = this.graphs.getGraphTopologicalOrder2();

        boolean thereIsPath = thereIsPathBetweenVertices(graph, this.graphs.getVertexD(), this.graphs.getVertexC());

        assertThat(thereIsPath, is(false));
    }

    /**
     * Test of isCycle method, of class Activity7.
     */
    @Test
    public void testisACycleEmptyGraph() {
        final Graph<String, Integer> graph = this.graphs.getEmptyGraph();
        List<Vertex<String>> path = List.of(
            this.graphs.getVertexCO(),
            this.graphs.getVertexOU()
        );

        assertThat(isACycle(graph, path), is(false));
    }

    @Test
    public void testisACycleGraphCities() {
        final Graph<String, Integer> graph = this.graphs.getGraphCities();
        List<Vertex<String>> path = List.of(
            this.graphs.getVertexOU(),
            this.graphs.getVertexPO(),
            this.graphs.getVertexOU()
        );

        assertThat(isACycle(graph, path), is(true));
    }

    @Test
    public void testisACycleGraphCities2() {
        final Graph<String, Integer> graph = this.graphs.getGraphCities();
        List<Vertex<String>> path = List.of(
            this.graphs.getVertexCO(),
            this.graphs.getVertexOU(),
            this.graphs.getVertexPO(),
            this.graphs.getVertexLU(),
            this.graphs.getVertexCO()
        );

        assertThat(isACycle(graph, path), is(true));
    }

    @Test
    public void testisNotACycleGraphCities2() {
        final Graph<String, Integer> graph = this.graphs.getGraphCities();
        List<Vertex<String>> path = List.of(
            this.graphs.getVertexOU(),
            this.graphs.getVertexPO(),
            this.graphs.getVertexOU(),
            this.graphs.getVertexPO(),
            this.graphs.getVertexLU(),
            this.graphs.getVertexOU()
        );

        assertThat(isACycle(graph, path), is(false));
    }

    /**
     * Test of numberOfConnectedComponents method, of class Activity7.
     */
    @Test
    public void testNumberOfConnectedComponentsEmptyGraph() {
        final Graph<String, Integer> graph = this.graphs.getEmptyGraph();

        assertThat(numberOfConnectedComponents(graph), is(0));
    }

    @Test
    public void testNumberOfConnectedComponentsGraphCities() {
        final Graph<String, Integer> graph = this.graphs.getGraphCities();

        assertThat(numberOfConnectedComponents(graph), is(1));
    }

    @Test
    public void testNumberOfConnectedComponentsGraphUnconnected1() {
        final Graph<String, Integer> graph = this.graphs.getGraphUnconnected1();

        assertThat(numberOfConnectedComponents(graph), is(2));
    }

    @Test
    public void testNumberOfConnectedComponentsGraphUnconnected2() {
        final Graph<String, Integer> graph = this.graphs.getGraphUnconnected2();

        assertThat(numberOfConnectedComponents(graph), is(3));
    }


//    @Test
//    public void testNumComponentesConexas2() {
//        System.out.println("Test numero componentes conexas 2");
//        grafoConexo.eliminarArco(new Arco<>(v1, v4, 115));
//        grafoConexo.eliminarArco(new Arco<>(v4, v1, 115));
//        grafoConexo.eliminarArco(new Arco<>(v2, v3, 100));
//        grafoConexo.eliminarArco(new Arco<>(v3, v2, 100));
//
//        int expResult = 2;
//        int result = SolActividad7.numComponentesConexas(grafoConexo);
//        assertEquals(expResult, result);
//    }
//     @Test
//    public void testNumComponentesConexas3() {
//        System.out.println("Test numero componentes conexas 3");
//        grafoConexo.eliminarArco(new Arco<>(v1, v4, 115));
//        grafoConexo.eliminarArco(new Arco<>(v4, v1, 115));
//        grafoConexo.eliminarArco(new Arco<>(v2, v3, 100));
//        grafoConexo.eliminarArco(new Arco<>(v3, v2, 100));
//        grafoConexo.eliminarArco(new Arco<>(v1, v2, 10));
//        grafoConexo.eliminarArco(new Arco<>(v2, v1, 10));
//
//        int expResult = 3;
//        int result = SolActividad7.numComponentesConexas(grafoConexo);
//        assertEquals(expResult, result);
//    }

}
