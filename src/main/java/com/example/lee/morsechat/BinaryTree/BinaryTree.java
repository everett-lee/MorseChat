package com.example.lee.morsechat.BinaryTree;

import java.util.List;

public class BinaryTree {

    private static BinaryTree instance;
    private static Node root;
    private static MorseCode translator;
    private static Reader reader;
    private static Node liveNode;

    static {
        instance = new BinaryTree();
    }

    private BinaryTree() {
        this.root = new Node('\u0000');
        this.translator = new MorseCode();
        this.reader = new Reader();
        this.liveNode = this.root;
    }

    public static BinaryTree getInstance() {
        return instance;
    }

    public static Node getRoot() {
        return root;
    }

    public static void reset() {
        liveNode = root;
    }

    public static void buildTree(List<String> morseCode) {
        for (String codeString : morseCode) {
            insertNodes(codeString);
        }
    }

    public static void insertNodes(String codeString) {
        char value = translator.translate(codeString);
        Node currentNode = root;

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


    public static char treeTranslate(String codeString) {
        if (codeString.isEmpty()) {
            return '\u0000';
        }

        Node currentNode = root;

        for (char codeChar : codeString.toCharArray()) {
            currentNode = getNextNode(codeChar, currentNode);

            // return if search extends beyond tree
            if (currentNode == root) {
                return '\u0000';
            }
        }

        return currentNode.getValue();

    }

    public static Node getNextNode(char codeChar, Node currentNode) {
        if (codeChar == '.') {
            if (currentNode.getLeftChild() != null) {
                currentNode = currentNode.getLeftChild();
            } else {
                return root;
            }
        } else {
            if (currentNode.getRightChild() != null) {
                currentNode = currentNode.getRightChild();
            } else {
                return root;
            }
        }
        return currentNode;
    }

    public static Character traverseTreeDot() {
        liveNode = getNextNode('.', liveNode);
        return liveNode.getValue();
    }

    public static Character traverseTreeDash() {
        liveNode = getNextNode('-', liveNode);
        return liveNode.getValue();
    }

}