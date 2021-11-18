package test.m1graf2021;

import main.m1graf2021.UndirectedGraf;
import main.pw3.ChinesePostman;
import org.junit.Assert;
import org.junit.Test;

public class ChinesePostmanTest {
    @Test
    public void eulerianGraf(){
        UndirectedGraf g = new UndirectedGraf(2,3,0,1,3,0,1,2,0);
        ChinesePostman cp = new ChinesePostman(g);
        System.out.println(cp.countOddDegreeNodes());
        Assert.assertTrue(cp.isEulerian());
    }

    @Test
    public void semiEulerianGraf(){
        UndirectedGraf g = new UndirectedGraf(2,0,1,3,0,2,0);
        ChinesePostman cp = new ChinesePostman(g);
        System.out.println(cp.countOddDegreeNodes());
        Assert.assertTrue(cp.isSemiEulerian());
    }

    @Test
    public void nonEulerianGraf(){
        UndirectedGraf g = new UndirectedGraf(2,0,1,3,4,0,2,0,2,0);
        ChinesePostman cp = new ChinesePostman(g);
        System.out.println(cp.countOddDegreeNodes());
        Assert.assertTrue(cp.isNonEulerian());
    }
}
