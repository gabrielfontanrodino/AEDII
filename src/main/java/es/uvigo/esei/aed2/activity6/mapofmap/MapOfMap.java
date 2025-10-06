package es.uvigo.esei.aed2.activity6.mapofmap;

import es.uvigo.esei.aed2.activity6.HashMap.HashMap;
import es.uvigo.esei.aed2.graph.Edge;
import es.uvigo.esei.aed2.graph.Graph;
import es.uvigo.esei.aed2.graph.Vertex;
import es.uvigo.esei.aed2.map.Map;

import java.util.Set;

public class MapOfMap<T, E> implements Graph<T, E> {

    private final Map<Vertex<T>, Map<Vertex<T>, E>> mapOfVertices;

    public MapOfMap() {
        this.mapOfVertices = new HashMap<>();
    }

    public MapOfMap(Set<Vertex<T>> vertices, Set<Edge<T, E>> edges) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public boolean isEmpty() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public int numberOfVertices() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public boolean containsVertex(Vertex<T> vertex) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public boolean containsEdge(Vertex<T> source, Vertex<T> target, E label) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public Set<Vertex<T>> getVertices() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public Set<Edge<T, E>> getEdges() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public Set<Vertex<T>> getAdjacentsVertex(Vertex<T> vertex) throws NullPointerException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public boolean addVertex(Vertex<T> vertex) throws NullPointerException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public boolean addEdge(Vertex<T> source, Vertex<T> target, E label) throws NullPointerException, IllegalArgumentException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public boolean removeVertex(Vertex<T> vertex) throws NullPointerException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public boolean removeEdge(Vertex<T> source, Vertex<T> target, E label) throws NullPointerException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void clear() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

}
