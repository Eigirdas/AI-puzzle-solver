import java.io.File;
import java.io.PrintWriter;
import java.util.ArrayList;

public class Solver{

    // unexpanded nodes
    ArrayList<Node> unexpandedRoot = new ArrayList<>();
    // expanded nodes
    ArrayList<Node> expandedRoot = new ArrayList<>();
    // initial root
    Node Root;

    public Solver(char[] initialBoard){
        GameState initialState = new GameState(initialBoard);
        Root = new Node(initialState);
    }

    // the main calculations for Uniform cost search and finding solution
    public void solve(PrintWriter output){
        // initializing an empty tree with the root
        unexpandedRoot.add(Root);
        // while there is roots to expand
        while (unexpandedRoot.size() > 0){
            // grabbing the most cost element
            Node n = getMinimumCost();
            // adding the element to expanded root list
            expandedRoot.add(n);
            // removing it from the unexpanded root list
            unexpandedRoot.remove(0);
            // if we find the final state, print out the answer
            if (n.state.finish()){
                print(n, output); // calling an output method
                return;
            }
            else
            {
                // getting the list of available states
                ArrayList<GameState> moveList = n.state.possibleMoves();
                for (GameState gs:moveList){
                    // if the unexpanded root and expanded root is equal to null(empty):
                    if ((Node.FindingNode(unexpandedRoot, gs) == null) && (Node.FindingNode(expandedRoot, gs) == null)){
                        int newCost = n.getCost() +1; // adding successors cost
                        Node newNode = new Node(gs, n, newCost);
                        unexpandedRoot.add(newNode);
                    }
                }
            }
        }
    }

    // printing all the states and displaying them later on
    public void printstates(Node n, PrintWriter output){
        if (n.parent != null) printstates(n.parent, output);
        output.println(n.state);
    }

    public void print(Node n, PrintWriter output){
        printstates(n, output); // prints the game states each step it makes
        output.println(n.getCost() + " Moves");
        output.println(this.expandedRoot.size() + " Nodes expanded");
        output.println();
    }

    // getting the minimum cost for uniform cost search
    public Node getMinimumCost(){
        Node n = unexpandedRoot.get(0);
        for (Node minNode : unexpandedRoot){
            if(minNode.getCost() < n.getCost()){
                n = minNode;
            }
        }
        return n;
    }

    public static void main(String[] args) throws Exception{
        Solver tree = new Solver(GameState.Start_Board);
        File print = new File("output.txt");
        PrintWriter output = new PrintWriter(print);
        tree.solve(output);
        output.close();
    }
}


