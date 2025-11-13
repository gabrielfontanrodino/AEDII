package es.uvigo.esei.aed2.activity6.anagrams;

import es.uvigo.esei.aed2.activity6.HashMap.HashMap;
import es.uvigo.esei.aed2.map.Map;

import java.util.*;

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

public class Anagrams {

    /**
     * Agrupa palabras en conjuntos de anagramas usando tu implementación de HashMap.
     */
    public static Set<Set<String>> getAnagrams(List<String> words) {

        Map<String, List<String>> anagramGroups = new HashMap<>();

        if (words == null || words.isEmpty()) {
            return new HashSet<>();
        }

        // Iterar sobre cada palabra
        for (String word : words) {

            // Crear la clave (palabra ordenada)
            char[] chars = word.toCharArray();
            Arrays.sort(chars);
            String sortedKey = new String(chars);

            // Obtener la lista de anagramas actual para esta clave
            List<String> currentGroup = anagramGroups.get(sortedKey);

            if (currentGroup == null) {
                // Si no existe, crear la lista y añadirla al map
                currentGroup = new ArrayList<>();
                anagramGroups.add(sortedKey, currentGroup);
            }

            // Añadir la palabra original al grupo
            currentGroup.add(word);
        }

        // Convertir los valores del Map (Iterator<List<String>>) a Set<Set<String>>
        Set<Set<String>> finalGroups = new HashSet<>();
        Iterator<List<String>> groupsIterator = anagramGroups.getValues();

        while (groupsIterator.hasNext()) {
            List<String> groupList = groupsIterator.next();
            // Convertir la lista de anagramas en un conjunto
            finalGroups.add(new HashSet<>(groupList));
        }

        return finalGroups;
    }

    private static String buildSignature(String word) {
        //1. Crear un array con espacio para todas las letras del alfabeto
        int[] letterCounts = new int[26];

        //2. Convertir la palabra a minúsculas, convertirla en un array de caracteres y contar las apariciones de cada letra
        for (char c : word.toLowerCase().toCharArray()) {
            if (c >= 'a' && c <= 'z') {
                letterCounts[c - 'a']++;
            }
        }

        //3. Construir la firma a partir de las cuentas de letras
        StringBuilder signatureBuilder = new StringBuilder();

        for (int i = 0; i < letterCounts.length; i++) {
            if (letterCounts[i] > 0) {                      // Solo incluir letras que aparecen en la palabra
                signatureBuilder.append((char) (i + 'a'));  // Añadir la letra (le sumamos 'a' para obtener el carácter correcto)
                signatureBuilder.append(letterCounts[i]);   // Añadir el conteo de la letra
            }
        }

        return signatureBuilder.toString();
    }

    public static Set<Set<String>> getAnagramsEfficient(List<String> words) {

        Map<String, List<String>> anagramGroups = new HashMap<>();

        if (words == null || words.isEmpty()) {
            return new HashSet<>();
        }

        // Iterar sobre cada palabra
        for (String word : words) {

            // Crear la clave (palabra ordenada)
            String key = buildSignature(word);

            // Obtener la lista de anagramas actual para esta clave
            List<String> currentGroup = anagramGroups.get(key);

            if (currentGroup == null) {
                // Si no existe, crear la lista y añadirla al map
                currentGroup = new ArrayList<>();
                anagramGroups.add(key, currentGroup);
            }

            // Añadir la palabra original al grupo
            currentGroup.add(word);
        }

        // Convertir los valores del Map (Iterator<List<String>>) a Set<Set<String>>
        Set<Set<String>> finalGroups = new HashSet<>();
        Iterator<List<String>> groupsIterator = anagramGroups.getValues();

        while (groupsIterator.hasNext()) {
            List<String> groupList = groupsIterator.next();
            // Convertir la lista de anagramas en un conjunto
            finalGroups.add(new HashSet<>(groupList));
        }

        return finalGroups;
    }

}
