package com.dreamcode.app.data_layer.model;

import static org.junit.Assert.assertTrue;

import org.junit.Test;

public class NodeTest{



    @Test
    public void equals(){

        Node n1 = new Node("category",null);
        Node n2 = new Node("category",null);

        assertTrue(n1.equals(n2));
    }

    @Test
    public void addChild(){

        Node n1 = new Node("category",null);
        Node n2 = new Node("category",null);

        n1.addChild(n2);

        assertTrue(n1.children.get(0).equals(n2));
        assertTrue(n2.parent.equals(n1));
    }



}
