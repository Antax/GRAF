package main.pw3;
import main.m1graf2021.*;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class ChinesePostman {
    private UndirectedGraf graf;

    public ChinesePostman(UndirectedGraf graf){
        this.graf = graf;
    }

    public ChinesePostman(String pathToDotFile) {
        this.graf = new UndirectedGraf();
        //not using undirectedGraf's constructor cause it is bugged :/
        //if the size isn't enough to contain .dot
        if (pathToDotFile.length() <= 4) {
            System.out.println("Not a valid file path");
            return;
        }
        //If this isn't a dot file path
        String pathExtension = pathToDotFile.substring(pathToDotFile.length() - 4);
        if (!pathExtension.equals(".dot")) {
            System.out.println(pathExtension + " The file must be a dot file");
            return;
        }
        BufferedReader reader;
        try {
            reader = new BufferedReader(new FileReader(pathToDotFile));
            String line=reader.readLine();
            while (line != null) {
                line = reader.readLine();
                line = line.trim();

            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private UndirectedGraf copyGraf(){
        UndirectedGraf g = new UndirectedGraf(graf.toSuccessorArray());
        for (Edge e : graf.getAllEdges()){
            g.setEdgeWeight(e,e.weight());
        }
        return g;
    }

    public boolean isOddDegree(Node n){
        return graf.degree(n)%2 != 0;
    }

    public boolean isOddDegree(int id){
        return isOddDegree(new Node(id));
    }

    public int countOddDegreeNodes(){
        int res=0;
        for (Node n : graf.getAllNodes()){
            if(isOddDegree(n)){
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
        UndirectedGraf copyGraf = copyGraf();
        if(!isNonEulerian()){
            List<List<Edge>> subCircuits = new ArrayList<>();
            Map<Node, List<Edge>> edgesToVisit = new HashMap<>();
            for (Node n : copyGraf.getAllNodes()){
                edgesToVisit.put(n,copyGraf.getOutEdges(n));
            }
            List<Edge> res=new ArrayList<>();
            List<Node> stackOfNodes = copyGraf.getAllNodes();
            Collections.sort(stackOfNodes);

            //change priority of nodes
            if(isSemiEulerian()){
                for (Node n : stackOfNodes){
                    if (isOddDegree(n)){
                        stackOfNodes.remove(n);
                        stackOfNodes.add(0,n);
                        break;
                    }
                }
            }

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

    public int getHighestNode(){
        int max=0;
        for(Node e : graf.getAllNodes()){
            if (e.getId()>max){
                max=e.getId();
            }
        }
        return max;
    }

    public Map<Pair<Node,Node>, Pair<Integer, Node>> floydWarshall(){
        Integer myInf = Integer.MAX_VALUE;

        int highestnode= getHighestNode()+1;
        Map<Pair<Node,Node>, Pair<Integer, Node>> res = new HashMap<Pair<Node,Node>, Pair<Integer, Node>>();

        // Initialization
        List<Node> nodes=graf.getAllNodes();

        int[][] M = new int[highestnode][highestnode];
        int[][] Prec = new int[highestnode][highestnode];

        for(Node x : nodes){
            for(Node y : nodes){
                if (x.equals(y)){
                    M[x.getId()][y.getId()]=0;
                    Prec[x.getId()][y.getId()]=x.getId();
                    res.put(new Pair(x,y), new Pair(0,x));
                }else{
                    if (graf.existsEdge(x,y)){
                        M[x.getId()][y.getId()]= graf.getEdge(new Edge(x,y)).weight();
                        Prec[x.getId()][y.getId()]=x.getId();
                        res.put(new Pair(x,y), new Pair(M[x.getId()][y.getId()],x));

                    }else{
                        M[x.getId()][y.getId()] = myInf; //l'infini
                        res.put(new Pair(x,y), new Pair(myInf,null));
                    }
                }
            }
        }
        for(int i=1; i<highestnode;++i){
            for(int j=1; j<highestnode;++j){
                res.put(new Pair(i,j), new Pair(M[i][j],Prec[i][j]));
            }
        }
        // Shortest distances computation
        for(Node z : nodes) {
            for (Node x : nodes) {
                for (Node y : nodes) {
                    if ((M[x.getId()][z.getId()] != myInf) && (M[z.getId()][y.getId()] !=myInf) && ((M[x.getId()][z.getId()] + M[z.getId()][y.getId()]) < M[x.getId()][y.getId()])) {
                        M[x.getId()][y.getId()] = M[x.getId()][z.getId()] + M[z.getId()][y.getId()];
                        Prec[x.getId()][y.getId()]=Prec[z.getId()][y.getId()];

                        res.put(new Pair(x,y), new Pair(M[x.getId()][y.getId()],new Node(Prec[x.getId()][y.getId()])));
                    }
                }
            }
        }

        for(int i=1; i<highestnode;++i){
            for(int j=1; j<highestnode;++j){
                res.put(new Pair(i,j), new Pair(M[i][j],Prec[i][j]));
            }
        }

        return res;
    }

    public List<Node> getAllOddDegrees(){
        List<Node> res = new ArrayList<>();
        for (Node n: graf.getAllNodes()){
            if(isOddDegree(n)){
                res.add(n);
            }
        }
        return res;
    }

    public List<Edge> shortestPathBetween2Nodes(Map<Pair<Node,Node>, Pair<Integer, Node>> floydWarshallResult, Node first, Node second){
        List<Edge> res = new ArrayList<Edge>();
        Node current = second;
        Node previous = floydWarshallResult.get(new Pair<>(first,current)).getSecond();

        while(!current.equals(first)){
            Edge e = graf.getEdge(new Edge(previous,current));
            e.setWeight(graf.getEdge(e).weight());
            res.add(e);
            current = previous;
            previous = floydWarshallResult.get(new Pair<>(first,current)).getSecond();
        }
        Collections.reverse(res);
        return res;
    }

    public List<Edge> shortestPathBetween2Nodes(Map<Pair<Node,Node>, Pair<Integer, Node>> floydWarshallResult, int first, int second){
        return shortestPathBetween2Nodes(floydWarshallResult, new Node(first), new Node(second));
    }

    public int lengthOfShortestPathBetween2Nodes(Map<Pair<Node,Node>, Pair<Integer, Node>> floydWarshallResult, Node first, Node second){
        return floydWarshallResult.get(new Pair<>(first,second)).getFirst();
    }

    public int lengthOfShortestPathBetween2Nodes(Map<Pair<Node,Node>, Pair<Integer, Node>> floydWarshallResult, int first, int second){
        return floydWarshallResult.get(new Pair<>(new Node(first),new Node(second))).getFirst();
    }

    public List<Pair<Node,Node>> getPairwiseMatchingRandom(){
        List<Pair<Node,Node>> res = new ArrayList<Pair<Node,Node>>();
        List<Node> oddDegreeNodes = getAllOddDegrees();
        for (int i=0; i<oddDegreeNodes.size(); i+=2){
            res.add(new Pair<>(oddDegreeNodes.get(i),oddDegreeNodes.get(i+1)));
        }
        return res;
    }

    public List<Pair<Node,Node>> getPairwiseMatchingNaive(Map<Pair<Node,Node>, Pair<Integer, Node>> floydWarshallResult){
        List<Pair<Node,Node>> res = new ArrayList<Pair<Node,Node>>();
        //todo
        return res;
    }

    public UndirectedGraf getEquivalentGraf(List<Pair<Node,Node>> pairwiseMatching, Map<Pair<Node,Node>, Pair<Integer, Node>> floydWarshallResult){
        UndirectedGraf g = copyGraf();
        for(Pair<Node,Node> pair : pairwiseMatching){
            List<Edge> edgesToAdd = shortestPathBetween2Nodes(floydWarshallResult,pair.getFirst(),pair.getSecond());
            for (Edge toAdd : edgesToAdd){
                Edge e = new Edge(toAdd.from(), toAdd.to());
                e.setWeight(graf.getEdge(e).weight());
                System.out.println("ADDDING : "+ e);
                g.addEdge(e);
            }
        }
        return g;
    }
}
