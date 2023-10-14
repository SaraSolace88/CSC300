import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

public class Main {
    public static void main(String[] args) {
        GroceryList gl = new GroceryList();
        while(!StdIn.isEmpty()){
            String cmd = StdIn.readString();
            switch(cmd){
                case "add":
                    gl.add(StdIn.readString());
                    break;
                case "bought":
                    gl.bought(StdIn.readString());
                    break;
                case "print":
                    gl.print();
                    break;
                default:
                    StdOut.println("Unknown command: " + cmd);
            }
        }
    }
}
