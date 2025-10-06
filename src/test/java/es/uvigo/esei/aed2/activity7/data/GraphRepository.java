package es.uvigo.esei.aed2.activity7.data;

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
import es.uvigo.esei.aed2.graph.util.GraphParser;

import java.util.List;
import java.util.Set;
import java.util.function.Function;
import java.util.function.Supplier;

public class GraphRepository {

    private final static String GRAPH_RELATED_WORDS = //Activity 6
        """
            vertices:
              - fool
              - foil
              - foul
              - cool
              - pool
            edges:
              - from: fool
                to: foil
                value: 0
              - from: fool
                to: foul
                value: 0
              - from: fool
                to: cool
                value: 0
              - from: fool
                to: pool
                value: 0
              - from: foil
                to: fool
                value: 0
              - from: foul
                to: fool
                value: 0
              - from: cool
                to: fool
                value: 0
              - from: pool
                to: fool
                value: 0
              - from: foil
                to: foul
                value: 0
              - from: foul
                to: foil
                value: 0
              - from: cool
                to: pool
                value: 0
              - from: pool
                to: cool
                value: 0
            """;

    private final static String GRAPH_TOPOLOGICAL1 = //Activity 6
        """
            vertices:
              - A
              - B
              - C
              - D
            edges:
              - from: B
                to: D
                value: 10
              - from: A
                to: D
                value: 115
              - from: A
                to: C
                value: 115
              - from: C
                to: D
                value: 100
            """;

    private final static String GRAPH_TOPOLOGICAL2 = //Activity 6
        """
            vertices:
              - A
              - B
              - C
              - D
            edges:
              - from: C
                to: A
                value: 10
              - from: C
                to: B
                value: 10
              - from: C
                to: D
                value: 115
              - from: B
                to: D
                value: 115
            """;

    private final static String GRAPH_CITIES = //Activity 7
        """
            vertices:
              - Corunha
              - Lugo
              - Ourense
              - Pontevedra
            edges:
              - from: Lugo
                to: Corunha
                value: 10
              - from: Corunha
                to: Ourense
                value: 20
              - from: Lugo
                to: Pontevedra
                value: 115
              - from: Ourense
                to: Pontevedra
                value: 100
              - from: Lugo
                to: Ourense
                value: 100
              - from: Pontevedra
                to: Lugo
                value: 120
              - from: Pontevedra
                to: Ourense
                value: 120
            """;

    private final static String GRAPH_UNCONNECTED1 = //Activity 7
        """
            vertices:
              - A
              - B
              - C
              - D
            edges:
              - from: A
                to: B
                value: 10
              - from: B
                to: A
                value: 10
              - from: C
                to: D
                value: 115
              - from: D
                to: C
                value: 115
            """;

    private final GraphParser<String, Integer> parser;
    private final Supplier<Graph<String, Integer>> emptyGraphBuilder;

    public GraphRepository(
        GraphParser.GraphBuilder<String, Integer> builder,
        Supplier<Graph<String, Integer>> emptyGraphBuilder
    ) {
        this.parser = new GraphParser<>(
            emptyGraphBuilder,
            builder,
            Function.identity(),
            Integer::parseInt
        );
        this.emptyGraphBuilder = emptyGraphBuilder;
    }

    public Graph<String, Integer> getEmptyGraph() {
        return this.emptyGraphBuilder.get();
    }

    //Activity 6 - Related words
    public Graph<String, Integer> getGraphRelatedWords() {
        return this.parser.parseGraph(GRAPH_RELATED_WORDS);
    }

    //Activity 6 - topological order
    public Graph<String, Integer> getGraphTopologicalOrder1() {
        return this.parser.parseGraph(GRAPH_TOPOLOGICAL1);
    }

    public Graph<String, Integer> getGraphTopologicalOrder2() {
        return this.parser.parseGraph(GRAPH_TOPOLOGICAL2);
    }

    public List<Vertex<String>> getListOfVertices1() {
        return List.of(this.getVertexA(), this.getVertexB(), this.getVertexC(), this.getVertexD());
    }

    public List<Vertex<String>> getListOfVertices2() {
        return List.of(this.getVertexC(), this.getVertexA(), this.getVertexB(), this.getVertexD());
    }

    public List<Vertex<String>> getListOfVertices3() {
        return List.of(this.getVertexC(), this.getVertexB(), this.getVertexD(), this.getVertexA());
    }

    public List<Vertex<String>> getListOfVertices4() {
        return List.of(this.getVertexC(), this.getVertexB(), this.getVertexA(), this.getVertexD());
    }

    //Activity 7
    public Vertex<String> getVertexCO() {
        return new Vertex<>("Corunha");
    }

    public Vertex<String> getVertexOU() {
        return new Vertex<>("Ourense");
    }

    public Vertex<String> getVertexLU() {
        return new Vertex<>("Lugo");
    }

    public Vertex<String> getVertexPO() {
        return new Vertex<>("Pontevedra");
    }

    public Vertex<String> getVertexA() {
        return new Vertex<>("A");
    }

    public Vertex<String> getVertexB() {
        return new Vertex<>("B");
    }

    public Vertex<String> getVertexC() {
        return new Vertex<>("C");
    }

    public Vertex<String> getVertexD() {
        return new Vertex<>("D");
    }

    public Graph<String, Integer> getGraphCities() {
        return this.parser.parseGraph(GRAPH_CITIES);
    }

    public Graph<String, Integer> getGraphCitiesDrain() {
        Graph<String, Integer> graph = this.parser.parseGraph(GRAPH_CITIES);
        graph.removeEdge(this.getVertexOU(), this.getVertexPO(), 100);

        return graph;
    }

    public Graph<String, Integer> getGraphCitiesRegular() {
        Graph<String, Integer> graph = this.parser.parseGraph(GRAPH_CITIES);
        graph.removeEdge(this.getVertexLU(), this.getVertexOU(), 100);
        graph.addEdge(this.getVertexCO(), this.getVertexPO(), 100);
        graph.addEdge(this.getVertexOU(), this.getVertexLU(), 100);

        return graph;
    }

    public Graph<String, Integer> getGraphUnconnected1() {
        Graph<String, Integer> graph = this.parser.parseGraph(GRAPH_UNCONNECTED1);

        return graph;
    }

    public Graph<String, Integer> getGraphUnconnected2() {
        Graph<String, Integer> graph = this.parser.parseGraph(GRAPH_UNCONNECTED1);
        graph.removeEdge(this.getVertexA(), this.getVertexB(), 10);
        graph.removeEdge(this.getVertexB(), this.getVertexA(), 10);

        return graph;
    }

    public Set<Vertex<String>> getVertexGraphCities() {
        return this.parser.parseVertices(GRAPH_CITIES);
    }

    public Set<Edge<String, Integer>> getEdgesGraphCities() {
        return this.parser.parseEdges(GRAPH_CITIES);
    }

    public Set<Vertex<String>> getVerticesPredecessorsOU() {
        Set<Vertex<String>> setOfPredecessorsOU = getVertexGraphCities();
        setOfPredecessorsOU.remove(this.getVertexOU());
        return setOfPredecessorsOU;
    }

}
