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

import java.util.function.Supplier;

public class MaxHeapTestData {

    private final Supplier<MaxHeap<Integer>> maxHeapSupplier;

    public MaxHeapTestData(
        Supplier<MaxHeap<Integer>> maxHeapSupplier
    ) {
        this.maxHeapSupplier = maxHeapSupplier;
    }

    public MaxHeap<Integer> getEmptyMaxHeap() {
        return this.maxHeapSupplier.get();
    }

    /*
     *           50
     *        /     \
     *       47      22
     *     /   \    /  \
     *    32   44  12  19
     *    /\   /
     *   16 3 37
     */
    private MaxHeap<Integer> createMaxHeapWith(Integer... values) {
        final MaxHeap<Integer> heap = this.getEmptyMaxHeap();

        for (Integer value : values) {
            heap.add(value);
        }

        return heap;
    }

    public MaxHeap<Integer> getMaxHeap() {
        return this.createMaxHeapWith(50, 47, 22, 32, 44, 12, 19, 16, 3, 37);
    }

    public MaxHeap<Integer> getMaxHeapSingleNode() {
        return this.createMaxHeapWith(50);
    }

    public MaxHeap<Integer> getMaxHeapSingleMinusNode() {
        return this.createMaxHeapWith(3);
    }

    public MaxHeap<Integer> getRemoveMaxHeap() {
        return this.createMaxHeapWith(47, 44, 22, 32, 37, 12, 19, 16, 3);
    }

    private MaxHeap<Integer> createDesorderHeapWith(Integer... values) {
        final MaxHeap<Integer> heap = this.getEmptyMaxHeap();

        for (Integer value : values) {
            heap.insert(value);
        }

        return heap;
    }

    public MaxHeap<Integer> getDesorderHeap() {
        return this.createDesorderHeapWith(37, 22, 12, 50, 3, 44, 47, 16, 19, 32);
    }


}
