package main.m1graf2021;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import main.m1graf2021.*;

public class testProgram{

    public static void main(String[] args) {
        System.out.println(">>>>>>>> Creating the subject example graph in G");
        Graf g = new Graf(2, 4, 0, 0, 6, 0, 2, 3, 5, 8, 0, 0, 4, 7, 0, 3, 0, 7, 0);
        System.out.println(">>>> Graph information");
        System.out.println(">> DOT representation\n"+g.toDotString());
        System.out.println(""+g.nbNodes()+" nodes, "+g.nbEdges()+" edges");
        System.out.println(">> Nodes: ");
        List<Node> nodes = g.getAllNodes();
        Collections.sort(nodes);
        for (Node n: nodes)
            System.out.println("Node "+n+": degree "+g.degree(n)+" (in: "+g.inDegree(n)+"/ out: "+g.outDegree(n)+")");

        List<Edge> edges;
        System.out.println(">> Edges: ");
        System.out.println("---------------------------");
        System.out.println("Out-edges per node");
        for (Node n: nodes) {
            edges = g.getOutEdges(n);
            Collections.sort(edges);
            System.out.println(""+n+": "+edges);
        }

        System.out.println("In-edges per node");
        for (Node n: nodes) {
            edges = g.getInEdges(n);
            Collections.sort(edges);
            System.out.println(""+n+": "+edges);
        }

        /////////////////////////////////////////////////////

        System.out.println("\n>>>>>>>> creating isolated node 12");
        g.addNode(12);
        System.out.println("Graph now:");
        System.out.println(g.toDotString());
        System.out.println(""+g.nbNodes()+" nodes, "+g.nbEdges()+" edges");
        nodes = g.getAllNodes();
        Collections.sort(nodes);
        System.out.println("Nodes list: "+nodes);

        System.out.println("\n>>>>>>>> Removing node 3");
        g.removeNode(3);
        System.out.println("Graph now:");
        System.out.println(g.toDotString());
        System.out.println(""+g.nbNodes()+" nodes, "+g.nbEdges()+" edges");
        nodes = g.getAllNodes();
        Collections.sort(nodes);
        System.out.println("Nodes list: "+nodes);

        System.out.println(">> Edges: ");
        System.out.println("---------------------------");
        System.out.println("Out-edges per node");
        for (Node n: nodes) {
            edges = g.getOutEdges(n);
            Collections.sort(edges);
            System.out.println(""+n+": "+edges);
        }

        System.out.println("In-edges per node");
        for (Node n: nodes) {
            edges = g.getInEdges(n);
            Collections.sort(edges);
            System.out.println(""+n+": "+edges);
        }

        System.out.println("\n>>>>>>>> Recreating edges (4, 3), (3, 6), (7, 3), adding edge (12, 3), creating edge (3, 25)");
        g.addEdge(new Edge(4, 3));
        g.addEdge(new Edge(3, 6));
        g.addEdge(new Edge(7, 3));
        g.addEdge(new Edge(12, 3));
        g.addEdge(3, 25);
        System.out.println("Graph now:");
        System.out.println(g.toDotString());
        System.out.println(""+g.nbNodes()+" nodes, "+g.nbEdges()+" edges");
        nodes = g.getAllNodes();
        Collections.sort(nodes);
        System.out.println("Nodes list: "+nodes);

        System.out.println("");
        System.out.println("\n>>>>>>>>  Edges removal");
        System.out.println(">>>> Removing existing edges (7, 3) and (4, 8)");
        g.removeEdge(7, 3);
        g.removeEdge(4, 8);
        System.out.println(">>>> Removing absent edge (3, 4)");
        g.removeEdge(3, 4);
        System.out.println(">>>> Removing edges whith 1 or 2 not existing end-points: (-3, 4), (6, 0), (4, 11), (-1, -2), (13, 3), (9, 10)");
        g.removeEdge(-3, 4);
        g.removeEdge(6, 0);
        g.removeEdge(4, 11);
        g.removeEdge(-1, -2);
        g.removeEdge(13, 3);
        g.removeEdge(9, 10);

        System.out.println("Graph now:");
        System.out.println(g.toDotString());
        System.out.println(""+g.nbNodes()+" nodes, "+g.nbEdges()+" edges");
        nodes = g.getAllNodes();
        Collections.sort(nodes);
        System.out.println("Nodes list: "+nodes);

        System.out.println("\n>>>>>>>> MULTIGRAPH: adding a self-loop on node 6, and a second edge (1, 4)");
        g.addEdge(6, 6);
        g.addEdge(1, 4);
        System.out.println("Graph now:");
        System.out.println(g.toDotString());
        System.out.println(""+g.nbNodes()+" nodes, "+g.nbEdges()+" edges");
        nodes = g.getAllNodes();
        Collections.sort(nodes);
        System.out.println("Nodes list: "+nodes);
        System.out.println("Degree of node 6: "+g.degree(6)+" (in: "+g.inDegree(6)+"/ out: "+g.outDegree(6)+")");

        System.out.println(">> Edges: ");
        System.out.println("---------------------------");
        System.out.println("Out-edges per node");
        for (Node n: nodes) {
            edges = g.getOutEdges(n);
            Collections.sort(edges);
            System.out.println(""+n+": "+edges);
        }

        System.out.println("In-edges per node");
        for (Node n: nodes) {
            edges = g.getInEdges(n);
            Collections.sort(edges);
            System.out.println(""+n+": "+edges);
        }

        // DELETE
        // System.out.println("DOT");
        // System.out.println(g.toDotString());
        // System.out.println("Successor array");
        // int[] tab = g.toSuccessorArray();
        // for (int i=0; i<tab.length; i++)
        //     System.out.print(tab[i]+", ");
        // System.out.println();
        // END DELETE

        System.out.println(">>>>>>>>>>    Get the reverse graph");
        System.out.println(g.getReverse().toDotString());

        System.out.println(">>>>>>>>>>    Get the transitive closure");
        System.out.println(g.getTransitiveClosure().toDotString());

        System.out.println(">>>>>>>>>>    Emptying the graph by removing all its nodes");
        nodes = g.getAllNodes();
        for (Node u: nodes)
            g.removeNode(u);
        System.out.println("Graph now:");
        System.out.println(g.toDotString());

        System.out.println(">>>> Searching for node 7");
        if (g.existsNode(7))
            System.out.println("Node 7 exists");
        else
            System.out.println("There is no Node 7");

        System.out.println(">>>> Searching for edge (4, 2)");
        if (g.existsEdge(4, 2))
            System.out.println("Edge (4, 2) exists");
        else
            System.out.println("There is no edge (4, 2)");


        System.out.println("\n------------------------------");
        System.out.println("Now Testing UNDIRECTED GRAPHS");
        System.out.println("------------------------------");

        System.out.println("Building undirected graph gu, with multi-edges and self-loops");
        UndirectedGraf gu = new UndirectedGraf(1,1,2,2,3,0, 2,3,0, 0);

        String dotGU = gu.toDotString();
        System.out.println(dotGU);
        System.out.println("gu has "+gu.nbNodes()+" nodes and "+gu.nbEdges()+" edges.");


        System.out.println("\n>>>>>> Counting degrees and showing successors");
        for (Node u: gu.getAllNodes()) {
            System.out.println("Node "+u+". Degree: "+gu.degree(u.getId())+" (In: "+gu.inDegree(u.getId())+" / Out: "+gu.outDegree(u.getId())+")");
            System.out.println("\tSuccessors: "+gu.getSuccessors(u));
        }

        System.out.println(">>>>>> Edges of the graph");
        System.out.println("All edges of the graph: "+gu.getAllEdges());
        System.out.println("Out-edges per node");
        for (Node u: gu.getAllNodes())
            System.out.println(""+u+": "+gu.getOutEdges(u));
        System.out.println("In-edges per node");
        for (Node u: gu.getAllNodes())
            System.out.println(""+u+": "+gu.getInEdges(u));
        System.out.println("Incident edges per node");
        for (Node u: gu.getAllNodes())
            System.out.println(""+u+": "+gu.getIncidentEdges(u));

        System.out.println("\n>>>>>> Successor Array, Adjacency Matrix, and Graph Reverse");
        System.out.println("gu Successor array\n"+Arrays.toString(gu.toSuccessorArray()));

        System.out.println("gu Adjacency Matrix");
        for (int[] row: gu.toAdjMatrix())
            System.out.println("\t"+Arrays.toString(row));

        System.out.println("Testing via toDotString() the equality with the reverse graph");
        String dotRGU = gu.getReverse().toDotString();
        System.out.println("DOT of the reverse of gu\n"+dotRGU);
        System.out.println("Graph gu and its reverse "+(dotGU.equals(dotRGU)?"are identical":"differ"));

        System.out.println("-----------------\n      NOW a disconnected GRAPH    \n----------------");
        System.out.println("Building guDisc, a disconnected undirected graph with multi-edges and self-loops");
        UndirectedGraf guDisc = new UndirectedGraf(1,1,2,2,6,0, 2,3,6,0, 0, 6,0, 6,0, 0, 0, 9,10,0, 0, 0);
        System.out.println(guDisc.toDotString());

        System.out.println(">>>> DFS of guDisc: "+guDisc.getDFS());
        System.out.println(">>>> BFS of guDisc: "+guDisc.getBFS());

        System.out.println(">>>>>>> Computing guDisc's transitive closure");
        UndirectedGraf guDiscTC = guDisc.getTransitiveClosure();
        System.out.println(guDiscTC.toDotString());

    }

}
