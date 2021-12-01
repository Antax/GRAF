package main.pw3;
import main.m1graf2021.*;

import java.util.*;

public class ChinesePostman {
    private UndirectedGraf graf;

    public ChinesePostman(UndirectedGraf graf){
        this.graf = graf;
    }

    public int countOddDegreeNodes(){
        int res=0;
        for (Node n : graf.getAllNodes()){
            if((graf.degree(n))%2 != 0){
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

    public List<Edge> getEulerianPath(){
        if(!isNonEulerian()){
            List<List<Edge>> subCircuits = new ArrayList<>();
            Map<Node, List<Edge>> edgesToVisit = new HashMap<>();
            for (Node n : graf.getAllNodes()){
                edgesToVisit.put(n,graf.getOutEdges(n));
            }
            List<Edge> res=new ArrayList<>();
            List<Node> stackOfNodes = graf.getAllNodes();
            Collections.sort(stackOfNodes);

            //change priority of nodes
            if(isSemiEulerian()){
                for (Node n : stackOfNodes){
                    if (graf.degree(n)%2 != 0){
                        stackOfNodes.remove(n);
                        stackOfNodes.add(0,n);
                        break;
                    }
                }
            }

            System.out.println(stackOfNodes);

            while(!stackOfNodes.isEmpty()){
                List<Edge> newSubCircuit = new ArrayList<>();
                Node currentNode = stackOfNodes.get(0);
                boolean hasChanged = true;
                while(hasChanged){
                    hasChanged = false;
                    for(Edge e : edgesToVisit.get(currentNode)){
                        edgesToVisit.get(currentNode).remove(e);
                        edgesToVisit.get(e.to()).remove(e.getSymmetric());
                        newSubCircuit.add(e);
                        currentNode=e.to();
                        hasChanged = true;
                        break;
                    }
                    if(edgesToVisit.get(currentNode).size()==0){
                        stackOfNodes.remove(currentNode);
                    }
                }
                if(!newSubCircuit.isEmpty()){
                    subCircuits.add(newSubCircuit);
                }
            }
            if(subCircuits.size()==1){
                return subCircuits.get(0);
            }

            res.addAll(subCircuits.get(0));

            for(int i=1;i<subCircuits.size();++i){
                for(int j=0;j<res.size();++j){
                    if(res.get(j).to().equals(subCircuits.get(i).get(0).from())){
                        for (int k=0; k<subCircuits.get(i).size(); ++k){
                            ++j;
                            res.add(j,subCircuits.get(i).get(k));
                        }
                        break;
                    }
                }
            }
            return res;
        }
        return null;
    }
}
