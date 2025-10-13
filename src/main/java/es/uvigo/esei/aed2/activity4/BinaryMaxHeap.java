package es.uvigo.esei.aed2.activity4;

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

import static java.util.Objects.requireNonNull;

public class BinaryMaxHeap<T extends Comparable<T>> implements MaxHeap<T> {

    // Exercise 1
    private final ArrayList<T> array;

    public BinaryMaxHeap() {
        this.array = new ArrayList<>();
        this.array.addFirst(null);
    }

    /**
     * Comprueba si el heap está vacío.
     *
     * @return <code>true</code> cuando el heap está vacío y <code>false</code> en
     * caso contrario.
     */
    @Override
    public boolean isEmpty() {
        return array.size() < 2;
    }

    /**
     * Recupera el mayor elemento del heap.
     *
     * @return el elemento mayor del heap.
     * @throws HeapEmptyException cuando el heap está vacío.
     */
    @Override
    public T getMaxValue() throws HeapEmptyException {
        if (this.isEmpty()) throw new HeapEmptyException("The heap is empty");

        return array.get(1);
    }

    /**
     * Elimina y devuelve el elemento mayor del heap.
     *
     * @return el elemento mayor del heap.
     * @throws HeapEmptyException cuando el heap está vacío.
     */
    @Override
    public T removeMaxValue() throws HeapEmptyException {
        T max = this.getMaxValue();
        T last = array.removeLast();
        if (!this.isEmpty()) {
            array.set(1, last);
            this.sink(1);
        }
        return max;
    }

    /**
     * Mueve el elemento en la posición {@code hollow} hacia arriba en el heap
     * hasta que se cumpla la propiedad de ordenación.
     *
     * @param hollow la posición del elemento a mover.
     */
    private void sink(int hollow) {
        int n = array.size() - 1;       // último índice válido
        while (true) {
            int left = hollow * 2;
            if (left > n) break;        // Si no hay hijos, salir del bucle.
            int right = left + 1;
            int child = left;           // Por defecto, el hijo izquierdo es el mayor.

            // Si el hijo derecho existe y es mayor que el hijo izquierdo, actualizar `child`.
            if (right <= n && array.get(right).compareTo(array.get(left)) > 0) {
                child = right;
            }

            // Si el elemento actual es mayor o igual que el hijo mayor, salir del bucle.
            if (array.get(hollow).compareTo(array.get(child)) >= 0) break;

            // Intercambiar el elemento actual con el hijo mayor.
            T tmp = array.get(hollow);
            array.set(hollow, array.get(child));
            array.set(child, tmp);
            hollow = child;             // Actualizar la posición del elemento y continuar.
        }
    }

    /**
     * Añade un valor al heap.
     *
     * @param value el elemento a añadir.
     * @throws NullPointerException si el valor es <code>null</code>.
     */
    @Override
    public void add(T value) throws NullPointerException {
        array.add(requireNonNull(value, "Cannot add null value to the heap."));

        int hueco = array.size() - 1;           // índice del nuevo elemento
        while (hueco > 1 && array.get(hueco / 2).compareTo(value) <= 0) { // Tengo padre y el padre es menor o igual
            int parent = hueco / 2;
            array.set(hueco, array.get(parent));
            array.set(parent, value);
            hueco = parent;
        }
    }

    /**
     * Elimina los valores del heap, convirtiéndolo en un heap vacío.
     */
    @Override
    public void clear() {
        array.clear();
        array.addFirst(null);
    }

    /**
     * Añade un valor al heap sin mantener la propiedad de ordenacion
     *
     * @param value el elemento a añadir
     * @throws NullPointerException si el valor es <code>null</code>
     */
    @Override
    public void insert(T value) throws NullPointerException {
        array.add(requireNonNull(value, "Cannot insert null value to the heap."));
    }

    /**
     * Restablece el orden en el montículo binario
     */
    @Override
    public void orderHeap() {
        if (!this.isEmpty()) {
            for (int i = ((array.size() - 1) / 2); i > 0 ; i--) {
                sink(i);
            }
        }
    }

}
