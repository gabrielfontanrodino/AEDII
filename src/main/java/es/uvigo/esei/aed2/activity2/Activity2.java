package es.uvigo.esei.aed2.activity2;

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

import es.uvigo.esei.aed2.tree.binary.BinaryTree;
import es.uvigo.esei.aed2.tree.binary.LinkedBinaryTree;

public class Activity2 {

  //exercise 1
  public static <T> boolean isComplete(BinaryTree<T> tree) {
    // TODO: Implementa la comprobación recursiva de si el árbol es completo
    return false;
  }

  //exercise 2
  public static <T> boolean areIdentical(BinaryTree<T> treeA, BinaryTree<T> treeB) {
    // TODO: Implementa la comprobación recursiva de si dos árboles son idénticos
    return false;
  }

  //exercise 3
  public static <T> int countNodesInLevel(BinaryTree<T> tree, int level) {
    // TODO: Implementa el conteo recursivo de nodos en un nivel dado
    return 0;
  }

  //exercise 4
  public static <T> BinaryTree<T> removeLeaves(BinaryTree<T> tree) {
    // TODO: Implementa la eliminación recursiva de las hojas de un árbol
    return new LinkedBinaryTree<>();
  }

  //exercise 5
  public static <T> int calculateHeight(BinaryTree<T> tree) {
    // TODO: Implementa el cálculo recursivo de la altura de un árbol
    return -1;
  }

  //exercise 6
  public static BinaryTree<String> rebuild(String preorder, String inorder) {
    // TODO: Implementa la reconstrucción recursiva de un árbol binario a partir de los recorridos preorder e inorder
    return new LinkedBinaryTree<>();
  }

  //exercise 7
  public static <T extends Comparable<T>> boolean isSelectionTree(BinaryTree<T> tree) {
    // TODO: Implementa la comprobación recursiva de si el árbol es un árbol de selección
    return false;
  }

  //exercise 8
  public static <T> boolean isPath(BinaryTree<T> tree, String path) {
    // TODO: Implementa la comprobación recursiva de si existe un camino en el árbol que siga la secuencia dada
    return false;
  }

  //exercise 9
  public static <T> BinaryTree<T> copyingToLevel(BinaryTree<T> tree, int level) {
    // TODO: Implementa la copia recursiva del árbol hasta un nivel dado
    return new LinkedBinaryTree<>();
  }

  //exercise 10
  public static <T> boolean isLevelK(BinaryTree<T> tree, T elem, int k) {
    // TODO: Implementa la comprobación recursiva de si un elemento está en el nivel k del árbol
    return false;
  }
}