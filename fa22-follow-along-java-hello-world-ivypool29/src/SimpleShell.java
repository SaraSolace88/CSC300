import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

public class SimpleShell {

    public static void cat(){
        String fileName = StdIn.readString();
        try {
            In fileData = new In(fileName);
            while(!fileData.isEmpty()){
                StdOut.print(fileData.readAll());
            }
            fileData.close();
        }
        catch(IllegalArgumentException e){
            StdOut.println(e.toString());
            StdOut.println("failed to open file, try again");
        }
    }

    public static void echo(){
        String input = StdIn.readLine();
        StdOut.println(input);
    }

    public static void add(){
        int n1 = StdIn.readInt();
        int n2 = StdIn.readInt();
        StdOut.printf("%d + %d = %d\n", n1, n2, n1 + n2);
    }

    public static void main(String[] args) {
        boolean shellShouldRun = true;
        StdOut.print("$: ");
        while(shellShouldRun && !StdIn.isEmpty()){
            String cmd = StdIn.readString();
            switch(cmd){
                case "cat":
                    cat();
                    break;
                case "echo":
                    echo();
                    break;
                case "add":
                    add();
                    break;
                case "exit":
                    shellShouldRun = false;
                    break;
                default:
                    StdOut.printf("error: unknown command %s\n", cmd);
            }
            StdOut.print("$: ");
        }
    }
}
