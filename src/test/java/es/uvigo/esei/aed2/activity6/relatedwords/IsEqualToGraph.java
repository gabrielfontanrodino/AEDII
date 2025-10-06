package es.uvigo.esei.aed2.activity6.relatedwords;

import es.uvigo.esei.aed2.graph.Edge;
import es.uvigo.esei.aed2.graph.Graph;
import es.uvigo.esei.aed2.graph.Vertex;
import org.hamcrest.Description;
import org.hamcrest.TypeSafeMatcher;

import java.util.Set;

public class IsEqualToGraph<T, E> extends TypeSafeMatcher<Graph<T, E>> {

    private final Graph<T, E> expected;

    public IsEqualToGraph(Graph<T, E> expected) {
        this.expected = expected;
    }

    @Override
    public void describeTo(Description description) {
        description.appendText("equal graph");
    }

    @Override
    protected boolean matchesSafely(Graph<T, E> actual) {
        // Comparar vértices
        Set<Vertex<T>> verticesActual = actual.getVertices();
        Set<Vertex<T>> verticesExpected = expected.getVertices();
        if (!verticesActual.equals(verticesExpected)) {
            return false;
        }

        // Comparar arcos
        Set<Edge<T, E>> edgesActual = actual.getEdges();
        Set<Edge<T, E>> edgesExpected = expected.getEdges();
        return edgesActual.equals(edgesExpected);

    }

    public static <T, E> IsEqualToGraph<T, E> equalToGraph(Graph<T, E> expected) {
        return new IsEqualToGraph<>(expected);
    }

}
