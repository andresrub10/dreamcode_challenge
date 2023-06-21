package com.dreamcode.app.data_layer.model;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;

public class TreeTest{


    Tree tree;

    @Before
    public void setUp(){

        tree = new Tree();
    }

    @Test
    public void addNode(){

        //Begin: Testing that we can add 1 node
        ArrayList<String> kewords = new ArrayList<String>(){{

            add("category1");
            add("cat1");
        }};
        Node n1 = new Node("category1",kewords);

        tree.addNode(n1,"root");

        assertTrue(tree.root.children.get(0).equals(n1));
        //------------------------------------------------------------


        //Begin: Testing that we can add 1 node with depth 2
        ArrayList<String> kewords11 = new ArrayList<String>(){{

            add("category11");
            add("cat11");
        }};
        Node n11 = new Node("category11",kewords11);

        tree.addNode(n11,"category1");

        assertTrue(tree.root.children.get(0).children.get(0).equals(n11));

        //Verify is not the same as the first level
        assertFalse(tree.root.children.get(0).children.get(0).equals(n1));
        //------------------------------------------------------------
    }

    @Test
    public void getLevelOfCategory(){

        Node n1 = new Node("category1",null);
        tree.addNode(n1,"root");

        Node n2 = new Node("category2",null);
        tree.addNode(n2,"root");
        
        Node n11 = new Node("category11",null);
        tree.addNode(n11,"category1");

        assertEquals(tree.getLevelOfCategory("root"),0);
        assertEquals(tree.getLevelOfCategory("category1"),1);
        assertEquals(tree.getLevelOfCategory("category2"),1);
        assertEquals(tree.getLevelOfCategory("category11"),2);

    } 

    @Test
    public void getKeywordsOfBranch(){

        //Creating 3 nodes. On 2 branches
        //branch 1  = root->category1->category11
        //branch 2 = root->category2
        ArrayList<String> kewords1 = new ArrayList<String>(){{

            add("category1");
            add("cat1");
        }};
        Node n1 = new Node("category1",kewords1);

        tree.addNode(n1,"root");

        ArrayList<String> kewords2 = new ArrayList<String>(){{

            add("category2");
            add("cat2");
        }};
        Node n2 = new Node("category2",kewords2);
        tree.addNode(n2,"root");

        ArrayList<String> kewords11 = new ArrayList<String>(){{

            add("category11");
            add("cat11");
        }};
        Node n11 = new Node("category11",kewords11);

        tree.addNode(n11,"category1");
        //--------------------------------------------------------------

        ArrayList<String> toTest = new ArrayList<String>();

        toTest.addAll(n11.keywords);
        toTest.addAll(n1.keywords);
        toTest.addAll(tree.root.keywords);

        //Testing the returned kewords match the correct branch
        assertEquals(tree.getKeywordsOfBranch("category11"),toTest);

        ArrayList<String> toTestFalse = new ArrayList<String>();
        //FLipped n1 and n11 order so it wont match
        toTestFalse.addAll(n1.keywords);
        toTestFalse.addAll(n11.keywords);
        toTestFalse.addAll(tree.root.keywords);

        assertNotEquals(tree.getKeywordsOfBranch("category11"),toTestFalse);
        //--------------------------------------------------------------

        //Testing another branch
        ArrayList<String> toTestAnotherBranch = new ArrayList<String>();

        toTestAnotherBranch.addAll(n2.keywords);
        toTestAnotherBranch.addAll(tree.root.keywords);

        //Testing the returned kewords match the correct branch
        assertEquals(tree.getKeywordsOfBranch("category2"),toTestAnotherBranch);

        ArrayList<String> toTestFalseAnotherBranch = new ArrayList<String>();
        toTestFalseAnotherBranch.addAll(n1.keywords);
        toTestFalseAnotherBranch.addAll(n11.keywords);
        toTestFalseAnotherBranch.addAll(tree.root.keywords);

        assertNotEquals(tree.getKeywordsOfBranch("category11"),toTestFalseAnotherBranch);
        //--------------------------------------------------------------
    }




}
