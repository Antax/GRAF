# GRAF
# Baptiste Lé-Peria and Antoine Claudel (TPA)

#Implementation choices :
    Graph : 
        -If an edge is created whereas the node aren't yet, the nodes are created before the edge is.
    
    Undirected Graph :
        -If an edge wanted to be added to an Undirected Graph, its symmetrical is also added. It means that a directed 
        edge is added from the tail to the head of the endpoints, and in parallele, an other directed edge is added with the
        same endpoints but with reversed head and tail.
        
        -When printing the graph to the dot format, only an edge (undirected) is represented once. It means that the edge on
        dot file is represented by "--" and not by "<-" or "->".
        
        -The class undirected graph extends the Graf class.

    Pair :
        -A class Pair has been created to be used in DFS function (cf DFS Algorithm)
        
#MakeFile
    The makefile is located in the source of the the archive.
    
    While located on the source of the project (archive), the following commands are available :
        make : compiling the java classes
        run : launching the interactive menu (main method)
        clean : removing the .class files of the project
        
    A main folder, in the source of the archive, will contains all .java and .class files
    Indeed, when making project, the main folder/package has the role of source and target folder
           
#In the main :    
    An interactive menu permits to the user to create or modify a graph
        - When creating an empty graph, the user specify if it is undirected and/or weighted
        - In the case of importing graph by a .dot file, the user isn't asked to define the type,
        If the .dot file contains a "digraph" head, it is considered as a directed graph
        If the .dot file contains a weighted edge, it is considered as weighted
        -The adding of an edge follows the same rules as in the class, if no nodes are existing, each node is created before the
        edge is
        -The computing of the transitive closure of the graph modify the current instance of the graph in its transitive closure
        (such as the Reversal actionà
        -Traversal (both BFS and DFS) are printed in the consol as a suite of ids (ex : [4][5][7]..)
        -The two firsts option (creation or modification of a graph) are allowed to be called at any time. The current graph is
        also destroyed to be replaced by the new instance
        The other options (from 3 to 12) are not allowed until an instance of the graph is created
        -The incorrect use of the command has no effect on the current graph and the menu also repeat to give to the user the possibility
        to recall a valid action