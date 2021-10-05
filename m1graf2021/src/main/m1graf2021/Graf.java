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

    public Node getNode(int id){
        for (Map.Entry<Node, List<Edge>> node : adjEdList.entrySet()) {
            if(node.getKey().getId()==id){
                return node.getKey();
            }
        }
        return null;
    }

    public Graf(int[] data){
        adjEdList = new HashMap<>();
        int numberOfNode=1;
        int i=0;
        while(i<data.length) {
            //System.out.println("a");
            List<Edge> currentNodeEdges = new ArrayList<Edge>();
            Node currentNode=getNode(numberOfNode);
            if(currentNode==null) {
                currentNode = new Node(numberOfNode);
                adjEdList.put(currentNode, new ArrayList<Edge>());
            }
            //System.out.println("b");
            while (data[i]!=0){
                Node target=getNode(data[i]);
                if(target==null){
                    target=new Node (data[i]);
                    adjEdList.put(target,new ArrayList<Edge>());
                }
                currentNodeEdges.add(new Edge(currentNode,target));
                ++i;
            }
            //System.out.println("c");
            for(int e=0;e<currentNodeEdges.size();++e){
                adjEdList.get(currentNode).add(currentNodeEdges.get(e));
                System.out.print(this);
            }
            ++numberOfNode;
            ++i;
        }
    }

    public String toString(){
        String ret="";
        for (Map.Entry<Node, List<Edge>> node : adjEdList.entrySet()) {
            ret+=node.getKey().toString() + " : ";
            if(node.getValue()!=null){
                int numberOfEdges=node.getValue().size();
                for (int i=0;i<numberOfEdges;++i){
                    ret+=node.getValue().get(i).toString();
                }
            }
            ret+="\n";
        }
        return ret;
    }
}
