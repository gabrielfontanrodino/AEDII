package es.uvigo.esei.aed2.activity5;

import es.uvigo.esei.aed2.tree.exceptions.EmptyTreeException;
import es.uvigo.esei.aed2.tree.nary.Tree;

import java.util.List;
import java.util.Objects;

public class Activity5 {

    //exercise 1
    public static int getSum(Tree<Integer> tree) {
        if (tree.isEmpty()) return 0;

        int sum = tree.getRootValue();

        if (tree.hasChildren()) {
            Tree<Integer> currentSibling = tree.getLeftMostChild();
            while (true) {
                sum += getSum(currentSibling);
                if (!currentSibling.hasRightSibling()) break;
                currentSibling = currentSibling.getRightSibling();
            }
        }

        return sum;
    }

    //exercise 2
    public static <T> boolean isEqualStructure(Tree<T> a, Tree<T> b) {
        try {
            // Caso base: ambos vacíos
            if (a.isEmpty() && b.isEmpty()) return true;

            // Si uno está vacío y el otro no
            if (a.isEmpty() || b.isEmpty()) return false;

            // Comparamos las raíces, considerando 'null'
            T valA = a.getRootValue();
            T valB = b.getRootValue();

            if (!Objects.equals(valA, valB)) return false;

            // Si uno tiene hijos y el otro no -> estructuras diferentes
            if (a.hasChildren() != b.hasChildren()) return false;

            // Comparamos recursivamente los hijos
            Tree<T> childA = a.hasChildren() ? a.getLeftMostChild() : null;
            Tree<T> childB = b.hasChildren() ? b.getLeftMostChild() : null;

            while (childA != null && childB != null) {
                if (!isEqualStructure(childA, childB)) return false;

                childA = childA.hasRightSibling() ? childA.getRightSibling() : null;
                childB = childB.hasRightSibling() ? childB.getRightSibling() : null;
            }

            // Si uno tiene más hermanos que el otro
            return childA == null && childB == null;

        } catch (EmptyTreeException e) {
            // Si hay inconsistencia interna en el árbol
            return false;
        }
    }


    //exercise 3
    public static <T> boolean is23Tree(Tree<T> tree) {
        if (tree.isEmpty()) return true;

        // Si es una hoja (no tiene hijos), es válido
        if (!tree.hasChildren()) return true;

        // Contar el número de hijos
        int childCount = 0;
        Tree<T> currentChild = tree.getLeftMostChild();

        while (currentChild != null) {
            childCount++;
            // Verificar recursivamente cada hijo
            if (!is23Tree(currentChild)) return false;

            currentChild = currentChild.hasRightSibling() ? currentChild.getRightSibling() : null;
        }

        // Un nodo interno debe tener exactamente 2 o 3 hijos
        return childCount == 2 || childCount == 3;
    }

    //exercise 4
    public static <T extends Comparable<T>> boolean isSelection(Tree<T> tree) {
        try {
            if (tree.isEmpty()) return true;
            if (!tree.hasChildren()) return true;
            T root = tree.getRootValue();
            Tree<T> child = tree.getLeftMostChild();
            while (child != null) {
                T childVal = child.getRootValue();
                if (root.compareTo(childVal) > 0) return false;
                if (!isSelection(child)) return false;
                child = child.hasRightSibling() ? child.getRightSibling() : null;
            }
            return true;
        } catch (EmptyTreeException e) {
            return false;
        }
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
    public static <T> void breadthOrder(Tree<T> tree) {
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
