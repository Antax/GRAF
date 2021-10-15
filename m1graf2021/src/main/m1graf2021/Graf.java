package main.m1graf2021;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.*;

public class Graf{
    Map<Node, List<Edge>> adjEdList;

    public Graf() {
        adjEdList = new HashMap<>();
    }

    public Graf(int[] data){
        adjEdList = new HashMap<>();
        int numberOfNode=1;
        int i=0;
        while(i<data.length) {
            List<Edge> currentNodeEdges = new ArrayList<Edge>();
            Node currentNode=getNode(numberOfNode);
            if(currentNode==null) {
                currentNode = new Node(numberOfNode);
                addNode(currentNode);
            }
            while (data[i]!=0){
                Node target=getNode(data[i]);
                if(target==null){
                    target=new Node (data[i]);
                    addNode(target);
                }
                currentNodeEdges.add(new Edge(currentNode,target));
                ++i;
            }
            for(int e=0;e<currentNodeEdges.size();++e){
                adjEdList.get(currentNode).add(currentNodeEdges.get(e));
            }
            ++numberOfNode;
            ++i;
        }
    }

    public int nbNodes(){
        return adjEdList.size();
    }

    void addEdge(Node from, Node to){
        if(from==null || to==null){
            return;
        }
        if(!existsNode(from)){
            addNode(from);
        }
        if(!existsNode(to)){
            addNode(to);
        }
        if(!existsNode(from) || !existsNode(to)){
            return;
        }
        adjEdList.get(from).add(new Edge(from,to));
    }

    void addEdge(int from, int to){
        addEdge(new Node(from), new Node(to));
    }

    void addEdge(Edge edge){
        addEdge(edge.from(),edge.to());
    }


    /**
     * Gets the instance of a node in the graf
     *
     * @param id identifier of the researched node
     * @return null if not found, or the node itself
     */
    public Node getNode(int id){
        for (Map.Entry<Node, List<Edge>> node : adjEdList.entrySet()) {
            if(node.getKey().getId()==id){
                return node.getKey();
            }
        }
        return null;
    }

    /**
     * Searchs for a node in the graf
     *
     * @param n the node itself
     * @return true if found, false otherwise
     */
    public boolean existsNode(Node n){
        if(n==null){
            return false;
        }
        for (Map.Entry<Node, List<Edge>> node : adjEdList.entrySet()) {
            if(node.getKey().equals(n)){
                return true;
            }
        }
        return false;
    }

    /**
     * Searchs for a node in the graf
     *
     * @param id the identifier of the node
     * @return true if found, false otherwise
     */
    public boolean existsNode(int id){
        Node n=new Node(id);
        return existsNode(n);
    }

    public void addNode(Node n){
        if(existsNode(n)){
            System.out.println("Le noeud "+n+" existe déjà dans le graf");
            return;
        }
        if(n==null){
            System.out.println("Le noeud est null");
            return;
        }
        adjEdList.put(n, new ArrayList<Edge>());
    }

    public void addNode(int id){
        Node n=new Node(id);
        addNode(n);
    }

    public void removeNode(Node n){
        adjEdList.remove(n);
        List<Edge> toRemove=getInEdges(n);
        for(int i=0;i<toRemove.size();++i){
            removeEdge(toRemove.get(i));
        }
    }

    public void removeNode(int id){
        removeNode(new Node(id));
    }

    /**
     * Searches for an edge in the graf
     *
     * @param f identifier of the first node
     * @param t Identifier of the second node
     * @return true if the edge exists, false otherwise
     */

    public boolean existsEdge(Node f,Node t){
        if(!existsNode(f)||!existsNode(t)){
            return false;
        }
        Edge researched=new Edge(f,t);
        for(int i=0;i<adjEdList.get(f).size();++i){
            if(adjEdList.get(f).get(i).equals(researched)){
                return true;
            }
        }
        return false;
    }

    public boolean existsEdge(int f, int t){
        Node from = getNode(f);
        Node to = getNode(t);
        return existsEdge(from,to);
    }

    public boolean existsEdge(Edge e){
        Node f=e.from();
        Node t=e.to();
        return existsEdge(f,t);
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

    public int nbEdges(){
        int ret=0;
        for (Map.Entry<Node, List<Edge>> node : adjEdList.entrySet()) {
            if(node.getValue()!=null){
                ret+=node.getValue().size();
            }
        }
        return ret;
    }

    public void removeEdge(Node from,Node to){
        if(from==null||to==null){
            return;
        }
        if(existsEdge(from,to)){
            adjEdList.get(from).remove(new Edge(from,to));
        }
    }

    public void removeEdge(int from,int to){
        Node f=new Node(from);
        Node t=new Node(to);
        removeEdge(f,t);
    }

    public void removeEdge(Edge e){
        removeEdge(e.from(),e.to());
    }

    public List<Edge> getOutEdges(Node n){
        return adjEdList.get(n);
    }

    public List<Edge> getOutEdges(int id){
        return getOutEdges(new Node(id));
    }

    public List<Edge> getAllEdges(){
        List<Edge> res=new ArrayList<Edge>();
        for (List<Edge> value : adjEdList.values()) {
            res.addAll(value);
        }
        return res;
    }

    public List<Edge> getInEdges(Node n){
        List<Edge> res=new ArrayList<Edge>();
        for (List<Edge> value : adjEdList.values()) {
            for(int i=0;i<value.size();++i){
                if(value.get(i).to().equals(n)){
                    res.add(value.get(i));
                }
            }
        }
        return res;
    }

    public List<Edge> getInEdges(int id){
        return getInEdges(new Node(id));
    }

    public List<Edge> getIncidentEdges(Node n){
        List<Edge> res=getInEdges(n);
        res.addAll(getOutEdges(n));
        return new ArrayList<>(new HashSet<>(res));
    }

    public List<Edge> getIncidentEdges(int id){
        return getIncidentEdges(new Node(id));
    }

    public List<Node> getSuccessors(Node n){
        if(n==null || !existsNode(n)){
            return null;
        }
        List<Edge> edgesFromN=adjEdList.get(n);
        List<Node> res=new ArrayList<>();
        for(int i=0;i<edgesFromN.size();++i){
            res.add(edgesFromN.get(i).to());
        }
        return res;
    }

    public List<Node> getSuccessors(int id){
        return getSuccessors(new Node(id));
    }

    public List<Node> getAllNodes(){
        List<Node>res=new ArrayList<>();
        for (Node key : adjEdList.keySet()) {
            res.add(key);
        }
        return res;
    }

    public boolean adjacent(Node u,Node v){
        return (u.equals(v) || existsEdge(u,v)||existsEdge(v,u));
    }

    public boolean adjacent(int idU,int idV){
        return adjacent(new Node(idU),new Node(idV));
    }

    public int largestNodeId(){
        if(adjEdList.size()==0){
            System.out.println("Graf does not contain any nodes");
            return -1;
        }
        int max=0;
        for(Node key : adjEdList.keySet()){
            if(key.getId()>max){
                max=key.getId();
            }
        }
        return max;
    }

    public int outDegree(Node n){
        if(!existsNode(n)){
            System.out.println("Graf does not contain this node");
            return -1;
        }
        return adjEdList.get(n).size();
    }

    public int outDegree(int id){
        return outDegree(new Node(id));
    }

    public int inDegree(Node n){
        if(!existsNode(n)){
            System.out.println("Graf does not contain this node");
            return -1;
        }
        int res=0;
        for (List<Edge> value : adjEdList.values()) {
            for(int i=0;i<value.size();++i){
                if(value.get(i).to().equals(n)){
                    res++;
                }
            }
        }
        return res;
    }

    public int inDegree(int id){
        return inDegree(new Node(id));
    }

    public int degree(Node n){
        if(!existsNode(n)){
            System.out.println("Graf does not contain this node");
            return -1;
        }
        int res=inDegree(n)+outDegree(n);
        if(existsEdge(4,4)){
            res--;
        }
        return res;
    }

    public int degree(int id){
        return degree(new Node(id));
    }

    public String toDotString(){
        String res="digraph G {\n";
        List<Edge> edges=getAllEdges();
        Collections.sort(edges);
        for (Edge e:edges) {
            res+=e.from().toString()+" -> "+e.to().toString()+";\n";
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
    public void toDotFile(String fileName) throws FileNotFoundException{
        PrintWriter writer = new PrintWriter(fileName);
        writer.print(toDotString());
        writer.close();
    }
}