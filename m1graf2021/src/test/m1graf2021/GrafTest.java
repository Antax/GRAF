package test.m1graf2021;

import main.m1graf2021.*;
import org.junit.Assert;
import org.junit.Ignore;
import org.junit.Test;

public class GrafTest {
    @Test
    public void testNodeCreate() {
        Node n = new Node(5);
        Assert.assertEquals("5",n.toString());
    }

    @Test
    public void testNodeCreateName() {
        Node n = new Node(5,"Node 5");
        Assert.assertEquals("Node 5",n.toString());
    }

    @Test
    public void testEdgeCreate(){
        Node s= new Node(1,"source node");
        Node t= new Node(2,"target node");
        Edge e= new Edge(s,t);
        Assert.assertEquals("[source node-target node]",e.toString());
    }

    @Test
    public void testEdgeCreateNewNodes(){
        Edge e= new Edge(1,2);
        Assert.assertEquals("[1-2]",e.toString());
    }

    @Test
    public void testEdgeCreateWeight(){
        Node s= new Node(1,"source node");
        Node t= new Node(2,"target node");
        Edge e= new Edge(s,t,10);
        Assert.assertEquals("[source node-target node](10)",e.toString());
    }

    @Test
    public void testGrafCreateEmpty(){
        Graf g=new Graf();
        Assert.assertEquals("",g.toString());
    }

    @Test
    public void testGrafCreate(){
        int[] a={2, 4, 0, 0, 6, 0, 2, 3, 5, 8, 0, 0, 4, 7, 0, 3, 0, 7, 0};
        Graf g=new Graf(a);
        Assert.assertEquals(true,g.existsEdge(1,2));
        Assert.assertEquals(true,g.existsEdge(1,4));
        Assert.assertEquals(true,g.existsEdge(4,5));
        Assert.assertEquals(false,g.existsEdge(2,1));
    }

    @Test
    public void testNbNodes(){
        int[] a={2, 4, 0, 0, 6, 0, 2, 3, 5, 8, 0, 0, 4, 7, 0, 3, 0, 7, 0};
        Graf g=new Graf(a);
        Assert.assertEquals(8,g.nbNodes());
    }

    @Test
    public void testExistsNode(){
        int[] a={2, 4, 0, 0, 6, 0, 2, 3, 5, 8, 0, 0, 4, 7, 0, 3, 0, 7, 0};
        Graf g=new Graf(a);
        Assert.assertTrue(g.existsNode(5));
        Assert.assertTrue(g.existsNode(new Node(5)));
        Assert.assertFalse(g.existsNode(9));
        Assert.assertFalse(g.existsNode(new Node(9)));
    }

    @Test
    public void testAddNode(){
        Graf g=new Graf();
        g.addNode(5);
        Node n=new Node(4);
        g.addNode(n);
        Assert.assertTrue(g.existsNode(4));
        Assert.assertTrue(g.existsNode(5));
    }

    @Test
    public void testExistsEdge(){
        int[] a={2, 4, 0, 0, 6, 0, 2, 3, 5, 8, 0, 0, 4, 7, 0, 3, 0, 7, 0};
        Graf g=new Graf(a);
        Assert.assertTrue(g.existsEdge(1,2));
        Assert.assertFalse(g.existsEdge(1,5));
        Node n1=new Node(1);
        Node n2=new Node(2);
        Node n5=new Node(5);
        Assert.assertTrue(g.existsEdge(n1,n2));
        Assert.assertFalse(g.existsEdge(n1,n5));
        Edge e1=new Edge(1,2);
        Edge e2=new Edge(1,5);
        Assert.assertTrue(g.existsEdge(e1));
        Assert.assertFalse(g.existsEdge(e2));
    }
}
