package com.dreamcode.app.data_layer.model;

import java.util.ArrayList;

class Node {

    public String category;
    public ArrayList<String> keywords;
    public Node parent;
    public ArrayList<Node> children;

    public Node (String category, ArrayList<String> keywords){
        
        this.category = category;
        this.keywords = keywords;
        this.parent = null;
        this.children = new ArrayList<Node>();

    }

    public void addChild(Node toAdd){

        toAdd.parent = this;
        children.add(toAdd);

    }

    public boolean equals(Node node){

        return category.equals(node.category);

    }
        

}
