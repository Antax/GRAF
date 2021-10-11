package test.m1graf2021;

import main.m1graf2021.*;
import org.junit.Assert;
import org.junit.Ignore;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

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

    @Test
    public void testGetSymetric(){
        Edge e= new Edge(1,4);
        Assert.assertTrue(e.getSymmetric().equals(new Edge(4,1)));
    }

    @Test
    public void isSelfLoop(){
        Edge e1= new Edge(1,4);
        Edge e2= new Edge(3,3);
        Assert.assertFalse(e1.isSelfLoop());
        Assert.assertTrue(e2.isSelfLoop());
    }

    @Test
    public void testNbEdges(){
        int[] a={2, 4, 0, 0, 6, 0, 2, 3, 5, 8, 0, 0, 4, 7, 0, 3, 0, 7, 0};
        Graf g=new Graf(a);
        Assert.assertEquals(11,g.nbEdges());
    }

    @Test
    public void testRemoveEdge(){
        int[] a={2, 4, 0, 0, 6, 0, 2, 3, 5, 8, 0, 0, 4, 7, 0, 3, 0, 7, 0};
        Graf g=new Graf(a);
        Node n1=new Node(1);
        Node n2=new Node(2);
        Assert.assertTrue(g.existsEdge(n1,n2));
        g.removeEdge(n1,n2);
        Assert.assertFalse(g.existsEdge(n1,n2));

        Assert.assertTrue(g.existsEdge(1,4));
        g.removeEdge(1,4);
        Assert.assertFalse(g.existsEdge(1,4));

        Edge e=new Edge(3,6);
        Assert.assertTrue(g.existsEdge(e));
        g.removeEdge(e);
        Assert.assertFalse(g.existsEdge(e));
    }

    @Test
    public void testGetOutEdges(){
        int[] a={2, 4, 0, 0, 6, 0, 2, 3, 5, 8, 0, 0, 4, 7, 0, 3, 0, 7, 0};
        Graf g=new Graf(a);
        List<Edge>l=g.getOutEdges(new Node(1));
        List<Edge> expected=new ArrayList<Edge>();
        expected.add(new Edge(1,2));
        expected.add(new Edge(1,4));
        Assert.assertEquals(expected,l);
    }

    @Test
    public void testGetInEdges(){
        int[] a={2, 4, 0, 0, 6, 0, 2, 3, 5, 8, 0, 0, 4, 7, 0, 3, 0, 7, 0};
        Graf g=new Graf(a);
        List<Edge>l=g.getInEdges(new Node(4));
        List<Edge> expected=new ArrayList<Edge>();
        expected.add(new Edge(6,4));
        expected.add(new Edge(1,4));
        Collections.sort(l);
        Collections.sort(expected);
        Assert.assertEquals(expected,l);
    }

    @Test
    public void testGetAllEdges(){
        int[] a={2, 4, 0, 0, 6, 0, 2, 3, 5, 8, 0, 0, 4, 7, 0, 3, 0, 7, 0};
        Graf g=new Graf(a);
        List<Edge>l=g.getAllEdges();
        List<Edge> expected=new ArrayList<Edge>();
        expected.add(new Edge(1,2));
        expected.add(new Edge(1,4));
        expected.add(new Edge(3,6));
        expected.add(new Edge(4,2));
        expected.add(new Edge(4,3));
        expected.add(new Edge(4,5));
        expected.add(new Edge(4,8));
        expected.add(new Edge(6,4));
        expected.add(new Edge(6,7));
        expected.add(new Edge(7,3));
        expected.add(new Edge(8,7));
        Collections.sort(l);
        Collections.sort(expected);
        Assert.assertEquals(expected,l);
    }

    @Test
    public void testRemoveNode(){
        int[] a={2, 4, 0, 0, 6, 0, 2, 3, 5, 8, 0, 0, 4, 7, 0, 3, 0, 7, 0};
        Graf g=new Graf(a);
        g.removeNode(4);
        Assert.assertFalse(g.existsNode(4));
        Assert.assertFalse(g.existsEdge(1,4));
        Assert.assertFalse(g.existsEdge(6,4));
        Assert.assertFalse(g.existsEdge(4,2));
        Assert.assertFalse(g.existsEdge(4,3));
        Assert.assertFalse(g.existsEdge(4,5));
        Assert.assertFalse(g.existsEdge(4,8));
    }

    @Test
    public void testGetIncidentEdges(){
        int[] a={2, 4, 0, 0, 6, 0, 4, 2, 3, 5, 8, 0, 0, 4, 7, 0, 3, 0, 7, 0};
        Graf g=new Graf(a);
        List<Edge>l=g.getIncidentEdges(4);
        List<Edge>expected=new ArrayList<>();
        expected.add(new Edge(1,4));
        expected.add(new Edge(6,4));
        expected.add(new Edge(4,4));
        expected.add(new Edge(4,2));
        expected.add(new Edge(4,3));
        expected.add(new Edge(4,5));
        expected.add(new Edge(4,8));
        Collections.sort(l);
        Collections.sort(expected);
        Assert.assertEquals(expected,l);
    }
}
