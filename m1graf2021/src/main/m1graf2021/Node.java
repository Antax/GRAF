package main.m1graf2021;

import java.util.Objects;

public class Node implements Comparable<Node>{
    private int id;
    private String name;

    public Node(int i, String n) {
        id=i;
        name=n;
    }

    public Node(int i) {
        id=i;
        name=null;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Node node = (Node) o;
        return id == node.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name);
    }

    @Override
    public int compareTo(Node o) {
        if(o.id>id){
            return -1;
        }
        if(o.id==id){
            return 0;
        }
        return 1;
    }

    public String toString(){
        String ret="";
        if(name==null){
            ret+=id;
        }else{
            ret+=name;
        }
        return ret;
    }
}
