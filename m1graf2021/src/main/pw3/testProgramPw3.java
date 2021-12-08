package main.pw3;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

import main.m1graf2021.*;

public class testProgramPw3{
    /**
     * Check if the imported graph is an Undirected Graph, parsing the first line of the .dot file
     *
     * @param path the source of the dot file
     * @return a boolean that specify if the graph is Symmetric (Undirected) or not
     */
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

    /**
     * Function which scans the user input
     *
     * @return the choice of the user entering the path of the file he want to export
     */
    public static String enterDotPathWriteMenu() {
        String selection;
        Scanner input = new Scanner(System.in);

        /***************************************************/
        System.out.println("-------------------------\n");
        System.out.println("Enter the path of the dot file you want to export :");

        selection = input.next();
        return selection;
    }

    /**
     * Function which scans the user input
     *
     * @return the choice of the user entering the path of the file he want to read
     */
    public static String enterDotPathReadMenu() {
        String selection;
        Scanner input = new Scanner(System.in);

        /***************************************************/
        System.out.println("-------------------------\n");
        System.out.println("Enter the path of the dot file you want to read :");

        selection = input.next();
        return selection;
    }

    /**
     * Function which scans the user input
     *
     * @return the choice of the user entering the id of node he want to add
     */
    public static int choseStrategyMenu() {
        int selection;
        Scanner input = new Scanner(System.in);

        /***************************************************/
        System.out.println("-------------------------\n");
        System.out.println("Choose the strategy you want to use to compute Chinese-Postman Problem :");
        System.out.println("-------------------------\n");
        System.out.println("1 - Optimal strategy");
        System.out.println("2 - Random strategy");

        selection = input.nextInt();
        return selection;
    }

    /**
     * Function which scans the user input
     * The interactive menu reacts to 13 different inputs
     *
     * @return the choice of the user asked about the action he wanted to do
     */
    public static int menu() {

        int selection;
        Scanner input = new Scanner(System.in);

        /***************************************************/

        System.out.println("Choose from these choices");
        System.out.println("-------------------------\n");
        System.out.println("1 - Read a graph from a DOT file");
        System.out.println("2 - Quit interactive menu");

        selection = input.nextInt();
        return selection;
    }

    public static void main(String[] args) throws java.io.FileNotFoundException, java.io.IOException{

        UndirectedGraf g = new UndirectedGraf();
        int userChoice=0;
        String stringUserChoice;

        /*********************************************************/

        while (userChoice!=1 && userChoice!=2) {
            userChoice = menu();

            if (userChoice == 1) {
                stringUserChoice=enterDotPathReadMenu();

                System.out.println(stringUserChoice);
                g = new UndirectedGraf(stringUserChoice);

                if (g.getAllNodes().isEmpty()){
                    return;
                }

                ChinesePostman cp = new ChinesePostman(g);

                if (cp.isEulerian()){
                    System.out.println("This graph is eulerian.\n");

                    System.out.println("The eulerian path is :\n");
                    List eulerianpath = cp.getEulerianPath();
                    System.out.println(Arrays.toString(eulerianpath.toArray()));
                }
                if (cp.isSemiEulerian()){
                    System.out.println("This graph is semi-eulerian.\n");

                    System.out.println("The eulerian path is :\n");
                    List eulerianpath = cp.getEulerianPath();
                    System.out.println(Arrays.toString(eulerianpath.toArray()));
                }
                if (cp.isNonEulerian()){
                    System.out.println("This graph is non-eulerian.\n");
                    userChoice = choseStrategyMenu();

                    if (userChoice==2){

                        System.out.println("After adding extra edges, here is the eulerian path :\n");
                        Pair<UndirectedGraf,List<Edge>> res = (Pair<UndirectedGraf, List<Edge>>) cp.getChinesePostmanSolution(ChinesePostman.Strategy.INORDER).getSecond();
                        List list= cp.getChinesePostmanSolution(ChinesePostman.Strategy.INORDER).getSecond();
                        System.out.println(Arrays.toString(list.toArray()));

                        String stringUserChoice2=enterDotPathWriteMenu();
                        res.getFirst().toDotFile(stringUserChoice2);
                    }
                }
            }

            if (userChoice == 2) {
                return;
            }
        }
    }


}
