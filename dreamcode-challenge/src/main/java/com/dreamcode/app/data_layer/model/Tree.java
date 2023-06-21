package com.dreamcode.app.data_layer.model;

import java.util.ArrayList;

public class Tree {

    Node root;

    public Tree() {

        root = new Node("root", new ArrayList<String>() {
            {
                add("default keyword 1");
                add("default keyword 2");
            }
        });
    }

    public void addNode(Node nodeToAdd, String categoryToFind) {

        Node parentNode = findNode(root, categoryToFind);
        parentNode.addChild(nodeToAdd);

    }

    private Node findNode(Node node, String categoryToFind) {

        if (node == null) {
            return null;
        }

        if (node.category.equals(categoryToFind)) {
            return node;
        }

        //Try to find node on the children of current node
        for (Node child : node.children) {
            Node aux = null;
            if ((aux = findNode(child, categoryToFind)) != null) {
                return aux;
            }

        }

        //If not found return null
        return null;
    }

    public int getLevelOfCategory(String categoryToFind){

        int level = 0;
       
        Node parentNode = findNode(root,categoryToFind).parent;

        //Find parent of parent and everytime you find one parent different
        //to null, add one level
        while(parentNode!=null){
            level++;
            parentNode = findNode(root,parentNode.category).parent;
        }
        return level;


    }


    public ArrayList<String> getKeywordsOfBranch(String categoryToFind){

        ArrayList<String> output = new ArrayList<>();

        //Add all keywords of found node to output.
        Node actualNode = findNode(root,categoryToFind);
        output.addAll(actualNode.keywords);

        Node parentNode = actualNode.parent;

        //Find parent of parent and everytime you find one parent different
        //to null, add all keywords to ouptut.
        while(parentNode !=null){
            output.addAll(parentNode.keywords);
            parentNode = findNode(root,parentNode.category).parent;
        }
        return output;

    }









}
