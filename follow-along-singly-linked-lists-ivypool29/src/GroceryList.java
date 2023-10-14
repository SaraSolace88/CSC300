import edu.princeton.cs.algs4.StdOut;

public class GroceryList {
    private static class Item{
        String name;
        boolean purchased;
        public Item(String name){
            this.name = name;
            this.purchased = false;
        }
    }

    GenericList<Item> list;

    public GroceryList(){
        list = new GenericList<>();
    }

    public void add(String name){
        Item n = new Item(name);
        list.addToBack(n);
    }

    public void bought(String name){
        for(Item i : list){
            if(i.name.equals(name)){
                i.purchased = true;
                break;
            }
        }
    }

    public void print(){
        StdOut.print("List\n------\n");
        for(Item i : list){
            StdOut.print("[");
            if(i.purchased){
                StdOut.print("x");
            }else{
                StdOut.print(" ");
            }
            StdOut.printf("] %s\n", i.name);
        }
    }
}
