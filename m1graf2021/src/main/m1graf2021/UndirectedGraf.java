package main.m1graf2021;

import java.io.*;
import java.util.*;

public class UndirectedGraf extends Graf{
    public UndirectedGraf() {
        super();
    }

    public UndirectedGraf(int... ids){
        super(ids);
    }

    /**
     *
     * @param pathToDotFile the absolute path to a dot file (simplified version, according to the subject)
     */
    public UndirectedGraf(String pathToDotFile){
        super(pathToDotFile);
    }

    public int nbNodes(){
        return super.nbNodes();
    }

    void addEdge(Node from, Node to){
        super.addEdge(from,to);
        if(!from.equals(to)){
            super.addEdge(to,from);
        }
    }

    public void addEdge(int from, int to){
        addEdge(new Node(from),new Node(to));
    }

    public void addEdge(Edge edge){
        addEdge(edge.from(),edge.to());
    }

    /**
     * Gets the instance of a node in the graf
     *
     * @param id identifier of the researched node
     * @return null if not found, or the node itself
     */
    public Node getNode(int id){
        return super.getNode(id);
    }

    /**
     * Searchs for a node in the graf
     *
     * @param n the node itself
     * @return true if found, false otherwise
     */
    public boolean existsNode(Node n){
        return super.existsNode(n);
    }

    /**
     * Searchs for a node in the graf
     *
     * @param id the identifier of the node
     * @return true if found, false otherwise
     */
    public boolean existsNode(int id){
        return super.existsNode(id);
    }

    public void addNode(Node n){
        super.addNode(n);
    }

    public void addNode(int id){
        super.addNode(id);
    }

    public void removeNode(Node n){
        super.removeNode(n);
    }

    public void removeNode(int id){
        super.removeNode(id);
    }

    public Edge getEdge(Edge e){
        return super.getEdge(e);
    }

    /**
     * Searches for an edge in the graf
     *
     * @param f identifier of the first node
     * @param t Identifier of the second node
     * @return true if the edge exists, false otherwise
     */

    public boolean existsEdge(Node f,Node t){
        return super.existsEdge(f,t);
    }

    public boolean existsEdge(int f, int t){
        return super.existsEdge(f,t);
    }

    public boolean existsEdge(Edge e){
        return super.existsEdge(e);
    }

    public String toString(){
        return super.toString();
    }

    public int nbEdges(){
        int ret=0;
        for (Map.Entry<Node, List<Edge>> node : adjEdList.entrySet()) {
            if(node.getValue()!=null){
                for(Edge e : node.getValue()){
                    //To prevent counting an edge twice
                    if(e.from().getId()<=e.to().getId()){
                        ret++;
                    }
                }
            }
        }
        return ret;
    }

    public void removeEdge(Node from,Node to){
        super.removeEdge(from,to);
        if(!from.equals(to)){
            super.removeEdge(to,from);
        }
    }

    public void removeEdge(int from,int to){
        removeEdge(new Node(from),new Node(to));
    }

    public void removeEdge(Edge e){
        removeEdge(e.from(),e.to());
    }

    public List<Edge> getOutEdges(Node n){
        return super.getOutEdges(n);
    }

    public List<Edge> getOutEdges(int id){
        return getOutEdges(new Node(id));
    }

    public List<Edge> getAllEdges(){
        List<Edge> res=new ArrayList<>();
        for (List<Edge> value : adjEdList.values()) {
            for (Edge e : value){
                if(e.from().getId()<=e.to().getId()){
                    res.add(e);
                }
            }
        }
        return res;
    }

    public List<Edge> getInEdges(Node n){
        return super.getOutEdges(n);
    }

    public List<Edge> getInEdges(int id){
        return getInEdges(id);
    }

    public List<Edge> getIncidentEdges(Node n){
        return getInEdges(n);
    }

    public List<Edge> getIncidentEdges(int id){
        return getInEdges(new Node(id));
    }

    public List<Node> getSuccessors(Node n){
        return super.getSuccessors(n);
    }

    public List<Node> getSuccessors(int id){
        return super.getSuccessors(id);
    }

    public List<Node> getAllNodes(){
        return super.getAllNodes();
    }

    public boolean adjacent(Node u,Node v){
        return super.adjacent(u,v);
    }

    public boolean adjacent(int idU,int idV){
        return super.adjacent(idU,idV);
    }

    public int largestNodeId(){
        return super.largestNodeId();
    }

    public int outDegree(Node n){
        return degree(n);
    }

    public int outDegree(int id){
        return degree(id);
    }

    public int inDegree(Node n){
        return degree(n);
    }

    public int inDegree(int id){
        return degree(id);
    }

    public int degree(Node n){
        int res=0;
        for (Edge e : adjEdList.get(n)){
            if(e.to().equals(n)){
                ++res;
            }
            ++res;
        }
        return res;
    }

    public int degree(int id){
        return degree(new Node(id));
    }

    public String toDotString(){
        String res="graph {\n";
        List<Node> allNodes=getAllNodes();
        Collections.sort(allNodes);
        for (Node n:allNodes) {
            res+=n.toString();
            List<Edge>weightedEdges=new ArrayList<>();
            boolean firstEdge=true;
            List<Edge>outEdges=getOutEdges(n);
            Collections.sort(outEdges);
            for (Edge e:outEdges) {
                if(e.to().compareTo(e.from())>=0) {
                    if (e.hasWeight()) {
                        weightedEdges.add(e);
                    } else {
                        if (firstEdge) {
                            firstEdge = false;
                            res += " --";
                        } else {
                            res += ",";
                        }
                        res += " " + e.to().toString();
                    }
                }
            }
            res+=";\n";
            if(!weightedEdges.isEmpty()){
                for (Edge weightedEdge:weightedEdges) {
                    res+=n.toString()+" -- "+weightedEdge.to().toString()+"[len="+String.valueOf(weightedEdge.weight())+",label="+String.valueOf(weightedEdge.weight())+"];\n";
                }
            }
        }
        res+='}';
        return res;
    }

    /**
     * Writes the representation of the graph in the dot format, in a given file path
     * If the file already exists, ask for confirmation to replace it's content
     *
     * @param fileName path to the file to write in
     */
    public void toDotFile(String fileName) throws FileNotFoundException {
        super.toDotFile(fileName);
    }

    public void setEdgeWeight(Edge e,int w){
        super.setEdgeWeight(e,w);
    }

    public void setEdgeWeight(Node f, Node t, int w){
        super.setEdgeWeight(f,t,w);
    }

    public void setEdgeWeight(int f,int t, int w){
        super.setEdgeWeight(f,t,w);
    }

    public int[][] toAdjMatrix(){
        int nbNodes=nbNodes();
        int[][] res=new int[nbNodes][nbNodes];
        for(int i=0;i<nbNodes;++i){
            for(int j=0;j<nbNodes;++j){
                res[i][j]=0;
            }
        }
        List<Edge>edges=getAllEdges();
        for (Edge e : edges) {
            res[e.from().getId() - 1][e.to().getId() - 1]++;
            if(e.from()!=e.to()){
                res[e.to().getId()-1][e.from().getId()-1]++;
            }
        }
        return res;
    }

    public int[] toSuccessorArray(){
        ArrayList<Integer> arrayList = new ArrayList<Integer>();
        List<Node>nodes=getAllNodes();
        Collections.sort(nodes);
        for (Node node : nodes) {
            List<Edge> edgesFromCurrentNode = getOutEdges(node);
            for (Edge edge : edgesFromCurrentNode) {
                if(edge.to().compareTo(node) >= 0){
                    arrayList.add(edge.to().getId());
                }
            }
            arrayList.add(0);
        }
        int[] arr = new int[arrayList.size()];
        for(int i=0;i<arrayList.size();++i){
            arr[i]=arrayList.get(i);
        }
        return arr;
    }

    public UndirectedGraf getReverse(){
        return this;
    }

    public List<Node> getBFS(){
        return super.getBFS();
    }

    public List<Node> getDFS(){
        return super.getDFS();
    }

    public Pair<LinkedList<Node>,Map<Node,Boolean>> dfsVisit(Node n, LinkedList<Node> result, Map<Node,Boolean> visitedNodes){
        return super.dfsVisit(n,result,visitedNodes);
    }

    public List<Node> bfsFromOneNode(Node startingNode){
        return super.reach(startingNode);
    }

    public List<Node> reach(int id){
        return super.reach(id);
    }

    public UndirectedGraf getTransitiveClosure(){
        UndirectedGraf resGraf=new UndirectedGraf();
        for (Node n:this.getAllNodes()) {
            List<Node> reachableNodes = bfsFromOneNode(n);
            for(Node reachableNode : reachableNodes){
                if(!reachableNode.equals(n) && n.compareTo(reachableNode)<=0) {
                    resGraf.addEdge(n,reachableNode);
                }
            }
        }
        return resGraf;
    }

    public Boolean isWeighted() {
        return super.isWeighted();
    }
}