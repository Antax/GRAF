package main.m1graf2021;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Graf{
    Map<Node, List<Edge>> adjEdList;

    public Graf() {
        adjEdList = new HashMap<>();
    }

    public Graf(int[] data){
        int numberOfNode=1;
        int i=0;
        while(i<data.length) {
            List<Edge> currentNodeEdges = new ArrayList<Edge>();
            while (data[i] != 0) {
                currentNodeEdges.add(new Edge(numberOfNode,data[i]));
                ++i;
            }
            adjEdList.put(new Node(numberOfNode), currentNodeEdges);
            ++numberOfNode;
        }
    }

    public String toString(){
        String ret="";
        return ret;
    }
}
