import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

/* Basics of Java program:
    StdIn/StdOut
    file io
    classes
    exceptions
    recursion */


public class HelloWorld { /*object-orientated language idea: class - way to group data and the functions that operate on that data. */
                         /* v way to define arrays*/
    public static void main(String[] args){ //single function called main
        // StdOut.println("Hello World"); Most simplistic way to accomplish Hello World.



        //StdOut.println(args.length); Will print out the number of arguments provided through command line as integer.



        /* if(args.length == 1){
            StdOut.println(args[0]);
        }

        Will only print if there is only one argument provided.

         */



        /* if(args.length > 0){
            for(int i = 0; i < args.length; i++){
                StdOut.print(args[i] + " ");
            }
            StdOut.println();
        }

        Loops through all arguments provided on the command line, printing them, then follows with new line.

         */



        /*
        int input = StdIn.readInt();
        input = input * 2;
        StdOut.println(input);

        Prints out data type taken from standard input.

         */



        /*
        int[] myNumbers = new int[10]; //array type[] array name = array initialization[size of array]
        int[] myNumbers2 = myNumbers; //points at the same memory, change one, you change the other.
        int i = 0;
        while(!StdIn.isEmpty()){
            int input = StdIn.readInt();
            StdOut.println(input);
            if(i < 10){
                myNumbers[i++] = input;
            }else{
                break;
            }
        }
        for(i = 0; i < 10; i++){
            StdOut.println(myNumbers2[i]);
        }

         */

        /*
        In fileInput = new In(args[0]);
        while(!fileInput.isEmpty()) {
            String data = fileInput.readString();
            StdOut.println(data);
        }
         */

        StdOut.println("Hello World");

    }
}
