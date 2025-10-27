package es.uvigo.esei.aed2.activity6.mapofmap;

import es.uvigo.esei.aed2.activity6.HashMap.HashMap;
import es.uvigo.esei.aed2.graph.Edge;
import es.uvigo.esei.aed2.graph.Graph;
import es.uvigo.esei.aed2.graph.Vertex;
import es.uvigo.esei.aed2.map.Map;
import java.util.Set;
import java.util.HashSet;

public class MapOfMap<T, E> implements Graph<T, E> {

    private final Map<Vertex<T>, Map<Vertex<T>, E>> mapOfVertices;

    public MapOfMap() {
        this.mapOfVertices = new HashMap<>();
    }

    public MapOfMap(Set<Vertex<T>> vertices, Set<Edge<T, E>> edges) {
        this();

        for (Vertex<T> vertex : vertices) {
            addVertex(vertex);
        }

        for (Edge<T, E> edge : edges) {
            addEdge(edge.getSource(), edge.getTarget(), edge.getLabel());
        }
    }

    @Override
    public boolean isEmpty() {
        return mapOfVertices.size() == 0;
    }

    @Override
    public int numberOfVertices() {
        return mapOfVertices.size();
    }

    @Override
    public boolean containsVertex(Vertex<T> vertex) {
        if (vertex == null) {
            return false;
        }

        return mapOfVertices.get(vertex) != null;
    }

    @Override
    public boolean containsEdge(Vertex<T> source, Vertex<T> target, E label) {
        if (source == null || target == null) {
            return false;
        }

        Map<Vertex<T>, E> adjacents = mapOfVertices.get(source);

        if (adjacents == null) {
            return false;
        }

        E currentLabel = adjacents.get(target);
        return currentLabel != null && currentLabel.equals(label);
    }

    @Override
    public Set<Vertex<T>> getVertices() {
        return mapOfVertices.getKeys();
    }

    @Override
    public Set<Edge<T, E>> getEdges() {
        Set<Edge<T, E>> edges = new HashSet<>();

        for (Vertex<T> source : mapOfVertices.getKeys()) {
            Map<Vertex<T>, E> adjacents = mapOfVertices.get(source);

            for (Vertex<T> target : adjacents.getKeys()) {
                edges.add(new Edge<>(source, target, adjacents.get(target)));
            }
        }
        return edges;
    }

    @Override
    public Set<Vertex<T>> getAdjacentsVertex(Vertex<T> vertex) throws NullPointerException {
        if (vertex == null) {
            throw new NullPointerException("El vértice no puede ser null");
        }

        Map<Vertex<T>, E> adjacents = mapOfVertices.get(vertex);

        if (adjacents == null) {
            return new HashSet<>();
        }
        return adjacents.getKeys();
    }

    @Override
    public boolean addVertex(Vertex<T> vertex) throws NullPointerException {
        if (vertex == null) {
            throw new NullPointerException("El vértice no puede ser null.");
        }

        if (containsVertex(vertex)) {
            return false;
        }

        mapOfVertices.add(vertex, new HashMap<>());
        return true;
    }

    @Override
    public boolean addEdge(Vertex<T> source, Vertex<T> target, E label) throws NullPointerException, IllegalArgumentException{
        if (source == null || target == null) {
            throw new NullPointerException("Los vértices no pueden ser null.");
        }

        if (!containsVertex(source) || !containsVertex(target)) {
            throw new IllegalArgumentException("Los vértices deben existir en el grafo.");
        }

        Map<Vertex<T>, E> adjacents = mapOfVertices.get(source);
        E oldLabel = adjacents.get(target);
        adjacents.add(target, label);

        return !label.equals(oldLabel);
    }

    @Override
    public boolean removeVertex(Vertex<T> vertex) throws NullPointerException {
        if (vertex == null) {
            throw new NullPointerException("El vértice no puede ser null");
        }

        if (!containsVertex(vertex)) {
            return false;
        }

        mapOfVertices.remove(vertex);

        for (Vertex<T> other : mapOfVertices.getKeys()) {
            Map<Vertex<T>, E> adjacents = mapOfVertices.get(other);
            adjacents.remove(vertex);
        }

        return true;
    }

    @Override
    public boolean removeEdge(Vertex<T> source, Vertex<T> target, E label) throws NullPointerException {
        if (source == null || target == null) {
            throw new NullPointerException("Los vértices no pueden ser null");
        }

        Map<Vertex<T>, E> adjacents = mapOfVertices.get(source);

        if (adjacents == null) {
            return false;
        }

        E currentLabel = adjacents.get(target);
        if (currentLabel != null && currentLabel.equals(label)) {
            adjacents.remove(target);
            return true;
        }
        return false;
    }

    @Override
    public void clear(){
        mapOfVertices.clear();
    }
}
