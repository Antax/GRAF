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
        return existsNode(id);
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

    /*public void removeNode(){

    }*/

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
}