package es.uvigo.esei.aed2.activity3.implementation;

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

public class LinkedBinaryTreeNode<T> {

    private T value;
    private LinkedBinaryTreeNode<T> leftChild;
    private LinkedBinaryTreeNode<T> rightChild;

    public LinkedBinaryTreeNode() {
        this(null, null, null);
    }

    public LinkedBinaryTreeNode(T value) {
        this(value, null, null);
    }

    public LinkedBinaryTreeNode(T value, LinkedBinaryTreeNode<T> leftChild, LinkedBinaryTreeNode<T> rightChild) {
        this.leftChild = leftChild;
        this.rightChild = rightChild;
        this.value = value;
    }

    public T getValue() {
        return this.value;
    }

    public void setValue(T value) {
        this.value = value;
    }

    public boolean hasLeftChild() {
        return this.leftChild != null;
    }

    public LinkedBinaryTreeNode<T> getLeftChild() {
        return this.leftChild;
    }

    public void setLeftChild(LinkedBinaryTreeNode<T> leftChild) {
        this.leftChild = leftChild;
    }

    public boolean hasRightChild() {
        return this.rightChild != null;
    }

    public LinkedBinaryTreeNode<T> getRightChild() {
        return this.rightChild;
    }

    public void setRightChild(LinkedBinaryTreeNode<T> rightChild) {
        this.rightChild = rightChild;
    }
}
