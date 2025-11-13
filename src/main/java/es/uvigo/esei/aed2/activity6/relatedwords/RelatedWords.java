package es.uvigo.esei.aed2.activity6.relatedwords;

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

import es.uvigo.esei.aed2.activity6.HashMap.HashMap;
import es.uvigo.esei.aed2.activity6.mapofmap.MapOfMap;
import es.uvigo.esei.aed2.graph.Graph;
import es.uvigo.esei.aed2.graph.Vertex;
import es.uvigo.esei.aed2.map.Map;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class RelatedWords {
    /**
     * Construye un grafo que representa las relaciones entre las palabras.
     *
     * @param words palabras a relacionar.
     * @return grafo de palabras relacionadas.
     */
    public static Graph<String, Integer> buildGraph(List<String> words) {

        Graph<String, Integer> graph = new MapOfMap<>();

        if (words == null || words.isEmpty()) {
            return graph;
        }

        // Añade todos los vértices al grafo
        for (String w : words) {
            graph.addVertex(new Vertex<>(w));
        }

        // Agrupa palabras por clave con un guion en cada posición
        Map<String, List<String>> map = new HashMap<>();

        int wordsLength = words.getFirst().length();

        for (String word : words) {
            //Si las palabras no tienen la misma longitud, no pueden estar relacionadas
            if (word.length() != wordsLength) {
                continue;
            }

            //Crear entradas en el mapa con guion en cada posición
            for (int i = 0; i < wordsLength; i++) {
                String key = word.substring(0, i) + '_' + word.substring(i + 1);

                List<String> list = map.get(key);

                if (list == null) {
                    list = new ArrayList<>();
                    map.add(key, list);
                }

                list.add(word);
            }
        }

        System.out.println(map.size() + " buckets creados.");
        System.out.println(map.getKeys());

        for (var bucketKey : map.getKeys()) {
            System.out.println("Bucket " + bucketKey + ": " + map.get(bucketKey));
        }

        // Conecta en el grafo todas las palabras que comparten la misma clave
        Iterator<List<String>> it = map.getValues();

        while (it.hasNext()) {
            List<String> wordsGroup = it.next();
            for (int i = 0; i < wordsGroup.size(); i++) {
                for (int j = i + 1; j < wordsGroup.size(); j++) {

                    Vertex<String> v1 = new Vertex<>(wordsGroup.get(i));
                    Vertex<String> v2 = new Vertex<>(wordsGroup.get(j));

                    //Le pongo etiqueta 0 a las aristas porque son las definidas en los tests.
                    //En este caso nos dan igual las etiquetas.

                    // Añadir la arista en una dirección
                    graph.addEdge(v1, v2, 0);

                    // Añadir la arista inversa para un grafo no dirigido.
                    graph.addEdge(v2, v1, 0);
                }
            }
        }

        return graph;
    }
}
