package test.m1graf2021;

import main.m1graf2021.*;
import org.junit.Assert;
import org.junit.Ignore;
import org.junit.Test;

import java.io.FileNotFoundException;
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
        Assert.assertEquals("source node->target node",e.toString());
    }

    @Test
    public void testEdgeCreateNewNodes(){
        Edge e= new Edge(1,2);
        Assert.assertEquals("1->2",e.toString());
    }

    @Test
    public void testEdgeCreateWeight(){
        Node s= new Node(1,"source node");
        Node t= new Node(2,"target node");
        Edge e= new Edge(s,t,10);
        Assert.assertEquals("source node->target node(10)",e.toString());
    }

    @Test
    public void testGrafCreateEmpty(){
        Graf g=new Graf();
        Assert.assertEquals("",g.toString());
    }

    @Test
    public void testGrafCreateArray(){
        int[] a={2, 4, 0, 0, 6, 0, 2, 3, 5, 8, 0, 0, 4, 7, 0, 3, 0, 7, 0};
        Graf g=new Graf(a);
        Assert.assertEquals(true,g.existsEdge(1,2));
        Assert.assertEquals(true,g.existsEdge(1,4));
        Assert.assertEquals(true,g.existsEdge(4,5));
        Assert.assertEquals(false,g.existsEdge(2,1));
    }

    @Test
    public void testGrafCreateVarArgs(){
        Graf g=new Graf(2, 4, 0, 0, 6, 0, 2, 3, 5, 8, 0, 0, 4, 7, 0, 3, 0, 7, 0);
        Assert.assertEquals(true,g.existsEdge(1,2));
        Assert.assertEquals(true,g.existsEdge(1,4));
        Assert.assertEquals(true,g.existsEdge(4,5));
        Assert.assertEquals(false,g.existsEdge(2,1));
    }

    @Test
    public void testNbNodes(){
        int[] a={2, 4, 0, 0, 6, 0, 2, 3, 5, 8, 0, 0, 4, 7, 0, 3, 0, 7, 0};
        UndirectedGraf g=new UndirectedGraf(a);
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
    public void testExistsNodeEmptyGraf(){
        Graf g=new Graf();
        Assert.assertFalse(g.existsNode(5));
        Assert.assertFalse(g.existsNode(new Node(5)));
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
        System.out.println(g.toDotString());
        List<Edge>l=g.getInEdges(new Node(4));
        List<Edge> expected=new ArrayList<Edge>();
        expected.add(new Edge(6,4));
        expected.add(new Edge(1,4));
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

    @Test
    public void testSuccessors(){
        int[] a={2, 4, 0, 0, 6, 0, 4, 2, 3, 5, 8, 0, 0, 4, 7, 0, 3, 0, 7, 0};
        Graf g=new Graf(a);
        List<Node>expected=new ArrayList<>();
        expected.add(new Node(4));
        expected.add(new Node(2));
        expected.add(new Node(3));
        expected.add(new Node(5));
        expected.add(new Node(8));
        Collections.sort(expected);

        List<Node>successorsFrom4=g.getSuccessors(4);
        Collections.sort(successorsFrom4);
        Assert.assertEquals(expected,successorsFrom4);
    }

    @Test
    public void testGetAllNodes(){
        Graf g=new Graf();
        g.addNode(1);
        g.addNode(4);
        g.addNode(7);
        g.addNode(32);

        List<Node>expected=new ArrayList<>();
        expected.add(new Node(1));
        expected.add(new Node(4));
        expected.add(new Node(7));
        expected.add(new Node(32));
        Collections.sort(expected);

        List<Node>allNodes=g.getAllNodes();
        Collections.sort(allNodes);

        Assert.assertEquals(expected,allNodes);
    }

    @Test
    public void testAdjacent(){
        int[] a={2, 4, 0, 0, 6, 0, 4, 2, 3, 5, 8, 0, 0, 4, 7, 0, 3, 0, 7, 0};
        Graf g=new Graf(a);
        Assert.assertTrue(g.adjacent(4,8));
        Assert.assertTrue(g.adjacent(8,4));
        Assert.assertTrue(g.adjacent(1,1));
        Assert.assertFalse(g.adjacent(3,5));
    }

    @Test
    public void testLargestNodeId(){
        Graf g=new Graf();

        Assert.assertEquals(g.largestNodeId(),-1);

        g.addNode(1);
        g.addNode(32);
        g.addNode(4);
        g.addNode(7);


        Assert.assertEquals(g.largestNodeId(),32);
    }

    @Test
    public void testOutDegree(){
        int[] a={2, 4, 0, 0, 6, 0, 4, 2, 3, 5, 8, 0, 0, 4, 7, 0, 3, 0, 7, 0};
        Graf g=new Graf(a);
        Assert.assertEquals(-1,g.outDegree(19));
        Assert.assertEquals(5,g.outDegree(4));
    }

    @Test
    public void testInDegree(){
        int[] a={2, 4, 0, 0, 6, 0, 4, 2, 3, 5, 8, 0, 0, 4, 7, 0, 3, 0, 7, 0};
        Graf g=new Graf(a);
        Assert.assertEquals(-1,g.inDegree(19));
        Assert.assertEquals(3,g.inDegree(4));
    }

    @Test
    public void testDegree(){
        int[] a={2, 4, 0, 0, 6, 0, 4, 2, 3, 5, 8, 0, 0, 4, 7, 0, 3, 0, 7, 0};
        Graf g=new Graf(a);
        Assert.assertEquals(-1,g.degree(19));
        Assert.assertEquals(7,g.degree(4));
    }

    @Test
    public void testToDotString(){
        int[] a={2, 4, 0, 0, 6, 0, 2, 3, 5, 8, 0, 0, 4, 7, 0, 3, 0, 7, 0};
        Graf g=new Graf(a);
        g.setEdgeWeight(1,4,5);
        String expected="digraph {\n" +
                "1 -> 2;\n"+
                "1 -> 4[len=5,label=5];\n"+
                "2;\n"+
                "3 -> 6;\n"+
                "4 -> 2, 3, 5, 8;\n"+
                "5;\n"+
                "6 -> 4, 7;\n"+
                "7 -> 3;\n"+
                "8 -> 7;\n"+
                "}";
        Assert.assertEquals(expected,g.toDotString());
        System.out.println(g.toDotString());
    }

    /*
    @Test
    public void testToDotFile() throws FileNotFoundException {
        int[] a={3, 4, 0, 0, 6, 0, 2, 3, 5, 8, 0, 0, 4, 7, 0, 3, 0, 7, 0};
        Graf g=new Graf(a);
        g.toDotFile("\\D:\\M1\\GRAF\\test.dot");
    }*/

    @Test
    public void testDotToGraph(){
        Graf graphFromDot=new Graf("\\D:\\M1\\GRAF\\test.dot");
        List<Edge> edgesFromDot=graphFromDot.getAllEdges();
        Collections.sort(edgesFromDot);
        List<Node> nodesFromDot=graphFromDot.getAllNodes();
        Collections.sort(nodesFromDot);


        int[] a={2,3,0,2,3,0};
        Graf expected=new Graf(a);
        expected.setEdgeWeight(1,2,4);
        expected.setEdgeWeight(1,3,6);
        expected.setEdgeWeight(2,2,3);
        expected.setEdgeWeight(2,3,8);
        List<Edge> edgesExpected=expected.getAllEdges();
        Collections.sort(edgesExpected);
        List<Node> nodesExpected=expected.getAllNodes();
        Collections.sort(nodesExpected);

        Assert.assertEquals(edgesExpected,edgesFromDot);
        Assert.assertEquals(nodesExpected,nodesFromDot);
        Assert.assertEquals(8,graphFromDot.getEdge(new Edge(2,3)).weight());
    }

    @Test
    public void testToAdjMatrix(){
        int[] a={3, 4, 4, 0, 0, 6, 0, 2, 3, 5, 8, 0, 0, 4, 7, 0, 3, 0, 7, 0};
        Graf g=new Graf(a);

        int[][]expected=new int[g.nbNodes()][g.nbNodes()];
        expected[0]=new int[]{0,0,1,2,0,0,0,0};
        expected[1]=new int[]{0,0,0,0,0,0,0,0};
        expected[2]=new int[]{0,0,0,0,0,1,0,0};
        expected[3]=new int[]{0,1,1,0,1,0,0,1};
        expected[4]=new int[]{0,0,0,0,0,0,0,0};
        expected[5]=new int[]{0,0,0,1,0,0,1,0};
        expected[6]=new int[]{0,0,1,0,0,0,0,0};
        expected[7]=new int[]{0,0,0,0,0,0,1,0};

        Assert.assertArrayEquals(expected,g.toAdjMatrix());
    }

    @Test
    public void testToSuccessorArray(){
        int[] a={3, 4, 4, 0, 0, 6, 0, 2, 3, 5, 8, 0, 0, 4, 7, 0, 3, 0, 7, 0};
        Graf g=new Graf(a);
        Assert.assertArrayEquals(a,g.toSuccessorArray());
    }

    @Test
    public void testReverseGraf(){
        Graf g=new Graf();
        g.addEdge(1,1);
        g.addEdge(1,2);
        g.addEdge(1,4);
        g.addEdge(2,3);
        g.addEdge(3,4);
        g.addEdge(4,4);
        Graf gReverse=g.getReverse();
        List<Node>nodesReverse=gReverse.getAllNodes();
        Collections.sort(nodesReverse);
        List<Edge>edgesReverse=gReverse.getAllEdges();
        Collections.sort(edgesReverse);

        Graf gExpected=new Graf();
        gExpected.addEdge(1,1);
        gExpected.addEdge(2,1);
        gExpected.addEdge(4,1);
        gExpected.addEdge(3,2);
        gExpected.addEdge(4,3);
        gExpected.addEdge(4,4);
        List<Node>nodesExpected=gExpected.getAllNodes();
        Collections.sort(nodesExpected);
        List<Edge>edgesExpected=gExpected.getAllEdges();
        Collections.sort(edgesExpected);

        Assert.assertEquals(nodesExpected,nodesReverse);
        Assert.assertEquals(edgesExpected,edgesReverse);
    }

    @Test
    public void testBFS(){
        int[] a={2, 4, 0, 0, 6, 0, 2, 3, 5, 8, 0, 0, 4, 7, 0, 3, 0, 7, 0};
        Graf g=new Graf(a);
        System.out.println(g.toDotString());
        System.out.println(g.getBFS().toString());
    }

    @Test
    public void testDFS(){
        int[] a={2, 4, 0, 0, 6, 0, 2, 3, 5, 8, 0, 0, 4, 7, 0, 3, 0, 7, 0};
        Graf g=new Graf(a);
        System.out.println(g.toDotString());
        System.out.println(g.getDFS().toString());
    }

    @Test
    public void testTransitiveClosureWithMultiAndSelfEdges(){
        Graf g=new Graf(1,2,3,0,4,4,0,4,0,5,6,0,0,0);
        System.out.println(g.toDotString());
        Graf gExpected=new Graf(2,3,4,5,6,0,4,5,6,0,4,5,6,0,5,6,0,0,0);
        System.out.println(gExpected.toDotString());

        Graf gTransitiveClosure=g.getTransitiveClosure();
        List<Node>nodesTransitiveClosure=gTransitiveClosure.getAllNodes();
        Collections.sort(nodesTransitiveClosure);
        List<Edge>edgesTransitiveClosure=gTransitiveClosure.getAllEdges();
        Collections.sort(edgesTransitiveClosure);

        List<Node>nodesExpected=gExpected.getAllNodes();
        Collections.sort(nodesExpected);
        List<Edge>edgesExpected=gExpected.getAllEdges();
        Collections.sort(edgesExpected);

        Assert.assertEquals(nodesExpected,nodesTransitiveClosure);
        Assert.assertEquals(edgesExpected,edgesTransitiveClosure);
    }
}
