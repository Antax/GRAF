package m1graf2021;

import java.util.Objects;

public class Node {
    private int id;
    private String name;

    public Node(int i, String n) {
        id=i;
        name=n;
    }

    public Node(int i) {
        id=i;
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

}
