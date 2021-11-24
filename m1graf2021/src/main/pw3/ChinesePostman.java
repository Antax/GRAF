package main.pw3;
import main.m1graf2021.*;

import java.util.ArrayList;
import java.util.List;

public class ChinesePostman {
    private UndirectedGraf graf;

    public ChinesePostman(UndirectedGraf graf){
        this.graf = graf;
    }

    public int countOddDegreeNodes(){
        int res=0;
        for (Node n : graf.getAllNodes()){
            if((graf.degree(n))/2%2 != 0){
                res++;
            }
        }
        return res;
    }

    public boolean isEulerian(){
        return countOddDegreeNodes()==0;
    }

    public boolean isSemiEulerian(){
        return countOddDegreeNodes()==2;
    }

    public boolean isNonEulerian(){
        return countOddDegreeNodes()>2;
    }

    public List<Edge> getEulerianTrail(){
        //Node startingNode = graf.getAllNodes();
        return getEulerianTrailFromNode();
    }

    public List<Edge> getEulerianTrailFromNode(/*Node startingNode*/){
        return new ArrayList<>();
    }
}
