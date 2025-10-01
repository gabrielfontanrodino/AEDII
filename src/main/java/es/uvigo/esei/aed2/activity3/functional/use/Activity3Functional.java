package es.uvigo.esei.aed2.activity3.functional.use;

import es.uvigo.esei.aed2.activity3.functional.BinaryTreeWithFunctional;
import es.uvigo.esei.aed2.tree.binary.BinaryTree;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class Activity3Functional {

    /**
     * muestra todos los valores almacenados en el arbol
     *
     * @param <T>  el tipo de los valores del árbol
     * @param tree arbol binario con métodos funcionales
     */
    public static <T> void printAllValues(BinaryTreeWithFunctional<T> tree) {
        tree.forEach(System.out::println);
    }

    /**
     * recorre el árbol y guarda los valores pares en una lista
     *
     * @param tree árbol binario de enteros con métodos funcionales
     * @return una lista con los valores pares del árbol de enteros
     */
    public static List<Integer> listEvenValues(BinaryTreeWithFunctional<Integer> tree) {
        List<Integer> list = new ArrayList<>();
        tree.forEach(value -> {
            if(value % 2 == 0) list.add(value);
        });
        return list;
    }

    /**
     * muestra todos los valores mayores de 30
     *
     * @param tree árbol binario de enteros con métodos funcionales
     */
    public static void printValuesGreaterThan30(BinaryTreeWithFunctional<Integer> tree) {
        tree.forEach(System.out::println, value -> (value > 30));
    }

    /**
     * crea un nuevo arbol binario con los valores de los nodos triplicados
     *
     * @param tree arbol binario de enteros
     * @return un arbol binario con los valores triplicados.
     */
    public static BinaryTree<Integer> binaryTreeWithTripleValues(BinaryTreeWithFunctional<Integer> tree) {
        return tree.map(value -> value*3);
    }
}
