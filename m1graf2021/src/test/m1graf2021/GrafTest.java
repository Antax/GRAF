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
        Assert.assertEquals("",g.toString());
    }
}
