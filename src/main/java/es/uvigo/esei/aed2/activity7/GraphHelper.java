package es.uvigo.esei.aed2.activity7;

import es.uvigo.esei.aed2.graph.Edge;
import es.uvigo.esei.aed2.graph.Graph;
import es.uvigo.esei.aed2.graph.Vertex;

import java.util.Set;

public class GraphHelper {
    /**
     * Imprime una representación visual del grafo en la consola.
     * Muestra los vértices y sus aristas adyacentes con sus etiquetas.
     *
     * @param <T>   el tipo de dato almacenado en los vértices
     * @param <E>   el tipo de dato almacenado en las etiquetas de las aristas
     * @param graph el grafo a imprimir
     */
    public static <T, E> void printGraph(Graph<T, E> graph) {
        if (graph == null || graph.isEmpty()) {
            System.out.println("Grafo vacío");
            return;
        }

        System.out.println("╔═══════════════════════════════════════════════════════════╗");
        System.out.println("║                 REPRESENTACIÓN DEL GRAFO                  ║");
        System.out.println("╠═══════════════════════════════════════════════════════════╣");
        System.out.println("║ Vértices: " + graph.numberOfVertices());
        System.out.println("║ Aristas: " + graph.getEdges().size());
        System.out.println("╚═══════════════════════════════════════════════════════════╝");
        System.out.println();

        for (Vertex<T> vertex : graph.getVertices()) {
            System.out.println("┌─ Vértice: " + vertex);

            Set<Vertex<T>> adjacents = graph.getAdjacentsVertex(vertex);

            if (adjacents.isEmpty()) {
                System.out.println("│  └─ (sin aristas salientes)");
            } else {
                int count = 0;
                for (Vertex<T> adjacent : adjacents) {
                    count++;
                    boolean isLast = (count == adjacents.size());
                    String prefix = isLast ? "└──" : "├──";

                    // Buscar la etiqueta de la arista
                    E label = null;
                    for (Edge<T, E> edge : graph.getEdges()) {
                        if (edge.getSource().equals(vertex) && edge.getTarget().equals(adjacent)) {
                            label = edge.getLabel();
                            break;
                        }
                    }

                    System.out.println("│  " + prefix + "> " + adjacent +
                        (label != null ? " [" + label + "]" : ""));
                }
            }
            System.out.println();
        }
    }
}
