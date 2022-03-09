package es.uvigo.esei.aed2.activity3.implementation;

import es.uvigo.esei.aed2.tree.binary.BinaryTree;

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

import es.uvigo.esei.aed2.tree.exceptions.EmptyTreeException;

public class LinkedBinaryTree<T> implements BinaryTree<T> {

  private LinkedBinaryTreeNode<T> rootNode;

  public LinkedBinaryTree() {
    throw new UnsupportedOperationException("Not supported yet.");
  }

  public LinkedBinaryTree(T value) throws NullPointerException {
    throw new UnsupportedOperationException("Not supported yet.");
  }

  public LinkedBinaryTree(T value, BinaryTree<T> leftChild, BinaryTree<T> rightChild)
  throws NullPointerException {
    throw new UnsupportedOperationException("Not supported yet.");
  }

  // Métodos lanzan excepción
  @Override
  public T getRootValue() throws EmptyTreeException {
    throw new UnsupportedOperationException("Not supported yet.");
  }

  @Override
  public void setRootValue(T value) throws EmptyTreeException, NullPointerException {
    throw new UnsupportedOperationException("Not supported yet.");
  }

  @Override
  public boolean contains(T value) {
    throw new UnsupportedOperationException("Not supported yet.");
  }

  @Override
  public boolean hasLeftChild() {
    throw new UnsupportedOperationException("Not supported yet.");
  }

  @Override
  public BinaryTree<T> getLeftChild() throws EmptyTreeException {
    throw new UnsupportedOperationException("Not supported yet.");
  }

  @Override
  public void setLeftChild(BinaryTree<T> leftChild) throws EmptyTreeException, NullPointerException {
    throw new UnsupportedOperationException("Not supported yet.");
  }

  @Override
  public void removeLeftChild() throws EmptyTreeException {
    throw new UnsupportedOperationException("Not supported yet.");
  }

  @Override
  public boolean hasRightChild() {
    throw new UnsupportedOperationException("Not supported yet.");
  }

  @Override
  public BinaryTree<T> getRightChild() throws EmptyTreeException {
    throw new UnsupportedOperationException("Not supported yet.");
  }

  @Override
  public void setRightChild(BinaryTree<T> rightChild) throws EmptyTreeException, NullPointerException {
    throw new UnsupportedOperationException("Not supported yet.");
  }

  @Override
  public void removeRightChild() throws EmptyTreeException {
    throw new UnsupportedOperationException("Not supported yet.");
  }
  
  @Override
  public void clear() {
    throw new UnsupportedOperationException("Not supported yet.");
  }

  @Override
  public boolean isEmpty() {
    throw new UnsupportedOperationException("Not supported yet.");
  }
}