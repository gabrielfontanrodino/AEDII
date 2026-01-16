package es.uvigo.esei.aed2.activity10;

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

import java.util.ArrayList;

public class DivideAndConquerAlgorithm {
    // Exercise 1
    public static int quickSelection(int[] array, int k_minor, int i, int j) {
        // "elemento mayor de los dos primeros elementos DISTINTOS encontrados"
        int pivotIndex = searchPivotPosition(i, j, array);

        if (pivotIndex == -1) {
            return array[0];
        } else {
            int pivot = array[pivotIndex];
            // Pongo el pivote al final
            exchange(pivotIndex, j, array);
            // Particiono el array en menores y mayores o iguales que el pivote
            int k = partition(i, j, pivot, array);
            int numElementsLessThanPivot = k - i;
            // Ahora decido en qué parte seguir buscando el k-ésimo menor
            if (k_minor < numElementsLessThanPivot) {
                // Busco en la parte de los menores
                return quickSelection(array, k_minor, i, k - 1);
            } else if (k_minor > numElementsLessThanPivot) {
                // Busco en la parte de los mayores
                return quickSelection(array, k_minor - numElementsLessThanPivot, k, j);
            } else {
                // He encontrado el k-ésimo menor
                return array[k - 1];
            }
        }
    }

    /*
     * Entre dos elementos diferentes el pivote será el mayor devuelve la posición del elemento pivote.
     */
    private static int searchPivotPosition(int i, int j, int[] array) {
        int first = array[i];
        int k = i + 1;

        while (k <= j) {
            if (array[k] > first) {
                return k;
            } else if (array[k] < first) {
                return i;
            } else {
                k++;
            }
        }
        // Si llega al endal del array y todos los elementos son iguales
        return -1;
    }

    /*
     * Distribuye el array en menores que pivote, y mayores e igual a pivote.
     * Devuelve la posición del primer elemento de los mayores.
     */
    private static int partition(int i, int j, int pivot, int[] array) {
        int right = i;
        int left = j - 1; // privote está en la última posición
        do {

            while (array[right] < pivot) {
                right++;
            }

            while (array[left] >= pivot) {
                left--;
            }

            if (right < left) {
                exchange(right, left, array);
            }
        } while (right <= left);

        return right;
    }

    // Intercambia dos elementos en el array
    private static void exchange(int i, int j, int[] array) {
        int aux = array[i];
        array[i] = array[j];
        array[j] = aux;
    }

    // Exercise 2
    public static String moveHanoiDiscs(int numDiscs, String startPost, String auxiliaryPost, String endPost) {
        StringBuilder movements = new StringBuilder();
        moveHanoiDiscsRecursive(numDiscs, startPost, auxiliaryPost, endPost, movements);
        return movements.toString();
    }

    private static void moveHanoiDiscsRecursive(
        int numDiscs, String startPost, String auxiliaryPost, String endPost, StringBuilder movements
    ) {
        if (numDiscs == 1) {
            keepMoving(1, startPost, endPost, movements);
        } else {
            moveHanoiDiscsRecursive(numDiscs - 1, startPost, endPost, auxiliaryPost, movements);
            keepMoving(numDiscs, startPost, endPost, movements);
            moveHanoiDiscsRecursive(numDiscs - 1, auxiliaryPost, startPost, endPost, movements);
        }
    }

    private static void keepMoving(int n, String postI, String postF, StringBuilder movements) {
        movements.append("Disc ").append(n).append(" from ").append(postI).append(" to ").append(postF).append("\n");
    }

    // Exercise 3
    public static int numberExists(int[] array, int start, int end) {
        if (start > end) {
            return -1;
        }

        int mid = (start + end) / 2;

        if (array[mid] == mid) {
            return mid;
        } else if (array[mid] > mid) {
            // El valor encontrado en el array es mayor al índice del medio,
            // por lo que no encontraremos valores a la derecha que cuadren.
            // Conclusión: tenemos que buscar a la izquierda.
            return numberExists(array, start, mid - 1);
        } else {
            // Queda a la derecha por descarte
            return numberExists(array, mid + 1, end);
        }
    }

    // Exercise 4
    public static int searchPositionK(int[] v, int start, int end) {
        // La idea es ir analizando el valor de índice a índice
        // Cuando veamos que pasamos de un número cualquiera a uno
        // menor, marcamos ese como el índice "k", que es donde cambia
        // la curva

        if (start >= end) {
            return -1; // No se encuentra una posición K válida
        }

        int mid = (start + end) / 2;

        System.out.println("Mid: " + mid);

        // Verificamos si mid es la posición K
        if (
            (mid > 0 && mid < v.length - 1)
                && (v[mid - 1] > v[mid])
                && (v[mid] < v[mid + 1])
        ) {
            return mid;
        }

        // Si la curva sigue descendiendo, buscamos a la derecha y
        // ya no buscamos en la derecha
        if (mid > 0 && v[mid - 1] > v[mid]) {
            System.out.println("Buscamos a la derecha");
            return searchPositionK(v, mid - 1, end);
        }

        // Si la curva sigue ascendiendo, buscamos a la izquierda y
        // ya no buscamos en la derecha
        if (mid < v.length - 1 && v[mid] < v[mid + 1]) {
            System.out.println("Buscamos a la izquierda");
            return searchPositionK(v, start, mid + 1);
        }

        return -1; // No se encuentra una posición K válida

    }

    // Exercise 5
    public static int inversions(int[] array, int start, int end) {
        // Ambos tienen la misma cantidad de productos valorados
        // La valoración no puede contener valores repetidos y es ascendente
        if(start >= end) {
            return 0;
        }

        int mid = (start + end) / 2;
        int inversions = 0;

        inversions += inversions(array, start, mid);
        inversions += inversions(array, mid + 1, end);
        inversions += mergeCount(array, start, mid, mid + 1, end);

        return inversions;
    }

    // Mezcla ordenanado los elmentos de las dos mitades y devuelve el número de inversiones entre ellas
    private static int mergeCount(int[] aux, int start1, int end1, int start2, int end2) {
        int i = start1;
        int j = start2;
        int cont = 0;
        int pos = start2 - start1; // número máximo de diferencias
        ArrayList<Integer> temp = new ArrayList<>();
        while (i <= end1 && j <= end2) {
            if (aux[i] <= aux[j]) {
                temp.add(aux[i++]);
                pos--;
            } else {
                temp.add(aux[j++]);
                cont += pos;
            }
        }
        while (i <= end1) {
            temp.add(aux[i++]);
        }
        while (j <= end2) {
            temp.add(aux[j++]);
        }
        for (i = start1; i <= end2; i++) { // Paso todos los elementos del Temporal al array
            aux[i] = temp.removeFirst();
        }
        return cont;
    }

}
