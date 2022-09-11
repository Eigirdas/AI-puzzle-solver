import java.util.ArrayList;

// creating a class for node and declaring the elements
public class Node{
    Node parent;
    private int cost;
    GameState state;


    // method for node
    public Node(GameState state, Node parent, int cost){
        this.state = state;
        this.parent = parent;
        this.cost = cost;
    }

    // a returning method for cost
    public int getCost(){
        return cost;
    }

    // sets nodes cost to 0
    public Node(GameState state){
        this(state,null,0);
    }

    public static Node FindingNode(ArrayList<Node> List, GameState g){
        for (Node a : List){
            if (g.Board(a.state)) return a;
        }
        return null;
    }

}
