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

import org.junit.jupiter.api.Test;

import java.util.function.Supplier;

import static es.uvigo.esei.aed2.activity4.IsEqualToMaxHeap.equalToMaxHeap;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

public abstract class MaxHeapTestCase {

    final MaxHeapTestData data;

    public MaxHeapTestCase(Supplier<MaxHeap<Integer>> newEmptyHeap) {
        this.data = new MaxHeapTestData(newEmptyHeap);
    }

    /**
     * Test of isEmpty method, of class BinaryMaxHeap.
     */
    @Test
    public void testIsEmpty() {
        final MaxHeap<Integer> heap = this.data.getEmptyMaxHeap();

        assertThat(heap.isEmpty(), is(true));
    }

    @Test
    public void testIsEmptySingleNode() {
        final MaxHeap<Integer> heap = this.data.getMaxHeapSingleNode();

        assertThat(heap.isEmpty(), is(false));
    }

    @Test
    public void testIsEmptyMaxHeap() {
        final MaxHeap<Integer> heap = this.data.getMaxHeap();

        assertThat(heap.isEmpty(), is(false));
    }

    /**
     * Test of getMaxValue method, of class BinaryMaxHeap.
     */
    @Test
    public void testGetMaxValue() {
        final MaxHeap<Integer> heap = this.data.getEmptyMaxHeap();

        assertThrows(HeapEmptyException.class, heap::getMaxValue);
    }

    @Test
    public void testGetMaxValueSingleNode() {
        final MaxHeap<Integer> heap = this.data.getMaxHeapSingleNode();

        assertThat(heap.getMaxValue(), is(50));
    }

    @Test
    public void testGetMaxValueMaxHeap() {
        final MaxHeap<Integer> heap = this.data.getMaxHeap();

        assertThat(heap.getMaxValue(), is(50));
    }

    /**
     * Test of removeMaxValue method, of class BinaryMaxHeap.
     */
    @Test
    public void testRemoveMaxValueEmptyHeap() {
        final MaxHeap<Integer> heap = this.data.getEmptyMaxHeap();

        assertThrows(HeapEmptyException.class, heap::removeMaxValue);
    }

    @Test
    public void testRemoveMaxValueSingleNode() {
        final MaxHeap<Integer> heap = this.data.getMaxHeapSingleNode();
        heap.removeMaxValue();

        assertThat(heap.isEmpty(), is(true));
    }

    @Test
    public void testRemoveMaxValue() {
        final MaxHeap<Integer> heap = this.data.getMaxHeap();
        heap.removeMaxValue();

        final MaxHeap<Integer> heapRemoved = this.data.getRemoveMaxHeap();

        assertThat(heap, is(equalToMaxHeap(heapRemoved)));
    }

    @Test
    public void testRemove9Values() {
        final MaxHeap<Integer> heap = this.data.getMaxHeap();
        for (int i = 1; i <= 9; i++) {
            heap.removeMaxValue();
        }

        final MaxHeap<Integer> heapSingleNode = this.data.getMaxHeapSingleMinusNode();

        assertThat(heap, is(equalToMaxHeap(heapSingleNode)));
    }

    @Test
    public void testRemove10Values() {
        final MaxHeap<Integer> heap = this.data.getMaxHeap();
        for (int i = 1; i <= 10; i++) {
            heap.removeMaxValue();
        }
        assertThat(heap.isEmpty(), is(true));
    }

    /**
     * Test of add method, of class BinaryMaxHeap.
     */
    @Test
    public void testAddNullValue() {
        final MaxHeap<Integer> heap = this.data.getEmptyMaxHeap();

        assertThrows(NullPointerException.class, () -> heap.add(null));
    }

    @Test
    public void testAddSingleNode() {
        final MaxHeap<Integer> heap = this.data.getEmptyMaxHeap();
        heap.add(50);
        final MaxHeap<Integer> heapSingleNode = this.data.getMaxHeapSingleNode();

        assertThat(heap, is(equalToMaxHeap(heapSingleNode)));
    }

    @Test
    public void testAddValue() {
        final MaxHeap<Integer> heap = this.data.getRemoveMaxHeap();
        heap.add(50);

        final MaxHeap<Integer> heapAdd = this.data.getMaxHeap();

        assertThat(heap, is(equalToMaxHeap(heapAdd)));
    }

    /**
     * Test of clear method, of class BinaryMaxHeap.
     */
    @Test
    public void testClear() {
        final MaxHeap<Integer> heap = this.data.getMaxHeap();
        heap.clear();

        assertThat(heap.isEmpty(), is(true));
    }

    @Test
    public void testInsertNullValue() {
        final MaxHeap<Integer> heap = this.data.getEmptyMaxHeap();

        assertThrows(NullPointerException.class, () -> heap.add(null));
    }

    @Test
    public void testInsertSingleNode() {
        final MaxHeap<Integer> heap = this.data.getEmptyMaxHeap();
        heap.insert(50);

        final MaxHeap<Integer> heapSingleNode = this.data.getMaxHeapSingleNode();

        assertThat(heap, is(equalToMaxHeap(heapSingleNode)));
    }

    @Test
    public void testOrderEmptyHeap() {
        final MaxHeap<Integer> desorderHeap = this.data.getEmptyMaxHeap();
        desorderHeap.orderHeap();

        final MaxHeap<Integer> heap = this.data.getEmptyMaxHeap();
        assertThat(desorderHeap, is(equalToMaxHeap(heap)));
    }

    @Test
    public void testOrderMaxHeap() {
        final MaxHeap<Integer> desorderHeap = this.data.getDesorderHeap();
        desorderHeap.orderHeap();

        final MaxHeap<Integer> heap = this.data.getMaxHeap();
        assertThat(desorderHeap, is(equalToMaxHeap(heap)));
    }

}
