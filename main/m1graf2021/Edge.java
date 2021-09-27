package main.m1graf2021;

public class Edge implements Comparable<Edge>{
    Node source;
    Node target;

    public Edge(Node s, Node t) {
        source=s;
        target=t;
    }

    public Edge(int s, int t){
        source=new Node(s);
        target=new Node(t);
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
}