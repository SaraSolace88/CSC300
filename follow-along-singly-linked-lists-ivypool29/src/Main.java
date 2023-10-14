import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

public class Main {
    public static void main(String[] args) {
        GroceryList costcoList = new GroceryList();
        GroceryList wegmansList = new GroceryList();

        while(!StdIn.isEmpty()){
            String list = StdIn.readString();
            String cmd = StdIn.readString();
            if(list.equals("costco")){
                switch(cmd){
                    case "add":
                        costcoList.add(StdIn.readString());
                        break;
                    case "bought":
                        costcoList.bought(StdIn.readString());
                        break;
                    case "print":
                        costcoList.print();
                        break;
                    default:
                        StdOut.println("unknown command " + cmd);
                }
            }else if(list.equals("wegmans")){
                switch(cmd){
                    case "add":
                        wegmansList.add(StdIn.readString());
                        break;
                    case "bought":
                        wegmansList.bought(StdIn.readString());
                        break;
                    case "print":
                        wegmansList.print();
                        break;
                    default:
                        StdOut.println("unknown command " + cmd);
                }
            }
        }
    }
}
