package com.example.lee.morsechat.BinaryTree;

import java.util.ArrayList;
import java.util.List;

public class BinaryTree {

    private Node root;
    private MorseCode translator;
    private Reader reader;
    private Node liveNode;

    public BinaryTree() {
        this.root = new Node('\u0000');
        this.translator = new MorseCode();
        this.reader = new Reader();
        this.liveNode = this.root;
    }

    public Node getRoot() {
        return this.root;
    }

    public void reset() {
        liveNode = this.root;
    }

    public void buildTree(List<String> morseCode) {
        for (String codeString : morseCode) {
            insertNodes(codeString);
        }
    }

    public void insertNodes(String codeString) {
        char value = translator.translate(codeString);
        Node currentNode = this.root;

        for (char codeChar : codeString.toCharArray()) {
            if (codeChar == '.') {
                if (currentNode.getLeftChild() == null) {
                    currentNode.setLeftChild(new Node(value));
                    currentNode = currentNode.getLeftChild();
                } else {
                    currentNode = currentNode.getLeftChild();
                }
            }

            if (codeChar == '-') {
                if (currentNode.getRightChild() == null) {
                    currentNode.setRightChild(new Node(value));
                    currentNode = currentNode.getRightChild();
                } else {
                    currentNode = currentNode.getRightChild();
                }
            }
        }
    }


    public char treeTranslate(String codeString) {
        if (codeString.isEmpty()) {
            return '\u0000';
        }

        Node currentNode = this.root;

        for (char codeChar: codeString.toCharArray()) {
            currentNode = getNextNode(codeChar, currentNode);

            // return if search extends beyond tree
            if (currentNode == this.root) {
                return '\u0000';
            }
        }

        return currentNode.getValue();

    }

    public void printNodeValue() {
        char codeChar = reader.getInput();
        Node currentNode = getNextNode(codeChar, this.root);
        System.out.println(currentNode.getValue());

        while (true) {
            codeChar = reader.getInput();

            // break if empty string entered or search extends beyond tree
            if (codeChar == '\u0000' || currentNode == this.root) {
                break;
            }
            currentNode = getNextNode(codeChar, currentNode);
            System.out.println(currentNode.getValue());
        }
    }

    public Node getNextNode(char codeChar, Node currentNode) {
        if (codeChar == '.') {
            if (currentNode.getLeftChild() != null) {
                currentNode = currentNode.getLeftChild();
            } else {
                return this.root;
            }
        } else {
            if (currentNode.getRightChild() != null) {
                currentNode = currentNode.getRightChild();
            } else {
                return this.root;
            }
        }
        return currentNode;
    }

    public Character traverseTreeDot() {
        liveNode = getNextNode('.', liveNode);
        return liveNode.getValue();
    }

    public Character traverseTreeDash() {
        liveNode = getNextNode('-', liveNode);
        return liveNode.getValue();
    }

}