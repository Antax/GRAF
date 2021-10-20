package main.m1graf2021;

public class Edge implements Comparable<Edge>{
    private Node source;
    private Node target;
    private int weight;
    private Boolean weightDefined;

    public Edge(Node s, Node t) {
        source=s;
        target=t;
        weight=0;
        weightDefined=false;
    }

    public Edge(Node s, Node t, int w) {
        source=s;
        target=t;
        weight=w;
        weightDefined=true;
    }

    public Edge(int s, int t){
        source=new Node(s);
        target=new Node(t);
        weight=0;
        weightDefined=false;
    }

    public Edge(int s, int t, int w){
        source=new Node(s);
        target=new Node(t);
        weight=w;
        weightDefined=true;
    }

    public Node from(){
        return source;
    }

    public Node to(){
        return target;
    }

    public int weight(){
        return weight;
    }

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

    @Override
    public boolean equals(Object o){
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Edge edge = (Edge) o;
        return (edge.source.equals(source) && edge.target.equals(target));
    }

    @Override
    public String toString() {
        String ret= "["+source.toString()+"-"+target.toString()+"]";
        if(weightDefined){
            ret+="("+weight+")";
        }
        return ret;
    }

    public Edge getSymmetric(){
        return new Edge(to(),from());
    }

    public boolean isSelfLoop(){
        return to().equals(from());
    }

    public void setWeight(int w){
        weight=w;
        weightDefined=true;
    }

    public Boolean hasWeight(){
        return weightDefined;
    }

}