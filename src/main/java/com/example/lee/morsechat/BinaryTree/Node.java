package com.example.lee.morsechat.BinaryTree;

public class Node {
    private Node parent;
    private Node leftChild;
    private Node rightChild;
    private char value;

    public Node(char value) {
        this.value = value;
    }

    public void setParent(Node parent) {
        this.parent = parent;
    }

    public void setLeftChild(Node leftChild) {
        this.leftChild = leftChild;
    }

    public void setRightChild(Node rightChild) {
        this.rightChild = rightChild;
    }

    public Node getParent() {
        return this.parent;
    }

    public Node getLeftChild() {
        return this.leftChild;
    }

    public Node getRightChild() {
        return this.rightChild;
    }

    public char getValue() {
        return this.value;
    }
}