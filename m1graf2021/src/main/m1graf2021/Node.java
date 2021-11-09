package main.m1graf2021;

import java.util.Objects;

public class Node implements Comparable<Node>{
    private int id;
    private String name;

    /**
     * Creates a node with a name
     *
     * @param i the unique id of the node
     * @param n the name of the node
     */
    public Node(int i, String n) {
        id=i;
        name=n;
    }

    /**
     * Creates a node without a name
     *
     * @param i the unique id of the node
     */
    public Node(int i) {
        id=i;
        name=null;
    }

    /**
     * Returns the id of the node
     *
     * @return the id of the node
     */
    public int getId() {
        return id;
    }

    /**
     * Returns the name of the node
     *
     * @return the name of the node
     */
    public String getName() {
        return name;
    }

    /**
     * Returns true if both two nodes have the same id
     *
     * @param o another node
     * @return true if both nodes have the same id
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Node node = (Node) o;
        return id == node.id;
    }

    /**
     * Returns a hash code for the node
     *
     * @return a hash code for the node
     */
    @Override
    public int hashCode() {
        return Objects.hash(id, name);
    }

    /**
     * Compare this node with another
     *
     * @param o another node
     * @return a positive value if the other has a lower id, a negative value if the other has a greater value, or 0 if the nodes are equal
     */
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

    /**
     * Returns a string representing the node
     *
     * @return the name of the node if it exists, or the id
     */
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
