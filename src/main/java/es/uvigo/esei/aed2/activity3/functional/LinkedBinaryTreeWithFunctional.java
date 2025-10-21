package es.uvigo.esei.aed2.activity3.functional;

import es.uvigo.esei.aed2.activity3.implementation.LinkedBinaryTree;
import es.uvigo.esei.aed2.tree.binary.BinaryTree;

import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;

public class LinkedBinaryTreeWithFunctional<T> extends LinkedBinaryTree<T> implements BinaryTreeWithFunctional<T> {

    public LinkedBinaryTreeWithFunctional() {
        super();
    }

    public LinkedBinaryTreeWithFunctional(T value) {
        super(value);
    }

    public LinkedBinaryTreeWithFunctional(
        T value,
        BinaryTree<T> left,
        BinaryTree<T> right
    ) {
        super(value, left, right);
    }

    @Override
    public void forEach(Consumer<T> action, Predicate<T> filter) {
        forEach(this, action, filter == null ? t -> true : filter);
    }

    private static <T> void forEach(BinaryTree<T> tree, Consumer<T> action, Predicate<T> filter) {
        if (tree == null || tree.isEmpty()) return;

        // 1) Procesar la raíz
        T value = tree.getRootValue();
        if (filter.test(value)) action.accept(value);

        // 2) Subárbol izquierdo
        if (tree.hasLeftChild()) {
            BinaryTree<T> left = tree.getLeftChild();
            if (left != null && !left.isEmpty()) {
                forEach(left, action, filter);
            }
        }

        // 3) Subárbol derecho
        if (tree.hasRightChild()) {
            BinaryTree<T> right = tree.getRightChild();
            if (right != null && !right.isEmpty()) {
                forEach(right, action, filter);
            }
        }
    }


    @Override
    public <E> BinaryTree<E> map(Function<T, E> mapper) {
        return map(this, mapper);
    }

    private static <E, T> BinaryTree<E> map(BinaryTree<T> tree, Function<T, E> mapper) {
        if (tree.isEmpty()) {
            return new LinkedBinaryTreeWithFunctional<>();
        } else {
            return new LinkedBinaryTreeWithFunctional<>(
                mapper.apply(tree.getRootValue()),
                map(tree.getLeftChild(), mapper),
                map(tree.getRightChild(), mapper)
            );
        }
    }
}
