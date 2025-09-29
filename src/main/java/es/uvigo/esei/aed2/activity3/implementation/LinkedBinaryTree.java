package es.uvigo.esei.aed2.activity3.implementation;

import es.uvigo.esei.aed2.tree.binary.BinaryTree;
import es.uvigo.esei.aed2.tree.exceptions.EmptyTreeException;

import static java.util.Objects.requireNonNull;

public class LinkedBinaryTree<T> implements BinaryTree<T> {

    private LinkedBinaryTreeNode<T> rootNode;

    public LinkedBinaryTree() {
        this.rootNode = null;
    }

    public LinkedBinaryTree(T value) throws NullPointerException {
        this(new LinkedBinaryTreeNode<>(requireNonNull(value, "Null values cannot be saved in a binary tree.")));
    }

    public LinkedBinaryTree(T value, BinaryTree<T> leftChild, BinaryTree<T> rightChild) throws NullPointerException {
        this.rootNode = new LinkedBinaryTreeNode<>(requireNonNull(value, "Null values cannot be saved in a binary tree."), buildNodeFromTree(leftChild), buildNodeFromTree(rightChild));
    }

    private LinkedBinaryTree(LinkedBinaryTreeNode<T> root) {
        this.rootNode = root;
    }

    private LinkedBinaryTreeNode<T> buildNodeFromTree(BinaryTree<T> tree) {
        if (tree.isEmpty()) return null;

        return new LinkedBinaryTreeNode<>(tree.getRootValue(), tree.hasLeftChild() ? buildNodeFromTree(tree.getLeftChild()) : null, tree.hasLeftChild() ? buildNodeFromTree(tree.getRightChild()) : null);
    }

    // Métodos lanzan excepción
    @Override
    public T getRootValue() throws EmptyTreeException {
        if (this.isEmpty()) throw new EmptyTreeException("The tree has no values.");

        return this.rootNode.getValue();
    }

    @Override
    public void setRootValue(T value) throws EmptyTreeException, NullPointerException {
        if (this.isEmpty()) throw new EmptyTreeException("The tree has no values");

        this.rootNode.setValue(requireNonNull(value, "The value cannot be null"));
    }

    @Override
    public boolean contains(T value) {
        if (this.isEmpty()) return false;

        return value != null && contains(this.rootNode, value);
    }

    private boolean contains(LinkedBinaryTreeNode<T> node, T value) {
        if (node == null) return false;
        if (node.getValue().equals(value)) return true;
        return contains(node.getLeftChild(), value) || contains(node.getRightChild(), value);
    }

    @Override
    public boolean hasLeftChild() {
        return !this.isEmpty() && this.rootNode.hasLeftChild();
    }

    @Override
    public BinaryTree<T> getLeftChild() throws EmptyTreeException {
        if (this.isEmpty()) throw new EmptyTreeException("The tree has no values");

        if (this.hasLeftChild()) return new LinkedBinaryTree<>(this.rootNode.getLeftChild());
        else return new LinkedBinaryTree<>();
    }

    @Override
    public void setLeftChild(BinaryTree<T> leftChild) throws EmptyTreeException, NullPointerException {
        if (this.isEmpty()) throw new EmptyTreeException("The tree has no values");

        this.rootNode.setLeftChild(buildNodeFromTree(requireNonNull(leftChild, "Children cannot be null")));
    }

    @Override
    public void removeLeftChild() throws EmptyTreeException {
        if (this.isEmpty()) throw new EmptyTreeException("The tree has no values");

        this.rootNode.setLeftChild(null);
    }

    @Override
    public boolean hasRightChild() {
        return !this.isEmpty() && this.rootNode.hasRightChild();
    }

    @Override
    public BinaryTree<T> getRightChild() throws EmptyTreeException {
        if (this.isEmpty()) throw new EmptyTreeException("The tree has no values");

        if (this.hasLeftChild()) return new LinkedBinaryTree<>(this.rootNode.getRightChild());
        else return new LinkedBinaryTree<>();
    }

    @Override
    public void setRightChild(BinaryTree<T> rightChild) throws EmptyTreeException, NullPointerException {
        if (this.isEmpty()) throw new EmptyTreeException("The tree has no values");

        this.rootNode.setRightChild(buildNodeFromTree(requireNonNull(rightChild, "Children cannot be null")));
    }

    @Override
    public void removeRightChild() throws EmptyTreeException {
        if (this.isEmpty()) throw new EmptyTreeException("The tree has no values");

        this.rootNode.setRightChild(null);
    }

    @Override
    public void clear() {
        if (!this.isEmpty()) this.rootNode = null;
    }

    @Override
    public boolean isEmpty() {
        return this.rootNode == null;
    }
}
