package main;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

import main.m1graf2021.*;

public class testProgram{

    public static boolean isSym(String path) throws IOException {
        BufferedReader reader;
        try {
            reader = new BufferedReader(new FileReader(path));
            String line = reader.readLine();
            while (line != null) {
                line = line.trim();
                if (line.contains("digraph")) {
                    return false;
                }
                if (line.contains("graph")) {
                    return true;
                }
                line = reader.readLine();
            }
            reader.close();
        }catch(IOException e){
            e.printStackTrace();
        }
        return false;
    }

    public static int addWeightMenu() {
        int selection;
        Scanner input = new Scanner(System.in);

        /***************************************************/
        System.out.println("-------------------------\n");
        System.out.println("Enter the weight of the edge you've added :");

        selection = input.nextInt();
        return selection;
    }

    public static String enterDotPathWriteMenu() {
        String selection;
        Scanner input = new Scanner(System.in);

        /***************************************************/
        System.out.println("-------------------------\n");
        System.out.println("Enter the path of the dot file you want to export :");

        selection = input.next();
        return selection;
    }

    public static String enterDotPathReadMenu() {
        String selection;
        Scanner input = new Scanner(System.in);

        /***************************************************/
        System.out.println("-------------------------\n");
        System.out.println("Enter the path of the dot file you want to read :");

        selection = input.next();
        return selection;
    }

    public static int removeEdgeFromMenu() {
        int selection;
        Scanner input = new Scanner(System.in);

        /***************************************************/
        System.out.println("-------------------------\n");
        System.out.println("Enter the tail of the edge you want to remove :");

        selection = input.nextInt();
        return selection;
    }

    public static int removeEdgeToMenu() {
        int selection;
        Scanner input = new Scanner(System.in);

        /***************************************************/
        System.out.println("-------------------------\n");
        System.out.println("Enter the head of the edge you want to remove :");

        selection = input.nextInt();
        return selection;
    }

    public static int addEdgeFromMenu() {
        int selection;
        Scanner input = new Scanner(System.in);

        /***************************************************/
        System.out.println("-------------------------\n");
        System.out.println("Enter the tail of the edge you want to add :");

        selection = input.nextInt();
        return selection;
    }

    public static int addEdgeToMenu() {
        int selection;
        Scanner input = new Scanner(System.in);

        /***************************************************/
        System.out.println("-------------------------\n");
        System.out.println("Enter the head of the edge you want to add :");

        selection = input.nextInt();
        return selection;
    }

    public static int removeNodeMenu() {
        int selection;
        Scanner input = new Scanner(System.in);

        /***************************************************/
        System.out.println("-------------------------\n");
        System.out.println("Enter the id of the node you want to remove :");

        selection = input.nextInt();
        return selection;
    }

    public static int addNodeMenu() {
        int selection;
        Scanner input = new Scanner(System.in);

        /***************************************************/
        System.out.println("-------------------------\n");
        System.out.println("Enter the id of the node you want to add :");

        selection = input.nextInt();
        return selection;
    }

    public static String createEmptyGraphSymmetricMenu() {

        String selection;
        Scanner input = new Scanner(System.in);

        /***************************************************/
        System.out.println("-------------------------\n");
        System.out.println("Symmetric graph ?");
        System.out.println("Answer by yes or no (y/n) :");

        selection = input.next();
        return selection;
    }

    public static String createEmptyGraphWeightedMenu() {

        String selection;
        Scanner input = new Scanner(System.in);

        /***************************************************/
        System.out.println("-------------------------\n");
        System.out.println("Weighted graph ?");
        System.out.println("Answer by yes or no (y/n) :");

        selection = input.next();
        return selection;
    }

    public static int menu() {

        int selection;
        Scanner input = new Scanner(System.in);

        /***************************************************/

        System.out.println("Choose from these choices");
        System.out.println("-------------------------\n");
        System.out.println("1 - Create empty graph");
        System.out.println("2 - Read a graph from a DOT file");
        System.out.println("3 - Add a node");
        System.out.println("4 - Add an edge");
        System.out.println("5 - Remove a node");
        System.out.println("6 - Remove an edge");
        System.out.println("7 - Print on the console the graph in the DOT format");
        System.out.println("8 - Export the graph to a DOT file");
        System.out.println("9 - Reverse the graph");
        System.out.println("10 - Compute the transitive closure of the graph");
        System.out.println("11 - Traverse the graph in DSF");
        System.out.println("12 - Traverse the graph in BSF");
        System.out.println("13 - Quit interactive menu");

        selection = input.nextInt();
        return selection;
    }

    public static void main(String[] args) throws java.io.FileNotFoundException, java.io.IOException{

        Graf g = new Graf();
        boolean weighted = false;
        int userChoice=0;
        String stringUserChoice;

        /*********************************************************/

        while (userChoice!=1 && userChoice!=2) {
            userChoice = menu();

            if (userChoice == 1) {
                g = new Graf();
                do {
                    stringUserChoice = createEmptyGraphSymmetricMenu();
                    if (stringUserChoice.equals("y")) {
                        g = new UndirectedGraf();
                    }
                }while (!stringUserChoice.equals("y") && !stringUserChoice.equals("n"));
                do {
                    stringUserChoice = createEmptyGraphWeightedMenu();
                    if (stringUserChoice.equals("y")) {
                        weighted = true;
                    }
                }while (!stringUserChoice.equals("y") && !stringUserChoice.equals("n"));
            }

            if (userChoice == 2) {
                stringUserChoice=enterDotPathReadMenu();
                if (isSym(stringUserChoice)){
                    g = new UndirectedGraf(stringUserChoice);
                }else {
                    g = new Graf(stringUserChoice);
                }
                if (g.isWeighted()){
                    weighted=true;
                }
            }

            if (userChoice == 3 || userChoice == 4 || userChoice == 5 || userChoice == 6 || userChoice == 7 || userChoice == 8 || userChoice == 9 || userChoice == 10 || userChoice == 11 || userChoice == 12) {
                System.out.println("A graph must have been created. Please create an empty graph or import a graph before making actions.");
            }
            if (userChoice == 13) {
                return;
            }
        }
        while (true) {
            userChoice = menu();
            if (userChoice == 1) {
                g = new Graf();
                stringUserChoice = "z";
                do{
                    stringUserChoice = createEmptyGraphSymmetricMenu();
                    if (stringUserChoice.equals("y")) {
                        g = new UndirectedGraf();
                    }
                }while (!stringUserChoice.equals("y") && !stringUserChoice.equals("n"));
                do {
                    stringUserChoice = createEmptyGraphWeightedMenu();
                    if (stringUserChoice.equals("y")) {
                        weighted = true;
                    }
                }while (!stringUserChoice.equals("y") && !stringUserChoice.equals("n"));
            }
            if (userChoice == 2) {
                stringUserChoice=enterDotPathReadMenu();
                if (isSym(stringUserChoice)){
                    g = new UndirectedGraf(stringUserChoice);
                }else {
                    g = new Graf(stringUserChoice);
                }
                if (g.isWeighted()){
                    weighted=true;
                }
            }
            if (userChoice == 3) {
                userChoice = addNodeMenu();
                g.addNode(userChoice);
            }
            if (userChoice == 4) {
                int userChoiceFrom=addEdgeFromMenu();
                int userChoiceTo=addEdgeToMenu();
                g.addEdge(userChoiceFrom,userChoiceTo);
                if (weighted){
                    int userChoiceWeight=addWeightMenu();
                    g.setEdgeWeight(userChoiceFrom,userChoiceTo,userChoiceWeight);
                }
            }
            if (userChoice == 5) {
                userChoice=removeNodeMenu();
                g.removeNode(userChoice);
            }
            if (userChoice == 6) {
                int userChoiceFrom=removeEdgeFromMenu();
                int userChoiceTo=removeEdgeToMenu();
                g.removeEdge(userChoiceFrom,userChoiceTo);
            }
            if (userChoice == 7) {
                System.out.println(g.toDotString());
            }
            if (userChoice == 8) {
                stringUserChoice=enterDotPathWriteMenu();
                g.toDotFile(stringUserChoice);
            }
            if (userChoice == 9) {
                g=g.getReverse();
            }
            if (userChoice == 10) {
                g=g.getTransitiveClosure();
            }
            if (userChoice == 11) {
                if (g.getBFS().isEmpty()){
                    continue;
                }
                System.out.println(Arrays.toString(g.getBFS().toArray()));
            }
            if (userChoice == 12) {
                if (g.getDFS().isEmpty()){
                    continue;
                }
                System.out.println(Arrays.toString(g.getDFS().toArray()));
            }
            if (userChoice == 13) {
                return;
            }
        }
    }


}
