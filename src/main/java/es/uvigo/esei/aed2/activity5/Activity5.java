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
    // Árbol de selección: cada nodo debe ser <= que todos sus hijos
    public static <T extends Comparable<T>> boolean isSelection(Tree<T> tree) {
        try {
            if (tree.isEmpty()) return true;
            if (!tree.hasChildren()) return true;

            T root = tree.getRootValue();
            Tree<T> child = tree.getLeftMostChild();

            while (child != null) {
                if (!child.isEmpty()) {
                    T childVal = child.getRootValue();
                    // La raíz debe ser menor o igual que cada hijo
                    if (root.compareTo(childVal) > 0) return false;
                    // Verificar recursivamente cada subárbol
                    if (!isSelection(child)) return false;
                    child = child.hasRightSibling() ? child.getRightSibling() : null;
                }
            }
            return true;
        } catch (EmptyTreeException e) {
            return false;
        }
    }

    //exercise 5
    // Devuelve el nivel donde está el valor (-1 si no existe)
    public static <T> int getLevel(Tree<T> tree, T value) {
        return getLevelHelper(tree, value, 0);
    }

    private static <T> int getLevelHelper(Tree<T> tree, T value, int currentLevel) {
        if (tree.isEmpty()) return -1;

        // Si encontramos el valor, devolvemos el nivel actual
        if (Objects.equals(tree.getRootValue(), value)) return currentLevel;

        // Buscar en los hijos
        if (tree.hasChildren()) {
            Tree<T> child = tree.getLeftMostChild();
            while (child != null) {
                int result = getLevelHelper(child, value, currentLevel + 1);
                if (result != -1) return result; // Encontrado
                child = child.hasRightSibling() ? child.getRightSibling() : null;
            }
        }
        return -1;
    }

    //exercise 6
    // Grado del árbol: máximo número de hijos que tiene cualquier nodo
    public static <T> int getGrade(Tree<T> tree) {
        if (tree.isEmpty()) return 0;

        int maxGrade = 0;

        // Contar hijos del nodo actual
        if (tree.hasChildren()) {
            int childCount = 0;
            Tree<T> child = tree.getLeftMostChild();
            while (child != null) {
                childCount++;
                // Obtener el grado máximo de cada subárbol
                int childGrade = getGrade(child);
                maxGrade = Math.max(maxGrade, childGrade);
                child = child.hasRightSibling() ? child.getRightSibling() : null;
            }
            maxGrade = Math.max(maxGrade, childCount);
        }

        return maxGrade;
    }

    //exercise 7
    // Altura: máxima distancia desde la raíz hasta una hoja
    public static <T> int getHeight(Tree<T> tree) {
        if (tree.isEmpty() || !tree.hasChildren()) return 0;

        int maxHeight = 0;
        Tree<T> child = tree.getLeftMostChild();

        while (child != null) {
            int childHeight = getHeight(child);
            maxHeight = Math.max(maxHeight, childHeight);
            child = child.hasRightSibling() ? child.getRightSibling() : null;
        }

        return maxHeight + 1;
    }

    //exercise 8
    // Recorrido en anchura (por niveles)
    public static <T> void breadthOrder(Tree<T> tree) {
        if (tree.isEmpty()) return;

        // Usar una cola para el recorrido por niveles
        java.util.Queue<Tree<T>> queue = new java.util.LinkedList<>();
        queue.add(tree);

        while (!queue.isEmpty()) {
            Tree<T> current = queue.poll();
            System.out.println(current.getRootValue());

            // Añadir todos los hijos a la cola
            if (current.hasChildren()) {
                Tree<T> child = current.getLeftMostChild();
                while (child != null) {
                    queue.add(child);
                    child = child.hasRightSibling() ? child.getRightSibling() : null;
                }
            }
        }
    }

    //exercise 9
    // Cuenta cuántos valores pares hay en el árbol
    public static int getNumberOfEvenValues(Tree<Integer> tree) {
        if (tree.isEmpty()) return 0;

        int count = (tree.getRootValue() % 2 == 0) ? 1 : 0;

        if (tree.hasChildren()) {
            Tree<Integer> child = tree.getLeftMostChild();
            while (child != null) {
                count += getNumberOfEvenValues(child);
                child = child.hasRightSibling() ? child.getRightSibling() : null;
            }
        }

        return count;
    }

    //exercise 10
    // Obtiene todas las hojas del árbol en una lista (nodos sin hijos)
    public static <T> void getListLeaves(Tree<T> tree, List<T> list) {
        if (tree.isEmpty()) return;

        // Si es una hoja, añadirla a la lista
        if (!tree.hasChildren()) {
            list.add(tree.getRootValue());
            return;
        }

        // Recorrer todos los hijos
        Tree<T> child = tree.getLeftMostChild();
        while (child != null) {
            getListLeaves(child, list);
            child = child.hasRightSibling() ? child.getRightSibling() : null;
        }
    }

    // Copia los valores del árbol hasta un nivel específico en una lista (nivel 0 = solo raíz)
    public static <T> void copyUntilLevel(Tree<T> tree, int maxLevel, List<T> result) {
        copyUntilLevelHelper(tree, maxLevel, 0, result);
    }

    private static <T> void copyUntilLevelHelper(Tree<T> tree, int maxLevel, int currentLevel, List<T> result) {
        if (tree.isEmpty() || currentLevel > maxLevel) return;

        // Añadir el valor actual
        result.add(tree.getRootValue());

        // Si aún no hemos llegado al nivel máximo, copiar los hijos
        if (currentLevel < maxLevel && tree.hasChildren()) {
            Tree<T> child = tree.getLeftMostChild();
            while (child != null) {
                copyUntilLevelHelper(child, maxLevel, currentLevel + 1, result);
                child = child.hasRightSibling() ? child.getRightSibling() : null;
            }
        }
    }

}
