package main.m1graf2021;

public class Edge implements Comparable<Edge>{
    private Node source;
    private Node target;
    private Integer weight;

    public Edge(Node s, Node t) {
        source=s;
        target=t;
        weight=null;
    }

    public Edge(Node s, Node t, int w) {
        source=s;
        target=t;
        weight=w;
    }

    public Edge(int s, int t){
        source=new Node(s);
        target=new Node(t);
        weight=null;
    }

    public Edge(int s, int t, int w){
        source=new Node(s);
        target=new Node(t);
        weight=w;
    }

    public Node from(){
        return source;
    }

    public Node to(){
        return target;
    }

    @Override
    public int compareTo(Edge o) {
        if(source.compareTo(o.source)==1){
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
        if(weight!=null){
            ret+="("+weight+")";
        }
        return ret;
    }
}