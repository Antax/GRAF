package test.m1graf2021;

import main.m1graf2021.*;
import main.pw3.ChinesePostman;
import org.junit.Assert;
import org.junit.Test;

import java.util.List;

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
        List<Edge> res = cp.getEulerianPath();
        System.out.println(res);
    }

    @Test
    public void eulerianPathWithMultiEdges(){
        UndirectedGraf g = new UndirectedGraf(2,2,2,3,0,3,4,4,0,4,4,0);
        System.out.println(g.toDotString());
        ChinesePostman cp = new ChinesePostman(g);
        List<Edge> res = cp.getEulerianPath();
        System.out.println(res);
    }

    @Test
    public void eulerianPathSemi(){
        UndirectedGraf g = new UndirectedGraf(2,6,0,3,8,0,4,6,7,0,5,0,6,0,0,8,0,0);
        System.out.println(g.toDotString());
        ChinesePostman cp = new ChinesePostman(g);
        List<Edge> res = cp.getEulerianPath();
        System.out.println(res);
        System.out.println(g.toDotString());

    }
}
