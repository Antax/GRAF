package test.m1graf2021;

import main.m1graf2021.*;
import main.pw3.ChinesePostman;
import org.junit.Assert;
import org.junit.Test;

import java.util.List;
import java.util.Map;

public class ChinesePostmanTest {
    @Test
    public void eulerianGraf(){
        UndirectedGraf g = new UndirectedGraf(2,3,0,3,0,0);
        ChinesePostman cp = new ChinesePostman(g);
        System.out.println(cp.countOddDegreeNodes());
        Assert.assertTrue(cp.isEulerian());
    }

    @Test
    public void semiEulerianGraf(){
        UndirectedGraf g = new UndirectedGraf(2,0,3,0,0);
        ChinesePostman cp = new ChinesePostman(g);
        System.out.println(cp.countOddDegreeNodes());
        Assert.assertTrue(cp.isSemiEulerian());
    }

    @Test
    public void nonEulerianGraf(){
        UndirectedGraf g = new UndirectedGraf(2,0,3,4,0,0,0);
        ChinesePostman cp = new ChinesePostman(g);
        System.out.println(cp.countOddDegreeNodes());
        Assert.assertTrue(cp.isNonEulerian());
    }

    @Test
    public void eulerianPath(){
        UndirectedGraf g = new UndirectedGraf(2,6,0,3,6,8,0,4,6,7,0,5,0,6,0,0,8,0,0);
        System.out.println(g.toDotString());
        ChinesePostman cp = new ChinesePostman(g);
        List<Edge> res = cp.getEulerianPath(cp.graf);
        System.out.println(res);
    }

    @Test
    public void eulerianPathWithMultiEdges(){
        UndirectedGraf g = new UndirectedGraf(2,2,2,3,0,3,4,4,0,4,4,0);
        System.out.println(g.toDotString());
        ChinesePostman cp = new ChinesePostman(g);
        List<Edge> res = cp.getEulerianPath(cp.graf);
        System.out.println(res);
    }

    @Test
    public void eulerianPathSemi(){
        UndirectedGraf g = new UndirectedGraf(2,6,0,3,8,0,4,6,7,0,5,0,6,0,0,8,0,0);
        System.out.println(g.toDotString());
        ChinesePostman cp = new ChinesePostman(g);
        List<Edge> res = cp.getEulerianPath(cp.graf);
        System.out.println(res);
        System.out.println(g.toDotString());
    }

    @Test
    public void floydWarshall(){
        UndirectedGraf g = new UndirectedGraf(2,3,4,0,3,4,0,4,0,0);
        g.setEdgeWeight(1,2,8);
        g.setEdgeWeight(1,3,6);
        g.setEdgeWeight(1,4,1);

        g.setEdgeWeight(2,3,1);
        g.setEdgeWeight(2,4,2);

        g.setEdgeWeight(3,4,9);
        System.out.println(g.toDotString());

        ChinesePostman cp = new ChinesePostman(g);
        Map<Pair<Node,Node>, Pair<Integer, Node>> floydWarshallResult = cp.floydWarshall();

        System.out.println(cp.shortestPathBetween2Nodes(floydWarshallResult,3,1));
    }

    @Test
    public void pairwiseMatchingInOrder(){
        UndirectedGraf g = new UndirectedGraf();
        g.addEdge(1,6,1);
        g.addEdge(1,4,2);

        g.addEdge(6,3,3);
        g.addEdge(6,5,8);

        g.addEdge(3,2,2);

        g.addEdge(4,5,6);
        g.addEdge(4,7,4);

        g.addEdge(5,2,5);
        g.addEdge(5,8,8);

        g.addEdge(2,9,2);

        g.addEdge(7,8,2);

        g.addEdge(8,9,2);

        ChinesePostman cp = new ChinesePostman(g);
        Pair<UndirectedGraf,List<Edge>> res = cp.getChinesePostmanSolution(ChinesePostman.Strategy.INORDER);
    }

    @Test
    public void pairwiseMatchingGreedy(){
        UndirectedGraf g = new UndirectedGraf();
        g.addEdge(1,6,1);
        g.addEdge(1,4,2);

        g.addEdge(6,3,3);
        g.addEdge(6,5,8);

        g.addEdge(3,2,2);

        g.addEdge(4,5,6);
        g.addEdge(4,7,4);

        g.addEdge(5,2,5);
        g.addEdge(5,8,8);

        g.addEdge(2,9,2);

        g.addEdge(7,8,2);

        g.addEdge(8,9,2);

        ChinesePostman cp = new ChinesePostman(g);
        Pair<UndirectedGraf,List<Edge>> res = cp.getChinesePostmanSolution(ChinesePostman.Strategy.GREEDY);
    }

    @Test
    public void pairwiseMatchingBestPath(){
        UndirectedGraf g = new UndirectedGraf();
        g.addEdge(1,6,1);
        g.addEdge(1,4,2);

        g.addEdge(6,3,3);
        g.addEdge(6,5,8);

        g.addEdge(3,2,2);

        g.addEdge(4,5,6);
        g.addEdge(4,7,4);

        g.addEdge(5,2,5);
        g.addEdge(5,8,8);

        g.addEdge(2,9,2);

        g.addEdge(7,8,2);

        g.addEdge(8,9,2);
        System.out.println(g.toDotString());

        ChinesePostman cp = new ChinesePostman(g);
        Pair<UndirectedGraf,List<Edge>> res = cp.getChinesePostmanSolution(ChinesePostman.Strategy.OPTIMAL);
        System.out.println(res.getFirst().toDotString());
        System.out.println(res.getSecond());
    }
}
