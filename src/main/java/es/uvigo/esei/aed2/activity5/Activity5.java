package es.uvigo.esei.aed2.activity5;

import java.util.List;

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

import es.uvigo.esei.aed2.tree.nary.Tree;

public class Activity5 {

  //exercise 1
  public static int getSum(Tree<Integer> tree) {
    // TODO: Implementa la suma recursiva de los valores del árbol n-ario
    return 0;
  }

  //exercise 2
  public static <T> boolean isEqualStructure(Tree<T> treeA, Tree<T> treeB) {
    // TODO: Implementa la comprobación recursiva de igualdad de estructura entre dos árboles n-arios
    return false;
  }

  //exercise 3
  public static <T> boolean is23Tree(Tree<T> tree) {
    // TODO: Implementa la comprobación recursiva de si el árbol es un 2-3 árbol
    return false;
  }

  //exercise 4
  public static <T extends Comparable<T>> boolean isSelection(Tree<T> tree) {
    // TODO: Implementa la comprobación recursiva de si el árbol es de selección
    return false;
  }

  //exercise 5
  public static <T> int getLevel(Tree<T> tree, T value) {
    // TODO: Implementa la búsqueda recursiva del nivel de un valor en el árbol
    return -1;
  }

  //exercise 6
  public static <T> int getGrade(Tree<T> tree) {
    // TODO: Implementa la obtención recursiva del grado máximo del árbol
    return 0;
  }

  //exercise 7
  public static <T> int getHeight(Tree<T> tree) {
    // TODO: Implementa la obtención recursiva de la altura del árbol
    return 0;
  }

  //exercise 8
  public static <T> void breadthOrder(Tree<T> tree){
    // TODO: Implementa el recorrido en anchura del árbol n-ario
  }

  //exercise 9
  public static int getNumberOfEvenValues(Tree<Integer> tree) {
    // TODO: Implementa el conteo recursivo de valores pares en el árbol
    return 0;
  }

  //exercise 10 
  public static <T> void getListLeaves(Tree<T> tree, List<T> list) {
    // TODO: Implementa la obtención recursiva de las hojas del árbol en una lista
  }
  
}