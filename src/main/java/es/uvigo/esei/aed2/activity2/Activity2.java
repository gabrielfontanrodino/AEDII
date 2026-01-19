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

import java.util.Objects;

public class Activity2 {

    //exercise 1
    public static <T> boolean isComplete(BinaryTree<T> tree) {
        if (tree.isEmpty()) return true;

        if (!tree.hasLeftChild() && !tree.hasRightChild()) return true; // Llegar a una hoja del árbol (válido)
        if (!tree.hasLeftChild() && tree.hasRightChild()) return false; // Tener solo un hijo en la derecha (inválido)
        if (tree.hasLeftChild() && !tree.hasRightChild()) return false; // Tener un hijo en la izquierda (inválido)

        return isComplete(tree.getLeftChild()) && isComplete(tree.getRightChild());
    }

    //exercise 2
    public static <T> boolean areIdentical(BinaryTree<T> treeA, BinaryTree<T> treeB) {
        if (treeA.isEmpty() && treeB.isEmpty()) return true;

        if (treeA.isEmpty() || treeB.isEmpty()) return false;

        if (treeA.getRootValue().equals(treeB.getRootValue()))
            return areIdentical(treeA.getLeftChild(), treeB.getLeftChild()) && areIdentical(treeA.getRightChild(), treeB.getRightChild());
        else return false;
    }

    //exercise 3
    public static <T> int countNodesInLevel(BinaryTree<T> tree, int level) {
        if (tree.isEmpty()) {
            return 0;
        }

        if (level == 0) {
            return 1;
        }

        return countNodesInLevel(tree.getLeftChild(), level - 1) + countNodesInLevel(tree.getRightChild(), level - 1);
    }

    public static <T> BinaryTree<T> removeLeaves(BinaryTree<T> tree) {
        if (tree.isEmpty()) return new LinkedBinaryTree<>(); //Si el árbol está vacío, devuelve un árbol vacío
        if (!tree.hasLeftChild() && !tree.hasRightChild())
            return new LinkedBinaryTree<>(); //Si es una hoja, devuelve un árbol vacío

        BinaryTree<T> left = removeLeaves(tree.getLeftChild());
        BinaryTree<T> right = removeLeaves(tree.getRightChild());

        return new LinkedBinaryTree<>(tree.getRootValue(), left, right);
    }

    //exercise 5
    public static <T> int calculateHeight(BinaryTree<T> tree) {
        if (tree.isEmpty()) return -1;

        // if(!tree.hasLeftChild() && !tree.hasRightChild()) return 0; // Si es una hoja, la altura es 0

        int leftHeight = calculateHeight(tree.getLeftChild());
        int rightHeight = calculateHeight(tree.getRightChild());

        return 1 + Math.max(leftHeight, rightHeight);
    }

    //exercise 6
    public static BinaryTree<String> rebuild(String preorder, String inorder) {
        if (preorder == null || inorder == null || preorder.isEmpty() || inorder.isEmpty()) {
            return new LinkedBinaryTree<>();
        }

        String root = String.valueOf(preorder.charAt(0)); // El primer elemento del recorrido en preorden es la raíz
        int inorderRootIndex = inorder.indexOf(root); // Encuentra el índice de la raíz en el recorrido en inorden

        // Con las dos líneas anteriores hemos dividido el árbol en raíz, subárbol izquierdo y subárbol derecho,
        // ya que así sabemos los límites de cada uno en los recorridos

        String preorderLeftSubstring = preorder.substring(1, 1 + inorderRootIndex);
        String inorderLeftSubstring = inorder.substring(0, inorderRootIndex);

        // Llamamos recursivamente a rebuild - RAIZ -> SUBÁRBOL IZQUIERDO -> SUBÁRBOL DERECHO
        BinaryTree<String> left = rebuild(preorderLeftSubstring, inorderLeftSubstring);

        String preorderRightSubstring = preorder.substring(1 + inorderRootIndex);
        String inorderRightSubstring = inorder.substring(inorderRootIndex + 1);

        // Llamamos recursivamente a rebuild - SUBÁRBOL IZQUIERDO -> RAIZ -> SUBÁRBOL DERECHO
        BinaryTree<String> right = rebuild(preorderRightSubstring, inorderRightSubstring);

        LinkedBinaryTree<String> tree = new LinkedBinaryTree<>(root);
        if (!left.isEmpty()) tree.setLeftChild(left);
        if (!right.isEmpty()) tree.setRightChild(right);
        return tree;
    }

    /**
     * Verifica si un árbol binario cumple con las propiedades de un árbol de selección.
     * Un árbol de selección es aquel en el que el valor de cada nodo es igual al menor
     * de los valores de sus hijos (si existen).
     *
     * @param <T>  El tipo de los valores almacenados en el árbol, que debe ser comparable.
     * @param tree El árbol binario a verificar.
     * @return true si el árbol cumple con las propiedades de un árbol de selección, false en caso contrario.
     */
    public static <T extends Comparable<T>> boolean isSelectionTree(BinaryTree<T> tree) {
        // Un árbol vacío es un árbol de selección válido
        if (tree.isEmpty()) return true;

        // Un nodo hoja (sin hijos) es un árbol de selección válido
        if (!tree.hasLeftChild() && !tree.hasRightChild()) return true;

        // Caso: Nodo con solo hijo izquierdo
        // Verifica que el valor del nodo sea igual al de su hijo izquierdo y que el subárbol izquierdo sea válido
        if (tree.hasLeftChild() && !tree.hasRightChild()) {
            return tree.getRootValue().equals(tree.getLeftChild().getRootValue())
                && isSelectionTree(tree.getLeftChild());
        }

        // Caso: Nodo con solo hijo derecho
        // Verifica que el valor del nodo sea igual al de su hijo derecho y que el subárbol derecho sea válido
        if (!tree.hasLeftChild() && tree.hasRightChild()) {
            return tree.getRootValue().equals(tree.getRightChild().getRootValue())
                && isSelectionTree(tree.getRightChild());
        }

        // Caso: Nodo con ambos hijos
        // Obtiene los valores de los hijos izquierdo y derecho
        T leftVal = tree.getLeftChild().getRootValue();
        T rightVal = tree.getRightChild().getRootValue();

        // Determina el menor valor entre los hijos
        T lowest = leftVal.compareTo(rightVal) <= 0 ? leftVal : rightVal;

        // Verifica que el valor del nodo sea igual al menor de sus hijos
        // y que ambos subárboles sean árboles de selección válidos
        return tree.getRootValue().equals(lowest)
            && isSelectionTree(tree.getLeftChild())
            && isSelectionTree(tree.getRightChild());
    }


    //exercise 8

    /**
     * Verifica si existe un camino en el árbol binario que coincida con la secuencia de caracteres
     * especificada en el parámetro `path`.
     *
     * @param <T>  El tipo de los valores almacenados en el árbol.
     * @param tree El árbol binario en el que se buscará el camino.
     * @param path La secuencia de caracteres que representa el camino a verificar.
     * @return true si el camino especificado por `path` existe en el árbol, false en caso contrario.
     */
    public static <T> boolean isPath(BinaryTree<T> tree, String path) {
        // Si el camino es nulo, no puede existir en el árbol
        if (path == null) return false;

        // Si el camino está vacío, solo es válido si el árbol también está vacío
        if (path.isEmpty()) return tree.isEmpty();

        // Si el árbol está vacío, no puede contener ningún camino
        if (tree.isEmpty()) return false;

        // Obtiene el primer carácter del camino
        String first = path.substring(0, 1);

        // Verifica si el valor de la raíz del árbol coincide con el primer carácter del camino
        if (!tree.getRootValue().equals(first)) return false;

        // Si el camino tiene un solo carácter y coincide con la raíz, el camino es válido
        if (path.length() == 1) return true;

        // Obtiene el resto del camino (excluyendo el primer carácter)
        String rest = path.substring(1);

        // Verifica recursivamente si el resto del camino existe en el subárbol izquierdo o derecho
        return isPath(tree.getLeftChild(), rest) || isPath(tree.getRightChild(), rest);
    }

    /**
     * Crea una copia de un árbol binario hasta un nivel específico.
     * Los nodos por debajo del nivel especificado no se incluirán en la copia.
     *
     * @param <T>   El tipo de los valores almacenados en el árbol.
     * @param tree  El árbol binario original que se copiará.
     * @param level El nivel hasta el cual se copiará el árbol (0 para solo la raíz).
     * @return Una copia del árbol binario hasta el nivel especificado.
     */
    public static <T> BinaryTree<T> copyingToLevel(BinaryTree<T> tree, int level) {
        // Si el árbol está vacío o el nivel es negativo, devuelve un árbol vacío
        if (tree.isEmpty() || level < 0) return new LinkedBinaryTree<>();

        // Si el nivel es 0, devuelve un árbol con solo la raíz
        if (level == 0) return new LinkedBinaryTree<>(tree.getRootValue());

        // Copia recursivamente los subárboles izquierdo y derecho hasta el nivel - 1
        BinaryTree<T> left = copyingToLevel(tree.getLeftChild(), level - 1);
        BinaryTree<T> right = copyingToLevel(tree.getRightChild(), level - 1);

        // Crea un nuevo árbol con la raíz del árbol original
        LinkedBinaryTree<T> copy = new LinkedBinaryTree<>(tree.getRootValue());

        // Establece los subárboles izquierdo y derecho si no están vacíos
        if (!left.isEmpty()) copy.setLeftChild(left);
        if (!right.isEmpty()) copy.setRightChild(right);

        return copy;
    }
    //exercise 10

    /**
     * Verifica si un elemento dado se encuentra en el nivel especificado de un árbol binario.
     *
     * @param <T>  El tipo de los valores almacenados en el árbol.
     * @param tree El árbol binario en el que se buscará el elemento.
     * @param elem El elemento a buscar en el nivel especificado.
     * @param k    El nivel del árbol en el que se buscará el elemento (0 para la raíz).
     * @return true si el elemento se encuentra en el nivel especificado, false en caso contrario.
     */
    public static <T> boolean isLevelK(BinaryTree<T> tree, T elem, int k) {
        // Si el árbol está vacío o el nivel es negativo, devuelve false
        if (tree.isEmpty() || k < 0) return false;

        // Si el nivel es 0, verifica si el valor de la raíz coincide con el elemento buscado
        if (k == 0) return Objects.equals(tree.getRootValue(), elem);

        // Verifica recursivamente en los subárboles izquierdo y derecho, reduciendo el nivel en 1
        return isLevelK(tree.getLeftChild(), elem, k - 1) || isLevelK(tree.getRightChild(), elem, k - 1);
    }
}
