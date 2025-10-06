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

import es.uvigo.esei.aed1.tads.queue.LinkedQueue;
import es.uvigo.esei.aed1.tads.queue.Queue;
import org.hamcrest.Description;
import org.hamcrest.TypeSafeMatcher;

public class IsEqualToMaxHeap<T extends Comparable<T>> extends TypeSafeMatcher<MaxHeap<T>> {

    private final MaxHeap<T> expected;

    public IsEqualToMaxHeap(MaxHeap<T> expected) {
        this.expected = expected;
    }

    @Override
    public void describeTo(Description description) {
        description.appendText("equal heaps");
    }

    private MaxHeap<T> clone(MaxHeap<T> heap) {
        MaxHeap<T> copy = new BinaryMaxHeap<>();
        Queue<T> queue = new LinkedQueue<>();
        while (!heap.isEmpty()) {
            queue.add(heap.removeMaxValue());
        }
        while (!queue.isEmpty()) {
            T value = queue.remove();
            copy.add(value);
            heap.add(value);
        }
        return copy;
    }

    @Override
    protected boolean matchesSafely(MaxHeap<T> actual) {
        MaxHeap<T> cloneHeapA = clone(this.expected);
        MaxHeap<T> cloneHeapB = clone(actual);

        return compareHeaps(cloneHeapA, cloneHeapB);
    }

    private static <T extends Comparable<T>> boolean compareHeaps(MaxHeap<T> heapA, MaxHeap<T> heapB) {
        while (!heapA.isEmpty() && !heapB.isEmpty()) {
            if (!heapA.removeMaxValue().equals(heapB.removeMaxValue())) {
                return false;
            }
        }
        return heapA.isEmpty() && heapB.isEmpty();
    }

    public static <T extends Comparable<T>> IsEqualToMaxHeap<T> equalToMaxHeap(MaxHeap<T> expected) {
        return new IsEqualToMaxHeap<>(expected);
    }

}
