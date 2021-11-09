package main.m1graf2021;

public class Edge implements Comparable<Edge>{
    private Node source;
    private Node target;
    private int weight;
    private Boolean weightDefined;

    /**
     * Creates an edge, using two node
     *
     * @param s the source node
     * @param t the target node
     */
    public Edge(Node s, Node t) {
        source=s;
        target=t;
        weight=0;
        weightDefined=false;
    }

    /**
     * Creates a weighted edge, using two node
     *
     * @param s the source node
     * @param t the target node
     * @param w the weight
     */
    public Edge(Node s, Node t, int w) {
        source=s;
        target=t;
        weight=w;
        weightDefined=true;
    }

    /**
     * Creates an edge, using two ids
     *
     * @param s the source node's id
     * @param t the target node's id
     */
    public Edge(int s, int t){
        source=new Node(s);
        target=new Node(t);
        weight=0;
        weightDefined=false;
    }

    /**
     * Creates a weighted edge, using two ids
     *
     * @param s the source node's id
     * @param t the target node's id
     * @param w the weight
     */
    public Edge(int s, int t, int w){
        source=new Node(s);
        target=new Node(t);
        weight=w;
        weightDefined=true;
    }

    /**
     * returns the source node
     *
     * @return the source node
     */
    public Node from(){
        return source;
    }

    /**
     * returns the target node
     *
     * @return the target node
     */
    public Node to(){
        return target;
    }

    /**
     * returns the weight of the edge
     *
     * @return the weight of the edge
     */
    public int weight(){
        return weight;
    }

    /**
     * Compares an edge with another
     *
     * @param o another edge
     * @return an integer value. compares the source node, and then the target node in case of equality
     */
    @Override
    public int compareTo(Edge o) {
        if(source.compareTo(o.source) > 0){
            return 1;
        }
        if(source.compareTo(o.source)==0){
            return target.compareTo(o.target);
        }
        return -1;
    }

    /**
     * returns true if both edge have the same sources and targets
     *
     * @param o another edge
     * @return true if both edge have the same sources and targets
     */
    @Override
    public boolean equals(Object o){
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Edge edge = (Edge) o;
        return (edge.source.equals(source) && edge.target.equals(target));
    }

    /**
     * Returns a string representation of the edge
     *
     * @return a string representation of the edge
     */
    @Override
    public String toString() {
        String ret= source.toString()+"->"+target.toString();
        if(weightDefined){
            ret+="("+weight+")";
        }
        return ret;
    }

    /**
     * returns a symmetrical edge
     *
     * @return a symmetrical edge
     */
    public Edge getSymmetric(){
        return new Edge(to(),from());
    }

    /**
     * Returns true if the edge is a self loop
     *
     * @return true if the edge is a self loop
     */
    public boolean isSelfLoop(){
        return to().equals(from());
    }

    /**
     * Sets the weight of the edge
     *
     * @param w the weight of the edge
     */
    public void setWeight(int w){
        weight=w;
        weightDefined=true;
    }

    /**
     * Return true if the edge is weighted
     *
     * @return true if the edge is weighted
     */
    public Boolean hasWeight(){
        return weightDefined;
    }

}