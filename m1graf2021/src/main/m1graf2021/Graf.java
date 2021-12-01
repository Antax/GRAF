package main.m1graf2021;

import java.io.*;
import java.util.*;

public class Graf{
    Map<Node, List<Edge>> adjEdList;
    /**
     * Creates en empty graph
     */
    public Graf() {
        adjEdList = new HashMap<>();
    }

    /**
     * Creates a graph, using a successor array
     *
     * @param ids an array of integer or variadics integer arguments, representing a successor array
     */
    public Graf(int... ids){
        //converting the arguments into an array
        int[] data=new int[ids.length];
        int j=0;
        for(int id : ids) {
            data[j]=id;
            ++j;
        }
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
            for (Edge currentNodeEdge : currentNodeEdges) {
                addEdge(currentNodeEdge);
            }
            ++numberOfNode;
            ++i;
        }

        //removing nodes that arent' connected to anyother node
        for (Node n : getAllNodes()){
            if(getOutEdges(n).isEmpty() && getInEdges(n).isEmpty()){
                removeNode(n);
            }
        }
    }

    /**
     * Creates a graf using a dot file
     *
     * @param pathToDotFile the absolute path to a dot file (simplified version, according to the subject)
     */
    public Graf(String pathToDotFile){
        adjEdList=new HashMap<>();
        //if the size isn't enough to contain .dot
        if(pathToDotFile.length()<=4){
            System.out.println("Not a valid file path");
            return;
        }
        //If this isn't a dot file path
        String pathExtension = pathToDotFile.substring(pathToDotFile.length() - 4);
        if(!pathExtension.equals(".dot")){
            System.out.println(pathExtension +" The file must be a dot file");
            return;
        }
        BufferedReader reader;
        try {
            reader = new BufferedReader(new FileReader(pathToDotFile));
            String line=reader.readLine();
            while (line != null) {
                line = reader.readLine();
                if(line!=null){
                    line=line.trim();
                    if(!line.contains("{")&&!line.contains("}")) {
                        //A node (with a label)
                        if (!line.contains("->") && !line.contains("--")) {
                            addNode(Integer.parseInt(line.substring(0,line.length()-1)));
                        }
                        //An edge
                        if (line.contains("->") || line.contains("--")) {
                            int from=Integer.parseInt(line.substring(0,line.indexOf("-")-1).trim());
                            //not a weighted edge
                            if(!line.contains("len")){
                                int i=line.indexOf("-")+3;
                                while(i<line.length()){
                                    String out="";
                                    while(line.charAt(i)!=',' && line.charAt(i)!=';'){
                                        out+=line.charAt(i);
                                        ++i;
                                    }
                                    addEdge(from,Integer.parseInt(out));
                                    i+=2;
                                }
                            }else{ //a weighted edge
                                int weight=Integer.parseInt(line.substring(line.indexOf("\"")+1,line.lastIndexOf(",")-1).trim());
                                int i=line.indexOf("-")+3;
                                while(i<line.substring(0,line.indexOf("[")).length()){
                                    StringBuilder out= new StringBuilder();
                                    while(line.charAt(i)!='[' && line.charAt(i)!=','){
                                        out.append(line.charAt(i));
                                        ++i;
                                    }
                                    i+=2;
                                    addEdge(from,Integer.parseInt(out.toString()));
                                    setEdgeWeight(from,Integer.parseInt(out.toString()),weight);
                                }
                            }
                        }
                    }
                }
            }
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * returns the number of nodes in the graph
     *
     * @return the number of nodes in the graph
     */
    public int nbNodes(){
        return adjEdList.size();
    }

    /**
     * Adds an edge from two node. If the nodes don't exist in the graph, they are created.
     *
     * @param from the source node
     * @param to the target node
     */
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
        adjEdList.get(from).add(new Edge(from,to));
    }

    /**
     * Adds an edge using two node's id. If the nodes don't exist in the graf, they are created
     *
     * @param from the source node's id
     * @param to the target node's id
     */
    public void addEdge(int from, int to){
        addEdge(new Node(from), new Node(to));
    }

    /**
     * Adds an edge.
     *
     * @param edge the edge to add
     */
    public void addEdge(Edge edge){
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
        return adjEdList.containsKey(n);
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

    /**
     * Adds a node to the graph
     *
     * @param n the node to add
     */
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

    /**
     * Adds a node to the graph
     *
     * @param id the id of the node
     */
    public void addNode(int id){
        Node n=new Node(id);
        addNode(n);
    }

    /**
     * Removes a node from the graph
     *
     * @param n the node to remove
     */
    public void removeNode(Node n){
        List<Edge> toRemove=getInEdges(n);
        for(int i=0;i<toRemove.size();++i){
            removeEdge(toRemove.get(i));
        }
        adjEdList.remove(n);
    }

    /**
     * Removes a node from the graph
     *
     * @param id the node to remove
     */
    public void removeNode(int id){
        removeNode(new Node(id));
    }

    /**
     * Gets an edge from the graph
     *
     * @param e the edge to get
     * @return the edge
     */
    public Edge getEdge(Edge e){
        if(!existsNode(e.from())||!existsNode(e.to())){
            return null;
        }
        Edge researched=new Edge(e.from(),e.to());
        for(int i=0;i<adjEdList.get(e.from()).size();++i){
            if(adjEdList.get(e.from()).get(i).equals(researched)){
                return adjEdList.get(e.from()).get(i);
            }
        }
        return null;
    }

    /**
     * Searches for an edge in the graph
     *
     * @param f source node
     * @param t the target node
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

    /**
     * Searches for an edge in the graph
     *
     * @param f the id of the source node
     * @param t the id of the target node
     * @return true if the edge exists, false otherwise
     */
    public boolean existsEdge(int f, int t){
        Node from = getNode(f);
        Node to = getNode(t);
        return existsEdge(from,to);
    }

    /**
     * Searches for an edge in the graph
     *
     * @param e the edge
     * @return true if the edge exists, false otherwise
     */
    public boolean existsEdge(Edge e){
        Node f=e.from();
        Node t=e.to();
        return existsEdge(f,t);
    }

    /**
     * Returns a string representation of the graph
     *
     * @return the string representation of the graph
     */
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

    /**
     * returns the number of edges in the graph
     *
     * @return the number of edges in the graph
     */
    public int nbEdges(){
        int ret=0;
        for (Map.Entry<Node, List<Edge>> node : adjEdList.entrySet()) {
            if(node.getValue()!=null){
                ret+=node.getValue().size();
            }
        }
        return ret;
    }

    /**
     * Removes an edge from the graph, if it exists
     *
     * @param from the source node
     * @param to the target node
     */
    public void removeEdge(Node from,Node to){
        if(from==null||to==null){
            return;
        }
        if(existsEdge(from,to)){
            adjEdList.get(from).remove(new Edge(from,to));
        }
    }

    /**
     * Removes an edge from the graph, if it exists
     *
     * @param from the source node
     * @param to the target node
     */
    public void removeEdge(int from,int to){
        Node f=new Node(from);
        Node t=new Node(to);
        removeEdge(f,t);
    }

    /**
     * Removes an edge from the graph, if it exists
     *
     * @param e the edge to remove
     */
    public void removeEdge(Edge e){
        removeEdge(e.from(),e.to());
    }

    /**
     * Returns a list of all the edges having a specific node as the source
     *
     * @param n the source node
     * @return a list of all the out edges
     */
    public List<Edge> getOutEdges(Node n){
        return adjEdList.get(n);
    }

    /**
     * Returns a list of all the edges having a specific node as the source
     *
     * @param id the source node's if
     * @return a list of all the out edges
     */
    public List<Edge> getOutEdges(int id){
        return getOutEdges(new Node(id));
    }

    /**
     * Returns all the edges
     *
     * @return a list of all the edges
     */
    public List<Edge> getAllEdges(){
        List<Edge> res=new ArrayList<Edge>();
        for (List<Edge> value : adjEdList.values()) {
            res.addAll(value);
        }
        return res;
    }

    /**
     * Returns a list of all the edges having a specific node as the source
     *
     * @param n The node
     * @return a list of all the in edges
     */
    public List<Edge> getInEdges(Node n){
        List<Edge> res=new LinkedList<>();
        List<Edge> allEdges=getAllEdges();
        Collections.sort(allEdges);
        for(Edge e:allEdges){
            if(e.to().equals(n)){
                res.add(e);
            }
        }
        return res;
    }

    /**
     * Returns a list of all the edges having a specific node as the source
     *
     * @param id The node's id
     * @return a list of all the in edges
     */
    public List<Edge> getInEdges(int id){
        return getInEdges(new Node(id));
    }

    /**
     * Returns a list of incident edges to a specific node
     *
     * @param n the node
     * @return a list of all the incident edges
     */
    public List<Edge> getIncidentEdges(Node n){
        List<Edge> res=getInEdges(n);
        res.addAll(getOutEdges(n));
        return new ArrayList<>(new HashSet<>(res));
    }

    /**
     * Returns a list of incident edges to a specific node
     *
     * @param id the node's id
     * @return a list of all the incident edges
     */
    public List<Edge> getIncidentEdges(int id){
        return getIncidentEdges(new Node(id));
    }

    /**
     * Returns a list of all the successors of a node
     *
     * @param n the node
     * @return a list of all the successors
     */
    public List<Node> getSuccessors(Node n){
        if(n==null || !existsNode(n)){
            return null;
        }
        List<Edge> edgesFromN=adjEdList.get(n);
        List<Node> res=new ArrayList<>();
        for (Edge toAdd : edgesFromN) {
            if (!res.contains(toAdd.to())) {
                res.add(toAdd.to());
            }
        }
        return res;
    }

    /**
     * Returns a list of all the successors of a node
     *
     * @param id the node's id
     * @return a list of all the successors
     */
    public List<Node> getSuccessors(int id){
        return getSuccessors(new Node(id));
    }

    /**
     * Returns all the nodes of the graph
     *
     * @return a list of all the nodes
     */
    public List<Node> getAllNodes(){
        return new ArrayList<>(adjEdList.keySet());
    }

    /**
     * Checks if two nodes are adjacent
     *
     * @param u a node
     * @param v another node
     * @return true if the nodes are adjacent
     */
    public boolean adjacent(Node u,Node v){
        return (u.equals(v) || existsEdge(u,v)||existsEdge(v,u));
    }

    /**
     * Checks if two nodes are adjacent
     *
     * @param idU the id of a node
     * @param idV the id of another node
     * @return true if the nodes are adjacent
     */
    public boolean adjacent(int idU,int idV){
        return adjacent(new Node(idU),new Node(idV));
    }

    /**
     * returns the largest id of the nodes of the graph
     *
     * @return the largest id of the nodes of the graph
     */
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


    /**
     * Returns the out degree of a given node
     *
     * @param n the node
     * @return the out degree
     */
    public int outDegree(Node n){
        if(!existsNode(n)){
            System.out.println("Graf does not contain this node");
            return -1;
        }
        return adjEdList.get(n).size();
    }

    /**
     * Returns the out degree of a given node
     *
     * @param id the node's id
     * @return the out degree of a given node
     */
    public int outDegree(int id){
        return outDegree(new Node(id));
    }

    /**
     * Returns the in degree of a given node
     *
     * @param n the node
     * @return the in degree
     */
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

    /**
     * returns the in degree
     *
     * @param id the node's id
     * @return the in degree
     */
    public int inDegree(int id){
        return inDegree(new Node(id));
    }

    /**
     * returns the degree of a node
     *
     * @param n the node
     * @return the degree of a node
     */
    public int degree(Node n){
        if(!existsNode(n)){
            System.out.println("Graf does not contain this node");
            return -1;
        }
        int res=inDegree(n)+outDegree(n);
        if(existsEdge(n,n)){
            res--;
        }
        return res;
    }

    /**
     * returns the degree of a given node
     *
     * @param id the node's id
     * @return the degree
     */
    public int degree(int id){
        return degree(new Node(id));
    }

    /**
     * returns a string representation of the graph
     *
     * @return a string representation of the graph
     */
    public String toDotString(){
        String res="digraph {\n";
        List<Node> allNodes=getAllNodes();
        Collections.sort(allNodes);
        for (Node n:allNodes) {
            res+=n.toString();
            List<Edge>weightedEdges=new ArrayList<>();
            boolean firstEdge=true;
            List<Edge>outEdges=getOutEdges(n);
            Collections.sort(outEdges);
            for (Edge e:outEdges) {
                if(e.hasWeight()){
                    weightedEdges.add(e);
                }else{
                    if(firstEdge){
                        firstEdge=false;
                        res+=" ->";
                    }else{
                        res+=",";
                    }
                    res+=" "+e.to().toString();
                }
            }
            res+=";\n";
            if(!weightedEdges.isEmpty()){
                for (Edge weightedEdge:weightedEdges) {
                    res+=n.toString()+" -> "+weightedEdge.to().toString()+"[len="+String.valueOf(weightedEdge.weight())+",label="+String.valueOf(weightedEdge.weight())+"];\n";
                }
            }
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

    /**
     * Sets the weight of an edge
     *
     * @param e the edge
     * @param w the weight
     */
    public void setEdgeWeight(Edge e,int w){
        Edge localEdge=getEdge(e);
        if(localEdge!=null){
            localEdge.setWeight(w);
        }
    }

    /**
     * Sets the weight of an edge
     *
     * @param f the edge's source node
     * @param t the edge's target node
     * @param w the weight
     */
    public void setEdgeWeight(Node f, Node t, int w){
        setEdgeWeight(new Edge(f,t),w);
    }

    /**
     * Sets the weight of an edge
     *
     * @param f the edge's source node's id
     * @param t the edge's target node's id
     * @param w the weight
     */
    public void setEdgeWeight(int f,int t, int w){
        setEdgeWeight(new Edge(new Node(f),new Node(t)),w);
    }

    /**
     * Returns an adjacency matrix of representation of the graph
     *
     * @return an adjacency matrix of the graph
     */
    public int[][] toAdjMatrix(){
        int nbNodes=nbNodes();
        int[][] res=new int[nbNodes][nbNodes];
        for(int i=0;i<nbNodes;++i){
            for(int j=0;j<nbNodes;++j){
                res[i][j]=0;
            }
        }
        List<Edge>edges=getAllEdges();
        for (Edge e : edges) {
            res[e.from().getId() - 1][e.to().getId() - 1]++;
        }
        return res;
    }

    /**
     * Returns a successor array representation of the graph
     *
     * @return a successor array representation of the graph
     */
    public int[] toSuccessorArray(){
        //need arrayList because we don't know the size of the result yet
        ArrayList<Integer> arrayList = new ArrayList<Integer>();
        List<Node>nodes=getAllNodes();
        Collections.sort(nodes);
        for (Node node : nodes) {
            List<Edge> edgesFromCurrentNode = getOutEdges(node);
            for (Edge edge : edgesFromCurrentNode) {
                arrayList.add(edge.to().getId());
            }
            arrayList.add(0);
        }
        int[] arr = new int[arrayList.size()];
        for(int i=0;i<arrayList.size();++i){
            arr[i]=arrayList.get(i);
        }
        return arr;
    }

    /**
     * Returns a reversal graph from the current instance
     *
     * @return a reversal graph
     */
    public Graf getReverse(){
        List<Node> nodes=getAllNodes();
        List<Edge> edges=getAllEdges();

        Graf g=new Graf();
        for(int i=0;i<nodes.size();++i){
            g.addNode(nodes.get(i));
        }

        for(int j=0;j<edges.size();++j){
            g.addEdge(edges.get(j).to(),edges.get(j).from());
        }

        return g;
    }

    /**
     * Returns a breadth-first search of the graph
     *
     * @return a breadth-first search of the graph
     */
    public List<Node> getBFS(){
        List<Node> allNodes=getAllNodes();
        Collections.sort(allNodes);
        Map<Node, Boolean> visitedNodes=new HashMap<>();

        for (Node n :allNodes) {
            visitedNodes.put(n,false);
        }
        Node firstNode=allNodes.get(0);

        LinkedList<Node> queue=new LinkedList<>();

        LinkedList<Node> result=new LinkedList<>();

        visitedNodes.replace(firstNode,true);

        while(!allNodes.isEmpty()){
            queue.add(allNodes.get(0));
            result.add(allNodes.get(0));
            while(!queue.isEmpty()){
                Node current=queue.removeFirst();
                visitedNodes.replace(current,true);
                allNodes.remove(current);
                queue.remove(current);

                List<Node> successors = getSuccessors(current);
                Collections.sort(successors);
                for (Node n : successors) {
                    if(!visitedNodes.get(n)){
                        visitedNodes.replace(n,true);
                        queue.add(n);
                        result.add(n);
                    }
                }
            }
        }

        return result;
    }

    /**
     * Returns a deep-first search of the graph
     *
     * @return a deep-first search of the graph
     */
    public List<Node> getDFS(){
        List<Node> allNodes=getAllNodes();
        Collections.sort(allNodes);

        Map<Node, Boolean> visitedNodes=new HashMap<>();

        for (Node n :allNodes) {
            visitedNodes.put(n,false);
        }
        LinkedList<Node> result=new LinkedList<>();

        for (Node n: allNodes) {
            if(!visitedNodes.get(n)){
                Pair<LinkedList<Node>,Map<Node,Boolean>> dfsVisitRes = dfsVisit(n,result,visitedNodes);
                result=dfsVisitRes.getFirst();
                visitedNodes=dfsVisitRes.getSecond();
            }
        }
        return result;
    }

    public Pair<LinkedList<Node>,Map<Node,Boolean>> dfsVisit(Node n, LinkedList<Node> result, Map<Node,Boolean> visitedNodes){
        visitedNodes.replace(n,true);
        result.add(n);
        for (Node adj:getSuccessors(n)) {
            if(!visitedNodes.get(adj)){
                Pair<LinkedList<Node>,Map<Node,Boolean>> dfsVisitRes=dfsVisit(adj,result,visitedNodes);
                result=dfsVisitRes.getFirst();
                visitedNodes=dfsVisitRes.getSecond();
            }
        }
        return new Pair<>(result, visitedNodes);
    }

    /**
     * computes the accessible nodes from a given node, using breadth-first search
     *
     * @param startingNode the node
     * @return a list of all the accessible nodes
     */
    public List<Node> reach(Node startingNode){
        if(!existsNode(startingNode)){
            return null;
        }

        List<Node> allNodes=getAllNodes();
        Collections.sort(allNodes);

        Map<Node, Boolean> visitedNodes=new HashMap<>();

        for (Node n :allNodes) {
            visitedNodes.put(n,false);
        }

        LinkedList<Node> queue=new LinkedList<>();
        queue.add(startingNode);

        LinkedList<Node> result=new LinkedList<>();
        result.add(startingNode);

        visitedNodes.replace(startingNode,true);

        while(!queue.isEmpty()){
            Node current=queue.removeFirst();
            List<Node> successors = getSuccessors(current);
            Collections.sort(successors);
            for (Node n : successors) {
                if(!visitedNodes.get(n)){
                    visitedNodes.replace(n,true);
                    queue.add(n);
                    result.add(n);
                }
            }
        }
        return result;
    }

    /**
     * computes the accessible nodes from a given node, using breadth-first search
     *
     * @param id the node's id
     * @return a list of all the accessible nodes
     */
    public List<Node> reach(int id){
        return reach(new Node(id));
    }

    /**
     * Computes the transitive closure of the graph
     *
     * @return the transitive closure of the graph
     */
    public Graf getTransitiveClosure(){
        Graf resGraf=new Graf();
        for (Node n:this.getAllNodes()) {
            List<Node> reachableNodes = reach(n);
            for(Node reachableNode : reachableNodes){
                if(!reachableNode.equals(n)) {
                    resGraf.addEdge(n,reachableNode);
                }
            }
        }
        return resGraf;
    }

    /**
     * Retrurns true if the graph contains at least one weighted edge
     *
     * @return true if the graph contains at least one weighted edge
     */
    public Boolean isWeighted(){
        for (Edge e : getAllEdges()){
            if(e.hasWeight()){
                return true;
            }
        }
        return false;
    }
}